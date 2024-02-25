package Ptyxiaki.Dtos;

import Ptyxiaki.Enums.ApplicationStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationDto {

    private Long id;

    private String postTitle;

    private String status;
}
