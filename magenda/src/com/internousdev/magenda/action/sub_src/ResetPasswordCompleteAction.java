package com.internousdev.magenda.action;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.magenda.dao.UserInfoDAO;
import com.opensymphony.xwork2.ActionSupport;

public class ResetPasswordCompleteAction extends ActionSupport implements SessionAware {

	private Map<String, Object> session;

	public String execute() throws SQLException{

		if(!session.containsKey("mCategoryDTOList")){
			return "timeout";
		}

		String result = ERROR;

		session.remove("loginIdErrorMessageList");
		session.remove("passwordErrorMessageList");
		session.remove("passwordIncorrectErrorMessageList");
		session.remove("newPasswordErrorMessageList");
		session.remove("reConfirmationNewPasswordErrorMessageList");
		session.remove("newPasswordIncorrectErrorMessageList");
		session.remove("resetLoginId");

		UserInfoDAO userInfoDAO = new UserInfoDAO();
		int count = userInfoDAO.resetPassword(String.valueOf(session.get("loginId")), String.valueOf(session.get("newPassword")));
		if (count > 0) {
			result = SUCCESS;
		} else {
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
