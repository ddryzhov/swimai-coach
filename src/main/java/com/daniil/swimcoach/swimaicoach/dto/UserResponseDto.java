package com.daniil.swimcoach.swimaicoach.dto;

import com.daniil.swimcoach.swimaicoach.model.enums.RoleName;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Set<RoleName> roles;
    private LocalDateTime createdAt;
    private LocalDateTime lastLogin;
}
