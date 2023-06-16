package com.reservation.tableproject.service;

import com.reservation.tableproject.domain.partner.Partner;
import com.reservation.tableproject.domain.partner.PartnerRepository;
import com.reservation.tableproject.dto.SignUpRequestDto;
import com.reservation.tableproject.exception.PartnerAlreadyExistException;
import com.reservation.tableproject.exception.UserAlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PartnerService {

    private final PartnerRepository partnerRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * 해당 이메일을 가진 유저가 있는지 조회 한후, 없다면 유저를 DB에 저장
     * @param signUpRequestDto
     */
    public void register(SignUpRequestDto signUpRequestDto){
        Optional<Partner> optionalPartner = partnerRepository.findByEmail(signUpRequestDto.getEmail());

        if(optionalPartner.isPresent()){
            throw new PartnerAlreadyExistException("이미 존재하는 파트너입니다.");
        }

        String encodedPassword = passwordEncoder.encode(signUpRequestDto.getPassword());

        Partner partner = Partner.builder()
                .name(signUpRequestDto.getName())
                .email(signUpRequestDto.getEmail())
                .password(encodedPassword)
                .build();

        partnerRepository.save(partner);
    }
}
