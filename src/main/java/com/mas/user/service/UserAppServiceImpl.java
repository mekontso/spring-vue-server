package com.mas.user.service;

import com.mas.user.UserApp;
import com.mas.user.UserAppRepository;
import com.mas.user.dto.UserAppCreateDTO;
import com.mas.user.dto.UserAppDTO;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAppServiceImpl implements UserAppService{

    private final UserAppRepository userAppRepository;
    private final ModelMapper modelMapper = new ModelMapper(); // convert objects to dto and vice versa

    private JSONObject jsonResponse;

    @Override
    public ResponseEntity<Object> register(UserAppCreateDTO userAppCreateDTO) {
        jsonResponse = new JSONObject();
        try{
            // Convert dto to object and save to DB
            UserApp userApp = userAppRepository.save(modelMapper.map(userAppCreateDTO, UserApp.class));
            jsonResponse.append("error", false);
            jsonResponse.append("message", "Success register new user");
            jsonResponse.append("data", modelMapper.map(userApp, UserAppDTO.class));
            return ResponseEntity.ok(jsonResponse.toMap());
        }catch (Exception ex){
            jsonResponse.append("error", true);
            jsonResponse.append("message", "Error register new user");
            jsonResponse.append("data", ex.getMessage());
        }
        return new ResponseEntity<>(jsonResponse.toMap(), HttpStatusCode.valueOf(500));
    }
}
