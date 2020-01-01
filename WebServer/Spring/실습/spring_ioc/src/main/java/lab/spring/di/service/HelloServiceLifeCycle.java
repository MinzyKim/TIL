package lab.spring.di.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import lab.spring.di.persist.SimpleMessage;

public class HelloServiceLifeCycle implements HelloService, BeanNameAware, BeanFactoryAware, InitializingBean, DisposableBean {
		

		private String name;
		private SimpleMessage myMessage;
		private String beanName;
		private BeanFactory beanFactory;
		
		
		public HelloServiceLifeCycle() {
			super();
			System.out.println("1. default 생성자를 이용해서 빈 인스턴스 생성");
			// TODO Auto-generated constructor stub
		}

		public void setName(String name) {
			this.name = name;
			System.out.println("2. 의존성 체크 후 property로 빈 인스턴스 주입");  
		}
	
		public void setMyMessage(SimpleMessage myMessage) {
			this.myMessage = myMessage;
			System.out.println("2. 의존성 체크 후 property로 빈 인스턴스 주입");
		}
		
		public void destroy() throws Exception {
			System.out.println("8. IoC컨테이너로부터 빈이 제거될 때 정리, 종류 수행");
		}
		
	public void custom_end() {
		System.out.println("9. IoC컨테이너로부터 빈이 제거 될 때 사용자 정의 종료");
	}
	
 public void afterPropertiesSet() throws Exception{
	 System.out.println("5. 모든 property가 설정된 후 유효성 체크등의 수행하기 위한 용도");
	 
	 
 }
 public void custom_init() {
	 System.out.println("6. 사용자 정의 초기화 메서드(스프링컨테이너에 독립적인..)");
 }
	public void setBeanFactory(BeanFactory arg0) throws BeansException {
		// TODO Auto-generated method stub
		System.out.println("4. 스프링 컨테이너 객체 주입");
		this.beanFactory=arg0;
	}

	public void setBeanName(String arg0) {
		// TODO Auto-generated method stub
		System.out.println("3. 설정 파일에서의 빈 이름을 주입");
		this.beanName=arg0;
		System.out.println("주입받은 빈 이름 :"+beanName);
	}

	public void sayHello() {
		// TODO Auto-generated method stub
		System.out.println("7. 빈의 서비스 메서드 호출");
		System.out.println(myMessage.getMessage()+"from "+name);
		
	}
}
