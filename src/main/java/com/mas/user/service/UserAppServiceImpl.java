package com.mas.user.service;

import com.mas.user.UserApp;
import com.mas.user.UserAppRepository;
import com.mas.user.dto.UserAppCreateDTO;
import com.mas.user.dto.UserAppDTO;
import com.mas.user.dto.UserAppLoginDTO;
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
            return Util.generateErrorResponseAPI("Password do not match",HttpStatusCode.valueOf(400));
        }
        try{
            var userApp = modelMapper.map(userAppCreateDTO, UserApp.class); // Convert dto to object
            userApp.setPassword(passwordEncoder.encode(userApp.getPassword())); // encode password
            userApp = userAppRepository.save(userApp); // save to DB
            return Util.generateSuccessResponseAPI( modelMapper.map(userApp, UserAppDTO.class), HttpStatusCode.valueOf(201));
        }catch (Exception ex){
            return Util.generateErrorResponseAPI(ex.getCause().getCause().getLocalizedMessage(),HttpStatusCode.valueOf(500));
        }
    }

    @Override
    public ResponseEntity<Object> login(UserAppLoginDTO userAppLoginDTO) {
        // find user by email and check password
        var optionalUserApp = userAppRepository.findByEmail(userAppLoginDTO.getEmail());
        if (optionalUserApp.isEmpty() || !passwordEncoder.matches(userAppLoginDTO.getPassword(), optionalUserApp.get().getPassword())){ // if user not found or password not match
            return Util.generateErrorResponseAPI("invalid credentials",HttpStatusCode.valueOf(400));
        }
        // return user
        return Util.generateSuccessResponseAPI(modelMapper.map(optionalUserApp.get(), UserAppDTO.class), HttpStatusCode.valueOf(200));
    }
}
