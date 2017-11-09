package ro.duclad.primefaces.application.services;

import ro.duclad.primefaces.application.domain.WorkflowStep;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class WorkflowService {

    private final ArrayList<WorkflowStep> workflowSteps = new ArrayList<WorkflowStep>() {
        {
            add(new WorkflowStep("Reception", 3, null));
            add(new WorkflowStep("Data Capture", 7, null));
            add(new WorkflowStep("Formality Examination", 12, null));
            add(new WorkflowStep("Classification", 14, null));
            add(new WorkflowStep("Search", 15, null));
            add(new WorkflowStep("Publication", 17, null));
            add(new WorkflowStep("Examination", 20, null));
            add(new WorkflowStep("Registration", 20, null));
        }
    };

    List<WorkflowStep> case1 = new ArrayList<WorkflowStep>() {
        {
            add(new WorkflowStep("Reception", 3, false));
            add(new WorkflowStep("Data Capture", 8, true));
            add(new WorkflowStep("Formality Examination", 10, false));
            add(new WorkflowStep("Classification", 14, false));

        }
    };
    List<WorkflowStep> case2 = new ArrayList<WorkflowStep>() {
        {
            add(new WorkflowStep("Reception", 3, false));
            add(new WorkflowStep("Data Capture", 8, false));
            add(new WorkflowStep("Formality Examination", 10, false));
            add(new WorkflowStep("Classification", 14, false));

        }
    };
    List<WorkflowStep> case3 = new ArrayList<WorkflowStep>() {
        {
            add(new WorkflowStep("Reception", 3, false));
            add(new WorkflowStep("Data Capture", 8, false));
            add(new WorkflowStep("Formality Examination", 10, false));
            add(new WorkflowStep("Classification", 14, false));
            add(new WorkflowStep("ERROR", 10, false));

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
            default:
                return case3;
        }
    }
}
