package com.internousdev.kiyurumi.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.kiyurumi.dao.CartInfoDAO;
import com.internousdev.kiyurumi.dao.DestinationInfoDAO;
import com.internousdev.kiyurumi.dao.MCategoryDAO;
import com.internousdev.kiyurumi.dao.UserInfoDAO;
import com.internousdev.kiyurumi.dto.DestinationInfoDTO;
import com.internousdev.kiyurumi.dto.MCategoryDTO;
import com.internousdev.kiyurumi.dto.UserInfoDTO;
import com.internousdev.kiyurumi.util.InputChecker;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements SessionAware{
	private String categoryId;
	private String loginId;
	private String password;
	private boolean savedLoginId;
	private List<MCategoryDTO> mCategoryDtoList = new ArrayList<MCategoryDTO>();
	private List<String> loginIdErrorMessageList = new ArrayList<String>();


	private List<String> passwordErrorMessageList = new ArrayList<String>();
	private Map<String, Object> session;
	private List<String> loginErrorMessageList = new ArrayList<String>();

	int count=0;

	private String settlement;

	public String execute() {

		if(!session.containsKey("settlement")){
			session.put("settlement","0");
		}
		String result = ERROR;
		if(savedLoginId==true) {
			session.put("savedLoginId", true);
			session.put("loginId", loginId);
		}else {
			session.put("savedLoginId", false);
			session.remove("loginId" ,loginId);
		}

		InputChecker inputChecker = new InputChecker();

		session.remove("loginIdErrorMessageList", loginIdErrorMessageList);
		session.remove("passwordErrorMessageList", passwordErrorMessageList);
		session.remove("loginErrorMessageList", loginErrorMessageList);
		loginIdErrorMessageList = inputChecker.doCheck("ログインID", loginId, 1, 8, true, false, false, true, false, false, false);
		passwordErrorMessageList = inputChecker.doCheck("パスワード", password, 1, 16, true, false, false, true, false, false, false);
		loginErrorMessageList  = inputChecker.doLoginError(loginId,password);
		if(loginIdErrorMessageList.size()==0
		&& passwordErrorMessageList.size()==0
		&& loginErrorMessageList.size()==0) {

		}else {
			session.put("loginIdErrorMessageList", loginIdErrorMessageList);
			session.put("passwordErrorMessageList", passwordErrorMessageList);
			session.put("loginErrorMessageList", loginErrorMessageList);

		}

		if(!session.containsKey("mCategoryList")) {
			MCategoryDAO mCategoryDao = new MCategoryDAO();
			mCategoryDtoList = mCategoryDao.getMCategoryList();
			session.put("mCategoryDtoList", mCategoryDtoList);
		}

		UserInfoDAO userInfoDao = new UserInfoDAO();
		if(userInfoDao.isExistsUserInfo(loginId, password)) {


			if(userInfoDao.login(loginId, password) > 0) {


				UserInfoDTO userInfoDTO = userInfoDao.getUserInfo(loginId, password);
//				管理者かどうか
				int status = userInfoDTO.getStatus();
				session.put("loginId", userInfoDTO.getUserId());
				session.put("status", status);

				if(status==1){

					result = "admin";

				}else{
					CartInfoDAO cartInfoDao = new CartInfoDAO();
					if(session.containsKey("tempUserID")){
					count = cartInfoDao.linkToLoginId(String.valueOf(session.get("tempUserId")),loginId);
					}else{
					count = cartInfoDao.linkToLoginId(String.valueOf(session.get("cartRamdomId")), loginId);
					}

					if(session.get("settlement").toString().equals("決済")) {
						DestinationInfoDAO destinationInfoDao = new DestinationInfoDAO();
						try {
							List<DestinationInfoDTO> destinationInfoDtoList = new ArrayList<DestinationInfoDTO>();
							destinationInfoDtoList = destinationInfoDao.getDestinationInfo(loginId);
							Iterator<DestinationInfoDTO> iterator = destinationInfoDtoList.iterator();
							if(!(iterator.hasNext())) {
								destinationInfoDtoList = null;
							}
							session.put("destinationInfoDtoList", destinationInfoDtoList);
						} catch (SQLException e) {
							e.printStackTrace();
						}

						result = "settlement";

					}else{
							result = SUCCESS;
					}
				}
			}
				session.put("logined", 1);
				session.remove("settlement");

		}
		return result;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isSavedLoginId() {
		return savedLoginId;
	}

	public void setSavedLoginId(boolean savedLoginId) {
		this.savedLoginId = savedLoginId;
	}

	public List<MCategoryDTO> getmCategoryDtoList() {
		return mCategoryDtoList;
	}

	public void setmCategoryDtoList(List<MCategoryDTO> mCategoryDtoList) {
		this.mCategoryDtoList = mCategoryDtoList;
	}

	public List<String> getLoginIdErrorMessageList() {
		return loginIdErrorMessageList;
	}

	public void setLoginIdErrorMessageList(List<String> loginIdErrorMessageList) {
		this.loginIdErrorMessageList = loginIdErrorMessageList;
	}

	public List<String> getPasswordErrorMessageList() {
		return passwordErrorMessageList;
	}

	public void setPasswordErrorMessageList(List<String> passwordErrorMessageList) {
		this.passwordErrorMessageList = passwordErrorMessageList;
	}

	public List<String> getLoginErrorMessageList() {
		return loginErrorMessageList;
	}

	public void setLoginErrorMessageList(List<String> loginErrorMessageList) {
		this.loginErrorMessageList = loginErrorMessageList;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String getSettlement() {
		return settlement;
	}

	public void setSettlement(String settlement) {
		this.settlement = settlement;
	}



}
