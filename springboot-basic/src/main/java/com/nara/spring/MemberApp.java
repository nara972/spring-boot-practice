package com.nara.spring;

import com.nara.spring.member.Grade;
import com.nara.spring.member.Member;
import com.nara.spring.member.MemberService;
import com.nara.spring.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {

        //MemberService memberService= new MemberServiceImpl();

        AppConfig appConfig = new AppConfig();
        MemberService memberService= appConfig.memberService();;
        Member member=new Member(1L, "memberA" , Grade.VIP);
        memberService.join(member);

        Member findMember=memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find member = "+ findMember.getName());
    }
}
