package edu.pnu.machine;


public class CoffeeMaker {
	//private EspressoMachine espressoMachine;
	private CoffeeMachine coffeeMachine;
	
//	public CoffeeMaker() {
//		espressoMachine = new EspressoMachine();
//	}
	
	public void setCoffeeMachine(CoffeeMachine coffeeMachine) {
		this.coffeeMachine = coffeeMachine;
	}
	

	public void makeCoffee() {
		System.out.println(coffeeMachine.brew());
	}
	

}
