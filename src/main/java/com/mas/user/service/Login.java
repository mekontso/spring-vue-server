package com.mas.user.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mas.jwt.Token;
import lombok.Getter;

@Getter
public class Login {
    @JsonProperty("access_token")
    private final Token accessToken;
    @JsonProperty("refresh_token")
    private final Token refreshToken;

    private Login(Token accessToken, Token refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }


    public static Login of(Long userAppId, String accessSecret, String refreshSecret){
        return  new Login(
                Token.of(userAppId,1L,accessSecret),
                Token.of(userAppId,1440L,refreshSecret)
        );
    }
}
