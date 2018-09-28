package com.internousdev.ecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.internousdev.ecsite.dto.UserCreateConfirmDTO;
import com.internousdev.ecsite.util.DBConnector;

public class UserCreateConfirmDAO {

	private DBConnector dbConnector = new DBConnector();
	private Connection connection = dbConnector.getConnection();

	public ArrayList<UserCreateConfirmDTO>getUserCreateConfirmDTO(String login_id) throws SQLException{

		ArrayList<UserCreateConfirmDTO> userCreateConfirmDTO= new ArrayList<UserCreateConfirmDTO>();

		String sql="SELECT login_id FROM login_user_transaction WHERE login_id = ? ";

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, login_id);
			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next()){
				UserCreateConfirmDTO dto = new UserCreateConfirmDTO();
				dto.setAlreadyLoginId(resultSet.getString("login_id"));
				userCreateConfirmDTO.add(dto);
			}


		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}

		return userCreateConfirmDTO;

	}

}
