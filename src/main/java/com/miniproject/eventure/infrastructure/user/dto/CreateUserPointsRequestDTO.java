package com.miniproject.eventure.infrastructure.user.dto;

import com.miniproject.eventure.entity.user.User;
import com.miniproject.eventure.entity.user.UserPoints;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.OffsetDateTime;


@Data

public class CreateUserPointsRequestDTO {

    @NotNull
    private Double points;

    private String validityPeriod;

    public UserPoints toEntity(User user, OffsetDateTime expiryAt){
        UserPoints userPoints = new UserPoints();

        userPoints.setUser(user);
        userPoints.setPoints(this.points);
        userPoints.setUsedPoints(0.0);
        userPoints.setExpiredAt(expiryAt);

        return userPoints;
    }
}
