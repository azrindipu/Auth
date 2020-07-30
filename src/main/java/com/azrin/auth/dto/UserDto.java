package com.azrin.auth.dto;

import com.azrin.auth.utils.ValidationMessage;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    @JsonProperty("email")
    @NotEmpty(message = ValidationMessage.EMAIL_CAN_NOT_BE_EMPTY)
    @NotNull(message = ValidationMessage.EMAIL_CAN_NOT_BE_NULL)
    @Email(message = ValidationMessage.INVALID_EMAIL)
    private String email;

    @JsonProperty("password")
    @NotEmpty(message = ValidationMessage.PASSWORD_CAN_NOT_BE_EMPTY)
    @NotNull(message = ValidationMessage.PASSWORD_CAN_NOT_BE_NULL)
    @Min(value = 8, message = ValidationMessage.INVALID_PASSWORD)
    private String password;

    @JsonProperty("role_name")
    @NotEmpty(message = ValidationMessage.ROLE_CAN_NOT_BE_EMPTY)
    @NotNull(message = ValidationMessage.ROLE_CAN_NOT_BE_NULL)
    private String roleName;

}
