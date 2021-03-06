package com.woowacourse.pelotonbackend.race.presentation.dto;

import static com.woowacourse.pelotonbackend.race.domain.RaceFixture.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.woowacourse.pelotonbackend.race.domain.RaceFixture;

class RaceRetrieveResponseTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @DisplayName("RaceRetrieveResponse가 올바르게 Serialize되는 지 확인한다.")
    @Test
    void RaceRetrieveResponseTest() throws JsonProcessingException {
        final String responseBody = "{"
            + "\"id\":1,"
            + "\"title\":\"14층 녀석들 기상 레이스\","
            + "\"description\":\"아침 6시에 일어나보자!\","
            + "\"thumbnail\":\"https://lh3.googleusercontent.com/5EfQBHDb47tchiART6U6yk3yYS9qBYr6VUssB5wHE1AgavqV5E2SSuzyiNkc7UgVng\","
            + "\"certification_example\":\"https://lh3.googleusercontent.com/5EfQBHDb47tchiART6U6yk3yYS9qBYr6VUssB5wHE1AgavqV5E2SSuzyiNkc7UgVng\","
            + "\"race_duration\":{"
            + "\"start_date\":\""+TEST_START_TIME.toString()+"\","
            + "\"end_date\":\""+TEST_END_TIME.toString()+"\""
            + "},"
            + "\"category\":\"TIME\","
            + "\"entrance_fee\":\"20000\""
            + "}";

        assertThat(objectMapper.writeValueAsString(RaceFixture.retrieveResponse())).isEqualTo(responseBody);
    }
}
