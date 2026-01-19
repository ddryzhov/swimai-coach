package com.daniil.swimcoach.swimaicoach.service;

import com.daniil.swimcoach.swimaicoach.dto.user.UpdateUserProfileRequestDto;
import com.daniil.swimcoach.swimaicoach.dto.user.UpdateUserRoleRequestDto;
import com.daniil.swimcoach.swimaicoach.dto.user.UserRegistrationRequestDto;
import com.daniil.swimcoach.swimaicoach.dto.user.UserResponseDto;
import com.daniil.swimcoach.swimaicoach.model.User;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto);

    void updateLastLogin(User user);

    UserResponseDto getProfile(User user);

    UserResponseDto updateProfile(User user, UpdateUserProfileRequestDto requestDto);

    UserResponseDto updateUserRole(Long userId, UpdateUserRoleRequestDto requestDto);
}
