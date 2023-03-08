package com.razor.genericUtility.WebAction;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
/**
 * 
 * @author Vishwanath-Naik
 *
 */
public class InteractionUtility {
	private Actions act;
	public InteractionUtility(WebDriver driver)
	{
		act= new Actions(driver);
	}
	/**
	 * This method is used to perform mouse hover action
	 * @param element
	 */
	public void mouseHover(WebElement element) {
		act.moveToElement(element).perform();
	}
	/**
	 * This method is used to perform double click action
	 * @param element
	 */
	public void doubleClick(WebElement element) {
		act.doubleClick(element).perform();
	}
	/**
	 * This method is used to perform double click action
	 */
	public void doubleClick() {
		act.doubleClick().perform();
	}
	/**
	 * This method is used to perform right click action
	 * @param element
	 */
	public void rightClick(WebElement element) {
		act.contextClick(element).perform();
	}
	public void rightClick() {
		act.contextClick().perform();
	}
	/**
	 * This method is used to click enter
	 */
	public void enter()
	{
		act.keyDown(Keys.ENTER).perform();
	}
	/**
	 * This method is used to perform drag and drop
	 * @param srcElement
	 * @param targetElement
	 */
	public void dragDrop(WebElement srcElement, WebElement targetElement) {
		act.dragAndDrop(srcElement, targetElement).perform();
	}
}
