package com.internousdev.magenda.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.magenda.dao.MCategoryDAO;
import com.internousdev.magenda.dao.UserInfoDAO;
import com.internousdev.magenda.dto.MCategoryDTO;
import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport implements SessionAware{

	private List<MCategoryDTO> mCategoryDTOList = new ArrayList<MCategoryDTO>();
	private Map<String, Object> session;

	public String execute() throws SQLException{

		if(!session.containsKey("mCategoryDTOList")){
			return "timeout";
		}

		UserInfoDAO userInfoDAO=new UserInfoDAO();
		String loginId=String.valueOf(session.get("loginId"));
		boolean savedLoginId=Boolean.valueOf(String.valueOf(session.get("savedLoginId")));
		int count = userInfoDAO.logout(loginId);
		if(count>0){
			session.clear();
			if(savedLoginId){
				session.put("savedLoginId", savedLoginId);
				session.put("checkedLoginId", loginId);
			}
		}

		if(!session.containsKey("mCategoryList")) {
			MCategoryDAO mCategoryDAO = new MCategoryDAO();
			mCategoryDTOList = mCategoryDAO.getMCategoryList();
			session.put("mCategoryDTOList", mCategoryDTOList);
		}
		return SUCCESS;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
