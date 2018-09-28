package com.internousdev.kiyurumi.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.kiyurumi.dao.CartInfoDAO;
import com.internousdev.kiyurumi.dao.MCategoryDAO;
import com.internousdev.kiyurumi.dto.CartInfoDTO;
import com.internousdev.kiyurumi.dto.MCategoryDTO;
import com.internousdev.kiyurumi.util.CommonUtility;
import com.opensymphony.xwork2.ActionSupport;

public class CartAction extends ActionSupport implements SessionAware{
	private String categoryId;
	private String keywords;
	private List<MCategoryDTO> mCategoryDtoList = new ArrayList<MCategoryDTO>();
	private Map<String,Object> session;

	public String execute(){
		String result = ERROR;
		String userId = null;
		CartInfoDAO cartInfoDao = new CartInfoDAO();
		List<CartInfoDTO> cartInfoDtoList = new ArrayList<CartInfoDTO>();

		if(session.containsKey("checkListErrorMessageList")){
		session.remove("checkListErrorMessageList");
		}

//		ログインIDがあればログインIDなければtempUserIdをuserIdにいれる

			if(session.containsKey("loginId")){
				userId = String.valueOf(session.get("loginId"));

//ログインIDはあるけれど、未ログインの場合、tempUserIdがないので、ここでもう一度挿入

				if(String.valueOf(session.get("logined")).equals("0")){

					if(!(session.containsKey("cartRamdomId"))){
						CommonUtility commonUtility = new CommonUtility();
						session.put("cartRamdomId", commonUtility.getRamdomValue());
					}
					cartInfoDtoList = null;
					userId = String.valueOf(session.get("cartRamdomId"));
				}


			}else if(session.containsKey("tempUserId")){
				userId = String.valueOf(session.get("tempUserId"));
			}
//		カート情報取得
			cartInfoDtoList = cartInfoDao.getCartInfoDtoList(userId);
			Iterator<CartInfoDTO> iterator = cartInfoDtoList.iterator();
			if(!(iterator.hasNext())){
				cartInfoDtoList = null;
			}

			session.put("cartInfoDtoList",cartInfoDtoList);

//		合計金額
			int totalPrice = Integer.parseInt(String.valueOf(cartInfoDao.getTotalPrice(userId)));
			session.put("totalPrice", totalPrice);
			result = SUCCESS;

//		もしカテゴリーリストなければput
		if(!session.containsKey("mCategoryList")){
			MCategoryDAO mCategoryDao = new MCategoryDAO();
			mCategoryDtoList = mCategoryDao.getMCategoryList();
			session.put("mCategoryDtoList", mCategoryDtoList);
		}
		return result;
	}

	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public List<MCategoryDTO> getmCategoryDtoList() {
		return mCategoryDtoList;
	}
	public void setmCategoryDtoList(List<MCategoryDTO> mCategoryDtoList) {
		this.mCategoryDtoList = mCategoryDtoList;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}


}
