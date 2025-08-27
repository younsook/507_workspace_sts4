package edu.pnu.machine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;


@Component
public class CoffeeMaker {
	
	@Autowired  //객체가 자동으로 DI를 받는 링크 어노테이션 
	private CoffeeMachine coffeeMachine;
	
	@PostConstruct
	public void makeCoffee() {
		 System.out.println(coffeeMachine.brew());
	}
}


