package waterworks.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class ElapsedTimeAspect {
    private final Log log = LogFactory.getLog(getClass());

    @Around("within(waterworks..*)")
    public Object doStopWatch(ProceedingJoinPoint pjp) {
        StopWatch stopWatch = new StopWatch();
        Object retVal = null;
        try {
            stopWatch.start();

            retVal = pjp.proceed();

        } catch(Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            stopWatch.stop();

            //[class name].[method name] [속도]ms
            log.info(String.format("%s.%s %dms", pjp.getTarget().getClass().getName(), pjp.getSignature().getName(), stopWatch.getTotalTimeMillis()));
        }
        return retVal;
    }
}
