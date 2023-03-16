package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{
    /* 역할과 구현을 잘 분리하고, 인터페이스와 구현 객체도 분리한 상태이다.
    * 스프링 기능을 활용하지 않고서는 OCP, DIP 라는 객체지향 설계 원칙은 준수되지 못했다.
    * 지금 클라이언트인 OrderServiceImpl은 RateDiscountPolicy라는 구현클래스에도 함께 의존하고 있는 상태이다.
    * 그래서 구현 클래스를 변경하면 그 클래스가 의존하고있는 다른 클래스도 변경해야하는 상황이다.
    * 서로 다른 책임을 의존하고 있는 클래스가 2개 이상 가지고 있는것이다.
    */

    /* 해결방안
    * 이 문제를 해결하려면 누군가가 구현 객체의 클라이언트인 객체에게
    * 대신 구현 객체를 생성하고 주입해주어야 한다.
    * */
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
