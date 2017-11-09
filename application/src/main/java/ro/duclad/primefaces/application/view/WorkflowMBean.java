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
import ro.duclad.primefaces.application.domain.WorkflowStep;
import ro.duclad.primefaces.application.services.WorkflowService;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;
import ro.duclad.primefaces.components.workflow.WorkflowItem;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

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

    private Map<WorkflowStep, Integer> steps;

    private WorkflowStep currentStep;

    @PostConstruct
    public void init() {
        workflow = new DefaultMenuModel();
        steps = new LinkedHashMap<>();
        workflowService.getMainSteps().forEach(step -> steps.put(step, step.getDuration()));

        steps.forEach((step, duration) -> {
            WorkflowItem workflowItem = new WorkflowItem();
            workflowItem.setValue(step.getName());
            workflowItem.setLimitDuration(duration);
            workflowItem.setExecuted(false);
            workflow.addElement(workflowItem);
        });
        currentStep = steps.keySet().stream().findFirst().get();

    }

    public void loadWorkflow() {
        init();
        List<WorkflowStep> workflowStepsFromService = workflowService.getWorkflowSteps(fileId);
        workflowStepsFromService.forEach(step -> steps.put(step, step.getDuration()));
        AtomicBoolean processFailed = new AtomicBoolean(false);
        workflowStepsFromService.forEach(step -> {
            Optional<WorkflowItem> workflowStep = workflow.getElements().stream().map(item -> (WorkflowItem) item).filter(item -> item.getValue().equals(step.getName())).findFirst();
            if (workflowStep.isPresent()) {
                WorkflowItem item = workflowStep.get();
                item.setDuration(step.getDuration());
                item.setProblematic(step.getProblematic());
                item.setExecuted(true);
            } else {
                processFailed.set(true);
            }
        });
        if (processFailed.get()) {
            Optional<WorkflowItem> workflowItem = workflow.getElements().stream().reduce((first, second) -> second).map(item -> (WorkflowItem) item);
            workflowItem.ifPresent(item -> {
                item.setFailed(true);
                item.setExecuted(true);
                item.setShowDuration(false);
            });
            currentStep = steps.keySet().stream().reduce((first, second) -> second).get();

        } else {
            currentStep = workflowStepsFromService.stream().reduce((first, second) -> second).get();
        }
    }
}
