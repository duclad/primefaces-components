package ro.duclad.primefaces.components.workflow;

import org.primefaces.component.menu.AbstractMenu;
import org.primefaces.component.menu.BaseMenuRenderer;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.util.ComponentTraversalUtils;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@FacesRenderer(componentFamily = "ro.duclad.primefaces.components", rendererType = "ro.duclad.primefaces.components.WorkflowRenderer")
public class WorkflowRenderer extends BaseMenuRenderer {
    public WorkflowRenderer() {
    }

    private void updateWorkflow(Workflow workflow) {
        workflow.getExecutedSteps().getElements().stream().map(step -> (WorkflowItem) step).forEach(step -> {
            Optional<WorkflowItem> workflowStep = workflow.getModel().getElements().stream().map(item -> (WorkflowItem) item).filter(item -> item.getValue().equals(step.getValue())).findFirst();
            if (workflowStep.isPresent()) {
                WorkflowItem item = workflowStep.get();
                item.setStepDuration(step.getStepDuration());
                item.setStepProblematic(step.isStepProblematic());
                if (step.getStepState() == WorkflowItem.WorkflowItemState.FAILED) {
                    item.setStepState(WorkflowItem.WorkflowItemState.NOT_EXECUTED);
                    workflow.getModel().getElements().stream().map(workflowItem -> (WorkflowItem) workflowItem).reduce((a, b) -> b).ifPresent(workflowItem -> {
                        workflowItem.setValue(step.getStepOutcome() == null ? "Failed in step: " + step.getValue() : step.getStepOutcome());
                        workflowItem.setStepState(WorkflowItem.WorkflowItemState.FAILED);
                    });
                } else {
                    item.setStepState(step.getStepState());
                }
            }
        });
    }

    protected void encodeMarkup(FacesContext context, AbstractMenu abstractMenu) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        Workflow workflow = (Workflow) abstractMenu;
        String clientId = workflow.getClientId(context);
        int activeIndex = workflow.getActiveIndex();
        updateWorkflow(workflow);
        List<MenuElement> elements = workflow.getElements();
        writer.startElement("div", workflow);
        writer.writeAttribute("id", clientId, null);
        String styleClass = " ";
        if (workflow.getStyleClass() != null) {
            styleClass = styleClass + workflow.getStyleClass();
        }
        if (workflow.getStyle() != null) {
            writer.writeAttribute("style", workflow.getStyle(), "style");
        }

        writer.startElement("ul", null);
        writer.writeAttribute("role", "tablist", null);
        writer.writeAttribute("class", "ui-workflow" + styleClass, null);
        int i = 0;
        if (elements != null && !elements.isEmpty()) {

            for (MenuElement element : elements) {
                if (element.isRendered() && element instanceof WorkflowItem) {
                    this.encodeItem(context, workflow, (WorkflowItem) element, activeIndex, i);
                    ++i;
                }
            }
        }

        writer.endElement("ul");
        writer.endElement("div");
        writer.startElement("script", null);
        writer.writeText("adjustWorkflow()", null);
        writer.endElement("script");
    }


    private void encodeItem(FacesContext context, Workflow workflow, WorkflowItem item, int activeIndex, int index) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        String itemClass = "ui-workflow-step ";
        if (item.getStepState() == WorkflowItem.WorkflowItemState.NOT_EXECUTED) {
            itemClass += Workflow.STEP_NOTEXECUTED_CLASS;
        } else if (item.isStepProblematic()) {
            itemClass += Workflow.STEP_PROBLEMATIC_CLASS;
        } else if (item.getStepState() == WorkflowItem.WorkflowItemState.EXECUTED) {
            itemClass += Workflow.STEP_EXECUTED_CLASS;
        } else if (item.getStepState() == WorkflowItem.WorkflowItemState.FAILED) {
            itemClass += Workflow.STEP_FAILED_CLASS;
        } else if (item.getStepState() == WorkflowItem.WorkflowItemState.RUNNING) {
            itemClass += Workflow.STEP_RUNNING_CLASS;
        }

        if (workflow.isReadonly()) {
            itemClass += Workflow.WORKFLOW_DISABLED_CLASS;
        }

        String containerStyle = item.getContainerStyle();
        String containerStyleClass = item.getContainerStyleClass();
        if (containerStyleClass != null) {
            itemClass = itemClass + " " + containerStyleClass;
        }

        writer.startElement("li", null);
        writer.writeAttribute("class", itemClass, null);
        writer.writeAttribute("role", "tab", null);
        if (containerStyle != null) {
            writer.writeAttribute("style", containerStyle, null);
        }

        this.encodeMenuItem(context, workflow, item, activeIndex, index);
        writer.endElement("li");
    }


    public void encodeChildren(FacesContext context, UIComponent component) {
    }

    protected void encodeScript(FacesContext context, AbstractMenu abstractMenu) {
    }


    private void encodeMenuItem(FacesContext context, Workflow workflow, WorkflowItem menuitem, int activeIndex, int index) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        String title = menuitem.getTitle();
        String style = menuitem.getStyle();
        String styleClass = this.getLinkStyleClass(menuitem);
        writer.startElement("a", null);
        writer.writeAttribute("tabindex", "-1", null);
        if (this.shouldRenderId(menuitem)) {
            writer.writeAttribute("id", menuitem.getClientId(), null);
        }

        if (title != null) {
            writer.writeAttribute("title", title, null);
        }

        if (style != null) {
            writer.writeAttribute("style", style, null);
        }

        if (!workflow.isReadonly() && !menuitem.isDisabled() && menuitem.getStepState() != WorkflowItem.WorkflowItemState.NOT_EXECUTED) {
            String onclick = menuitem.getOnclick();
            if (menuitem.getUrl() == null && menuitem.getOutcome() == null) {
                writer.writeAttribute("href", "#", null);
                UIComponent form = ComponentTraversalUtils.closestForm(context, workflow);
                if (form == null) {
                    throw new FacesException("MenuItem must be inside a form element");
                }

                String command;
                if (menuitem.isDynamic()) {
                    String menuClientId = workflow.getClientId(context);
                    Map<String, List<String>> params = menuitem.getParams();
                    if (params == null) {
                        params = new LinkedHashMap<>();
                    }

                    List<String> idParams = new ArrayList<>();
                    idParams.add(menuitem.getId());
                    params.put(menuClientId + "_menuid", idParams);
                    command = menuitem.isAjax() ? this.buildAjaxRequest(context, workflow, menuitem, form, params) : this.buildNonAjaxRequest(context, workflow, form, menuClientId, params, true);
                } else {
                    command = menuitem.isAjax() ? this.buildAjaxRequest(context, menuitem, form) : this.buildNonAjaxRequest(context, menuitem, form, menuitem.getClientId(context), true);
                }

                onclick = onclick == null ? command : onclick + ";" + command;
            } else {
                String targetURL = this.getTargetURL(context, menuitem);
                writer.writeAttribute("href", targetURL, null);
                if (menuitem.getTarget() != null) {
                    writer.writeAttribute("target", menuitem.getTarget(), null);
                }
            }

            if (onclick != null) {
                writer.writeAttribute("onclick", onclick, null);
            }
        } else {
            writer.writeAttribute("href", "#", null);
            writer.writeAttribute("onclick", "return false;", null);
        }
        Object value = menuitem.getValue();
        writer.startElement("span", workflow);
        writer.writeAttribute("class", Workflow.STEP_TITLE_CLASS, null);
        if (workflow.isShowIndex()) {
            writer.writeText(index + 1 + ". ", null);
        }
        writer.writeText(value + " ", null);
        if (menuitem.isShowStepDuration() && menuitem.getStepState()!= WorkflowItem.WorkflowItemState.FAILED) {
            writer.writeText(menuitem.getStepDuration() == null ? menuitem.getStepLimitDuration() : menuitem.getStepDuration(), null);
            if (menuitem.getStepDuration() != null && menuitem.getStepDuration() < menuitem.getStepLimitDuration()) {
                writer.startElement("font", workflow);
                writer.writeAttribute("class", Workflow.STEP_EARLY_CLASS, null);
                writer.writeText("(-" + (menuitem.getStepLimitDuration() - menuitem.getStepDuration()) + ")", null);
                writer.endElement("font");
            } else if (menuitem.getStepDuration() != null && menuitem.getStepDuration() > menuitem.getStepLimitDuration()) {
                writer.startElement("font", workflow);
                writer.writeAttribute("class", Workflow.STEP_LATE_CLASS, null);
                writer.writeText("(+" + (menuitem.getStepDuration() - menuitem.getStepLimitDuration()) + ")", null);
                writer.endElement("font");
            }
        }
        writer.endElement("span");
        writer.endElement("a");
    }
}
