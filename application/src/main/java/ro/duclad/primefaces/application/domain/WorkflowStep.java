package ro.duclad.primefaces.application.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(of = "name")
public class WorkflowStep {
    private String name;
    private int duration;
    private Boolean problematic;

}
