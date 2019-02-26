package com.internousdev.magenda.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.magenda.dao.CartInfoDAO;
import com.internousdev.magenda.dao.UserInfoDAO;
import com.internousdev.magenda.dto.CartInfoDTO;
import com.internousdev.magenda.dto.UserInfoDTO;
import com.internousdev.magenda.util.InputChecker;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements SessionAware{

	private String loginId;
	private String password;
	private boolean savedLoginId;
	private Map<String, Object> session;
	@SuppressWarnings("unchecked")

	public String execute()throws SQLException{

		if(!session.containsKey("mCategoryDTOList")){
			return "timeout";
		}

		String result=ERROR;

		List<String> loginIdErrorMessageList=new ArrayList<String>();
		List<String> passwordErrorMessageList=new ArrayList<String>();
		session.put("loginIdErrorMessageList", "");
		session.put("passwordErrorMessageList", "");
		session.put("tempLoginId", loginId);


		if(savedLoginId==true){
			session.put("savedLoginId", true);
			session.put("checkedLoginId", loginId);
			session.remove("tempLoginId");
		}
		else{
			session.put("savedLoginId", false);
			session.remove("checkedLoginId");
		}

		if(session.containsKey("createLoginId") && session.containsKey("createPassword")){
			loginId=String.valueOf(session.get("createLoginId"));
			password=String.valueOf(session.get("createPassword"));
		}
		session.remove("createLoginId");
		session.remove("createPassword");

		InputChecker inputChecker = new InputChecker();
		loginIdErrorMessageList = inputChecker.doCheck("ログインID", loginId, 1, 8, true, false, false, true, false, false, false, false, false);
		passwordErrorMessageList = inputChecker.doCheck("パスワード", password, 1, 16, true, false, false, true, false, false, false, false, false);

		if(loginIdErrorMessageList.size()!=0 || passwordErrorMessageList.size()!=0){
			session.put("loginIdErrorMessageList", loginIdErrorMessageList);
			session.put("passwordErrorMessageList", passwordErrorMessageList);
			session.put("logined", 0);
			return ERROR;
		}

		UserInfoDAO userInfoDAO=new UserInfoDAO();
		UserInfoDTO userInfoDTO=userInfoDAO.getUserInfo(loginId,password);

		if (!userInfoDAO.isExistsUserInfo(loginId,password)) {
			session.put("loginIdErrorMessageList", "ユーザーIDまたはパスワードが異なります。");
			session.remove("passwordErrorMessageList", passwordErrorMessageList);
			session.put("logined", 0);
			return ERROR;
		} else {
			if(userInfoDAO.login(loginId, password) > 0) {

				List<CartInfoDTO> cartInfoDTOList  = new ArrayList<CartInfoDTO>();

				cartInfoDTOList = (List<CartInfoDTO>) session.get("cartInfoDTOList");
				if (cartInfoDTOList != null) {
					changeCartInfo(cartInfoDTOList);
				}

				if (session.containsKey("cartFlg")) {
					session.remove("cartFlg");
					result = "cart";
				} else {
					result = SUCCESS;
				}
				session.put("loginId", userInfoDTO.getLoginId());
				session.put("logined", 1);
			}

		}

		return result;
	}

	private void changeCartInfo(List<CartInfoDTO> cartInfoDTOList) throws SQLException {
		CartInfoDAO cartInfoDAO = new CartInfoDAO();

     	String tempUserId = session.get("tempUserId").toString();

		for (CartInfoDTO dto : cartInfoDTOList) {
			if (cartInfoDAO.isExistsCartInfo(loginId, dto.getProductId())) {
				cartInfoDAO.updateProductCount(loginId, dto.getProductId(), dto.getProductCount());
				cartInfoDAO.delete(String.valueOf(dto.getProductId()), tempUserId);
			} else {
				 cartInfoDAO.linkToLoginId(tempUserId,loginId);
				 break;
			}
		}


			List<CartInfoDTO> newCartInfoDTOList = cartInfoDAO.getCartInfoDTOList(loginId);

			session.put("cartInfoDTOList", newCartInfoDTOList);

			int totalPrice = cartInfoDAO.getTotalPrice(loginId);
			session.put("totalPrice",totalPrice);
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

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}



}
