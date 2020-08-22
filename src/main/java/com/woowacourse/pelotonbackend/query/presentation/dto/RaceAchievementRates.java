package com.woowacourse.pelotonbackend.query.presentation.dto;

import java.beans.ConstructorProperties;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.woowacourse.pelotonbackend.certification.domain.Certification;
import com.woowacourse.pelotonbackend.common.exception.MissionCountInvalidException;
import com.woowacourse.pelotonbackend.member.domain.Member;
import com.woowacourse.pelotonbackend.mission.domain.Mission;
import com.woowacourse.pelotonbackend.race.domain.Race;
import com.woowacourse.pelotonbackend.rider.domain.Rider;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE, onConstructor_ = @ConstructorProperties({"raceId", "raceTitle",
    "totalMissionCount", "raceAchievementRates"}))
@Builder
@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RaceAchievementRates {
    private final Long raceId;
    private final String raceTitle;
    private final int totalMissionCount;
    private final List<RaceAchievementRate> raceAchievementRates;

    public static RaceAchievementRates create(final Race race, final List<Rider> riders, final List<Mission> missions,
        final List<Certification> certifications, final List<Member> members) {

        final int totalMissionCount = getTotalMissionCount(race, missions);

        final List<RaceAchievementRate> result = riders.stream()
            .map(rider -> RaceAchievementRate.of(rider, members, certifications, totalMissionCount))
            .collect(Collectors.toList());

        return RaceAchievementRates.builder()
            .raceId(race.getId())
            .raceTitle(race.getTitle())
            .totalMissionCount(totalMissionCount)
            .raceAchievementRates(result)
            .build();
    }

    private static int getTotalMissionCount(final Race race, final List<Mission> missions) {
        final int totalMissionCount = missions.size();

        if (totalMissionCount == 0) {
            throw new MissionCountInvalidException(race.getId());
        }
        return totalMissionCount;
    }
}
