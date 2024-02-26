package Ptyxiaki.Services.Implementation;

import Ptyxiaki.Dtos.RegisterDto;
import Ptyxiaki.Entities.AppRole;
import Ptyxiaki.Entities.AppUser;
import Ptyxiaki.Repositories.IRoleRepository;
import Ptyxiaki.Repositories.IUserRepository;
import Ptyxiaki.Services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(RegisterDto registrationDto) {
        AppUser user = new AppUser();
        user.setName(registrationDto.getName());
        user.setSurname(registrationDto.getSurname());
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setPhone(registrationDto.getPhone());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));

        AppRole userRole = roleRepository.findByName("USER");
        AppRole role = roleRepository.findByName(registrationDto.getUserRole().toString());
        user.setRoles(List.of(userRole, role));
        userRepository.save(user);
    }

    @Override
    public AppUser findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public AppUser findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
