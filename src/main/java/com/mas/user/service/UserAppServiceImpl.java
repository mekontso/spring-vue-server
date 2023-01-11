package com.mas.user.service;

import com.mas.user.UserApp;
import com.mas.user.UserAppRepository;
import com.mas.user.dto.UserAppCreateDTO;
import com.mas.user.dto.UserAppDTO;
import com.mas.util.Util;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserAppServiceImpl implements UserAppService{

    private final UserAppRepository userAppRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper = new ModelMapper(); // convert objects to dto and vice versa


    @Override
    public ResponseEntity<Object> register(UserAppCreateDTO userAppCreateDTO) {

        // Validate password match
        if (!Objects.equals(userAppCreateDTO.getPassword(), userAppCreateDTO.getPasswordConfirm())){
            return Util.generateResponseAPI(true,"Password do not match", null,HttpStatusCode.valueOf(400));
        }
        try{
            var userApp = modelMapper.map(userAppCreateDTO, UserApp.class); // Convert dto to object
            userApp.setPassword(passwordEncoder.encode(userApp.getPassword())); // encode password
            userApp = userAppRepository.save(userApp); // save to DB
            return Util.generateResponseAPI(false, "Success register new user",modelMapper.map(userApp, UserAppDTO.class), HttpStatusCode.valueOf(201));
        }catch (Exception ex){
            return Util.generateResponseAPI(true,"Error register new user in DB", ex.getMessage(),HttpStatusCode.valueOf(500));
        }
    }
}
