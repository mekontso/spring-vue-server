package com.mas.user.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserAppLoginDTO {
    private String email;
    private String password;
}
