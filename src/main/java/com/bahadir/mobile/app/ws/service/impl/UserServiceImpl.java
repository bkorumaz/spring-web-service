package com.bahadir.mobile.app.ws.service.impl;

import com.bahadir.mobile.app.ws.UserRepository;
import com.bahadir.mobile.app.ws.io.entity.UserEntity;
import com.bahadir.mobile.app.ws.service.UserService;
import com.bahadir.mobile.app.ws.shared.Utils;
import com.bahadir.mobile.app.ws.shared.dto.UserDto;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils utils;

    @Override
    public UserDto createUser(UserDto user) {

        if(userRepository.findByEmail(user.getEmail()) != null) throw new RuntimeException("Record already exists");

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);

        String publicUserId = utils.generateUserId(30);
        userEntity.setEncryptedPassword("this is an encrypted password");
        userEntity.setUserId(publicUserId);

        UserEntity storedUserDetails = userRepository.save(userEntity); //this may include id value I am not sure

        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(storedUserDetails, returnValue);

        return returnValue;
    }
}
