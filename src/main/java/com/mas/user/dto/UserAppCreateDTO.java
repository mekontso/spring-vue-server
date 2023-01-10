package com.mas.user.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserAppCreateDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
