package com.internousdev.magenda.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.magenda.dao.CartInfoDAO;
import com.internousdev.magenda.dao.ProductInfoDAO;
import com.internousdev.magenda.dto.CartInfoDTO;
import com.internousdev.magenda.dto.ProductInfoDTO;
import com.internousdev.magenda.util.CommonUtility;
import com.opensymphony.xwork2.ActionSupport;

public class AddCartAction extends ActionSupport implements SessionAware{

	private int productId;
	private String productCount;
	public Map<String, Object> session;

	public String execute() throws SQLException{

		if(!session.containsKey("mCategoryDTOList")){
			return "timeout";
		}

		String result = ERROR;
		String loginId = null;
		String tempUserId = null;

		if(productId!=(Integer.parseInt(String.valueOf(session.get("productId"))))){
			return ERROR;
		}

		if(!(session.containsKey("loginId")) && !(session.containsKey("tempUserId"))){
			CommonUtility commonUtility = new CommonUtility();
			session.put("tempUserId", commonUtility.getRamdomValue());
		}
	    if(session.containsKey("loginId")){
		loginId = String.valueOf(session.get("loginId"));
	    }else{
		loginId = String.valueOf(session.get("tempUserId"));
	    tempUserId = String.valueOf(session.get("tempUserId"));
		}

	    ProductInfoDAO productInfoDAO=new ProductInfoDAO();
	    ProductInfoDTO productInfoDTO=productInfoDAO.getProductInfo(productId);

		int intProductCount=Integer.parseInt(productCount);
    	CartInfoDAO cartInfoDAO=new CartInfoDAO();
		int count=0;
		if(intProductCount > 0){
		if(cartInfoDAO.isExistsCartInfo(loginId, productId)){
			 count=cartInfoDAO.updateProductCount(loginId, productId, intProductCount);
		}else{
			 count=cartInfoDAO.addProduct(loginId,tempUserId,productId,intProductCount,productInfoDTO.getPrice());
		}
		}
		if(count > 0){
			List<CartInfoDTO> cartInfoDTOList = new ArrayList<CartInfoDTO>();
			cartInfoDTOList = cartInfoDAO.getCartInfoDTOList(loginId);
			Iterator<CartInfoDTO> iterator = cartInfoDTOList.iterator();
			if(!(iterator.hasNext())){
				cartInfoDTOList = null;
			}
			session.put("cartInfoDTOList", cartInfoDTOList);
			int totalPrice=Integer.parseInt(String.valueOf(cartInfoDAO.getTotalPrice(loginId)));
			session.put("totalPrice",totalPrice);
			 result = SUCCESS;
		}

		return result;
	}
	public String getProductCount() {
		return productCount;
	}

	public void setProductCount(String productCount) {
		this.productCount = productCount;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}


	public Map<String, Object> getSession(){
		return session;
	}

	public void setSession(Map<String, Object> session){
		this.session = session;
	}
}