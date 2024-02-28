package Ptyxiaki.Dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequirementDto {

    @NotEmpty(message = "This field is required.")
    private String label;

    @NotEmpty(message = "This field is required.")
    private int minimumYears;
}
