package com.daniil.swimcoach.swimaicoach.service.impl;

import com.daniil.swimcoach.swimaicoach.dto.UserRegistrationRequestDto;
import com.daniil.swimcoach.swimaicoach.dto.UserResponseDto;
import com.daniil.swimcoach.swimaicoach.exception.RegistrationException;
import com.daniil.swimcoach.swimaicoach.mapper.UserMapper;
import com.daniil.swimcoach.swimaicoach.model.Role;
import com.daniil.swimcoach.swimaicoach.model.User;
import com.daniil.swimcoach.swimaicoach.model.enums.RoleName;
import com.daniil.swimcoach.swimaicoach.repository.RoleRepository;
import com.daniil.swimcoach.swimaicoach.repository.UserRepository;
import com.daniil.swimcoach.swimaicoach.service.UserService;
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
