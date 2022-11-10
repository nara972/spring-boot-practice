package com.nara.spring;

import com.nara.spring.discount.DiscountPolicy;
import com.nara.spring.discount.FixDiscountPolicy;
import com.nara.spring.discount.RateDiscountPolicy;
import com.nara.spring.member.MemberRepository;
import com.nara.spring.member.MemberService;
import com.nara.spring.member.MemberServiceImpl;
import com.nara.spring.member.MemoryMemberRepository;
import com.nara.spring.order.OrderService;
import com.nara.spring.order.OrderServiceImpl;

/*애플리케이션의 실제 동작에 필요한 구현 객체를 생성한다.
* 생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해서 주입(연결)해준다.*/
public class AppConfig {


    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(),discountPolicy());
    }

    public DiscountPolicy discountPolicy(){
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
    
}
