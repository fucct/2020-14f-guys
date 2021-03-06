package com.woowacourse.pelotonbackend.query.presentation;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.woowacourse.pelotonbackend.certification.domain.CertificationStatus;
import com.woowacourse.pelotonbackend.member.presentation.dto.MemberResponse;
import com.woowacourse.pelotonbackend.query.application.QueryService;
import com.woowacourse.pelotonbackend.query.presentation.dto.RaceAchievementRates;
import com.woowacourse.pelotonbackend.query.presentation.dto.RaceCertificationsResponse;
import com.woowacourse.pelotonbackend.query.presentation.dto.RaceDetailResponse;
import com.woowacourse.pelotonbackend.query.presentation.dto.UpcomingMissionResponses;
import com.woowacourse.pelotonbackend.race.presentation.dto.RaceResponses;
import com.woowacourse.pelotonbackend.support.annotation.LoginMember;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/queries")
@RequiredArgsConstructor
@RestController
public class QueryController {
    private final QueryService queryService;

    @GetMapping("/races")
    public ResponseEntity<RaceResponses> retrieveRacesBy(@LoginMember final MemberResponse loginMember) {
        return ResponseEntity.ok(queryService.retrieveRacesBy(loginMember));
    }

    @GetMapping("/races/{raceId}/certifications")
    public ResponseEntity<RaceCertificationsResponse> findCertificationsByRaceId(
        @PathVariable final Long raceId, final Pageable pageable) {

        return ResponseEntity.ok(queryService.findCertificationsByRaceIdAndStatus(raceId, CertificationStatus.SUCCESS, pageable));
    }

    @GetMapping("/missions/upcoming")
    public ResponseEntity<UpcomingMissionResponses> retrieveUpcomingMissionsBy(
        @LoginMember final MemberResponse loginMember) {

        return ResponseEntity.ok(queryService.retrieveUpcomingMissionsBy(loginMember));
    }

    @GetMapping("/races/{raceId}/detail")
    public ResponseEntity<RaceDetailResponse> findRaceDetail(
        @PathVariable final Long raceId) {

        return ResponseEntity.ok(queryService.findRaceDetail(raceId));
    }

    @GetMapping("/races/{raceId}/achievement")
    public ResponseEntity<RaceAchievementRates> findRaceAchievementRates(@PathVariable final Long raceId) {

        return ResponseEntity.ok(queryService.findRaceAchievement(raceId));
    }
}
