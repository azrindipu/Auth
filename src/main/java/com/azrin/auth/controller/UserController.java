package com.azrin.auth.controller;

import com.azrin.auth.ExceptionHandler.AlreadyExist;
import com.azrin.auth.ExceptionHandler.BadRequest;
import com.azrin.auth.ExceptionHandler.InternalServerError;
import com.azrin.auth.ExceptionHandler.NotFound;
import com.azrin.auth.dto.UserDto;
import com.azrin.auth.manager.UserManager;
import com.azrin.auth.utils.AllEndPoints;
import com.azrin.auth.utils.ApiVersion;
import com.azrin.auth.utils.Constant;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(ApiVersion.API_VERSION)
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserManager userManager;

    @PostMapping(value = AllEndPoints.USERS_POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<JSONObject> createUser(@Valid @RequestBody UserDto userDto)throws Exception {
        UserDto result = null;
        try {
            logger.info("Calling service");
            result = userManager.createUser(userDto);
        }catch (AlreadyExist e){
            throw new AlreadyExist(e.getMessage());
        }catch (NotFound e){
            throw new NotFound(e.getMessage());
        }catch (BadRequest e){
            throw new BadRequest(e.getMessage());
        }catch (InternalServerError e){
            throw new InternalServerError(e.getMessage());
        }catch (Exception e){
            throw new InternalServerError(e.getMessage());
        }

        JSONObject responseBody = new JSONObject();
        responseBody.put(Constant.RESPONSE_BODY_DATA, result);
        responseBody.put(Constant.RESPONSE_BODY_STATUS, HttpStatus.OK);
        responseBody.put(Constant.RESPONSE_BODY_MESSAGE, Constant.MESSAGE_SUCCESSFUL);

        logger.info(responseBody.toString());
        return ResponseEntity.ok(responseBody);
    }
}
