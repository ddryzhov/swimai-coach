package com.daniil.swimcoach.swimaicoach.service;

import com.daniil.swimcoach.swimaicoach.dto.UserLoginRequestDto;
import com.daniil.swimcoach.swimaicoach.dto.UserLoginResponseDto;
import com.daniil.swimcoach.swimaicoach.dto.UserRegistrationRequestDto;
import com.daniil.swimcoach.swimaicoach.dto.UserResponseDto;

public interface AuthService {
    UserResponseDto register(UserRegistrationRequestDto requestDto);

    UserLoginResponseDto login(UserLoginRequestDto requestDto);
}
