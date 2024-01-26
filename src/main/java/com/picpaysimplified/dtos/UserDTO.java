package com.picpaysimplified.dtos;

import com.picpaysimplified.domain.user.UserType;

import java.math.BigDecimal;

public record UserDTO(String firsName, String lastName, String document, String email, BigDecimal balance, String password, UserType userType) {
}
