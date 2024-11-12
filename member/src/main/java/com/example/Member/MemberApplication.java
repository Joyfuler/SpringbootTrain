package com.example.Member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class MemberApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemberApplication.class, args);
	}
	
//	@Bean
//	public DriverManagerDataSource dataSource()
//	{
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		
//		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
//		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
//		dataSource.setUsername("MINKOOK");
//		dataSource.setPassword("a1234");		
//		
//		return dataSource;
//	}
	
//	@Bean
//	public CommandLineRunner test (JdbcMemberRepository mr)
//	{
//		return (args) -> { 
////			Member m = new Member("1q1111","pasdfw","naasdfme");
////			mr.insert(m);
////			for(Member m : mr.findAll())
////			{
////				System.out.println(m);
////			}
////			System.out.println(mr.findById(1l));
////			Member m = new Member("newpw", "new", 1l);
////			mr.update(m);
////			mr.delete(1l);
//		};
//	}
	

}
