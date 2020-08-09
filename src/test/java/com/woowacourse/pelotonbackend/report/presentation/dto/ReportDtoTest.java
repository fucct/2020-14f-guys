package com.woowacourse.pelotonbackend.report.presentation.dto;

import static com.woowacourse.pelotonbackend.race.domain.RaceFixture.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.woowacourse.pelotonbackend.race.domain.RaceFixture;
import com.woowacourse.pelotonbackend.race.presentation.dto.RaceCreateRequest;
import com.woowacourse.pelotonbackend.report.domain.ReportFixture;

class ReportDtoTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @DisplayName("ReportCreateRequest가 올바르게 Deserialize되는 지 확인한다.")
    @Test
    void reportCreateRequestTest() throws JsonProcessingException {
        final String requestBody = "{\n"
            + "\"report_type\":\"FAKE\",\n"
            + "\"description\":\"설명\",\n"
            + "\"report_member_id\":\"1\",\n"
            + "\"certification_id\":\"5\"\n"
            + "}";

        final ReportCreateRequest request = objectMapper.readValue(requestBody, ReportCreateRequest.class);

        assertThat(request).isEqualToComparingFieldByField(ReportFixture.createRequestContent());
    }
}
