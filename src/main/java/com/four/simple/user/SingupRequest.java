package com.four.simple.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingupRequest {
    private String username;

    private String email;

    private Set<String> role;

    private String password;
}
