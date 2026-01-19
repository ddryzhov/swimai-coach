package com.daniil.swimcoach.repository;

import com.daniil.swimcoach.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}
