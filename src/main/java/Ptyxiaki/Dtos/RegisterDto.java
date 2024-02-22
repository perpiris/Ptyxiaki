package Ptyxiaki.Dtos;

import Ptyxiaki.Enums.UserRole;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDto {

    @NotEmpty(message = "This field is required.")
    private String name;

    @NotEmpty(message = "This field is required.")
    private String surname;

    @NotEmpty(message = "This field is required.")
    private String username;

    @NotEmpty(message = "This field is required.")
    private String email;

    @NotEmpty(message = "This field is required.")
    private String phone;

    @NotEmpty(message = "This field is required.")
    private String password;

    @NotNull(message = "This field is required.")
    private UserRole userRole;
}
