//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ro.duclad.primefaces.components.workflow;

import org.primefaces.component.api.AjaxSource;
import org.primefaces.component.api.Confirmable;
import org.primefaces.component.api.PrimeClientBehaviorHolder;
import org.primefaces.component.api.UIOutcomeTarget;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.util.ComponentUtils;

import javax.el.MethodExpression;
import javax.faces.application.ResourceDependencies;
import javax.faces.component.FacesComponent;
import javax.faces.component.UICommand;
import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.component.behavior.ClientBehaviorHolder;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.BehaviorEvent;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@ResourceDependencies({})
@FacesComponent("ro.duclad.primefaces.components.WorkflowItem")
public class WorkflowItem extends UICommand implements AjaxSource, UIOutcomeTarget, MenuItem, Confirmable, ClientBehaviorHolder, PrimeClientBehaviorHolder {
    public static final String COMPONENT_TYPE = "ro.duclad.primefaces.components.WorkflowItem";
    public static final String COMPONENT_FAMILY = "ro.duclad.primefaces.components";
    private static final String DEFAULT_EVENT = "click";
    private static final Map<String, Class<? extends BehaviorEvent>> BEHAVIOR_EVENT_MAPPING = Collections.unmodifiableMap(new HashMap<String, Class<? extends BehaviorEvent>>() {
        {
            this.put("click", null);
        }
    });
    private static final Collection<String> EVENT_NAMES;

    static {
        EVENT_NAMES = BEHAVIOR_EVENT_MAPPING.keySet();
    }

    private String confirmationScript;

    public WorkflowItem() {
        this.setRendererType(null);
    }

    public String getFamily() {
        return "ro.duclad.primefaces.components";
    }

    public String getUrl() {
        return (String) this.getStateHelper().eval(WorkflowItem.PropertyKeys.url, null);
    }

    public void setUrl(String _url) {
        this.getStateHelper().put(WorkflowItem.PropertyKeys.url, _url);
    }

    public String getTarget() {
        return (String) this.getStateHelper().eval(WorkflowItem.PropertyKeys.target, null);
    }

    public void setTarget(String _target) {
        this.getStateHelper().put(WorkflowItem.PropertyKeys.target, _target);
    }

    public String getStyle() {
        return (String) this.getStateHelper().eval(WorkflowItem.PropertyKeys.style, null);
    }

    public void setStyle(String _style) {
        this.getStateHelper().put(WorkflowItem.PropertyKeys.style, _style);
    }

    public String getStyleClass() {
        return (String) this.getStateHelper().eval(WorkflowItem.PropertyKeys.styleClass, null);
    }

    public void setStyleClass(String _styleClass) {
        this.getStateHelper().put(WorkflowItem.PropertyKeys.styleClass, _styleClass);
    }

    public String getOnclick() {
        return (String) this.getStateHelper().eval(WorkflowItem.PropertyKeys.onclick, null);
    }

    public void setOnclick(String _onclick) {
        this.getStateHelper().put(WorkflowItem.PropertyKeys.onclick, _onclick);
    }

    public String getUpdate() {
        return (String) this.getStateHelper().eval(WorkflowItem.PropertyKeys.update, null);
    }

    public void setUpdate(String _update) {
        this.getStateHelper().put(WorkflowItem.PropertyKeys.update, _update);
    }

    public String getProcess() {
        return (String) this.getStateHelper().eval(WorkflowItem.PropertyKeys.process, null);
    }

    public void setProcess(String _process) {
        this.getStateHelper().put(WorkflowItem.PropertyKeys.process, _process);
    }

    public String getOnstart() {
        return (String) this.getStateHelper().eval(WorkflowItem.PropertyKeys.onstart, null);
    }

    public void setOnstart(String _onstart) {
        this.getStateHelper().put(WorkflowItem.PropertyKeys.onstart, _onstart);
    }

    public boolean isDisabled() {
        return (Boolean) this.getStateHelper().eval(WorkflowItem.PropertyKeys.disabled, false);
    }

    public void setDisabled(boolean _disabled) {
        this.getStateHelper().put(WorkflowItem.PropertyKeys.disabled, _disabled);
    }

    public String getOncomplete() {
        return (String) this.getStateHelper().eval(WorkflowItem.PropertyKeys.oncomplete, null);
    }

    public void setOncomplete(String _oncomplete) {
        this.getStateHelper().put(WorkflowItem.PropertyKeys.oncomplete, _oncomplete);
    }

    public String getOnerror() {
        return (String) this.getStateHelper().eval(WorkflowItem.PropertyKeys.onerror, null);
    }

    public void setOnerror(String _onerror) {
        this.getStateHelper().put(WorkflowItem.PropertyKeys.onerror, _onerror);
    }

    public String getOnsuccess() {
        return (String) this.getStateHelper().eval(WorkflowItem.PropertyKeys.onsuccess, null);
    }

    public void setOnsuccess(String _onsuccess) {
        this.getStateHelper().put(WorkflowItem.PropertyKeys.onsuccess, _onsuccess);
    }

    public boolean isGlobal() {
        return (Boolean) this.getStateHelper().eval(WorkflowItem.PropertyKeys.global, true);
    }

    public void setGlobal(boolean _global) {
        this.getStateHelper().put(WorkflowItem.PropertyKeys.global, _global);
    }

    public String getDelay() {
        return (String) this.getStateHelper().eval(WorkflowItem.PropertyKeys.delay, null);
    }

    public void setDelay(String _delay) {
        this.getStateHelper().put(WorkflowItem.PropertyKeys.delay, _delay);
    }

    public int getTimeout() {
        return (Integer) this.getStateHelper().eval(WorkflowItem.PropertyKeys.timeout, 0);
    }

    public void setTimeout(int _timeout) {
        this.getStateHelper().put(WorkflowItem.PropertyKeys.timeout, _timeout);
    }

    public boolean isAsync() {
        return (Boolean) this.getStateHelper().eval(WorkflowItem.PropertyKeys.async, false);
    }

    public void setAsync(boolean _async) {
        this.getStateHelper().put(WorkflowItem.PropertyKeys.async, _async);
    }

    public boolean isAjax() {
        return (Boolean) this.getStateHelper().eval(WorkflowItem.PropertyKeys.ajax, true);
    }

    public void setAjax(boolean _ajax) {
        this.getStateHelper().put(WorkflowItem.PropertyKeys.ajax, _ajax);
    }

    public String getIcon() {
        return (String) this.getStateHelper().eval(WorkflowItem.PropertyKeys.icon, null);
    }

    public void setIcon(String _icon) {
        this.getStateHelper().put(WorkflowItem.PropertyKeys.icon, _icon);
    }

    public String getIconPos() {
        return (String) this.getStateHelper().eval(WorkflowItem.PropertyKeys.iconPos, "right");
    }

    public void setIconPos(String _iconPos) {
        this.getStateHelper().put(WorkflowItem.PropertyKeys.iconPos, _iconPos);
    }

    public boolean isPartialSubmit() {
        return (Boolean) this.getStateHelper().eval(WorkflowItem.PropertyKeys.partialSubmit, false);
    }

    public void setPartialSubmit(boolean _partialSubmit) {
        this.getStateHelper().put(WorkflowItem.PropertyKeys.partialSubmit, _partialSubmit);
    }

    public boolean isResetValues() {
        return (Boolean) this.getStateHelper().eval(WorkflowItem.PropertyKeys.resetValues, false);
    }

    public void setResetValues(boolean _resetValues) {
        this.getStateHelper().put(WorkflowItem.PropertyKeys.resetValues, _resetValues);
    }

    public boolean isIgnoreAutoUpdate() {
        return (Boolean) this.getStateHelper().eval(WorkflowItem.PropertyKeys.ignoreAutoUpdate, false);
    }

    public void setIgnoreAutoUpdate(boolean _ignoreAutoUpdate) {
        this.getStateHelper().put(WorkflowItem.PropertyKeys.ignoreAutoUpdate, _ignoreAutoUpdate);
    }

    public String getTitle() {
        return (String) this.getStateHelper().eval(WorkflowItem.PropertyKeys.title, null);
    }

    public void setTitle(String _title) {
        this.getStateHelper().put(WorkflowItem.PropertyKeys.title, _title);
    }

    public String getOutcome() {
        return (String) this.getStateHelper().eval(WorkflowItem.PropertyKeys.outcome, null);
    }

    public void setOutcome(String _outcome) {
        this.getStateHelper().put(WorkflowItem.PropertyKeys.outcome, _outcome);
    }

    public boolean isIncludeViewParams() {
        return (Boolean) this.getStateHelper().eval(WorkflowItem.PropertyKeys.includeViewParams, false);
    }

    public void setIncludeViewParams(boolean _includeViewParams) {
        this.getStateHelper().put(WorkflowItem.PropertyKeys.includeViewParams, _includeViewParams);
    }

    public String getFragment() {
        return (String) this.getStateHelper().eval(WorkflowItem.PropertyKeys.fragment, null);
    }

    public void setFragment(String _fragment) {
        this.getStateHelper().put(WorkflowItem.PropertyKeys.fragment, _fragment);
    }

    public boolean isDisableClientWindow() {
        return (Boolean) this.getStateHelper().eval(WorkflowItem.PropertyKeys.disableClientWindow, false);
    }

    public void setDisableClientWindow(boolean _disableClientWindow) {
        this.getStateHelper().put(WorkflowItem.PropertyKeys.disableClientWindow, _disableClientWindow);
    }

    public String getContainerStyle() {
        return (String) this.getStateHelper().eval(WorkflowItem.PropertyKeys.containerStyle, null);
    }

    public void setContainerStyle(String _containerStyle) {
        this.getStateHelper().put(WorkflowItem.PropertyKeys.containerStyle, _containerStyle);
    }

    public String getContainerStyleClass() {
        return (String) this.getStateHelper().eval(WorkflowItem.PropertyKeys.containerStyleClass, null);
    }

    public void setContainerStyleClass(String _containerStyleClass) {
        this.getStateHelper().put(WorkflowItem.PropertyKeys.containerStyleClass, _containerStyleClass);
    }

    public String getPartialSubmitFilter() {
        return (String) this.getStateHelper().eval(WorkflowItem.PropertyKeys.partialSubmitFilter, null);
    }

    public void setPartialSubmitFilter(String _partialSubmitFilter) {
        this.getStateHelper().put(WorkflowItem.PropertyKeys.partialSubmitFilter, _partialSubmitFilter);
    }

    public String getForm() {
        return (String) this.getStateHelper().eval(WorkflowItem.PropertyKeys.form, null);
    }

    public void setForm(String _form) {
        this.getStateHelper().put(WorkflowItem.PropertyKeys.form, _form);
    }

    public boolean isEscape() {
        return (Boolean) this.getStateHelper().eval(WorkflowItem.PropertyKeys.escape, true);
    }

    public void setEscape(boolean _escape) {
        this.getStateHelper().put(WorkflowItem.PropertyKeys.escape, _escape);
    }

    public String getRel() {
        return (String) this.getStateHelper().eval(WorkflowItem.PropertyKeys.rel, null);
    }

    public void setRel(String _rel) {
        this.getStateHelper().put(WorkflowItem.PropertyKeys.rel, _rel);
    }

    public Integer getStepLimitDuration() {
        return (Integer) this.getStateHelper().eval(PropertyKeys.stepLimitDuration, null);
    }

    public void setStepLimitDuration(int limitDuration) {
        this.getStateHelper().put(PropertyKeys.stepLimitDuration, limitDuration);
    }

    public Integer getStepDuration() {
        return (Integer) this.getStateHelper().eval(PropertyKeys.stepDuration, null);
    }

    public void setStepDuration(int duration) {
        this.getStateHelper().put(PropertyKeys.stepDuration, duration);
    }

    public Boolean isShowStepDuration() {
        return (Boolean) this.getStateHelper().eval(PropertyKeys.showStepDuration, true);
    }

    public void setShowStepDuration(boolean showDuration) {
        this.getStateHelper().put(PropertyKeys.showStepDuration, showDuration);
    }

    public Boolean isStepProblematic() {
        return (Boolean) this.getStateHelper().eval(PropertyKeys.stepProblematic, false);
    }

    public void setStepProblematic(boolean problematic) {
        this.getStateHelper().put(PropertyKeys.stepProblematic, problematic);
    }


    public WorkflowItemState getStepState() {
        return (WorkflowItemState) this.getStateHelper().eval(PropertyKeys.stepState, WorkflowItemState.NOT_EXECUTED);
    }

    public void setStepState(WorkflowItemState state) {
        this.getStateHelper().put(PropertyKeys.stepState, state);
    }

    public String getStepOutcome() {
        return (String) this.getStateHelper().eval(WorkflowItem.PropertyKeys.stepOutcome, null);
    }

    public void setStepOutcome(String outcome) {
        this.getStateHelper().put(WorkflowItem.PropertyKeys.stepOutcome, outcome);
    }


    public Map<String, Class<? extends BehaviorEvent>> getBehaviorEventMapping() {
        return BEHAVIOR_EVENT_MAPPING;
    }

    public Collection<String> getEventNames() {
        return EVENT_NAMES;
    }

    public String getDefaultEventName() {
        return "click";
    }

    @Override
    public void setId(String id) {
        super.setId('_' + id);
    }

    public void decode(FacesContext facesContext) {
        Map<String, String> params = facesContext.getExternalContext().getRequestParameterMap();
        String clientId = this.getClientId(facesContext);
        if (params.containsKey(clientId)) {
            this.queueEvent(new ActionEvent(this));
        }

    }

    public boolean shouldRenderChildren() {
        if (this.getChildCount() == 0) {
            return false;
        } else {
            Iterator var1 = this.getChildren().iterator();

            UIComponent child;
            do {
                if (!var1.hasNext()) {
                    return false;
                }

                child = (UIComponent) var1.next();
            } while (child instanceof UIParameter);

            return true;
        }
    }

    public boolean isPartialSubmitSet() {
        return this.getStateHelper().get(WorkflowItem.PropertyKeys.partialSubmit) != null || this.getValueExpression(WorkflowItem.PropertyKeys.partialSubmit.toString()) != null;
    }

    public boolean isResetValuesSet() {
        return this.getStateHelper().get(WorkflowItem.PropertyKeys.resetValues) != null || this.getValueExpression(WorkflowItem.PropertyKeys.resetValues.toString()) != null;
    }

    public String getHref() {
        return this.getUrl();
    }

    public boolean isDynamic() {
        return false;
    }

    public Map<String, List<String>> getParams() {
        return ComponentUtils.getUIParams(this);
    }

    public String getCommand() {
        MethodExpression expr = super.getActionExpression();
        return expr != null ? expr.getExpressionString() : null;
    }

    public boolean isAjaxified() {
        return this.getUrl() == null && this.getOutcome() == null && this.isAjax();
    }

    public void setParam(String key, Object value) {
        throw new UnsupportedOperationException("Use UIParameter component instead to add parameters.");
    }

    public String getConfirmationScript() {
        return this.confirmationScript;
    }

    public void setConfirmationScript(String confirmationScript) {
        this.confirmationScript = confirmationScript;
    }

    public boolean requiresConfirmation() {
        return this.confirmationScript != null;
    }

    public enum WorkflowItemState {NOT_EXECUTED, EXECUTED, RUNNING, FAILED}

    public enum PropertyKeys {
        url,
        target,
        style,
        styleClass,
        onclick,
        update,
        process,
        onstart,
        disabled,
        oncomplete,
        onerror,
        onsuccess,
        global,
        delay,
        timeout,
        async,
        ajax,
        icon,
        iconPos,
        partialSubmit,
        resetValues,
        ignoreAutoUpdate,
        title,
        outcome,
        includeViewParams,
        fragment,
        disableClientWindow,
        containerStyle,
        containerStyleClass,
        partialSubmitFilter,
        form,
        escape,
        rel,
        stepLimitDuration,
        stepDuration,
        showStepDuration,
        stepProblematic,
        stepState,
        stepOutcome;

        String toString;

        PropertyKeys(String toString) {
            this.toString = toString;
        }

        PropertyKeys() {
        }

        public String toString() {
            return this.toString != null ? this.toString : super.toString();
        }
    }
}
