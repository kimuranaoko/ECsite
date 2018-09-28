package com.internousdev.ecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.internousdev.ecsite.dto.ItemTableDTO;
import com.internousdev.ecsite.util.DBConnector;

public class ItemTableDAO {

	private DBConnector dbConnector = new DBConnector();
	private Connection connection = dbConnector.getConnection();

//全件取得
	public ArrayList<ItemTableDTO> getItemTableInfo() throws SQLException{

		ArrayList<ItemTableDTO> itemTableDTO = new ArrayList<ItemTableDTO>();

		String sql="SELECT id,item_name,item_price,item_stock,insert_date,update_date "
				+ "FROM item_info_transaction  ORDER BY id ";

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next()){
				ItemTableDTO dto = new ItemTableDTO();
				dto.setItemId(resultSet.getString("id"));
				dto.setItemName(resultSet.getString("item_name"));
				dto.setItemPrice(resultSet.getString("item_price"));
				dto.setItemStock(resultSet.getString("item_stock"));
				dto.setInsert_date(resultSet.getString("insert_date"));

				if(null == resultSet.getString("update_date")){
					dto.setUpdate_date("---");
				}else{
				dto.setUpdate_date(resultSet.getString("update_date"));
				}
				itemTableDTO.add(dto);
			}

//		for (ItemTableDTO dto : itemTableDTO) {
//			System.out.println("名前："+dto.getItemName());
//			System.out.println("値段:"+dto.getItemPrice());
//			System.out.println("在庫:"+dto.getItemStock());
//			System.out.println("登録日:"+dto.getInsert_date());
//		}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}

		return itemTableDTO;

	}

//削除
	public  int itemDelete() throws SQLException{

		String sql="DELETE FROM item_info_transaction";

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
