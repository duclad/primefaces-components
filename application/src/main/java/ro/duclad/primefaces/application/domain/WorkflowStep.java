package ro.duclad.primefaces.application.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(of = "name")
public class WorkflowStep {
    private String name;
    private int duration;
    private boolean problematic;
    @Builder.Default
    private WorkflowStepStatus status = WorkflowStepStatus.EXECUTED;
    private String outcome;

    public enum WorkflowStepStatus {EXECUTED, RUNNING, FAILED}


}

