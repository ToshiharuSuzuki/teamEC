package com.internousdev.magenda.action;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.magenda.dao.UserInfoDAO;
import com.opensymphony.xwork2.ActionSupport;

public class CreateUserCompleteAction extends ActionSupport implements SessionAware{

	private Map<String, Object> session;

	public String execute() throws SQLException{

		if(!session.containsKey("mCategoryDTOList")){
			return "timeout";
		}

		String result = ERROR;


		if(session.get("sex")=="男性"){
			session.put("sex", 0);
		}else{
			session.put("sex", 1);
		}

		UserInfoDAO UserInfoDAO = new UserInfoDAO();
		int count = UserInfoDAO.createUser(
				String.valueOf(session.get("familyName")),
				String.valueOf(session.get("firstName")),
				String.valueOf(session.get("familyNameKana")),
				String.valueOf(session.get("firstNameKana")),
				String.valueOf(session.get("sex")),
				String.valueOf(session.get("email")),
				String.valueOf(session.get("createLoginId")),
				String.valueOf(session.get("createPassword")));
		if(count > 0) {
			result = SUCCESS;
			session.remove("familyName");
			session.remove("familyNameKana");
			session.remove("firstName");
			session.remove("firstNameKana");
			session.remove("email");
			session.remove("userAddress");
			session.remove("sex");
			session.remove("familyNameErrorMessageList");
			session.remove("firstNameErrorMessageList");
			session.remove("familyNameKanaErrorMessageList");
			session.remove("firstNameKanaErrorMessageList");
			session.remove("emailErrorMessageList");
			session.remove("userLoginIdErrorMessageList");
			session.remove("passwordErrorMessageList");
		}else{
			result = ERROR;
		}

		return result;
	}


	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
