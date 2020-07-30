package com.azrin.auth.service;

import com.azrin.auth.model.User;
import com.azrin.auth.repository.UserRepository;
import com.azrin.auth.utils.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private Validate validate;

    public User createUser(User user) throws Exception{
        logger.info("Called service and start validation.");
        validate.isRoleValid(user.getRoleName());
        validate.isUserExist(user.getEmail());
        logger.info("Validation finished and going to DAO layer.");
        return userRepository.saveUser(user);
    }

}
