package com.internousdev.ecsite.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite.dao.ItemTableDAO;
import com.internousdev.ecsite.dto.ItemTableDTO;
import com.opensymphony.xwork2.ActionSupport;

public class ItemTableAction extends ActionSupport implements SessionAware{

	public Map<String,Object> session;
	private ItemTableDAO itemTableDAO = new ItemTableDAO();
	private ArrayList<ItemTableDTO> itemList = new ArrayList<ItemTableDTO>();
	private String deleteFlg;
	private String message;

	public String execute() throws SQLException{
		if(deleteFlg == null){
			itemList = itemTableDAO.getItemTableInfo();

			if(itemList.isEmpty()){
				setMessage("商品情報がありません。先に登録してください。");
			}
		}else if(deleteFlg.equals("1")){
			delete();
		}


		String result = SUCCESS;
		return result;

	}

	public void delete() throws SQLException{

		int res = itemTableDAO.itemDelete();

		if(res>0){
			itemList=null;
			setMessage("商品を正しく削除しました。");

		}else if(res==0){
			setMessage("商品情報の削除に失敗しました。");

		}
	}

	public void setDeleteFlg(String deleteFlg){
		this.deleteFlg = deleteFlg;
	}

	@Override
	public void setSession(Map<String,Object> session){
		this.session = session;
	}

	public ArrayList<ItemTableDTO> getItemList(){
		return this.itemList;
	}

	public String getMessage(){
		return this.message;
	}

	public void setMessage(String message){
		this.message = message;
	}

}
