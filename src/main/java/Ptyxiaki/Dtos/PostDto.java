package Ptyxiaki.Dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {

    private Long id;

    @NotEmpty(message = "This field is required.")
    private String title;

    @NotEmpty(message = "This field is required.")
    private String description;
}
