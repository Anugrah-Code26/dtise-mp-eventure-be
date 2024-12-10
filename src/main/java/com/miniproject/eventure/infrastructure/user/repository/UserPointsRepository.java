package com.miniproject.eventure.infrastructure.user.repository;

import com.miniproject.eventure.entity.user.UserPoints;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPointsRepository extends JpaRepository<UserPoints, Long> {
}