package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;

    // DB와 JPA를 통해 자동적으로 AutoIncrement 되게 했던 방식을 직접 쳐보니 아이러니하다.
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    // Optional을 통한 NPE 방지
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    // 출퇴근 때, 공부하는 것들 중에 기본적인 stream도 잘 모르기에 공부했는데 실전에서 써먹을 정도로 많이 써봐야겠다.
    // findByName도 JPA로 간단하게 쓰던 것을 내부적으로 어떻게 동작하는지 직접 구현해보니 더 신기하다.
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    // findAll도 마찬가지
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
}
