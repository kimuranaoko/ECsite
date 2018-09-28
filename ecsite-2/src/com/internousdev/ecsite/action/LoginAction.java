package com.internousdev.ecsite.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite.dao.BuyItemDAO;
import com.internousdev.ecsite.dao.LoginDAO;
import com.internousdev.ecsite.dto.BuyItemDTO;
import com.internousdev.ecsite.dto.LoginDTO;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements SessionAware{

	private String loginUserId;
	private String loginPassword;
	public Map<String,Object> session;
	private LoginDAO loginDAO = new LoginDAO();
	private LoginDTO loginDTO = new LoginDTO();
	private BuyItemDAO buyItemDAO = new BuyItemDAO();
	private ArrayList<BuyItemDTO> buyItemList = new ArrayList<BuyItemDTO>();

	public String execute() throws SQLException{
		String result = ERROR;
		loginDTO = loginDAO.getLoginUserInfo(loginUserId,loginPassword);
		session.put("loginUser", loginDTO);

		if(((LoginDTO)session.get("loginUser")).getLoginFlg()){

			buyItemList = buyItemDAO.getBuyItemInfo();

			session.put("login_user_id",loginDTO.getLoginId());
			session.put("flg",loginDTO.getFlg());
			session.put("buyItemList",buyItemList);

			System.out.println(session.get("flg"));

			if("0".equals(session.get("flg"))){
				result = SUCCESS;
			}else{
				result = "admin";
			}

			System.out.println(result);
		}
		return result;
	}

	public ArrayList<BuyItemDTO> getBuyItemList() {
		return buyItemList;
	}

	public void setBuyItemList(ArrayList<BuyItemDTO> buyItemList) {
		this.buyItemList = buyItemList;
	}

	public String getLoginUserId(){
		return loginUserId;
	}

	public void setLoginUserId(String loginUserId){
		this.loginUserId = loginUserId;
	}

	public String getLoginPassword(){
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword){
		this.loginPassword = loginPassword;
	}

	@Override
	public void setSession(Map<String,Object>session){
		this.session = session;
	}

}
