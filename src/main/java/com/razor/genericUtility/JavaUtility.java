package com.razor.genericUtility;

import java.util.Base64;
import java.util.Random;
/**
 * 
 * @author Vishwanath-Naik
 *
 */
public class JavaUtility {
	/**
	 * 
	 * @param boundryValue
	 * @return
	 */
	public int getRandomNumber(int boundryValue) {
		return new Random().nextInt(boundryValue);
		
	}
	/**
	 * This method is used to decode the credentials
	 * @param s
	 * @return
	 */
	public String decode(String propertyData)
	{
		return new String (Base64.getDecoder().decode(propertyData.getBytes()));
	}
	/**
	 * this method is used to encode the credentials
	 * @param s
	 * @return
	 */
	public String encode(String propertyData)
	{
		return new String(Base64.getEncoder().encode(propertyData.getBytes()));
	}
	
}
