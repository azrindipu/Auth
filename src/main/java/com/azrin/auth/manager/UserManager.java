package com.azrin.auth.manager;

import com.azrin.auth.dto.UserDto;
import com.azrin.auth.service.UserService;
import com.azrin.auth.utils.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserManager {
    private static final Logger logger = LoggerFactory.getLogger(UserManager.class);

    @Autowired
    private Converter converter;

    @Autowired
    private UserService userService;

    public UserDto createUser(UserDto userDto) throws Exception{
        logger.info("Called manager to manage request");
        return converter.userEntityToUserDto(userService.createUser(converter.userDtoToUserEntity(userDto)));
    }
}
