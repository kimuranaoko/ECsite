package com.internousdev.ecsite.action;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite.dao.ItemContentsDAO;
import com.internousdev.ecsite.dto.ItemContentsDTO;
import com.opensymphony.xwork2.ActionSupport;


public class ItemContentsAction extends ActionSupport implements SessionAware{

	private Map<String,Object> session;
	private ItemContentsDAO itemContentsDAO = new ItemContentsDAO();
	private String itemId;
	private String flg;
	private String message;
	private String itemName;
	private String itemPrice;
	private String itemStock;
	private String editMessage;


//詳細
	public String execute() throws SQLException{

		if(flg == null){

		ItemContentsDTO dto = itemContentsDAO.getItemContents(itemId);

		session.put("AItemId",dto.getItemId());
		session.put("AItemName",dto.getItemName());
		session.put("AItemPrice", dto.getItemPrice());
		session.put("AItemStock", dto.getItemStock());
		session.put("Ainsert_date",dto.getInsert_date());
		session.put("Aupdate_date", dto.getUpdate_date());


		}else if(flg.equals("1")){
			delete();
		}else if(flg.equals("2")){
			edit1();
		}else if(flg.equals("3")){
			edit2();
		}

		String result = SUCCESS;
		return result;
	}

//消去
	public void delete() throws SQLException{

		int res = itemContentsDAO.itemDelete(itemId);

		if(res>0){
			setMessage("商品情報を正しく削除しました。");

		}else if(res == 0){
			setMessage("商品情報の削除に失敗しました。");
		}

	}

//編集

	public void edit1()  throws SQLException{

	}

	public void edit2() throws SQLException{

		int i=itemContentsDAO.itemEdit(itemName,itemPrice,itemStock,session.get("AItemId").toString());

		if(i>0){
			setEditMessage("商品情報を正しく更新しました。");

		}else if(i== 0){
			setEditMessage("商品情報の更新に失敗しました。");
		}

	}




	public String getMessage(){
		return this.message;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getItemId(){
		return itemId;
	}

	public void setItemId(String itemId){
		this.itemId = itemId;
	}

	public String getItemName(){
		return this.itemName;
	}

	public void setItemName(String itemName){
		this.itemName = itemName;
	}

	public String getItemPrice(){
		return itemPrice;
	}

	public void setItemPrice(String itemPrice){
		this.itemPrice = itemPrice;
	}

	public String getItemStock(){
		return itemStock;
	}

	public void setItemStock(String itemStock){
		this.itemStock = itemStock;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public String getFlg(){
		return flg;
	}

	public void setFlg(String flg){
		this.flg = flg;
	}



	public String getEditMessage(){
		return editMessage;
	}

	public void setEditMessage(String editMessage){
		this.editMessage = editMessage;
	}

	@Override
	public void setSession(Map<String,Object> session){
		this.session = session;
	}


	public ItemContentsDAO getItemContentsDAO() {
		return itemContentsDAO;
	}

	public void setItemContentsDAO(ItemContentsDAO itemContentsDAO) {
		this.itemContentsDAO = itemContentsDAO;
	}

}
