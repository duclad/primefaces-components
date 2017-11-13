package ro.duclad.primefaces.application.services;

import ro.duclad.primefaces.application.domain.workflow.WorkflowStep;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class WorkflowService {

    private final ArrayList<WorkflowStep> workflowSteps = new ArrayList<WorkflowStep>() {
        {
            add(WorkflowStep.builder().name("Filed").duration(3).build());
            add(WorkflowStep.builder().name("Certified").duration(7).build());
            add(WorkflowStep.builder().name("Formality").duration(12).build());
            add(WorkflowStep.builder().name("Classify").duration(14).build());
            add(WorkflowStep.builder().name("Searched").duration(15).build());
            add(WorkflowStep.builder().name("Publish").duration(17).build());
            add(WorkflowStep.builder().name("Examin").duration(20).build());
            add(WorkflowStep.builder().name("Registered").duration(24).build());
        }
    };

    List<WorkflowStep> case1 = new ArrayList<WorkflowStep>() {
        {
            add(WorkflowStep.builder().name("Filed").status(WorkflowStep.WorkflowStepStatus.EXECUTED).duration(3).build());
            add(WorkflowStep.builder().name("Certified").status(WorkflowStep.WorkflowStepStatus.EXECUTED).duration(8).problematic(true).build());
            add(WorkflowStep.builder().name("Formality").status(WorkflowStep.WorkflowStepStatus.EXECUTED).duration(12).build());
            add(WorkflowStep.builder().name("Classify").duration(14).status(WorkflowStep.WorkflowStepStatus.RUNNING).build());
        }
    };
    List<WorkflowStep> case2 = new ArrayList<WorkflowStep>() {
        {
            add(WorkflowStep.builder().name("Filed").status(WorkflowStep.WorkflowStepStatus.EXECUTED).duration(3).build());
            add(WorkflowStep.builder().name("Certified").status(WorkflowStep.WorkflowStepStatus.EXECUTED).duration(8).build());
            add(WorkflowStep.builder().name("Formality").status(WorkflowStep.WorkflowStepStatus.EXECUTED).duration(12).build());
            add(WorkflowStep.builder().name("Classify").status(WorkflowStep.WorkflowStepStatus.EXECUTED).duration(14).build());
            add(WorkflowStep.builder().name("Searched").status(WorkflowStep.WorkflowStepStatus.EXECUTED).duration(12).build());
            add(WorkflowStep.builder().name("Publish").duration(17).status(WorkflowStep.WorkflowStepStatus.RUNNING).build());
        }
    };
    List<WorkflowStep> case3 = new ArrayList<WorkflowStep>() {
        {
            add(WorkflowStep.builder().name("Filed").status(WorkflowStep.WorkflowStepStatus.EXECUTED).duration(3).build());
            add(WorkflowStep.builder().name("Certified").status(WorkflowStep.WorkflowStepStatus.EXECUTED).duration(7).build());
            add(WorkflowStep.builder().name("Formality").status(WorkflowStep.WorkflowStepStatus.EXECUTED).duration(12).build());
            add(WorkflowStep.builder().name("Classify").status(WorkflowStep.WorkflowStepStatus.EXECUTED).duration(14).build());
            add(WorkflowStep.builder().name("Searched").status(WorkflowStep.WorkflowStepStatus.EXECUTED).duration(15).build());
            add(WorkflowStep.builder().name("Publish").status(WorkflowStep.WorkflowStepStatus.EXECUTED).duration(17).build());
            add(WorkflowStep.builder().name("Examin").status(WorkflowStep.WorkflowStepStatus.EXECUTED).duration(20).build());
            add(WorkflowStep.builder().name("Registered").status(WorkflowStep.WorkflowStepStatus.EXECUTED).duration(24).build());
        }
    };
    List<WorkflowStep> case4 = new ArrayList<WorkflowStep>() {
        {
            add(WorkflowStep.builder().name("Filed").status(WorkflowStep.WorkflowStepStatus.EXECUTED).duration(3).build());
            add(WorkflowStep.builder().name("Certified").status(WorkflowStep.WorkflowStepStatus.EXECUTED).duration(8).build());
            add(WorkflowStep.builder().name("Formality").status(WorkflowStep.WorkflowStepStatus.EXECUTED).duration(12).build());
            add(WorkflowStep.builder().name("Classify").duration(14).status(WorkflowStep.WorkflowStepStatus.FAILED).outcome("Invalid").build());
        }
    };

    public List<WorkflowStep> getMainSteps() {
        return workflowSteps;
    }

    public List<WorkflowStep> getWorkflowSteps(String fileId) {
        switch (fileId) {
            case "file1":
                return case1;
            case "file2":
                return case2;
            case "file3":
                return case3;
            default:
                return case4;
        }
    }
}
