package com.hmei.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import com.hmei.bean.Customer;
import com.hmei.bean.Manager;
import com.hmei.bean.Person;
import com.hmei.util.DatabaseUtil;

public class PersonDao {
	public static Person isValid(String userType, String userName, String password){
		Person person = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String msg = null;
		String userSql = "select * from customer where email='" + userName + "'" + " and upassword='" + password + "'";
		String adminSql = "select * from Manager where username='" + userName + "'" + " and mpassword='" + password
				+ "'";
		conn = DatabaseUtil.getConnection();
		
		try {
			stmt = conn.createStatement();
			if (userType.equals("customer")) {
				rs = stmt.executeQuery(userSql);
				if (rs.next()) {
					Customer customer = new Customer();
					customer.setCustomerId(rs.getString(1));
					customer.setFirstName(rs.getString(2));
					customer.setLastName(rs.getString(3));
					customer.setGender(rs.getString(4));
					customer.setEmail(rs.getString(5));
					customer.setPassword(rs.getString(6));

					customer.setDateOfBirth(rs.getDate(7).toLocalDate());
					customer.setAddress(rs.getString(8));
					customer.setTelHome(rs.getString(9));
					person = customer;
				}
			} else if (userType.equals("admin")) {
				rs = stmt.executeQuery(adminSql);
				if (rs.next()) {
					Manager manager = new Manager();
					manager.setManagerId(rs.getString(1));
					manager.setUserName(rs.getString(2));
					manager.setPassword(rs.getString(3));
					manager.setFirstName(rs.getString(4));
					manager.setLastName(rs.getString(5));

					person = manager;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return person;
	}

	public static boolean addRecord(Customer customer){
		LocalDate dateOfBirth = customer.getDateOfBirth();
		String[] date = dateOfBirth.toString().split("-");
		String insertSql = "insert into customer (Customer_id, FirstName,LastName,gender,Email,Upassword,DateOfBirth,"
				+ "Address,Tel_home) values (" + "seq_customer.nextval," + "'" + customer.getFirstName() + "'," + "'"
				+ customer.getLastName() + "'," + "'" + customer.getGender() + "'," + "'" + customer.getEmail() + "',"
				+ "'" + customer.getPassword() + "'," + "to_date ('" + date[1] + "/" + date[2] + "/" + date[0]
				+ "', 'MM/DD/YYYY')" + "," + "'" + customer.getAddress() + "'," + "'" + customer.getTelHome() + "')";
		//sysout
		System.out.println(insertSql);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn = DatabaseUtil.getConnection();
		

		try {
			pstmt = conn.prepareStatement(insertSql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	public static boolean updateRecord(Customer customer){

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String msg = null;
		String updateSQL = "update customer set firstname= ?, lastname = ?,"
				+ " gender = ?, upassword = ?, dateofbirth = to_date(?,'MM/DD/YYYY'), "
				+ "tel_home = ?, address = ? where customer_id = ?";
		conn = DatabaseUtil.getConnection();
		try {
			stmt = conn.prepareStatement(updateSQL);
			String[] date = customer.getDateOfBirth().toString().split("-");
			String dateOfBirth = date[1] + "/" + date[2] + "/" + date[0];
			stmt.setString(1, customer.getFirstName());
			stmt.setString(2, customer.getLastName());
			stmt.setString(3, customer.getGender());
			stmt.setString(4, customer.getPassword());
			stmt.setString(5, dateOfBirth);
			stmt.setString(6, customer.getTelHome());
			stmt.setString(7, customer.getAddress());
			stmt.setInt(8, Integer.parseInt(customer.getCustomerId()));

			rs = stmt.executeQuery();
			if (rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(conn != null)
			{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}

}
