package Ptyxiaki.Services;

import Ptyxiaki.Dtos.RegisterDto;
import Ptyxiaki.Entities.AppUser;

public interface IUserService {

    void saveUser(RegisterDto registrationDto);
    AppUser findByEmail(String email);
    AppUser findByUsername(String username);
}
