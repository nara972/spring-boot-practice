package com.nara.spring.order;

import com.nara.spring.discount.DiscountPolicy;
import com.nara.spring.discount.FixDiscountPolicy;
import com.nara.spring.discount.RateDiscountPolicy;
import com.nara.spring.member.Member;
import com.nara.spring.member.MemberRepository;
import com.nara.spring.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    /*인터페이스에만 의존하도록 설계와 코드를 변경
    * but, 구현체 없이 어떻게 코드를 실행?
    * ->클라이언트인 'OrderServiceImpl'에 'DiscountPolicy'의 구현 객체를 대신 생성하고 주입*/
    //private DiscountPolicy discountPolicy;

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy){
        this.memberRepository=memberRepository;
        this.discountPolicy=discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice=discountPolicy.discount(member,itemPrice);

        return new Order(memberId, itemName, itemPrice,discountPrice);

    }

}
