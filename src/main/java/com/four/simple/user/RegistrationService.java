package com.four.simple.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.Set;


@RestController
@RequestMapping(path = "api/auth")
@Tag(name = "Registration", description = "Registration API")
public class RegistrationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping("/signup")
    public ModelAndView showRegistrationPage() {
        ModelAndView modelAndView=new ModelAndView("signup");
        return modelAndView;
    }

    @PostMapping("/signup")
    @Operation(summary = "Create User & It's Roles")
    public String registerUser(SingupRequest singupRequest){
        if(userRepository.existsByUsername(singupRequest.getUsername())){
            return "Error: Username is already taken!";
        }
        if(userRepository.existsByEmail(singupRequest.getEmail())){
            return "Error: Email is already in use!";
        }

        Set<String> strRoles= singupRequest.getRole();
        Set<UserRole> roles= new HashSet<>();

        if(strRoles ==null){
            UserRole userRole = userRoleRepository.findByName(UserRoleType.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        }else{
            strRoles.forEach(role -> {
                switch (role){
                    case "admin":
                        UserRole adminRole = userRoleRepository.findByName(UserRoleType.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: role is not found"));
                        roles.add(adminRole);
                        break;

                    default:
                        UserRole userRole= userRoleRepository.findByName(UserRoleType.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: role is not found"));

                        roles.add(userRole);
                }
            });
        }
        User user= User.builder()
                .username(singupRequest.getUsername())
                .email(singupRequest.getEmail())
                .password(encoder.encode(singupRequest.getPassword()))
                .roles(roles)
                .build();

        userRepository.save(user);

        return "You have successfully registered now!";
    }
}
