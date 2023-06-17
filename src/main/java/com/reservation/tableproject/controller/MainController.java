package com.reservation.tableproject.controller;

import com.reservation.tableproject.dto.LoginRequestDto;
import com.reservation.tableproject.dto.SignUpRequestDto;
import com.reservation.tableproject.service.PartnerService;
import com.reservation.tableproject.service.UserService;
import com.reservation.tableproject.util.CookieUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;
    private final PartnerService partnerService;

    // 해당 경로로 올 시 register.html이라는 뷰 표시
    @GetMapping("/signup")
    public String moveToSignup(){
        return "register.html";
    }

    /**
     * signUpRequestDto의 타입에 따라(ex: 유저, 파트너) 저장
     * @param signUpRequestDto
     * @return 회원가입 성공 페이지로 리다이렉
     */
    @PostMapping("/signup")
    public String register(SignUpRequestDto signUpRequestDto){
        if("user".equals(signUpRequestDto.getUserType())){
            userService.register(signUpRequestDto);
        }else{
            partnerService.register(signUpRequestDto);
        }
        return "register_complete.html";
    }

    @GetMapping("/login")
    public String moveToLoginPage(){
        return "login.html";
    }

    /**
     * 유저가 입력한 아이디 비밀번호를 바탕으로 토큰을 생성하고, 토큰을 쿠키에 포함 시킨 후 응답을 보냄
     * @param loginRequestDto
     * @param response
     * @return
     */
    @PostMapping("/login")
    public String login(LoginRequestDto loginRequestDto, HttpServletResponse response) {

        String token;
        if("user".equals(loginRequestDto.getUserType())){
            token = userService.login(loginRequestDto);
        }else{
            token = partnerService.login(loginRequestDto);
        }

        Cookie cookie = CookieUtil.generateCookie(token);
        response.addCookie(cookie);

        return "login_complete";
    }
}
