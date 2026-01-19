package com.daniil.swimcoach.mapper;

import com.daniil.swimcoach.dto.user.UpdateUserProfileRequestDto;
import com.daniil.swimcoach.dto.user.UserRegistrationRequestDto;
import com.daniil.swimcoach.dto.user.UserResponseDto;
import com.daniil.swimcoach.model.Role;
import com.daniil.swimcoach.model.User;
import com.daniil.swimcoach.model.enums.RoleName;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", source = "password")
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "enabled", constant = "true")
    @Mapping(target = "lastLogin", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "roles", ignore = true)
    User toEntity(UserRegistrationRequestDto dto);

    @Mapping(target = "roles", expression = "java(extractRoleNames(user.getRoles()))")
    UserResponseDto toDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "lastLogin", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "roles", ignore = true)
    void updateEntity(@MappingTarget User user, UpdateUserProfileRequestDto dto);

    default Set<RoleName> extractRoleNames(Set<Role> roles) {
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
    }
}
