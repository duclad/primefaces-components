package ro.duclad.primefaces.components.workflow;

import org.primefaces.component.api.Widget;
import org.primefaces.component.menu.AbstractMenu;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;
import org.primefaces.util.ComponentUtils;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.behavior.ClientBehavior;
import javax.faces.component.behavior.ClientBehaviorBase;
import javax.faces.component.behavior.ClientBehaviorContext;

@ResourceDependencies({@ResourceDependency(
        library = "workflow",
        name = "workflow.css"
),@ResourceDependency(
        library = "workflow",
        name = "workflow.js"
)})
@FacesComponent("ro.duclad.primefaces.components.Workflow")
public class Workflow extends AbstractMenu implements Widget {
    public static final String COMPONENT_TYPE = "ro.duclad.primefaces.components.Workflow";
    public static final String COMPONENT_FAMILY = "ro.duclad.primefaces.components";
    public static final String DEFAULT_RENDERER = "ro.duclad.primefaces.components.WorkflowRenderer";
    public static final String STEP_TITLE_CLASS = "ui-workflow-step-title";
    public static final String STEP_EARLY_CLASS = "ui-workflow-step-early";
    public static final String STEP_LATE_CLASS = "ui-workflow-step-late";
    public static final String STEP_PROBLEMATIC_CLASS = "ui-workflow-step-problematic";
    public static final String STEP_RUNNING_CLASS = "ui-workflow-step-running";
    public static final String STEP_EXECUTED_CLASS = "ui-workflow-step-executed";
    public static final String STEP_NOTEXECUTED_CLASS = "ui-workflow-step-notexecuted";
    public static final String STEP_FAILED_CLASS = "ui-workflow-step-failed";
    public static final String WORKFLOW_DISABLED_CLASS = "ui-workflow-disabled";

    public Workflow() {
        this.setRendererType(DEFAULT_RENDERER);
    }

    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }

    public String getWidgetVar() {
        return (String) this.getStateHelper().eval(Workflow.PropertyKeys.widgetVar, null);
    }

    public void setWidgetVar(String _widgetVar) {
        this.getStateHelper().put(Workflow.PropertyKeys.widgetVar, _widgetVar);
    }

    public MenuModel getModel() {
        return (MenuModel) this.getStateHelper().eval(Workflow.PropertyKeys.model, null);
    }

    public void setModel(MenuModel _model) {
        this.getStateHelper().put(Workflow.PropertyKeys.model, _model);
    }

    public String getStyle() {
        return (String) this.getStateHelper().eval(Workflow.PropertyKeys.style, null);
    }

    public void setStyle(String _style) {
        this.getStateHelper().put(Workflow.PropertyKeys.style, _style);
    }

    public String getStyleClass() {
        return (String) this.getStateHelper().eval(Workflow.PropertyKeys.styleClass, null);
    }

    public void setStyleClass(String _styleClass) {
        this.getStateHelper().put(Workflow.PropertyKeys.styleClass, _styleClass);
    }

    public int getActiveIndex() {
        return (Integer) this.getStateHelper().eval(Workflow.PropertyKeys.activeIndex, 0);
    }

    public void setActiveIndex(int _activeIndex) {
        this.getStateHelper().put(Workflow.PropertyKeys.activeIndex, _activeIndex);
    }

    public boolean isReadonly() {
        return (Boolean) this.getStateHelper().eval(Workflow.PropertyKeys.readonly, true);
    }

    public void setReadonly(boolean _readonly) {
        this.getStateHelper().put(Workflow.PropertyKeys.readonly, _readonly);
    }

    public MenuModel getExecutedSteps() {
        return (MenuModel) this.getStateHelper().eval(PropertyKeys.executedSteps, new DefaultMenuModel());
    }

    public void setExecutedSteps(MenuModel executedSteps) {
        this.getStateHelper().put(PropertyKeys.executedSteps, executedSteps);
    }

    public String resolveWidgetVar() {
        return ComponentUtils.resolveWidgetVar(this.getFacesContext(), this);
    }

    public Boolean isShowIndex() {
        return (Boolean) this.getStateHelper().eval(Workflow.PropertyKeys.showIndex, false);
    }

    public void setShowIndex(boolean showIndex) {
        this.getStateHelper().put(Workflow.PropertyKeys.showIndex, showIndex);
    }

    public enum PropertyKeys {
        widgetVar,
        model,
        style,
        styleClass,
        activeIndex,
        executedSteps,
        showIndex,
        readonly;

        String toString;

        PropertyKeys() {
        }

        public String toString() {
            return this.toString != null ? this.toString : super.toString();
        }
    }

}
