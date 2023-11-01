package toyproject.springmvcboard.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {
    //toyproject.springmvcboard 패키지 하위에 있는 모든 것에 적용
    @Around("execution(* toyproject.springmvcboard..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        //어떤 메소드를 호출하는지 확인
        System.out.println("START: " + joinPoint.toString());
        try {
            //다음 메소드로 진행
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }


    }
}
