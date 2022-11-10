package com.nara.spring.discount;

import com.nara.spring.member.Grade;
import com.nara.spring.member.Member;

public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent= 10;


    @Override
    public int discount(Member member, int price) {
        if(member.getGrade()== Grade.VIP){
            return price * discountPercent/100;
        }else {
            return 0;
        }
    }
}
