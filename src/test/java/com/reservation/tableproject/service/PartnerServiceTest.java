package com.reservation.tableproject.service;

import com.reservation.tableproject.domain.partner.Partner;
import com.reservation.tableproject.domain.partner.PartnerRepository;
import com.reservation.tableproject.domain.user.User;
import com.reservation.tableproject.domain.user.UserRepository;
import com.reservation.tableproject.dto.SignUpRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class PartnerServiceTest {

    @Autowired
    private PartnerService partnerService;

    @MockBean
    private PartnerRepository partnerRepository;

    @MockBean
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void 파트너_회원가입(){
        //Given
        String email = "test@naver.com";
        String name = "test";
        String password = "1234";

        SignUpRequestDto signUpRequestDto = SignUpRequestDto.builder()
                .email(email)
                .name(name)
                .password(password).build();

        when(partnerRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        // when
        partnerService.register(signUpRequestDto);

        //then
        verify(partnerRepository, times(1)).save(any(Partner.class));
    }
}