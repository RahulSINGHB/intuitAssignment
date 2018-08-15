package com.intuit.app;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.intuit.model.Bank;

@SpringBootApplication
@ComponentScan(basePackages="com.intuit")

@EnableAspectJAutoProxy
@CrossOrigin(maxAge=4000)
public class App implements CommandLineRunner {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	@Override
    public void run(String... args) throws Exception {
		mongoTemplate.dropCollection(Bank.class);
		System.out.println("#################### init data ####################");
		Bank bank1 = new Bank("BOI", "boi@gmail.com", "+91 9078563412");
		Bank bank2 = new Bank("State Bank Of India", "sbi@gmail.com", "+91 45678765567");
		mongoTemplate.save(bank1);
		mongoTemplate.save(bank2);
	}
}