package hello.hellospring.repository;

import hello.hellospring.domain.Member;
//import org.junit.jupiter.api.Assertions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 통합테스트를 할 경우, member1객체에 이미 저장된 것 때문에 단위테스트는 통과하지만 통합테스트에서 에러가 난다.
    // 따라서 아래 추가, 각 테스트는 서로 의존관계가 없도록, 순서에 상관 없도록

    // 콜백 메소드 - 아래 테스트 마다 끝나고 호출
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        //given -> ㅎㅎ .. 알곤 있는 표현 방식 추가하기
        Member member = new Member();
        member.setName("eoni");
        repository.save(member);

        //when
        Member result = repository.findById(member.getId()).get();

        //then
//        Assertions.assertEquals(result, member);
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        //given
        Member member1 = new Member();
        member1.setName("eoni1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("eoni");
        repository.save(member2);

        //when
        Member result = repository.findByName("eoni1").get();

        //then
        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){

        //given
        Member member1 = new Member();
        member1.setName("eoni1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("eoni2");
        repository.save(member2);

        //when
        List<Member> result = repository.findAll();

        //then
        Assertions.assertThat(result.size()).isEqualTo(2);
        // 강의 이외에 추가해보기
        Assertions.assertThat(result.get(0)).isEqualTo(member1);
    }
}