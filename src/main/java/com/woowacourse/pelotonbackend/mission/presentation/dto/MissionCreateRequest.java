package com.woowacourse.pelotonbackend.mission.presentation.dto;

import java.beans.ConstructorProperties;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.data.jdbc.core.mapping.AggregateReference;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.woowacourse.pelotonbackend.mission.domain.DateTimeDuration;
import com.woowacourse.pelotonbackend.mission.domain.Mission;
import com.woowacourse.pelotonbackend.mission.domain.MissionInstruction;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE, onConstructor_ = @ConstructorProperties({"missionDuration", "missionInstruction", "raceId"}))
@Builder
@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class MissionCreateRequest {
    @NotNull @Valid
    private final DateTimeDuration missionDuration;

    @NotNull @Valid
    private final MissionInstruction missionInstruction;

    @NotNull
    private final Long raceId;

    public Mission toMission() {
        return Mission.builder()
            .missionDuration(missionDuration)
            .missionInstruction(missionInstruction)
            .raceId(AggregateReference.to(raceId))
            .build();
    }
}
