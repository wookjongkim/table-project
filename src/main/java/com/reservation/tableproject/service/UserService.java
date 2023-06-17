package com.reservation.tableproject.service;

import com.reservation.tableproject.domain.partner.CustomPartnerDetails;
import com.reservation.tableproject.domain.partner.Partner;
import com.reservation.tableproject.domain.user.CustomUserDetails;
import com.reservation.tableproject.domain.user.User;
import com.reservation.tableproject.domain.user.UserRepository;
import com.reservation.tableproject.dto.LoginRequestDto;
import com.reservation.tableproject.dto.SignUpRequestDto;
import com.reservation.tableproject.exception.EmailInvalidException;
import com.reservation.tableproject.exception.PasswordInvalidException;
import com.reservation.tableproject.exception.UserAlreadyExistException;
import com.reservation.tableproject.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
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

    public String login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new EmailInvalidException("존재하지 않는 이메일 형식입니다."));

        if(!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())){
            throw new PasswordInvalidException("비밀번호가 틀렸습니다.");
        }

        return JwtUtil.createToken(loginRequestDto.getEmail(), "ROLE_USER");
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저는 존재하지 않습니다: " + username));
        return new CustomUserDetails(user);
    }
}
