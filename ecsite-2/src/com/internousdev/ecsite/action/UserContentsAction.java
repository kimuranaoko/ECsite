package com.internousdev.ecsite.action;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite.dao.UserContentsDAO;
import com.internousdev.ecsite.dto.UserContentsDTO;
import com.opensymphony.xwork2.ActionSupport;

public class UserContentsAction extends ActionSupport implements SessionAware{

	private Map<String,Object> session;
	private UserContentsDAO userContentsDAO = new UserContentsDAO();
	private int id;
	private String loginId;
	private String loginPass;
	private String userName;
	private String insert_date;
	private String update_date;
	private String flg;

	private String message;
	private String editMessage;

	public String execute() throws SQLException{

		if(null == flg){
		UserContentsDTO dto = userContentsDAO.getUserContents(id);

		session.put("id",dto.getId());
		session.put("loginId", dto.getLoginId());
		session.put("loginPass", dto.getLoginPass());
		session.put("userName", dto.getUserName());
		session.put("insert_date",dto.getInsert_date());
		session.put("update_date", dto.getUpdate_date());


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

	public void delete() throws SQLException{

		int i=userContentsDAO.deleteUser(id);

		if(i == 0){
			setMessage("削除に失敗しました。");
		}else if(i > 0){
			setMessage("正しく削除しました。");
		}

	}

	public void edit1() throws SQLException{
		if(2 == id){
			setEditMessage("管理者ＩＤは変更できません。");
		}

	}

	public void edit2() throws SQLException{

		String idMessage="ログインIDを更新しました　";
		String passMessage="ログインパスワードを更新しました　";
		String userMessage="ユーザーIDを更新しました　";


		if(loginId.equals(session.get("loginId"))){
			this.loginId = session.get("loginId").toString();
			idMessage = "ログインIDは変更ありません　";
		}

		if(loginPass.equals(session.get("loginPass"))){
			this.loginPass = session.get("loginPass").toString();
			passMessage = "ログインパスワードは変更ありません　";
		}

		if(userName.equals(session.get("userName"))){
			this.userName = session.get("userName").toString();
			userMessage = "ユーザーIDは変更ありません　";
		}


		int i=userContentsDAO.editUser(Integer.parseInt(session.get("id").toString()), loginId, loginPass, userName);

		if(i>0){
			setEditMessage(idMessage + passMessage + userMessage);

		}else if(i== 0){
			setEditMessage("商品情報の更新に失敗しました。");
		}

	}








	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public String getLoginId(){
		return loginId;
	}

	public void setLoginId(String loginId){
		this.loginId = loginId;
	}

	public String getLoginPass(){
		return loginPass;
	}

	public void setLoginPass(String loginPass){
		this.loginPass = loginPass;
	}

	public String getUserName(){
		return userName;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getInsert_date(){
		return insert_date;
	}

	public void setInsert_date(String insert_date){
		this.insert_date = insert_date;
	}

	public String getUpdate_date(){
		return update_date;
	}

	public void setUpdate_date(String update_date){
		this.update_date = update_date;
	}

	public String getFlg(){
		return flg;
	}

	public void setFlg(String flg){
		this.flg = flg;
	}

	public String getMessage(){
		return message;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getEditMessage(){
		return editMessage;
	}

	public void setEditMessage(String editMessage){
		this.editMessage = editMessage;
	}


	public Map<String,Object> getSession(){
		return session;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;

	}

	public UserContentsDAO getItemContentsDAO(){
		return userContentsDAO;
	}

	public void setItemContentsDAO(UserContentsDAO userContentsDAO){
		this.userContentsDAO = userContentsDAO;
	}


}
