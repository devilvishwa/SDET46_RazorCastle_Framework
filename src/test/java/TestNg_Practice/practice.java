package TestNg_Practice;

import org.testng.annotations.Test;

public class practice {
	
	@Test(groups = "sanity")
	public void test() {
		System.out.println("welcome home");
	}
	
	@Test(groups = {"regression","major"})
	public void test2() {
		System.out.println("dinner");
	}
	
	@Test(groups = {"regression","minor"})
	public void test3() {
		System.out.println("good night");
	}
}
