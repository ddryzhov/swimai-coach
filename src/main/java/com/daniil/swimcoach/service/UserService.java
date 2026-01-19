package com.daniil.swimcoach.service;

import com.daniil.swimcoach.dto.user.UpdateUserProfileRequestDto;
import com.daniil.swimcoach.dto.user.UpdateUserRoleRequestDto;
import com.daniil.swimcoach.dto.user.UserRegistrationRequestDto;
import com.daniil.swimcoach.dto.user.UserResponseDto;
import com.daniil.swimcoach.model.User;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto);

    void updateLastLogin(User user);

    UserResponseDto getProfile(User user);

    UserResponseDto updateProfile(User user, UpdateUserProfileRequestDto requestDto);

    UserResponseDto updateUserRole(Long userId, UpdateUserRoleRequestDto requestDto);
}
