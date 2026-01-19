package com.daniil.swimcoach.swimaicoach.service.impl;

import com.daniil.swimcoach.swimaicoach.dto.UserLoginRequestDto;
import com.daniil.swimcoach.swimaicoach.dto.UserLoginResponseDto;
import com.daniil.swimcoach.swimaicoach.dto.UserRegistrationRequestDto;
import com.daniil.swimcoach.swimaicoach.dto.UserResponseDto;
import com.daniil.swimcoach.swimaicoach.exception.AuthenticationException;
import com.daniil.swimcoach.swimaicoach.model.User;
import com.daniil.swimcoach.swimaicoach.security.JwtUtil;
import com.daniil.swimcoach.swimaicoach.service.AuthService;
import com.daniil.swimcoach.swimaicoach.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto) {
        return userService.register(requestDto);
    }

    @Override
    public UserLoginResponseDto login(UserLoginRequestDto requestDto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDto.getEmail(),
                            requestDto.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("Invalid email or password");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(requestDto.getEmail());
        String token = jwtUtil.generateToken(userDetails);

        userService.updateLastLogin((User) userDetails);

        UserResponseDto userDto = userService.getProfile((User) userDetails);

        return UserLoginResponseDto.builder()
                .token(token)
                .user(userDto)
                .build();
    }
}
