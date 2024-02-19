package Ptyxiaki.Dtos;

import lombok.Data;

import jakarta.validation.constraints.NotEmpty;

@Data
public class RegisterDto {

    private Long id;

    @NotEmpty
    private String username;

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;
}
