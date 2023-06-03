package com.four.simple.repositories;

import com.four.simple.user.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootTest
public class UserRoleTest {
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCRUD(){
        //Test Init UserRole Count
        long userRoleCount=userRoleRepository.count();
        assertEquals(2,userRoleCount);

        //Create new User & assign its roles.
        User userC=User.builder().username("userC").email("userC@depaul.edu")
                .password("password").build();
        UserRole roleUser=new UserRole();
        roleUser.setName(UserRoleType.ROLE_USER);
        userRoleRepository.save(roleUser);
        Set<UserRole> roleSet=new HashSet<>();
        roleSet.add(roleUser);
        userC.setRoles(roleSet);
        userRepository.save(userC);

        assertEquals(true,userRepository.existsByEmail("userC@depaul.edu"));

        //READ User Role from Repo
        Optional<User> userCR=userRepository.findByUsername("userC");
        Set<UserRole> userCRole=userCR.get().getRoles();
        UserRoleType userRoleType=userCRole.iterator().next().getName();
        assertEquals(userRoleType,UserRoleType.ROLE_USER);

        //Updating User Role from Repo
        UserRole roleAdmin=new UserRole();
        roleAdmin.setName(UserRoleType.ROLE_ADMIN);
        userRoleRepository.save(roleAdmin);
        roleSet.add(roleAdmin);
        userC.setRoles(roleSet);
        userRepository.save(userC);

        userCR=userRepository.findByUsername("userC");
        Set<UserRole> userUpdateRole=userCR.get().getRoles();
        Set<UserRoleType> expectedRoles= Set.of(UserRoleType.ROLE_USER,UserRoleType.ROLE_ADMIN);
        Set<UserRoleType> actualRoles=userUpdateRole.stream().map(UserRole::getName)
                        .collect(Collectors.toSet());

        assertEquals(expectedRoles,actualRoles);


        //Delete User role from repo
        Optional<User> deleteUserFromC = userRepository.findByUsername("userC");
        deleteUserFromC.ifPresent(user -> {
            user.setRoles(Set.of(roleAdmin));
            userRepository.save(user);}
        );
        userRoleRepository.delete(roleUser);

        deleteUserFromC = userRepository.findByUsername("userC");
        userUpdateRole = deleteUserFromC.get().getRoles();
        expectedRoles=Set.of(UserRoleType.ROLE_ADMIN);
        actualRoles=userUpdateRole.stream().map(UserRole::getName)
                .collect(Collectors.toSet());

        assertEquals(expectedRoles,actualRoles);
    }
}
