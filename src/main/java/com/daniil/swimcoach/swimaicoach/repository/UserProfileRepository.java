package com.daniil.swimcoach.swimaicoach.repository;

import com.daniil.swimcoach.swimaicoach.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}
