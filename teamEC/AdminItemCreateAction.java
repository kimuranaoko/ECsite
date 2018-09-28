package com.internousdev.kiyurumi.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.kiyurumi.dao.MCategoryDAO;
import com.internousdev.kiyurumi.dao.ProductInfoDAO;
import com.internousdev.kiyurumi.dto.MCategoryDTO;
import com.opensymphony.xwork2.ActionSupport;

public class AdminItemCreateAction extends ActionSupport implements SessionAware{

	private Map<String,Object> session;
	private int id;
	private int productId;
	private String productName;
	private String productNameKana;
	private String productDescription;
	private int categoryId;
	private int price;
	private String imageFilePath;
	private String imageFileName;
	private String birthDate;
	private String birthPlace;
	private int status;
	private Date registDate;
	private String itemImageFileName;
	private String itemImage;
	private List<MCategoryDTO> mCategoryDtoList = new ArrayList<MCategoryDTO>();

	public String execute() throws ParseException{
		String result=ERROR;

		// キーが存在するか確認
		if (!session.containsKey("mCategoryList")) {
			MCategoryDAO mCategoryDao = new MCategoryDAO();
			mCategoryDtoList = mCategoryDao.itemCreateMCategoryList();
			session.put("mCategoryDtoList", mCategoryDtoList);
		}

		session.remove("productNameErrorMessageList");
		session.remove("productNameKanaErrorMessageList");
		session.remove("productDescriptionErrorMessageList");
		session.remove("priceErrorMessageList");
		session.remove("birthPlaceErrorMessageList");
		session.remove("birthDateErrorMessageList");
		session.remove("imageFileNameErrorMessageList");
		session.remove("productIdErrorMessageList");
		session.remove("itemImageFileNameErrorMessageList");
		if(productId==0){
			ProductInfoDAO productInfoDAO=new ProductInfoDAO();
			int productId=productInfoDAO.checkProductIdMAX();
			productId = productId+1;
			session.put("productId", productId);
		}else{
		session.put("productId",productId);
		}
		session.put("productName",productName);
		session.put("productNameKana",productNameKana);
		session.put("productDescription",productDescription);
		session.put("categoryId",categoryId);
		session.put("price",price);
		session.put("imageFilePath",imageFilePath);
		session.put("imageFileName",imageFileName);
		session.put("birthDate",birthDate);
		session.put("birthPlace",birthPlace);
		session.put("registDate",registDate);
		session.put("itemImageFileName", itemImageFileName);
		session.put("itemImage", itemImage);

		result=SUCCESS;
		return result;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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

	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
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

	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getBirthPlace() {
		return birthPlace;
	}
	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	public Date getRegistDate() {
		return registDate;
	}
	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}

	public String getItemImageFileName() {
		return itemImageFileName;
	}

	public void setItemImageFileName(String itemImageFileName) {
		this.itemImageFileName = itemImageFileName;
	}
	public String getItemImage() {
		return itemImage;
	}

	public void setItemImage(String itemImage) {
		this.itemImage = itemImage;
	}

	public List<MCategoryDTO> getmCategoryDtoList() {
		return mCategoryDtoList;
	}
	public void setmCategoryDtoList(List<MCategoryDTO> mCategoryDtoList) {
		this.mCategoryDtoList = mCategoryDtoList;
	}

}
