package com.azrin.auth.utils;

import com.azrin.auth.ExceptionHandler.AlreadyExist;
import com.azrin.auth.ExceptionHandler.BadRequest;
import com.azrin.auth.model.User;
import com.azrin.auth.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Validate {
    private static final Logger logger = LoggerFactory.getLogger(Validate.class);

    @Autowired
    private UserRepository userRepository;

    public boolean isRoleValid(String roleName) throws Exception{
        logger.info("role name: "+roleName);
        if(roleName.equalsIgnoreCase(RoleType.ROLE_ADMIN) ||
                roleName.equalsIgnoreCase(RoleType.ROLE_USER) ||
                roleName.equalsIgnoreCase(RoleType.ROLE_MANAGER)){
            return true;
        }
        throw new BadRequest(ExceptionMessage.INVALID_ROLE_NAME);
    }

    public boolean isUserExist(String email){
        logger.info("email: "+email);
        User user = userRepository.findByEmail(email);
        if(user != null){
            throw new AlreadyExist(ExceptionMessage.USER_ALREADY_EXIST);
        }
        return true;
    }
}
