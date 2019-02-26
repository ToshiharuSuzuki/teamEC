package com.internousdev.magenda.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.magenda.dao.DestinationInfoDAO;
import com.opensymphony.xwork2.ActionSupport;

public class CreateDestinationCompleteAction extends ActionSupport implements SessionAware{

	private Map<String, Object> session;
	private String familyName;
	private String firstName;
	private String familyNameKana;
	private String firstNameKana;
	private String sex;
	private List<String> sexList=new ArrayList<String>();
	private String email;
	private String userAddress;
	private String telNumber;

	public String execute() throws SQLException{

		if(!session.containsKey("mCategoryDTOList")){
			return "timeout";
		}

		String result=ERROR;

		DestinationInfoDAO destinationInfoDAO=new DestinationInfoDAO();
		int count=destinationInfoDAO.insert(String.valueOf(session.get("loginId")),
				String.valueOf(session.get("familyName")),
				String.valueOf(session.get("firstName")),
				String.valueOf(session.get("familyNameKana")),
				String.valueOf(session.get("firstNameKana")),
				String.valueOf(session.get("email")),
				String.valueOf(session.get("telNumber")),
				String.valueOf(session.get("userAddress")));
		if(count>0){
			result=SUCCESS;
		}
		session.remove("familyName", familyName);
		session.remove("firstName", firstName);
		session.remove("familyNameKana", familyNameKana);
		session.remove("firstNameKana", firstNameKana);
		session.remove("email", email);
		session.remove("telNumber", telNumber);
		session.remove("userAddress", userAddress);
		return result;
	}
	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFamilyNameKana() {
		return familyNameKana;
	}

	public void setFamilyNameKana(String familyNameKana) {
		this.familyNameKana = familyNameKana;
	}

	public String getFirstNameKana() {
		return firstNameKana;
	}

	public void setFirstNameKana(String firstNameKana) {
		this.firstNameKana = firstNameKana;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	public List<String> getSexList() {
		return sexList;
	}

	public void setSexList(List<String> sexList) {
		this.sexList = sexList;
	}

}
