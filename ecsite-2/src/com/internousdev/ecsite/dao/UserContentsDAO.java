package com.internousdev.ecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.internousdev.ecsite.dto.UserContentsDTO;
import com.internousdev.ecsite.util.DBConnector;
import com.internousdev.ecsite.util.DateUtil;


public class UserContentsDAO {

	private DBConnector dbConnector = new DBConnector();
	private Connection connection = dbConnector.getConnection();
	private DateUtil dateUtil = new DateUtil();

	public UserContentsDTO getUserContents(int id) throws SQLException{
		UserContentsDTO dto = new UserContentsDTO();
		String sql="SELECT id,login_id,login_pass,user_name,insert_date,update_date FROM login_user_transaction WHERE id = ?";
		try{
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next()){
				dto.setId(id);
				dto.setLoginId(resultSet.getString("login_id"));
				dto.setLoginPass(resultSet.getString("login_pass"));
				dto.setUserName(resultSet.getString("user_name"));
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

	public int deleteUser(int id) throws SQLException{

		String sql="DELETE FROM login_user_transaction WHERE id=?";

		PreparedStatement preparedStatement;

		int i = 0;

		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);

			i = preparedStatement.executeUpdate();

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return i;
	}

	public int editUser(int id,String loginId,String loginPass,String userName) throws SQLException{
		String sql="UPDATE login_user_transaction SET login_id=?, login_pass=?,user_name=?,update_date=? WHERE id = ?";

		PreparedStatement preparedStatement;

		int i = 0;

		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, loginId);
			preparedStatement.setString(2, loginPass);
			preparedStatement.setString(3, userName);
			preparedStatement.setString(4, dateUtil.getDate());
			preparedStatement.setInt(5, id);

			i=preparedStatement.executeUpdate();

			if(i>0){
				System.out.println(i+"件更新されました");
			}else{
				System.out.println("該当するデータはありませんでした");
			}



		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return i;
	}

}
