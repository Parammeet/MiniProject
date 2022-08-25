package com.training;

public class RandomMethod {
	
	public int AccountNumber() {
		int min=12345678;
		int max=1645677896;
		int account_no=(int)Math.floor(Math.random()*(max-min+1)+min);
//		System.out.println("Your Account number is:"+account_no);
		return account_no;
	}

}
