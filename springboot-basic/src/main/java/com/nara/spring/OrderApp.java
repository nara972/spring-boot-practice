package com.nara.spring;

import com.nara.spring.member.Grade;
import com.nara.spring.member.Member;
import com.nara.spring.member.MemberService;
import com.nara.spring.member.MemberServiceImpl;
import com.nara.spring.order.Order;
import com.nara.spring.order.OrderService;
import com.nara.spring.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.security.auth.callback.LanguageCallback;

public class OrderApp {

    public static void main(String[] args) {
        //MemberService memberService = new MemberServiceImpl();
        //OrderService orderService = new OrderServiceImpl();

        //AppConfig appConfig = new AppConfig();
        //MemberService memberService = appConfig.memberService();
        //OrderService orderService = appConfig.orderService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService" , OrderService.class);

        Long memberId=1L;
        Member member = new Member(memberId,"memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId,"itemA",10000);

        System.out.println("order = " +  order);
        System.out.println("order.calculatePrice =" + order.calculatePrice());
    }
}
