package com.internousdev.ecsite.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite.dao.ItemCreateConfirmDAO;
import com.internousdev.ecsite.dto.ItemCreateConfirmDTO;
import com.opensymphony.xwork2.ActionSupport;


public class ItemCreateConfirmAction extends ActionSupport implements SessionAware{


	private String itemName;
	private String itemPrice;
	private String itemStock;
	public Map<String,Object> session;
	private String errorMassage;
	private ItemCreateConfirmDAO itemCreateConfirmDAO = new ItemCreateConfirmDAO();
	private ArrayList<ItemCreateConfirmDTO> alreadyItemList = new ArrayList<ItemCreateConfirmDTO>();

	public String execute() throws SQLException{
		String result = SUCCESS;

		if(!(itemName.equals(""))
				&& !(itemPrice.equals(""))
				&& !(itemStock.equals(""))){

			alreadyItemList =itemCreateConfirmDAO.geItemCreateConfirmDTO(itemName);

			if(!(alreadyItemList.isEmpty())){
				setErrorMassage("その商品はすでに登録されています。別の商品を登録してください。");
				result = ERROR;
			}else{
					session.put("itemName", itemName);
					session.put("itemPrice", itemPrice);
					session.put("itemStock", itemStock);
				}

		}else{
			setErrorMassage("未入力の項目があります。");
			result = ERROR;
		}
		return result;
	}

	public String getItemName(){
		return itemName;
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

	@Override
	public void setSession(Map<String,Object> session){
		this.session = session;
	}

	public String getErrorMassage(){
		return errorMassage;
	}

	public void setErrorMassage(String errorMassage){
		this.errorMassage = errorMassage;
	}
}


