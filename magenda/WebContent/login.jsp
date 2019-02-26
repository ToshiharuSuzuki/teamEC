<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
java.util.Calendar objCal1=java.util.Calendar.getInstance();
java.util.Calendar objCal2=java.util.Calendar.getInstance();
objCal2.set(1970,0,1,0,0,0);
response.setDateHeader("Last-Modified",objCal1.getTime().getTime());
response.setDateHeader("Expires",objCal2.getTime().getTime());
response.setHeader("progma","no-cache");
response.setHeader("Cache-Control","no-cache");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/magenda.css">
<title>ログイン</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div id="contents">
		<h1>ログイン画面</h1>
		<s:form action="LoginAction">
			<s:if test="!#session.loginIdErrorMessageList.isEmpty()">
				<div class="error">
					<div class="error-message">
						<s:iterator value="#session.loginIdErrorMessageList">
							<s:property />
							<br>
						</s:iterator>
					</div>
				</div>
			</s:if>
			<s:if test="!#session.passwordErrorMessageList.isEmpty()">
				<div class="error">
					<div class="error-message">
						<s:iterator value="#session.passwordErrorMessageList">
							<s:property />
							<br>
						</s:iterator>
					</div>
				</div>
			</s:if>
			<table class="vertical-list-table">
				<tr>
					<th scope="row"><s:label value="ユーザーID:" /></th>
					<s:if test="#session.savedLoginId==true">
						<td><s:textfield name="loginId" class="txt"
								placeholder="ユーザーID" value="%{#session.checkedLoginId}"
								autocomplete="off" /></td>
					</s:if>
					<s:else>
						<td><s:textfield name="loginId" class="txt"
								placeholder="ユーザーID" value="%{#session.tempLoginId}"
								autocomplete="off" /></td>
					</s:else>
				</tr>
				<tr>
					<th scope="row"><s:label value="パスワード:" /></th>
					<td><s:password name="password" class="txt"
							placeholder="パスワード" value="" autocomplete="off" /></td>
				</tr>
			</table>
			<div class="login_check_box">
				<s:if test="#session.savedLoginId==true">
					<s:checkbox name="savedLoginId" checked="checked" />
				</s:if>
				<s:else>
					<s:checkbox name="savedLoginId" />
				</s:else>
				<s:label value="ユーザーID保存" />
				<br>
			</div>
			<div class="submit_btn_box">
				<s:submit value="ログイン" class="submit_btn" />
			</div>
		</s:form>
		<br>
		<div class="login_btn_box">
			<div class="submit_btn_box">
				<div>
					<s:form action="CreateUserAction">
						<s:submit value="新規ユーザー登録" class="submit_btn" />
					</s:form>
				</div>
			</div>
			<div class="submit_btn_box">
				<div>
					<s:form action="ResetPasswordAction">
						<s:submit value="パスワード再設定" class="submit_btn" />
					</s:form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>