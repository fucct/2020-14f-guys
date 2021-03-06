package com.woowacourse.pelotonbackend.member.application;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woowacourse.pelotonbackend.infra.login.LoginAPIService;
import com.woowacourse.pelotonbackend.infra.login.dto.KakaoTokenResponse;
import com.woowacourse.pelotonbackend.infra.login.dto.KakaoUserResponse;
import com.woowacourse.pelotonbackend.member.domain.Role;
import com.woowacourse.pelotonbackend.member.presentation.dto.MemberCreateRequest;
import com.woowacourse.pelotonbackend.member.presentation.dto.MemberResponse;
import com.woowacourse.pelotonbackend.support.JwtTokenProvider;
import com.woowacourse.pelotonbackend.support.RandomGenerator;
import com.woowacourse.pelotonbackend.support.dto.JwtTokenResponse;
import com.woowacourse.pelotonbackend.vo.Cash;
import com.woowacourse.pelotonbackend.vo.ImageUrl;

@Service
@Transactional
public class LoginService {
    private static final boolean NOT_CREATED = false;
    private static final boolean CREATED = true;

    private final LoginAPIService<KakaoTokenResponse, KakaoUserResponse> kakaoAPIService;
    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;
    private final RandomGenerator randomGenerator;
    private final String basicProfileUrl;

    public LoginService(
        final LoginAPIService<KakaoTokenResponse, KakaoUserResponse> kakaoAPIService,
        final MemberService memberService,
        final JwtTokenProvider jwtTokenProvider,
        final RandomGenerator randomGenerator,
        @Value("${cloud.aws.s3.basic-profile-url}") final String basicProfileUrl) {
        this.kakaoAPIService = kakaoAPIService;
        this.memberService = memberService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.randomGenerator = randomGenerator;
        this.basicProfileUrl = basicProfileUrl;
    }

    public String createJwtTokenUrl(final String code) {
        return kakaoAPIService.createTokenUrl(createJwtToken(code));
    }

    private JwtTokenResponse createJwtToken(final String code) {
        final KakaoTokenResponse kakaoTokenResponse = kakaoAPIService.fetchOAuthToken(code).block();
        final KakaoUserResponse kakaoUserResponse = kakaoAPIService.fetchUserInfo(kakaoTokenResponse).block();
        if (memberService.existsByKakaoId(kakaoUserResponse.getId())) {
            final MemberResponse memberResponse = memberService.findByKakaoId(kakaoUserResponse.getId());
            return JwtTokenResponse.of(jwtTokenProvider.createToken(memberResponse.getKakaoId().toString()),
                NOT_CREATED);
        }

        final MemberCreateRequest memberCreateRequest = MemberCreateRequest.builder()
            .email(kakaoUserResponse.getEmail())
            .cash(Cash.initial())
            .kakaoId(kakaoUserResponse.getId())
            .name(kakaoUserResponse.getNickname() + randomGenerator.getRandomString())
            .profile(new ImageUrl(Optional.ofNullable(kakaoUserResponse.getProfileImage()).orElse(basicProfileUrl)))
            .role(Role.MEMBER)
            .build();

        return JwtTokenResponse.of(
            jwtTokenProvider.createToken(
                memberService.createMember(memberCreateRequest).getKakaoId().toString()), CREATED);
    }
}
