package com.internousdev.ecsite.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite.dao.UserCreateConfirmDAO;
import com.internousdev.ecsite.dto.UserCreateConfirmDTO;
import com.opensymphony.xwork2.ActionSupport;

public class UserCreateConfirmAction extends ActionSupport implements SessionAware{

	private String loginUserId;
	private String loginPassword;
	private String userName;
	public Map<String,Object> session;
	private String errorMassage;
	private UserCreateConfirmDAO userCreateConfirmDAO = new UserCreateConfirmDAO();
	private ArrayList<UserCreateConfirmDTO> alreadyUserList = new ArrayList<UserCreateConfirmDTO>();


	public String execute() throws SQLException{
		String result = SUCCESS;

		if(!(loginUserId.equals(""))
				&& !(loginPassword.equals(""))
				&& !(userName.equals(""))){

				alreadyUserList =userCreateConfirmDAO.getUserCreateConfirmDTO(loginUserId);

				for(UserCreateConfirmDTO aa : alreadyUserList){
					System.out.println(aa.getAlreadyLoginId());
				}


				if(!(alreadyUserList.isEmpty())){
					setErrorMassage("ログインIDがすでに使用されています。別のIDを登録してください。");
					result = ERROR;

				}else{
					session.put("loginUserId", loginUserId);
					session.put("loginPassword", loginPassword);
					session.put("userName", userName);
					session.put("flg", "0");
				}

		}else{
			setErrorMassage("未入力の項目があります。");
			result = ERROR;
		}
		return result;
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

	public String getUserName(){
		return userName;
	}

	public void setUserName(String userName){
		this.userName = userName;
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
