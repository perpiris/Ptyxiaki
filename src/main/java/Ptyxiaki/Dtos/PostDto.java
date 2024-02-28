package Ptyxiaki.Dtos;

import Ptyxiaki.Enums.JobType;
import Ptyxiaki.Enums.WorkLocation;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PostDto {

    private Long id;

    @NotEmpty(message = "This field is required.")
    private String title;

    @NotEmpty(message = "This field is required.")
    private String description;

    @NotNull(message = "This field is required.")
    private JobType jobType;

    @NotNull(message = "This field is required.")
    private WorkLocation workLocation;

    private String createdOn;

    private List<RequirementDto> requirements = new ArrayList<>();
}
