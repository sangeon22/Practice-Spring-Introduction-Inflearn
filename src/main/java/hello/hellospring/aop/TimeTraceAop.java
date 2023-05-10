package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//AOP 명시 어노테이션
@Aspect
@Component
public class TimeTraceAop {

    // 공통 관심 사항을 어디다 적용할지 타겟팅 -> Around 어노테이션 사용
    @Around("execution(* hello.hellospring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        System.out.println("START-common: " + joinPoint.toString());
        //아래는 제시어 중에 비슷한게 있어서 궁금해서 로그찍어봄
        System.out.println("START-long: " + joinPoint.toLongString());
        System.out.println("START-short: " + joinPoint.toShortString());

        try {
            // 다음 메소드로 진행
            return joinPoint.proceed();
        } finally {
            long endTime = System.currentTimeMillis();
            long time = endTime - startTime;
            System.out.println("END: " + joinPoint.toString() + " " + time + "ms");
        }


    }
}
