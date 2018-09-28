package com.internousdev.ecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.internousdev.ecsite.dto.UserTableDTO;
import com.internousdev.ecsite.util.DBConnector;


public class UserTableDAO {
	private DBConnector dbConnector = new DBConnector();
	private Connection connection = dbConnector.getConnection();

	public ArrayList<UserTableDTO> getUserTableInfo() throws SQLException{

		ArrayList<UserTableDTO> userTableDTO = new ArrayList<UserTableDTO>();

		String sql="SELECT id,login_id,login_pass,user_name,insert_date,update_date "
				+ "FROM login_user_transaction  ORDER BY user_name ";

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next()){

				UserTableDTO dto = new UserTableDTO();
				dto.setId(resultSet.getInt("id"));
				dto.setLoginId(resultSet.getString("login_id"));
				dto.setLoginPass(resultSet.getString("login_pass"));
				dto.setUserName(resultSet.getString("user_name"));
				dto.setInsert_date(resultSet.getString("insert_date"));

				if(null ==resultSet.getString("update_date")){
					dto.setUpdate_date("---");
				}else{
					dto.setUpdate_date(resultSet.getString("update_date"));
				}
				userTableDTO.add(dto);
			}


//		for (UserTableDTO dto : userTableDTO) {
//			System.out.println("ID："+dto.getLoginId());
//			System.out.println("PASS:"+dto.getLoginPass());
//			System.out.println("NAME:"+dto.getUserName());
//			System.out.println("DATE"+dto.getInsert_date());
//		}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}

		return userTableDTO;

	}

	public int userDelete() throws SQLException{

		String sql="DELETE FROM login_user_transaction WHERE flg=0";

		PreparedStatement preparedStatement;
		int result = 0;
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return result;
	}

}
