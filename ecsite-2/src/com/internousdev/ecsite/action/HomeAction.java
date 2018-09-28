package com.internousdev.ecsite.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite.dao.BuyItemDAO;
import com.internousdev.ecsite.dto.BuyItemDTO;
import com.opensymphony.xwork2.ActionSupport;

public class HomeAction extends ActionSupport implements SessionAware{

	public Map<String,Object> session;

	public String execute() throws SQLException{
		String result="login";
		if(session.containsKey("login_user_id")){
			BuyItemDAO buyItemDAO = new BuyItemDAO();
			ArrayList<BuyItemDTO> buyItemList = new ArrayList<BuyItemDTO>();
			buyItemList = buyItemDAO.getBuyItemInfo();
			session.put("buyItemList", buyItemList);
			result=SUCCESS;

			if("1".equals(session.get("flg").toString())){
				result="admin";
			}

			for(BuyItemDTO a:buyItemList){
				System.out.println("★"+a.getId());
				System.out.println("★"+a.getItemName());
				System.out.println("★"+a.getItemPrice());
			}

		}


		return result;
	}

	@Override
	public void setSession(Map<String,Object> session){
		this.session = session;
	}

	public Map<String,Object> getSession(){
		return this.session;
	}
}
