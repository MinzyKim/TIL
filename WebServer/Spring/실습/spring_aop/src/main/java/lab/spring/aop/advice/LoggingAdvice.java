package lab.spring.aop.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class LoggingAdvice { 
	 
	public void beforeAdviceMethod(JoinPoint thisJoinPoint) {
		Class  clazz = thisJoinPoint.getTarget().getClass();
		String className = thisJoinPoint.getTarget().getClass().getSimpleName();
		String methodName = thisJoinPoint.getSignature().getName();
		// 대상 메서드에 대한 로거를 얻어 해당 로거로 현재 class, method 정보 로깅		
		System.out.println("BeforeAdvice:"+className + "." + methodName + " 핵심 메소드 호출 전에 공통 기능 수행....");
	}
	
	 
	public void afterReturningAdviceMethod(JoinPoint thisJoinPoint,	Object retVal) {
		Class  clazz = thisJoinPoint.getTarget().getClass();
		String className = thisJoinPoint.getTarget().getClass().getSimpleName();
		String methodName = thisJoinPoint.getSignature().getName();
		System.out.println("AfterReturningAdvice:"+className + "." + methodName + " 핵심 메소드 정상 수행 후 공통 기능 수행...");
		System.out.println("return value is [" + ((Integer)retVal) + "]");
	}
	
	 
	public void afterThrowingAdviceMethod(JoinPoint thisJoinPoint,	Exception exception) 
			                                                             throws Exception{
		Class  clazz = thisJoinPoint.getTarget().getClass();
		String className = thisJoinPoint.getTarget().getClass().getSimpleName();
		String methodName = thisJoinPoint.getSignature().getName();
		System.out.print("afterThrowingAdvice:"+className + "." + methodName);	
		System.out.println("핵심 메소드가 수행 중 예외사항을 반환하고 종료하는 경우 수행된다");
		System.err.println("에러가 발생:"+ exception.getMessage());
			throw new Exception("에러가 발생했습니다. ", exception);
		}
    
	 
	public void afterAdviceMethod(JoinPoint thisJoinPoint) {
		Class  clazz = thisJoinPoint.getTarget().getClass();
		String className = thisJoinPoint.getTarget().getClass().getSimpleName();
		String methodName = thisJoinPoint.getSignature().getName();
		System.out.print("AfterAdvice:"+className + "." + methodName);	
		System.out.println("핵심 로직 메서드  정상 종료와 예외 발생 경우를 모두 처리 하는 Advice");
		}
	
	 
	public Object aroundAdviceMethod(ProceedingJoinPoint thisJoinPoint)	throws Throwable {
		Class  clazz = thisJoinPoint.getTarget().getClass();
		String className = thisJoinPoint.getTarget().getClass().getSimpleName();
		String methodName = thisJoinPoint.getSignature().getName();
		System.out.print("AroundAdvice:"+className + "." + methodName);	
		
		System.out.println("핵심 메소드 수행 전의 공통 기능 수행........");
		long time1 = System.currentTimeMillis();
		Object retVal = thisJoinPoint.proceed();//Target빈의 핵심 메소드 호출
		System.out.println("ProceedingJoinPoint executed. return value is [" + retVal + "]");
		 
		System.out.println("리턴 값 변경 ==>  [" + ((Integer)retVal) + "(modified)" + "]");
		long time2 = System.currentTimeMillis();
		System.out.println("핵심 메소드 수행 후의 공통 기능 수행........ Time("+ (time2 - time1) + ")");
		return retVal;
			}
	
	
	
	
	
	
	
	
	
	
	
	
	
}