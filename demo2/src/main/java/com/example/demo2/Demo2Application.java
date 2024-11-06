package com.example.demo2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Demo2Application {

	public static void main(String[] args) {

		SpringApplication.run(Demo2Application.class, args);
	}

	// 일반적으로 직접 A a = new A(); 등으로 생성하지 않고, bean 어노테이션을 통해 객체를 리턴받는 방식으로 사용
	@Bean
	public A a(){
		return new A();
		// 실제로는 a객체를 생성한 것과 동일하게 작용한다.
	}

	@Bean
	public C c(){
		return new C();
	}


	// 만일 Bean 어노테이션을 지운다면 실행 시
	// required a bean of type ~ 등의 오류 발생. 확인한 후 Bean 어노테이션 추가할것.

	@Bean
	public CommandLineRunner run(D d){
		// 매개변수 1개 인터페이스를 오버라이드. 추상메소드 1개이므로 람다식 사용可
		// Bean 어노테이션을 선언한 후에는
		// 위에 Bean으로 만든 a와 b객체는 new를 사용하지 않고 사용할 수 있게 된다. (Bean끼리만 가능)
		// 또 같은 타입으로 Bean을 생성하는 경우에도 중복으로 에러날수 있으니 주의
		return (args) -> {
			d.mD();
		};
	}

	@Bean
	public CommandLineRunner test(A a){
		return (args) -> {
			a.mA();
		};
	}

}
