package ro.duclad.primefaces.components.workflow;

import org.primefaces.component.menu.AbstractMenu;
import org.primefaces.component.menu.BaseMenuRenderer;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.util.ComponentTraversalUtils;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@FacesRenderer(componentFamily = "ro.duclad.primefaces.components", rendererType = "ro.duclad.primefaces.components.WorkflowRenderer")
public class WorkflowRenderer extends BaseMenuRenderer {
    public WorkflowRenderer() {
    }

    protected void encodeMarkup(FacesContext context, AbstractMenu abstractMenu) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        Workflow workflow = (Workflow) abstractMenu;
        String clientId = workflow.getClientId(context);
        String styleClass = workflow.getStyleClass();
        String containerClass = workflow.isReadonly() ? "ui-workflow ui-workflow-readonly ui-widget ui-helper-clearfix" : "ui-workflow ui-widget ui-helper-clearfix";
        styleClass = styleClass == null ? containerClass : containerClass + " " + styleClass;
        int activeIndex = workflow.getActiveIndex();
        List<MenuElement> elements = workflow.getElements();
        writer.startElement("div", workflow);
        writer.writeAttribute("id", clientId, null);
        writer.writeAttribute("class", styleClass, "styleClass");
        if (workflow.getStyle() != null) {
            writer.writeAttribute("style", workflow.getStyle(), "style");
        }

        writer.startElement("ul", null);
        writer.writeAttribute("role", "tablist", null);
        int i = 0;
        if (elements != null && !elements.isEmpty()) {
            Iterator var11 = elements.iterator();

            while (var11.hasNext()) {
                MenuElement element = (MenuElement) var11.next();
                if (element.isRendered() && element instanceof WorkflowItem) {
                    this.encodeItem(context, workflow, (WorkflowItem) element, activeIndex, i);
                    ++i;
                }
            }
        }

        writer.endElement("ul");
        writer.endElement("div");
    }

    protected void encodeItem(FacesContext context, Workflow workflow, WorkflowItem item, int activeIndex, int index) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        String itemClass;
        if (workflow.isReadonly()) {
            itemClass = workflow.getCurrentStep().equals(item.getValue()) ? "ui-workflow-item ui-state-highlight ui-corner-all" : "ui-workflow-item ui-state-default ui-state-disabled ui-corner-all";
        } else if (workflow.getCurrentStep().equals(item.getValue())) {
            itemClass = "ui-workflow-item ui-state-highlight ui-corner-all";
        } else if (item.isExecuted()) {
            itemClass = "ui-workflow-item ui-state-default ui-corner-all";
        } else {
            itemClass = "ui-workflow-item ui-state-default ui-state-disabled ui-corner-all";
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


    protected void encodeMenuItem(FacesContext context, Workflow workflow, WorkflowItem menuitem, int activeIndex, int index) throws IOException {
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

        if (!workflow.isReadonly() && !menuitem.isDisabled() && menuitem.isExecuted()) {
            String onclick = menuitem.getOnclick();
            if (menuitem.isFailed()) {
                writer.writeAttribute("class", styleClass + " " + Workflow.STEP_FAILED_CLASS, null);
            } else if (menuitem.isProblematic()) {
                writer.writeAttribute("class", styleClass + " " + Workflow.STEP_PROBLEMATIC_CLASS, null);
            } else {
                writer.writeAttribute("class", styleClass + " " + Workflow.STEP_OK_CLASS, null);
            }
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
                        params = new LinkedHashMap();
                    }

                    List<String> idParams = new ArrayList();
                    idParams.add(menuitem.getId());
                    ((Map) params).put(menuClientId + "_menuid", idParams);
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
            writer.writeAttribute("class", styleClass, null);
            writer.writeAttribute("href", "#", null);
            writer.writeAttribute("onclick", "return false;", null);
        }

        writer.startElement("span", workflow);
        writer.writeAttribute("class", Workflow.STEP_NUMBER_CLASS, null);
        writer.writeText(index + 1, null);
        writer.endElement("span");
        Object value = menuitem.getValue();
        writer.startElement("span", workflow);
        writer.writeAttribute("class", Workflow.STEP_TITLE_CLASS, null);
        writer.writeText(value, null);
        writer.endElement("span");
        if (menuitem.isShowDuration()) {
            writer.startElement("span", workflow);
            writer.writeAttribute("style", Workflow.INLINE_STYLE, null);
            writer.writeAttribute("class", Workflow.STEP_TITLE_CLASS, null);
            writer.writeText(menuitem.getDuration() == null ? menuitem.getLimitDuration() : menuitem.getDuration(), null);
            writer.endElement("span");
            if (menuitem.getDuration() != null && menuitem.getDuration() < menuitem.getLimitDuration()) {
                writer.startElement("span", workflow);
                writer.writeAttribute("style", Workflow.INLINE_STYLE, null);
                writer.writeAttribute("class", Workflow.STEP_EARLY_CLASS, null);
                writer.writeText("(-" + (menuitem.getLimitDuration() - menuitem.getDuration()) + ")", null);
                writer.endElement("span");
            } else if (menuitem.getDuration() != null && menuitem.getDuration() > menuitem.getLimitDuration()) {
                writer.startElement("span", workflow);
                writer.writeAttribute("style", Workflow.INLINE_STYLE, null);
                writer.writeAttribute("class", Workflow.STEP_LATE_CLASS, null);
                writer.writeText("(+" + (menuitem.getDuration() - menuitem.getLimitDuration()) + ")", null);
                writer.endElement("span");
            }
        }

        writer.endElement("a");
    }
}
