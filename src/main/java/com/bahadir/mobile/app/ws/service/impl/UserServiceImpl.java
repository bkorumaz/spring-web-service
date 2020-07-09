package com.bahadir.mobile.app.ws.service.impl;

import com.bahadir.mobile.app.ws.UserRepository;
import com.bahadir.mobile.app.ws.io.entity.UserEntity;
import com.bahadir.mobile.app.ws.service.UserService;
import com.bahadir.mobile.app.ws.shared.dto.UserDto;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto user) {

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);

        userEntity.setEncryptedPassword("this is an encrypted password");
        userEntity.setUserId("this is a user id");

        UserEntity storedUserDetails = userRepository.save(userEntity); //this may include id value I am not sure

        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(storedUserDetails, returnValue);

        return returnValue;
    }
}
