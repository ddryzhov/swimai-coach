package com.daniil.swimcoach.service;

import com.daniil.swimcoach.dto.user.UserLoginRequestDto;
import com.daniil.swimcoach.dto.user.UserLoginResponseDto;
import com.daniil.swimcoach.dto.user.UserRegistrationRequestDto;
import com.daniil.swimcoach.dto.user.UserResponseDto;

public interface AuthService {
    UserResponseDto register(UserRegistrationRequestDto requestDto);

    UserLoginResponseDto login(UserLoginRequestDto requestDto);
}
