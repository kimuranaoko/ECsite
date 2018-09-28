package com.internousdev.ecsite.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite.dao.UserTableDAO;
import com.internousdev.ecsite.dto.UserTableDTO;
import com.opensymphony.xwork2.ActionSupport;

public class UserTableAction extends ActionSupport implements SessionAware{

	public Map<String,Object> session;
	private UserTableDAO userTableDAO = new UserTableDAO();
	private ArrayList<UserTableDTO> userList = new ArrayList<UserTableDTO>();
	private String deleteFlg=null;
	private String message;

	public String execute() throws SQLException{
		if(deleteFlg == null){
			userList = userTableDAO.getUserTableInfo();
		}else if(deleteFlg.equals("1")){
			delete();
		}
		String result = SUCCESS;
		return result;
	}

	public void delete() throws SQLException{

		int res = userTableDAO.userDelete();

		if(res>0){
			userList=null;
			setMessage("アカウント情報を正しく削除しました。");
		}else if(res==0){
			setMessage("アカウント情報の削除に失敗しました。");
		}
	}

	public void setDeleteFlg(String deleteFlg){
		this.deleteFlg = deleteFlg;
	}

	@Override
	public void setSession(Map<String,Object> session){
		this.session = session;
	}

	public ArrayList<UserTableDTO> getUserList(){
		return this.userList;
	}

	public String getMessage(){
		return this.message;
	}

	public void setMessage(String message){
		this.message = message;
	}

}
