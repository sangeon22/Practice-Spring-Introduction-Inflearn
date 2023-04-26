package hello.hellospring;

import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {

   /* private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }*/

//    @PersistenceContext
    private EntityManager em;

    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        // 인터페이스 X 구현체 생성
        // return new MemoryMemberRepository();

        // 이 구현체만 교체해주면 된다!!!
        // return new JdbcMemberRepository(dataSource);
        // return new JdbcMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
}
