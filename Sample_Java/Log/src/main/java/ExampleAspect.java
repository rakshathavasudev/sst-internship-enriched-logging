import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;


@Aspect
public class ExampleAspect {
	
	
	@Around("@annotation(Timed)")
		
	public Object ExamleAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis();
        
        System.out.println("Called " + joinPoint.getSignature().getName() + " method with arguments " +joinPoint.getArgs()[0] + " and " + joinPoint.getArgs()[1] );
        Logger logger = Logger.getLogger(Main.class);
        
        String sec = "" + executionTime%100;
		ArrayList<Object> Attributes = new ArrayList<Object>();
		ArrayList<String> Attributetype = new ArrayList<String>();
		String classname = "" + joinPoint.getSignature().getDeclaringType(); 
		String method = "" +  joinPoint.getSignature().getName();
		
		
//		String callclassName = ((Throwable) joinPoint).getStackTrace()[0].getClassName();
		String callingclass = "Main";
//		System.out.println(callclassName);
        LogMessage logmessage = new LogMessage(sec,classname,method,callingclass,Attributes,Attributetype) ;
        int temp=logmessage.Attributes.size();
        Jsonconvert jsonconv = new Jsonconvert();
        String log = jsonconv.convert(logmessage.timestamp(),logmessage.classname(),
                logmessage.methodname(),logmessage.callingclassname(),logmessage.attributes(),logmessage.attributetypes(),temp);
        System.out.println("----Appending Log details to file----");
        logger.info(log);
        

        return proceed;
    }


}
