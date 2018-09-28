package com.internousdev.ecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.internousdev.ecsite.dto.ItemContentsDTO;
import com.internousdev.ecsite.util.DBConnector;
import com.internousdev.ecsite.util.DateUtil;


public class ItemContentsDAO {


	private DBConnector dbConnector = new DBConnector();
	private Connection connection = dbConnector.getConnection();
	private DateUtil dateUtil = new DateUtil();


//	詳細
	public  ItemContentsDTO getItemContents(String itemId) throws SQLException{
		ItemContentsDTO dto = new ItemContentsDTO();
		String sql="SELECT id,item_name,item_price,item_stock,insert_date,update_date FROM item_info_transaction WHERE id = ? ORDER BY id ";

		try{
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, itemId);
			ResultSet resultSet = preparedStatement.executeQuery();


			if(resultSet.next()){
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
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return dto;

	}

	//削除
		public  int itemDelete(String itemId) throws SQLException{

			String sql="DELETE FROM item_info_transaction WHERE id=?";

			PreparedStatement preparedStatement;

			int result = 0;

			try{
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, itemId);
				result = preparedStatement.executeUpdate();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				connection.close();
			}
			return result;
		}

//		編集
		public int itemEdit(String itemName,String itemPrice,String itemStock,String itemId) throws SQLException{

			String sql="UPDATE item_info_transaction SET item_name=?,item_price=?,item_stock=?,update_date=? where id=?";
			int i = 0;


			try{
				PreparedStatement preparedStatement;

				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, itemName);
				preparedStatement.setString(2, itemPrice);
				preparedStatement.setString(3, itemStock);
				preparedStatement.setString(4, dateUtil.getDate());
				preparedStatement.setString(5, itemId);

				i=preparedStatement.executeUpdate();

//				if(i>0){
//					System.out.println(i+"件更新されました");
//				}else{
//					System.out.println("該当するデータはありませんでした");
//				}

			}catch(Exception e){
				e.printStackTrace();
			}finally{
				connection.close();
			}
			return i;
		}
}
