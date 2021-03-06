package com.woowacourse.pelotonbackend.member.presentation;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.woowacourse.pelotonbackend.member.application.LoginService;
import com.woowacourse.pelotonbackend.support.dto.JwtTokenResponse;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/login")
@RequiredArgsConstructor
@RestController
public class LoginController {
    private final LoginService loginService;

    @GetMapping("/token")
    public ResponseEntity<Void> redirectTokenPage(@RequestParam final String code,
        final HttpServletResponse response) throws IOException {
        final String redirectUrl = loginService.createJwtTokenUrl(code);
        response.sendRedirect(redirectUrl);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/check")
    public ResponseEntity<JwtTokenResponse> loginCheck(@RequestParam(value = "access_token", required = false) final String token,
        @RequestParam final boolean success, @RequestParam(value="is_created") final boolean isCreated) {
        if (success) {
            return ResponseEntity.ok(JwtTokenResponse.of(token, isCreated));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
