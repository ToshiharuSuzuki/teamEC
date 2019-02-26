package com.internousdev.magenda.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class CreateDestinationAction extends ActionSupport implements SessionAware{

	private int backFlag;
	private List<String> sexList=new ArrayList<String>();
    private Map<String,Object> session;

	public String execute(){

		if(!session.containsKey("mCategoryDTOList")){
			return "timeout";
		}

		String result=SUCCESS;

		session.remove("familyNameErrorMessageList");
		session.remove("firstNameErrorMessageList");
		session.remove("familyNameKanaErrorMessageList");
		session.remove("firstNameKanaErrorMessageList");
		session.remove("emailErrorMessageList");
		session.remove("telNumberErrorMessageList");
		session.remove("userAddressErrorMessageList");

		if(backFlag != 1) {
			session.remove("familyName");
			session.remove("firstName");
			session.remove("familyNameKana");
			session.remove("firstNameKana");
			session.remove("userAddress");
			session.remove("telNumber");
			session.remove("email");
		}

		return result;
	}

	public int getBackFlag() {
		return backFlag;
	}

	public void setBackFlag(int backFlag) {
		this.backFlag = backFlag;
	}

	public List<String>getSexList(){
		return sexList;
	}

	public void setSexList(List<String> sexList){
		this.sexList=sexList;
	}


	public Map<String,Object>getSession(){
		return session;
	}

	public void setSession(Map<String,Object>session){
		this.session=session;
	}

}