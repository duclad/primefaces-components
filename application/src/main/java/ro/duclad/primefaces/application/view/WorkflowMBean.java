/*
 * Copyright 2016-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ro.duclad.primefaces.application.view;

import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;
import ro.duclad.primefaces.application.services.WorkflowService;
import ro.duclad.primefaces.components.workflow.WorkflowItem;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Welcome Page.
 *
 * @author Marcelo Fernandes
 */
@Setter
@Getter
@Named
@ViewScoped
public class WorkflowMBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String fileId = "";

    @Inject
    private WorkflowService workflowService;

    private MenuModel workflow;
    private MenuModel executedSteps;


    @PostConstruct
    public void init() {
        workflow = new DefaultMenuModel();
        workflowService.getMainSteps().forEach(step -> {
            WorkflowItem workflowItem = new WorkflowItem();
            workflowItem.setValue(step.getName());
            workflowItem.setStepLimitDuration(step.getDuration());
            workflow.addElement(workflowItem);
        });

    }

    public void loadWorkflow() {
        init();
        executedSteps = new DefaultMenuModel();
        workflowService.getWorkflowSteps(fileId).forEach(step -> {
            WorkflowItem workflowItem = new WorkflowItem();
            workflowItem.setValue(step.getName());
            workflowItem.setStepDuration(step.getDuration());
            workflowItem.setStepState(WorkflowItem.WorkflowItemState.valueOf(step.getStatus().toString()));
            workflowItem.setStepProblematic(step.isProblematic());
            workflowItem.setStepOutcome(step.getOutcome());
            executedSteps.addElement(workflowItem);
        });
    }
}
