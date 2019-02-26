package com.internousdev.magenda.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.magenda.dao.CartInfoDAO;
import com.internousdev.magenda.dto.CartInfoDTO;
import com.internousdev.magenda.dto.MCategoryDTO;
import com.opensymphony.xwork2.ActionSupport;

public class CartAction extends ActionSupport implements SessionAware{

	private String keywords;
	private List<MCategoryDTO> mCategoryDTOList = new ArrayList<MCategoryDTO>();
	private Map<String, Object> session;

	public String execute() throws SQLException{

		if(!session.containsKey("mCategoryDTOList")){
			return "timeout";
		}

		String loginId = null;
		CartInfoDAO cartInfoDAO = new CartInfoDAO();
		List<CartInfoDTO> cartInfoDTOList = new ArrayList<CartInfoDTO>();
		if(session.containsKey("loginId") && session.get("logined").equals(1)){
			loginId = String.valueOf(session.get("loginId"));
		}else if(session.containsKey("tempUserId")){
			loginId = String.valueOf(session.get("tempUserId"));
		}
		cartInfoDTOList = cartInfoDAO.getCartInfoDTOList(loginId);
		session.put("cartInfoDTOList", cartInfoDTOList);
		int totalPrice = Integer.parseInt(String.valueOf(cartInfoDAO.getTotalPrice(loginId)));
		session.put("totalPrice", totalPrice);
		return SUCCESS;
	}
	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public List<MCategoryDTO> getMCategoryDTOList() {
		return mCategoryDTOList;
	}
	public void setMCategoryDTOList(List<MCategoryDTO> mCategoryDTOList) {
		this.mCategoryDTOList = mCategoryDTOList;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
