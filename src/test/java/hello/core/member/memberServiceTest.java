package hello.core.member;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class memberServiceTest {

    MemberService memberService;
    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }
    @Test
    void join(){
        //given(주어진 상황)
        Member member = new Member(1L, "memberA", Grade.VIP);
        //when(이런 상황일 때)
        memberService.join(member);
        Member findMember = memberService.findMember(1L);
        //then(이렇게 된다)
        Assertions.assertThat(member).isEqualTo(findMember);

    }
}
