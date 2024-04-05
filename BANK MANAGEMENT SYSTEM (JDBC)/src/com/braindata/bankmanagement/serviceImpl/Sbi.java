package com.braindata.bankmanagement.serviceImpl;

import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.*;
import com.braindata.bankmanagement.model.Account;
import com.braindata.bankmanagement.service.Rbi;

public class Sbi implements Rbi {
	Account ac = new Account();
	Scanner sc = new Scanner(System.in);
	Statement smt;

	@Override
	public void createAccount() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_management_system", "root",
				"root");
		String sql = "CREATE TABLE ACCOUNTS(ACCNO bigint PRIMARY KEY , NAME VARCHAR(100), MOBNO LONG,ADHARNO LONG,GENDER VARCHAR(20),AGE INT,balance bigint)";
		smt = con.createStatement();
		smt.execute(sql);
		smt.close();
		con.close();
	}

	@Override
	public void insertDataIntoAccount() throws Exception {

		// Generate 8 digit Account no. Automatically
		Random random = new Random();
		int min = 10000000;
		int max = 99999999;
		int generate_acc_no = random.nextInt(max - min + 1) + min;
		ac.setAccNo(generate_acc_no);
		System.out.println("Your Account Number is: " + ac.getAccNo());

		while (true) {
			// Validation for name not containg special characters and numbers

			String a[] = { "`", "~", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "_", "-", "+", "=", "[", "]",
					"{", "}", "|", "\\", ";", ":", "\\", "'", "\"", "<", ".", ">", "?", "/", "1", "2", "3", "4", "5",
					"6", "7", "8", "9", "0" };

			System.out.println("Enter name");
			String name = sc.next() + sc.nextLine();
			int count = 0;

			for (int i = 0; i < a.length; i++) {
				if (name.contains(a[i])) {
					count++;
				}
			}
			if (count == 0) {
				ac.setName(name);
				break;
			} else {
				System.err.println("Invalid Name \nPlease Enter Name without using special characters and numbers.");
			}
		}

		while (true) {
			System.out.println("Enter Mobile number: ");
			ac.setMobNo(sc.nextLong());
			long moNo = ac.getMobNo();
			int count = 0;
			while (moNo != 0) {
				moNo = moNo / 10;
				count++;
			}
			if (count == 10) {
				break;
			} else {
				System.out.println("Please enter a valid 10-digit mobile number");
			}
		}

		while (true) {
			System.out.println("Enter Aadhaar Number: ");
			ac.setAdharNo(sc.nextLong());
			long adNo = ac.getAdharNo();
			int count = 0;
			while (adNo != 0) {
				adNo = adNo / 10;
				count++;
			}
			if (count == 12) {
				break;
			} else {
				System.out.println("Please enter a valid 12-digit aadhaar number");
			}
		}

		while (true) {
			List<String> genderList = new ArrayList<>();
			genderList.add("Male");
			genderList.add("Female");
			genderList.add("Other");
			System.out.println("Select Gender:\n\t1.Male\n\t2.Female\n\t3.Other");
			int se = sc.nextInt();
			if (se == 1) {
				ac.setGender(genderList.get(0));
				break;
			} else if (se == 2) {
				ac.setGender(genderList.get(1));
				break;
			} else if (se == 3) {
				ac.setGender(genderList.get(2));
				break;
			} else {
				System.out.println("Invalid Choice..");
			}
		}
		while (true) {
			System.out.println("Enter Age: ");
			ac.setAge(sc.nextInt());
			if (ac.getAge() >= 18) {
				break;
			} else {
				System.out.println("Age should be greater than 18");
			}
		}
		while (true) {
			System.out.println("Deposite atleast 1000 rs at the time of Account Creation.");
			System.out.println("Enter Amount: ");
			ac.setBalance(sc.nextDouble());
			if (ac.getBalance() >= 1000) {
				break;
			} else {
				System.out.println("Please deposite 1000 or more than 1000 rs at the time of account creation..");
			}
		}
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_management_system", "root",
				"root");
		String sql = "insert into accounts values(" + ac.getAccNo() + ",'" + ac.getName() + "'," + ac.getMobNo() + ","
				+ ac.getAdharNo() + ",'" + ac.getGender() + "'," + ac.getAge() + "," + ac.getBalance() + ")";
		String dep_sql = "insert into transactions (acc_no,deposite) values(" + ac.getAccNo() + "," + ac.getBalance()
				+ ")";
		smt = con.createStatement();
		smt.execute(sql);
		smt.execute(dep_sql);
		smt.close();
		con.close();
		System.out.println("Account Created Successfully....");
	}

	@Override
	public void displayAllDetails() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_management_system", "root",
				"root");
		String sql = "SELECT * from accounts ";
		smt = con.createStatement();

		ResultSet rs = smt.executeQuery(sql);
		while (rs.next()) {
			System.out.print("Account Number: " + rs.getLong(1) + " ");
			System.out.print("Account Holder Name: " + rs.getString(2) + " ");
			System.out.print("Mobile Number: " + rs.getLong(3) + " ");
			System.out.print("Aadhaar Number: " + rs.getLong(4) + " ");
			System.out.print("Gender: " + rs.getString(5) + " ");
			System.out.print("Age: " + rs.getInt(6) + " ");
			System.out.print("Account Balance: " + rs.getDouble(7) + " ");
			System.out.println("\n");
		}
		smt.close();
		con.close();
	}

	@Override
	public void displayAccDetails() throws Exception {
		System.out.println("Enter your Account Number: ");
		long acc_no = sc.nextLong();

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_management_system", "root",
				"root");
//		String sql = "SELECT * from accounts ";
//		smt = con.createStatement();

		List<Long> accNO_List = new ArrayList<>();
		String s = "SELECT * from accounts";
		smt = con.createStatement();
		ResultSet rs = smt.executeQuery(s);
		while (rs.next()) {
			// System.out.println(rs.getLong(1));
			accNO_List.add(rs.getLong(1));
		}
		if (accNO_List.contains(acc_no)) {

			String sql = "SELECT * from accounts WHERE accno=" + acc_no + "";

			ResultSet rss = smt.executeQuery(sql);
			while (rss.next()) {
				System.out.print("Account Number: " + rss.getLong(1) + " ");
				System.out.print("Account Holder Name: " + rss.getString(2) + " ");
				System.out.print("Mobile Number: " + rss.getLong(3) + " ");
				System.out.print("Aadhaar Number: " + rss.getLong(4) + " ");
				System.out.print("Gender: " + rss.getString(5) + " ");
				System.out.print("Age: " + rss.getInt(6) + " ");
				System.out.print("Account Balance: " + rss.getDouble(7) + " ");
				System.out.println("\n");
			}
		} else {
			System.err.println("Invalid Account Number....");
		}

		smt.close();
		con.close();
	}

	@Override
	public void depositeMoney() throws Exception {

		// get account number from user
		System.out.println("Enter your Account Number: ");
		long acc_no = sc.nextLong();

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_management_system", "root",
				"root");

		// store all account number into list
		List<Long> accNO_List = new ArrayList<>();
		String s = "SELECT * from accounts";
		smt = con.createStatement();
		ResultSet rs = smt.executeQuery(s);
		while (rs.next()) {
			// System.out.println(rs.getLong(1));
			accNO_List.add(rs.getLong(1));
		}

		// check account no. is in our database or not
		if (accNO_List.contains(acc_no)) {

			// select old amount from database and store into variable
			double bal = 0;
			String select_amount = "SELECT balance from accounts where accno=" + acc_no + "";
			ResultSet rs_bal = smt.executeQuery(select_amount);
			while (rs_bal.next()) {
//				System.out.println(rs_bal.getDouble(1));
				bal = rs_bal.getDouble(1);
			}

			System.out.println("Enter amount: ");
			double depositeAmount = sc.nextDouble();

			if (depositeAmount >= 100) {

				String dep_sql = "insert into transactions (acc_no, deposite) values(" + acc_no + "," + depositeAmount
						+ ")";

				ac.setBalance(bal + depositeAmount);
				String sql = "update accounts set balance =" + ac.getBalance() + " where accno=" + acc_no + "";
				smt.execute(sql);
				smt.execute(dep_sql);
				System.out.println("Money Deposited successfully....");
			} else {
				System.err.println("Deposite Amount Should be more than 100....");
			}
		} else {
			System.err.println("your account is not present in our bank....");
		}
		smt.close();
		con.close();
	}

	@Override
	public void withdrawal() throws Exception {
		System.out.println("Enter your Account Number: ");
		long acc_no = sc.nextLong();

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_management_system", "root",
				"root");

		// store all account number into list
		List<Long> accNO_List = new ArrayList<>();
		String s = "SELECT * from accounts";
		smt = con.createStatement();
		ResultSet rs = smt.executeQuery(s);
		while (rs.next()) {
			// System.out.println(rs.getLong(1));
			accNO_List.add(rs.getLong(1));
		}

		if (accNO_List.contains(acc_no)) {
			// select balance from database and store into variable
			double bal = 0;
			String select_amount = "SELECT balance from accounts where accno=" + acc_no + "";
			ResultSet rs_bal = smt.executeQuery(select_amount);
			while (rs_bal.next()) {
//			System.out.println(rs_bal.getDouble(1));
				bal = rs_bal.getDouble(1);
			}

			System.out.println("Enter amount: ");
			double withdrawl_Amount = sc.nextDouble();
			if (withdrawl_Amount >= 100) {
				// check withdral amount is present in bank or not
				if (bal > withdrawl_Amount) {
					String withdrawl_sql = "insert into transactions (acc_no,withdrawl) values(" + acc_no + ","
							+ withdrawl_Amount + ")";
					ac.setBalance(bal - withdrawl_Amount);
					String sql = "update accounts set balance =" + ac.getBalance() + " where accno=" + acc_no + "";
					smt.execute(sql);
					smt.execute(withdrawl_sql);
					System.out.println("Money withdrawl successfully..");
				} else {
					System.err.println("Insufficient Balance...");
				}
			} else {
				System.err.println("Withdrawl Amount should be more than 100....");
			}
		} else {
			System.err.println("your account is not present in our bank....");
		}
		smt.close();
		con.close();
	}

	@Override
	public void balanceCheck() throws Exception {
		System.out.println("Enter your Account Number: ");
		long acc_no = sc.nextLong();

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_management_system", "root",
				"root");

		List<Long> accNO_List = new ArrayList<>();
		String s = "SELECT * from accounts";
		smt = con.createStatement();
		ResultSet rs = smt.executeQuery(s);
		while (rs.next()) {
			// System.out.println(rs.getLong(1));
			accNO_List.add(rs.getLong(1));
		}

		if (accNO_List.contains(acc_no)) {
			String sql = "SELECT balance from accounts where accno =" + acc_no + "";
			ResultSet balance = smt.executeQuery(sql);
			while (balance.next()) {
				System.out.println("Your Balance is: " + balance.getDouble(1));
			}
		} else {
			System.err.println("Invalid Account Number........");
		}
		smt.close();
		con.close();
	}

	@Override
	public void deleteAccount() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_management_system", "root",
				"root");
		System.out.println("Enter your Account Number: ");
		long acc_no = sc.nextLong();

		List<Long> accNO_List = new ArrayList<>();
		String s = "SELECT * from accounts";
		smt = con.createStatement();
		ResultSet rs = smt.executeQuery(s);
		while (rs.next()) {
			accNO_List.add(rs.getLong(1));
		}
		if (accNO_List.contains(acc_no)) {
			String sql = "DELETE from accounts where accno =" + acc_no + "";
			smt.execute(sql);
			System.out.println("Account Deleted Successfully.....");
		} else {
			System.err.println("Invalid Account Number........");
		}
		smt.close();
		con.close();
	}

	@Override
	public void showLastTransactions() throws Exception {

		System.out.println("Enter your Account Number: ");
		long acc_no = sc.nextLong();

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_management_system", "root",
				"root");

		// store all account number into list
		List<Long> accNO_List = new ArrayList<>();
		String s = "SELECT * from accounts";
		smt = con.createStatement();
		ResultSet rs = smt.executeQuery(s);
		while (rs.next()) {
			// System.out.println(rs.getLong(1));
			accNO_List.add(rs.getLong(1));
		}

		if (accNO_List.contains(acc_no)) {
			String transac_sql = "select * from transactions where acc_no=" + acc_no + "";
//			smt=con.createStatement();
			ResultSet rs_transac = smt.executeQuery(transac_sql);
			int count = 1;
			while (rs_transac.next()) {
				System.out.println(count + ") Account number: " + rs_transac.getLong(1) + " Deposite: "
						+ rs_transac.getDouble(2) + " Withdrawl: " + rs_transac.getDouble(3));
				count++;
			}
		} else {
			System.err.println("Invalid Account Number....");
		}
		smt.close();
		con.close();
	}
}