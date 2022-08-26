package com.nara.security.service;

import com.nara.security.dto.UserDto;
import com.nara.security.exception.CustomException;
import com.nara.security.exception.ErrorCode;
import com.nara.security.model.User;
import com.nara.security.model.UserRoleEnum;
import com.nara.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private static final String ADMIN_PW = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";


    public User signup(UserDto userDto) {
        String email = userDto.getEmail();

        // 회원 ID 중복 확인
        Optional<User> found = userRepository.findByEmail(email);
        if (found.isPresent()) {
            throw new CustomException(ErrorCode.SAME_USER);
        }

        String nickname = userDto.getNickname();

        //패스워드 암호화
        String password = passwordEncoder.encode(userDto.getPassword());

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.ROLE_MEMBER;
        //true면 == 관리자이면
        //boolean 타입의 getter는 is를 붙인다
        if (userDto.isAdmin()) {
            if (!userDto.getAdminToken().equals(ADMIN_PW)) {
                throw new CustomException(ErrorCode.ADMIN_TOKEN);
            }
            //role을 admin으로 바꿔준다
            role = UserRoleEnum.ROLE_ADMIN;
        }

        User user = new User(email, nickname, password, role);
        userRepository.save(user);
        return user;
    }
}