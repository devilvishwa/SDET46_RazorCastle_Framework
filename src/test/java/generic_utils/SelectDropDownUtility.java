package generic_utils;

import java.awt.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SelectDropDownUtility {
	
		
		
		public void selectByIndex(WebElement ele,int i)
		{
			Select s=new Select(ele);
	 		s.selectByIndex(i);
		
		}
		public void selectByValue(WebElement ele,String value)
		{
			Select s=new Select(ele);
	 		s.selectByValue(value);
		}
		public void selectByText(WebElement ele,String text)
		{
			Select s=new Select(ele);
	 		s.selectByVisibleText(text);
		
		}
		public void deselectByIndexText(WebElement ele,int i)
		{
			Select s=new Select(ele);
	 		s.deselectByIndex(i);
		
		}
		public void deselectByValue(WebElement ele,String value)
		{
			Select s=new Select(ele);
	 		s.deselectByValue(value);
		}
		public void deselectByText(WebElement ele,String text)
		{
			Select s=new Select(ele);
	 		s.deselectByVisibleText(text);
		
		}
		public void deselectAll(WebElement ele)
		{
			Select s=new Select(ele);
			s.deselectAll();
	 	}
		public List getOptions (WebElement ele)
		{
			Select s=new Select(ele);
			return (List) s.getOptions();
	 	}
		 
		public WebElement getFirstSelectedOption1(WebElement ele)
		{
			Select s=new Select(ele);
			return s.getFirstSelectedOption();
	 	}
		
		public List getAllSelectedOptions(WebElement ele)
		{
			Select s=new Select(ele);
			return (List) s.getAllSelectedOptions();
	 	}
		
		public WebElement getWrappedElement(WebElement ele)
		{
			Select s=new Select(ele);
			return s.getWrappedElement();
	 	}
		
		public boolean isMultiple(WebElement ele)
		{
			Select s=new Select(ele);
			return s.isMultiple();
	 	}
		public int hashCode(WebElement ele)
		{
			Select s=new Select(ele);
			return s.hashCode();
	 	}
	}

