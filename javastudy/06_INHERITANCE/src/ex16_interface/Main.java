package ex16_interface;

public class Main {

	public static void main(String[] args) {
		
		
		
		Dog dog = new Dog("백구");
		Cat cat = new Cat("냥냥이");
		Snack snake = new Snack("낼름이");
		
		Person person = new Person();
		person.feedFood(dog, "개껌");      // 백구에게 개껌주기		
		
		person.feedFood(cat, "츄르");      // 냥냥이에게 츄르주기
	
		person.feedFood(snake, "쥐");      // 낼름이에게 쥐주기		
		
		
		person.walk(dog); // 백구와 산책
		person.walk(cat); // 백구와 산책
	//	person.walk(snake); // 백구와 산책
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
