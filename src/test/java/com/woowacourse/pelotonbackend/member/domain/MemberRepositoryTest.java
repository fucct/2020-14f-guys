package com.woowacourse.pelotonbackend.member.domain;

import static com.woowacourse.pelotonbackend.member.domain.MemberFixture.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;

import com.woowacourse.pelotonbackend.DataInitializeExecutionListener;
import com.woowacourse.pelotonbackend.vo.ImageUrl;

@SpringBootTest
@TestExecutionListeners(
    listeners = DataInitializeExecutionListener.class,
    mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("회원을 저장한다.")
    @Test
    void saveMember() {
        final Member member = MemberFixture.createWithoutId(KAKAO_ID, EMAIL, NAME);

        final Member persistMember = memberRepository.save(member);

        assertAll(
            () -> assertThat(persistMember).isEqualToIgnoringNullFields(member),
            () -> assertThat(persistMember.getId()).isNotNull(),
            () -> assertThat(persistMember.getCreatedAt()).isNotNull(),
            () -> assertThat(persistMember.getUpdatedAt()).isNotNull()
        );
    }

    @DisplayName("Profile이 null인 회원을 저장하면 예외가 발생한다.")
    @Test
    void saveMemberWithNullProfile_ThrowsException() {
        final Member member = createWithoutId(KAKAO_ID, EMAIL, NAME)
            .toBuilder()
            .profile(null)
            .build();

        assertThatThrownBy(() -> memberRepository.save(member))
            .isInstanceOf(ConstraintViolationException.class)
            .hasMessageContaining("profile:");
    }

    @DisplayName("Profile image의 url이 null인 회원을 저장하면 예외가 발생한다.")
    @Test
    void saveMemberWithNullProfileUrl_ThrowsException() {
        final Member member = createWithoutId(KAKAO_ID, EMAIL, NAME)
            .toBuilder()
            .profile(new ImageUrl(null))
            .build();

        assertThatThrownBy(() -> memberRepository.save(member))
            .isInstanceOf(ConstraintViolationException.class)
            .hasMessageContaining("profile.baseImageUrl:");
    }

    @DisplayName("Email이 null인 회원을 정상적으로 저장한다.")
    @Test
    void saveMemberWithNullEmail() {
        final Member member = createWithoutId(KAKAO_ID, null, NAME);

        final Member persist = memberRepository.save(member);

        assertThat(persist).isEqualToIgnoringGivenFields(member, "id", "email", "createdAt", "updatedAt");
        assertThat(persist.getEmail()).isNull();
    }

    @DisplayName("모든 회원들을 리스트로 반환한다.")
    @Test
    void findAll() {
        final Member member = MemberFixture.createWithoutId(KAKAO_ID, EMAIL, NAME);
        final Member otherMember = MemberFixture.createWithoutId(KAKAO_ID2, EMAIL2, NAME2);
        final Member savedMember = memberRepository.save(member);
        final Member savedOtherMember = memberRepository.save(otherMember);

        final List<Member> members = memberRepository.findAll();

        assertAll(
            () -> assertThat(members).hasSize(2),
            () -> assertThat(members.get(0)).isEqualTo(savedMember),
            () -> assertThat(members.get(1)).isEqualTo(savedOtherMember)
        );
    }

    @DisplayName("아이디에 해당하는 회원들을 찾는다.")
    @Test
    void findAllById() {
        final Member member1 = memberRepository.save(createWithoutId(KAKAO_ID, EMAIL, NAME));
        final Member member2 = memberRepository.save(createWithoutId(KAKAO_ID2, EMAIL2, NAME2));
        final Member member3 = memberRepository.save(createWithoutId(KAKAO_ID3, EMAIL3, NAME3));
        final List<Long> ids = Arrays.asList(member1.getId(), member2.getId(), member3.getId());

        final List<Member> members = memberRepository.findAllById(ids);

        final List<Long> membersIds = members.stream()
            .map(Member::getId)
            .collect(Collectors.toList());
        assertThat(membersIds).isEqualTo(ids);
    }

    @DisplayName("카카오 ID로 회원을 조회한다.")
    @Test
    void findByKakaoId() {
        final Member member = memberRepository.save(createWithoutId(KAKAO_ID, EMAIL, NAME));
        final Member persistMember = memberRepository.findByKakaoId(member.getKakaoId())
            .orElseThrow(AssertionError::new);

        assertThat(persistMember).isEqualToIgnoringGivenFields(member, "createdAt", "updatedAt");
    }
}
