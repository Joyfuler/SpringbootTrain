package com.example.memo;

import lombok.Getter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.DriverManager;

@SpringBootApplication
public class MemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemoApplication.class, args);
	}

	@Primary
	@Bean // DAO로 가서 붙는다.
	public DriverManagerDataSource dataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUsername("root");
		dataSource.setPassword("12345");
		dataSource.setUrl("jdbc:mysql://localhost:3306/jangdb?useSSL=false&serverTimezone=Asia/Seoul");

		return dataSource;
	}

	@Bean
	public DriverManagerDataSource oracleDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		dataSource.setUsername("system");
		dataSource.setPassword("a1234");
		return dataSource;
	}

	@Bean
	public CommandLineRunner test(JdbcMemoRepository memoRepository){
		return (args) -> {
			System.out.println(memoRepository.getDataSource());
		};
	}
}
