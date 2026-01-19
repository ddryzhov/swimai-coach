package com.daniil.swimcoach.swimaicoach.service;

import com.daniil.swimcoach.swimaicoach.dto.UserRegistrationRequestDto;
import com.daniil.swimcoach.swimaicoach.dto.UserResponseDto;
import com.daniil.swimcoach.swimaicoach.model.User;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto);

    void updateLastLogin(User user);

    UserResponseDto getProfile(User user);
}
