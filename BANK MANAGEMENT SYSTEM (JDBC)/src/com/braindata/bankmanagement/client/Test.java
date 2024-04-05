package com.braindata.bankmanagement.client;

import java.util.Scanner;

import com.braindata.bankmanagement.service.Rbi;
import com.braindata.bankmanagement.serviceImpl.Sbi;

public class Test {

	public static void main(String[] args) throws Exception {
		Rbi bank = new Sbi();
		while(true)
		{
		System.out.println("WelCome To State Bank Of India \n Choose options: \n 1.Create Account \n 2.Insert Data Into Account \n 3.Display Account Details \n 4.Deposite Money \n 5.Withdrawl Money \n 6.Check Balance \n 7.Delete Account \n 8.Transaction History");
		Scanner sc = new Scanner(System.in);
		int ch = sc.nextInt();
		
		switch(ch)
		{
		case 1:
			bank.createAccount();
			break;
			
		case 2:
			bank.insertDataIntoAccount();
			break;
			
		case 3:
			System.out.println("Select : 1.Display All Account Details \n2.Display Your Account Details");
			int se=sc.nextInt();
			switch(se) {
			case 1:
				bank.displayAllDetails();
				break;
				
			case 2:
				bank.displayAccDetails();
				break;
				
			default:
				System.err.println("Invalid Choice..");
			}
			break;
			
		case 4:
			bank.depositeMoney();
			break;
			
		case 5:
			bank.withdrawal();
			break;
			
		case 6:
			bank.balanceCheck();
			break;
			
		case 7:
			bank.deleteAccount();
			break;
			
		case 8:
			bank.showLastTransactions();
			break;
			
		default:
			System.out.println("Invalid Choice..........");
			System.exit(0);
			}
		}
	}
}
