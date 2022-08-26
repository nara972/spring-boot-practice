package com.nara.security.controller;

import com.nara.security.dto.UserDto;
import com.nara.security.model.User;
import com.nara.security.security.UserDetailsImpl;
import com.nara.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //회원가입 페이지 이동
    @GetMapping("/user/signup")
    public String signupForm(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        try {
            User user = userDetails.getUser();
            model.addAttribute("user", user);
        }catch (NullPointerException e){
            return "signup";
        }
        return "signup";
    }

    //회원가입
    @ResponseBody
    @PostMapping("/user/signup")
    public User signUp(@RequestBody UserDto userDto) {

        User user = userService.signup(userDto);
        return user;
    }

    //로그인 페이지 이동
    @GetMapping("/user/login")
    public String loginForm(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        try {
            User user = userDetails.getUser();
            model.addAttribute("user", user);
        }catch (NullPointerException e){
            model.addAttribute("user", "");
            return "login";
        }
        return "login";
    }

    //시큐리티홀더에 저장된 로그인 유저 정보 꺼내보기
    @GetMapping("/holder")
    @ResponseBody
    public String holder(HttpSession httpSession) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return httpSession.getId();

//        return userDetails.getUsername();
    }
}