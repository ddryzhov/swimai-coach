package com.daniil.swimcoach.swimaicoach.service;

import com.daniil.swimcoach.swimaicoach.dto.user.UserLoginRequestDto;
import com.daniil.swimcoach.swimaicoach.dto.user.UserLoginResponseDto;
import com.daniil.swimcoach.swimaicoach.dto.user.UserRegistrationRequestDto;
import com.daniil.swimcoach.swimaicoach.dto.user.UserResponseDto;

public interface AuthService {
    UserResponseDto register(UserRegistrationRequestDto requestDto);

    UserLoginResponseDto login(UserLoginRequestDto requestDto);
}
