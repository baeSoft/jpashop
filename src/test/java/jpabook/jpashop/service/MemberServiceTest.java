package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)    //@RunWith, @SpringBootTest가 있어야 스프링이랑 연결해서 실제로 스프링부트를 올려서 테스트를 할 수 있다.
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;


    @Test
    public void join() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("bae");

        //when
        Long saveId = memberService.join(member1);

        //then
        assertEquals(member1, memberRepository.findOne(saveId));
    }

    @Test
    public void duplicateMemberException() throws Exception{
        //given
        Member member2 = new Member();
        member2.setName("bae");

        Member member3 = new Member();
        member3.setName("bae");

        //when
        memberService.join(member2);
        try {
            memberService.join(member3);    //예외가 발생해야 한다.
        }catch (IllegalStateException e){
            return;
        }


        //then
        fail("예외가 발생해야 한다.");
    }

    @Test
    public void findOne() {
    }
}