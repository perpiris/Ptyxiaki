package Ptyxiaki.Dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterDto {

//    @NotEmpty
//    private String name;
//
//    @NotEmpty
//    private String surname;

    @NotEmpty(message = "This field is required.")
    private String username;

    @NotEmpty(message = "This field is required.")
    private String email;

    @NotEmpty(message = "This field is required.")
    private String password;

//    @NotNull
//    private String role;
}
