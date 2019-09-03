<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<body>
<h2>用户登录</h2>
  <c:if test=""></c:if>
	<div>
	  <h3 style='color: red'>${msg}</h3>
	</div>
<form action="${pageContext.request.contextPath}/user/login" method="POST">

  用户:<input name="username"/><br>
  密码:<input name="password" type="password"/><br>
  <label>
   <input type="checkbox" name="RememberMe" value="1">记住我的密码
   </label><br>
  <input type="submit" value="登录"/>
  
</form>
</body>
</html>

