package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

//@Service
public class MemberService {

    // 강의 초기라 DI 주입을 안하는 것 같다 -> 하군
    //    private final MemberRepository memberRepository = new MemoryMemberRepository();

    //    아래 new로 생성한 객체는 MemberServiceTest에서 생선한 객체와 동일한 객체가 아님 --> DI!! 의존성 주입 ㅎㅎ
    //    MemoryMemberRepository repository = new MemoryMemberRepository();
    //    외부에서 주입하도록 바꿔줌 -> 공부할 때, 많이 본 생성자 주입, 필드 주입, @AutoWired 순환참조 등등 뒤에 나오겠다
    private final MemberRepository memberRepository;
//    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     * @param member
     */
    public Long join(Member member) {
//         중복 이름 회원 X
//         Optional을 반환하는 건 권장하진 않음
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });
        
        // AOP 예제
        long startTime = System.currentTimeMillis();

        try {
            validateDuplicateMember(member);
            memberRepository.save(member);
            return member.getId();
        } finally {
            long endTime = System.currentTimeMillis();
            long time = endTime - startTime;
            System.out.println("join = " + time +"ms");
        }

    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 회원 조회
     * @param memberId
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

}
