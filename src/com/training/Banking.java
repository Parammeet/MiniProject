package com.training;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Banking {

	Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		Banking au = new Banking();
		int ch;

		do {
			System.out.println("Enter your choice");

			System.out.println("1.Insert the record of the user");
			System.out.println("2.Display the record of the user");
			System.out.println("3.Updating the record of the user");
			System.out.println("4.Deleting the record of the user");
			System.out.println("5.Deposit money");
			System.out.println("6.Withdraw Money");
			System.out.println("7.Check Balance");
			System.out.println("8.Exit");

			ch = sc.nextInt();

			switch (ch) {
			case 1:
				au.insertData();
				break;
			case 2:
				au.displayData();
				break;
			case 3:
				au.updateData();
				break;
			case 4:
				au.deleteData();
				break;
			case 5:
				au.depositMoney();
				break;
			case 6:
				au.withdrawMoney();
				break;
			case 7:
				au.checkBalance();
				break;
			case 8:
				System.out.println("Thank you");
			default:
				break;
			}
		}

		while (ch != 8);

	}

	public Connection getConnect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521/orcl.iiht.tech";
			String userName = "scott";
			String password = "tiger";

			Connection con = DriverManager.getConnection(url, userName, password);
			return con;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	Bank b = new Bank();
	RandomMethod rm = new RandomMethod();

	public void insertData() {

		System.out.println("Enter the userID");
		int user_id = sc.nextInt();
		b.setUser_id(user_id);

		System.out.println("Enter username");
		String username = sc.next();
		b.setUsername(username);

		System.out.println("Enter gender");
		String gender = sc.next();
		b.setGender(gender);

		System.out.println("Enter Addresss");
		String address = sc.next();
		b.setAddress(address);

		System.out.println("Enter email");
		String email = sc.next();
		b.setEmail(email);

		System.out.println("Enter contact number");
		int contact_no = sc.nextInt();
		b.setContact_no(contact_no);

		System.out.println("Enter PAN Number");
		int pan_no = sc.nextInt();
		b.setPan_no(pan_no);

		System.out.println("Enter Aadhar Number");
		int aadhar_no = sc.nextInt();
		b.setAadhar_no(aadhar_no);

		System.out.println("Enter Account Type");
		String account_type = sc.next();
		b.setAccount_type(account_type);

		System.out.println("Enter Balance");
		double balance = sc.nextDouble();
		b.setBalance(balance);

		int account_no = rm.AccountNumber();
		b.setAccount_no(account_no);

		try {
			Connection con = getConnect();

			PreparedStatement ps = con.prepareStatement(
					"insert into bankuser(user_id,username,gender,address,email,contact_no,pan_no,aadhar_no,account_type,balance,account_no) values(?,?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, b.getUser_id());
			ps.setString(2, b.getUsername());
			ps.setString(3, b.getGender());
			ps.setString(4, b.getAddress());
			ps.setString(5, b.getEmail());
			ps.setInt(6, b.getContact_no());
			ps.setInt(7, b.getPan_no());
			ps.setInt(8, b.getAadhar_no());
			ps.setString(9, b.getAccount_type());
			ps.setDouble(10, b.getBalance());
			ps.setInt(11, b.getAccount_no());

			int x = ps.executeUpdate();
			ps.close();
			con.close();
			if (x == 1) {
				System.out.println("Your Account has been open successfully.");

				System.out.println("Your account number is: " + account_no);

			}

		} catch (Exception e) {

		}

	}

	public void displayData() {

		try {

			Connection con = getConnect();

			PreparedStatement ps = con.prepareStatement("select * from bankuser ");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4)
						+ " " + rs.getString(5) + " " + rs.getInt(6) + " " + rs.getInt(7) + " " + rs.getInt(8) + " "
						+ rs.getString(9) + " " + rs.getDouble(10) + " " + rs.getInt(11));
			}
			rs.close();
			con.close();
			ps.close();

		} catch (Exception e) {

		}
	}

	public void updateData() {
		System.out.println("Enter the userID");
		int user_id = sc.nextInt();
		b.setUser_id(user_id);

		System.out.println("Enter username");
		String username = sc.next();
		b.setUsername(username);

		System.out.println("Enter gender");
		String gender = sc.next();
		b.setGender(gender);

		System.out.println("Enter Addresss");
		String address = sc.next();
		b.setAddress(address);

		System.out.println("Enter email");
		String email = sc.next();
		b.setEmail(email);

		System.out.println("Enter contact number");
		int contact_no = sc.nextInt();
		b.setContact_no(contact_no);

		System.out.println("Enter PAN Number");
		int pan_no = sc.nextInt();
		b.setPan_no(pan_no);

		System.out.println("Enter Aadhar Number");
		int aadhar_no = sc.nextInt();
		b.setAadhar_no(aadhar_no);

		System.out.println("Enter Account Type");
		String account_type = sc.next();
		b.setAccount_type(account_type);

		System.out.println("Enter Account Number");
		int account_no = sc.nextInt();
		b.setAccount_no(account_no);

		try {

			Connection con = getConnect();
			String str = "update bankuser set username=?,gender=?,address=?,email=?,contact_no=?,pan_no=?,aadhar_no=?,account_type=?,account_no=? where user_id=?";

			PreparedStatement ps = con.prepareStatement(str);

			ps.setString(1, b.getUsername());
			ps.setString(2, b.getGender());
			ps.setString(3, b.getAddress());
			ps.setString(4, b.getEmail());
			ps.setInt(5, b.getContact_no());
			ps.setInt(6, b.getPan_no());
			ps.setInt(7, b.getAadhar_no());
			ps.setString(8, b.getAccount_type());
			ps.setInt(9, b.getAccount_no());
			ps.setInt(10, b.getUser_id());

			int x = ps.executeUpdate();

			ps.close();
			con.close();
			if (x == 1) {
				System.out.println("Updated Successsfully");
			} else {
				System.out.println("Not updated");
			}
		} catch (Exception e) {

		}

	}

	public void deleteData() {

		try {
			int id;
			System.out.println("Enter the userid to delete the record");

			id = sc.nextInt();
			b.setUser_id(id);

			Connection con = getConnect();

			PreparedStatement ps = con.prepareStatement("delete from bankuser where user_id=? ");

			ps.setInt(1, b.getUser_id());

			int x = ps.executeUpdate();
			ps.close();
			con.close();

			if (x == 1) {
				System.out.println("Deleted successfully");
			} else {
				System.out.println("Please enter valid user_id");
			}
		} catch (Exception e) {

		}
	}

	public void depositMoney() {

		System.out.println("Enter the userID");
		int user_id = sc.nextInt();
		b.setUser_id(user_id);

		System.out.println("Enter your account number");
		int account_no = sc.nextInt();
		b.setAccount_no(account_no);

		System.out.println("Enter amount you want to deposit");
		double amount = sc.nextDouble();
		amount = amount + b.getBalance();
		b.setBalance(amount);

		try {
			Connection con = getConnect();

			PreparedStatement ps = con
					.prepareStatement("update bankuser set balance=balance+? where user_id=? and account_no=?");
//					"insert into transact(user_id,account_no,balance) values(?,?,?)");

			ps.setDouble(1, b.getBalance());
			ps.setInt(2, b.getUser_id());
			ps.setInt(3, b.getAccount_no());

			int x = ps.executeUpdate();
			ps.close();
			con.close();
			if (x == 1) {

				System.out.println("Money deposited successfully");
			}

		} catch (Exception e) {

		}

	}

	public void withdrawMoney() {

		System.out.println("Enter the userID");
		int user_id = sc.nextInt();
		b.setUser_id(user_id);

		System.out.println("Enter your account number");
		int account_no = sc.nextInt();
		b.setAccount_no(account_no);

		System.out.println("Enter amount you want to withdraw");
		double amount = sc.nextDouble();
		

		amount = Math.abs(amount - b.getBalance());
		b.setBalance(amount);

		try {
			Connection con = getConnect();

			PreparedStatement ps = con
					.prepareStatement("update bankuser set balance=balance-? where user_id=? and account_no=?");

			ps.setDouble(1, b.getBalance());
			ps.setInt(2, b.getUser_id());
			ps.setInt(3, b.getAccount_no());

			int x = ps.executeUpdate();
			ps.close();
			con.close();
			if (x == 1) {

				System.out.println("Money withdraw successfully");

			}

		} catch (Exception e) {

		}
	}

	public void checkBalance() {
		try {

			Connection con = getConnect();

			System.out.println("Enter the Account Number");
			int account_no = sc.nextInt();

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("select balance from bankuser where account_no=" + account_no);

			while (rs.next()) {
				System.out.println("Your current Balance is: " + rs.getDouble(1));
			}

			con.close();

		} catch (Exception e) {

		}

	}
}
