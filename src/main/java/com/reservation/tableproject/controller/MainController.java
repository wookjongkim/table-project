package com.reservation.tableproject.controller;

import com.reservation.tableproject.dto.SignUpRequestDto;
import com.reservation.tableproject.service.PartnerService;
import com.reservation.tableproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
}
