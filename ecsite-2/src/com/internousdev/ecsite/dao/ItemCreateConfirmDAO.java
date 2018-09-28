package com.internousdev.ecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.internousdev.ecsite.dto.ItemCreateConfirmDTO;
import com.internousdev.ecsite.util.DBConnector;

public class ItemCreateConfirmDAO {

	private DBConnector dbConnector = new DBConnector();
	private Connection connection = dbConnector.getConnection();

	public ArrayList<ItemCreateConfirmDTO>geItemCreateConfirmDTO(String item_name) throws SQLException{

		ArrayList<ItemCreateConfirmDTO> itemCreateConfirmDTO= new ArrayList<ItemCreateConfirmDTO>();

		String sql="SELECT item_name FROM item_info_transaction WHERE item_name= ? ";

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, item_name);
			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next()){
				ItemCreateConfirmDTO dto = new ItemCreateConfirmDTO();
				dto.setAlreadyItemName(resultSet.getString("item_name"));
				itemCreateConfirmDTO.add(dto);
			}


		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}

		return itemCreateConfirmDTO;

	}

}
