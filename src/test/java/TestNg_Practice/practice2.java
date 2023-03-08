package TestNg_Practice;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class practice2 {
	
	@AfterMethod
	public void test1() {
		System.out.println("after method");
	}
	@AfterClass
	public void test2() {
		System.out.println("after class");
	}
	@AfterTest
	public void test3() {
		System.out.println("after test");
	}
	@AfterSuite
	public void test4() {
		System.out.println("after suite");
	}
	@Test
	public void test5() {
		System.out.println("Test");
	}
	@BeforeSuite
	public void test6() {
		System.out.println("before suite");
	}
	@BeforeTest
	public void test7() {
		System.out.println("before test");
	}
	@BeforeClass
	public void test8() {
		System.out.println("before class");
	}
	@BeforeMethod
	public void test9() {
		System.out.println("before method");
	}

}
