package com.mas.user.service;

import com.mas.user.dto.UserAppCreateDTO;
import com.mas.user.dto.UserAppLoginDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

public interface UserAppService {
    /**
     * Save a user to db
     * @param userAppCreateDTO data of user in dto.
     * @return response to be sent to user
     */
    ResponseEntity<Object> register(UserAppCreateDTO userAppCreateDTO);
    ResponseEntity<Object> login(UserAppLoginDTO userAppLoginDTO, HttpServletResponse response);


}
