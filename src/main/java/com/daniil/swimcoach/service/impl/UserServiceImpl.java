package com.daniil.swimcoach.service.impl;

import com.daniil.swimcoach.dto.user.UpdateUserProfileRequestDto;
import com.daniil.swimcoach.dto.user.UpdateUserRoleRequestDto;
import com.daniil.swimcoach.dto.user.UserRegistrationRequestDto;
import com.daniil.swimcoach.dto.user.UserResponseDto;
import com.daniil.swimcoach.exception.RegistrationException;
import com.daniil.swimcoach.mapper.UserMapper;
import com.daniil.swimcoach.model.Role;
import com.daniil.swimcoach.model.User;
import com.daniil.swimcoach.model.enums.RoleName;
import com.daniil.swimcoach.repository.RoleRepository;
import com.daniil.swimcoach.repository.UserRepository;
import com.daniil.swimcoach.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public UserResponseDto register(UserRegistrationRequestDto requestDto) {
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new RegistrationException("Email already registered");
        }

        User user = userMapper.toEntity(requestDto);
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));

        Role customerRole = roleRepository.findByName(RoleName.ROLE_FREE)
                .orElseThrow(() -> new EntityNotFoundException("Role FREE not found"));

        user.getRoles().add(customerRole);

        User saved = userRepository.save(user);
        return userMapper.toDto(saved);
    }

    @Override
    @Transactional
    public UserResponseDto updateProfile(User user, UpdateUserProfileRequestDto requestDto) {
        userMapper.updateEntity(user, requestDto);
        User updated = userRepository.save(user);
        return userMapper.toDto(updated);
    }

    @Override
    @Transactional
    public UserResponseDto updateUserRole(Long userId, UpdateUserRoleRequestDto requestDto) {
        User user = userRepository.findByIdWithRoles(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: "
                        + userId));

        Role role = roleRepository.findByName(requestDto.getRoleName())
                .orElseThrow(() -> new EntityNotFoundException("Role not found: "
                        + requestDto.getRoleName()));

        user.getRoles().clear();
        user.getRoles().add(role);

        User updated = userRepository.save(user);
        return userMapper.toDto(updated);
    }

    @Override
    @Transactional
    public void updateLastLogin(User user) {
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto getProfile(User user) {
        return userMapper.toDto(user);
    }
}
