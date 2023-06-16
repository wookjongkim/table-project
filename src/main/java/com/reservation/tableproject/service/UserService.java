package com.reservation.tableproject.service;

import com.reservation.tableproject.domain.partner.Partner;
import com.reservation.tableproject.domain.user.User;
import com.reservation.tableproject.domain.user.UserRepository;
import com.reservation.tableproject.dto.SignUpRequestDto;
import com.reservation.tableproject.exception.UserAlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * 해당 이메일을 가진 파트너가 있는지 조회 한후, 없다면 파트너를 DB에 저장
     * @param signUpRequestDto
     */
    public void register(SignUpRequestDto signUpRequestDto) {
        Optional<User> optionalUser = userRepository.findByEmail(signUpRequestDto.getEmail());

        if (optionalUser.isPresent()) {
            throw new UserAlreadyExistException("이미 존재하는 유저입니다.");
        }

        String encodedPassword = passwordEncoder.encode(signUpRequestDto.getPassword());

        User user = User.builder()
                .name(signUpRequestDto.getName())
                .email(signUpRequestDto.getEmail())
                .password(encodedPassword)
                .build();

        userRepository.save(user);
    }
}
