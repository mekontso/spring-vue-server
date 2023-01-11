package com.mas.user;

import com.mas.user.dto.UserAppCreateDTO;
import com.mas.user.dto.UserAppLoginDTO;
import com.mas.user.service.UserAppServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserAppRestController {
    private final UserAppServiceImpl userAppService;

    @GetMapping("/hello")
    public String hello(){
        return "Hello world";
    }
    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody UserAppCreateDTO userAppCreateDTO){
        return userAppService.register(userAppCreateDTO);
    }
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserAppLoginDTO userAppLoginDTO){
        return userAppService.login(userAppLoginDTO);
    }
}
