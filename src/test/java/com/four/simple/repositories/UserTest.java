package com.four.simple.repositories;

/*
    Test UserRepository:
    CRUD
 */

import com.four.simple.user.User;
import com.four.simple.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import java.util.Optional;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class UserTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCRUD(){
        //Count Init user in the table.
        long userCount=userRepository.count();

        //We got 2 Excatly
        assertEquals(2,userCount);

        //Test Create
        User userA= User.builder().username("userA").email("userA@depaul.edu")
                .password("password").build();

        User userB= User.builder().username("userB").email("userB@depaul.edu")
                .password("password").build();
        userRepository.saveAll(List.of(userA,userB));

        userCount=userRepository.count();
        // We should have 4 in user table now
        assertEquals(4,userCount);



        //Test Get ID from repo
        Optional<User> userAR=userRepository.findByUsername("userA");
        long userAid=userAR.get().getId();
        assertEquals(1L,userAid);
        Optional<User> userBR=userRepository.findByUsername("userB");
        long userBid=userBR.get().getId();
        assertEquals(2L,userBid);


        //Read from id and retrive User name
        Optional<User> usernameA=userRepository.findById(1L);
        Optional<User> usernameB=userRepository.findById(2L);
        String AName = usernameA.get().getUsername();
        String BName = usernameB.get().getUsername();
        assertEquals(AName,"userA");
        assertEquals(BName,"userB");


        //Test Updated
        Optional<User> newUser=userRepository.findByUsername("userA");
        newUser.ifPresent(user ->
                {user.setUsername("newUserA");
                userRepository.save(user);}
        );
        Optional<User> vertifyUser=userRepository.findById(1L);
        String newName=vertifyUser.get().getUsername();
        assertEquals(newName,"newUserA");


        //Test Delete
        userRepository.deleteById(2L);
        assertEquals(false,userRepository.existsById(2L));
    }
}
