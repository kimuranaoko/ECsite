package com.internousdev.kiyurumi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.internousdev.kiyurumi.dto.CartInfoDTO;
import com.internousdev.kiyurumi.util.DBConnector;

public class CartInfoDAO {

//	カート全情報
	public List<CartInfoDTO> getCartInfoDtoList(String loginId){
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		List<CartInfoDTO> cartInfoDtoList = new ArrayList<CartInfoDTO>();

		String sql="SELECT"
				+ " ci.id as id," //カートID
				+ " ci.user_id as user_id," //ユーザーID
				+ " ci.temp_user_id as temp_user_id," //仮のユーザーID
				+ " ci.product_id as product_id," //商品ID
				+ " sum(ci.product_count) as product_count," //商品毎の購入数
				+ " pi.price as price," //商品の金額
				+ " pi.regist_date as regist_date," //商品登録日
				+ " pi.update_date as update_date," //商品変更日
				+ " pi.product_name as product_name," //商品名
				+ " pi.product_name_kana as product_name_kana," //商品名
				+ " pi.product_description as product_description," //商品詳細
				+ " pi.category_id as category_id," //商品テーブルからカテゴリーID
				+ " pi.image_file_path as image_file_path," //商品画像パス
				+ " pi.image_file_name as image_file_name," //商品画像ファイル名
				+ " pi.birth_date as birth_date," //キャラ生年月日
				+ " pi.birth_place as birth_place," //出身地
				+ " pi.status as status,"//商品のステータス
				+ " (sum(ci.product_count) * pi.price) as subtotal" //商品毎の合計金額
				+ " FROM cart_info as ci"
				+ " LEFT JOIN product_info as pi"
				+ " ON ci.product_id = pi.product_id"
				+ " WHERE ci.user_id = ?"
				+ " group by product_id";
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, loginId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				CartInfoDTO cartInfoDTO = new CartInfoDTO();
				cartInfoDTO.setId(resultSet.getInt("id"));
				cartInfoDTO.setUserId(resultSet.getString("user_id"));
				cartInfoDTO.setTempUserId(resultSet.getString("temp_user_id"));
				cartInfoDTO.setProductId(resultSet.getInt("product_id"));
				cartInfoDTO.setProductCount(resultSet.getInt("product_count"));
				cartInfoDTO.setPrice(resultSet.getInt("price"));
				cartInfoDTO.setRegistDate(resultSet.getDate("regist_date"));
				cartInfoDTO.setUpdateDate(resultSet.getDate("update_date"));
				cartInfoDTO.setProductName(resultSet.getString("product_name"));
				cartInfoDTO.setProductNameKana(resultSet.getString("product_name_kana"));
				cartInfoDTO.setProductDescription(resultSet.getString("product_description"));
				cartInfoDTO.setCategoryId(resultSet.getInt("category_id"));
				cartInfoDTO.setImageFilePath(resultSet.getString("image_file_path"));
				cartInfoDTO.setImageFileName(resultSet.getString("image_file_name"));
				cartInfoDTO.setBirthDate(resultSet.getString("birth_date"));
				cartInfoDTO.setBirthPlace(resultSet.getString("birth_place"));
				cartInfoDTO.setStatus(resultSet.getString("status"));
				cartInfoDTO.setSubtotal(resultSet.getInt("subtotal"));
				cartInfoDtoList.add(cartInfoDTO);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		try{
			connection.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return cartInfoDtoList;
	}

//	ユーザー毎の合計金額
	public int getTotalPrice(String userId){
		int totalPrice = 0;
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
//		cartInfoのuserIdが一致する所の数×金額（userId毎）にtotal_priceと名前をつける
		String sql="SELECT sum(product_count * price ) as total_price from cart_info where user_id=? group by user_id";
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				totalPrice = resultSet.getInt("total_price");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		try{
			connection.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return totalPrice;
	}

//	カートに新しく追加
	public int regist(String userId,String tempUserId,int productId,String productCount,int price){
		int count=0;
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		String sql = "INSERT INTO cart_info(user_id,temp_user_id,product_id,product_count,price,regist_date)"
				+ " VALUES(? ,? ,? ,? ,? ,now())";
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userId);
			preparedStatement.setString(2, tempUserId);
			preparedStatement.setInt(3, productId);
			preparedStatement.setString(4, productCount);
			preparedStatement.setInt(5,price);

			count = preparedStatement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		try{
			connection.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return count;
	}

//	１件削除
	public int delete(String productId,String userId){
		int count=0;
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		String sql="DELETE FROM cart_info WHERE product_id=? and user_id=?";
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, productId);
			preparedStatement.setString(2, userId);
			count = preparedStatement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		try{
			connection.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return count;
	}

//	そのユーザーの全消し
	public int deleteAll(String userId){
		int count = 0;
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		String sql="DELETE FROM cart_info WHERE user_id=?";
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userId);
			count = preparedStatement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		try{
			connection.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return count;
	}
//	tempUserIdで検索してその行のtempUserIdはnullにuser_idをセットする。
//	先にカート行ったときに適当なtempUserIDになってるから、ログインした時に
//	それを正しいIDの情報にUPDATEする

	public int linkToLoginId(String tempUserId,String loginId){
		int count = 0;
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		String sql= "UPDATE cart_info set user_id=?,temp_user_id=null,update_date=now() WHERE user_id=?";
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, loginId);
			preparedStatement.setString(2, tempUserId);
			count = preparedStatement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		try{
			connection.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return count;
	}

}
