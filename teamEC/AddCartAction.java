package com.internousdev.kiyurumi.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.kiyurumi.dao.CartInfoDAO;
import com.internousdev.kiyurumi.dto.CartInfoDTO;
import com.internousdev.kiyurumi.util.CommonUtility;
import com.opensymphony.xwork2.ActionSupport;

public class AddCartAction extends ActionSupport implements SessionAware{

	private int productId;
	private String productName;
	private String productNameKana;
	private String imageFilePath;
	private String imageFileName;
	private int price;
	private String productCount;
	private String birthPlace;
	private String birthDate;
	private String productDescription;
	private String categoryId;
	private Map<String,Object> session;


//	このActionは商品詳細(productDetails.jsp)からのみ呼び出される


	public String execute(){
		String result = ERROR;
		String userId = null;
		String tempUserId = null;
		if(session.containsKey("checkListErrorMessageList")){
		session.remove("checkListErrorMessageList");
		}

//		ログインIDもtempUserIDもなければtempUserIDにランダムの数字sessionput
		if(!(session.containsKey("loginId"))&&!(session.containsKey("tempUserId"))){
			CommonUtility commonUtility = new CommonUtility();
			session.put("tempUserId", commonUtility.getRamdomValue());
		}
//		ログインIDがあればログインIDをuserIDへ
		if(session.containsKey("loginId")){
			userId = String.valueOf(session.get("loginId"));

//ログインIDはあるけれど、未ログインの場合、tempUserIdがないので、ここでもう一度挿入してる
			if(String.valueOf(session.get("logined")).equals("0")){

				if(!(session.containsKey("cartRamdomId"))){
					CommonUtility commonUtility = new CommonUtility();
					session.put("cartRamdomId", commonUtility.getRamdomValue());
				}
				userId = String.valueOf(session.get("cartRamdomId"));
				tempUserId = String.valueOf(session.get("cartRamdomId"));
			}

		}
//		ログインIDがなくてtempUserIDがあればuserIdもtempUserIdもtempUserIdを入れる
		if(!(session.containsKey("loginId")) && session.containsKey("tempUserId")){
			userId = String.valueOf(session.get("tempUserId"));
			tempUserId = String.valueOf(session.get("tempUserId"));
		}
		productCount = String.valueOf((productCount.split(" ,",0))[0]);

		CartInfoDAO cartInfoDao = new CartInfoDAO();
//		新しくカート作成

		int count = cartInfoDao.regist(userId,tempUserId,productId,productCount,price);
		if(count > 0){
			result = SUCCESS;
		}
		List<CartInfoDTO> cartInfoDtoList = new ArrayList<CartInfoDTO>();
//		カート情報取得
		cartInfoDtoList = cartInfoDao.getCartInfoDtoList(userId);
//		もしカートの中身が空ならnull
		Iterator<CartInfoDTO> iterator = cartInfoDtoList.iterator();
		if(!(iterator.hasNext())){
			cartInfoDtoList = null;
		}
//		カート情報と合計金額をsessionput
		session.put("cartInfoDtoList", cartInfoDtoList);
		int totalPrice = Integer.parseInt(String.valueOf(cartInfoDao.getTotalPrice(userId)));
		session.put("totalPrice", totalPrice);

		return result;
	}




	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductNameKana() {
		return productNameKana;
	}

	public void setProductNameKana(String productNameKana) {
		this.productNameKana = productNameKana;
	}

	public String getImageFilePath() {
		return imageFilePath;
	}

	public void setImageFilePath(String imageFilePath) {
		this.imageFilePath = imageFilePath;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getProductCount() {
		return productCount;
	}

	public void setProductCount(String productCount) {
		this.productCount = productCount;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}