<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib  uri="http://shiro.apache.org/tags"  prefix="shiro"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
    table{
        width:460px;
        border:3px solid #ccc;
        border-collapse: collapse;/*表格内部单元格合并*/
        margin: auto; /*表格居中*/
        margin-top: 20px;
     }
     
     table th,table td{
          border:1px solid #ccc;
       } 
       
       span{
          width:20px;
          height:20px;
          display: inline-block;
          border:1px solid #ff6633;
          margin-left:5px;
       }
       
       span a{
           width:20px;
           height:20px;
           display: inline-block;
           text-decoration: none;
           font-size: 12px;
           text-align: center;
           line-height: 20px;
       }
       
       .on{
           background: #ccc;
           font-size: 14px;
           font-weight: 900;
       }
</style>
</head>
<body>
  <%--  <h3>欢迎您!${user.username}</h3> --%>
  <!--  在页面渲染数据到一个table中 -->
   <h2 align="center">员工列表</h2>
   <h3 align="center">欢迎您!<shiro:principal></shiro:principal><br></h3>
   <h4 align="center"><a href="${pageContext.request.contextPath}/user/logout">退出登录</a></h4>
    <!-- /工程名  /webdemo 
       跳转到添加员工的页面
    -->
    <h3 align="center">
      <a href="${pageContext.request.contextPath}/emp/toAdd">添加员工</a>
   </h3>
     <!--  
        使用js操作表单发送切换页面的请求
      -->
      <div align="center">
     <form name="searchForm"  
        action="${pageContext.request.contextPath}/emp/conditionList" method="POST">
        <input name="pageNo" type="hidden" value="1"/>
         <input name="cd"  value="${cd}"/>
         <input type="submit" value="搜索"/>
     </form>
     </div>
  <table>
      <tr>
         <th>姓名</th>
         <th>性别</th>
         <th>年龄</th>
         <th>薪资</th>
         <th>部门</th>
         <th>经理</th>
         <th>操作</th>        
      </tr>
      <c:forEach items="${pageBean.list}"  var="e">
        <tr>
         <td>${e.ename}</td>
         <td>${e.esex}</td>
         <td>${e.eage}</td>
         <td>${e.esalary}</td>
         <td>${e.dept.dname}</td>
         <td>${e.mgr.ename}</td>
         <th>
            <shiro:hasAnyRoles name="管理员">
            <a href="javascript:delEmp('${e.empno}')">删除</a>
            <a href="${pageContext.request.contextPath}/emp/toUpdate?empno=${e.empno}">修改</a>
            </shiro:hasAnyRoles>
         </th>        
      </tr>
      </c:forEach>
  </table>
   <p align="center">
      <c:forEach  begin="1"  end="${pageBean.last}"  var="no"> 
          <c:if test="${pageBean.pageNo==no}">
         <span class="on">
            <a href="javascript:changePage(${no});">${no}</a>
        </span>
          </c:if>
        <c:if test="${pageBean.pageNo!=no}">
           <span>
            <a href="javascript:changePage(${no});">${no}</a>
        </span>
          </c:if>      
      </c:forEach>     
   </p>
    <!-- restful删除请求表单 -->
    <div style="display: none;"> 
      <form name="delForm"
      action="${pageContext.request.contextPath}/emp/" method="POST">
       <input type="hidden" name="_method" value="DELETE"/>
      </form>
    </div>
    
   <script type="text/javascript">
        function changePage(no){
        	 //修改发送的页码
        	 document.searchForm.pageNo.value=no;
        	 //发送请求
        	 document.searchForm.submit();
        }
        //删除员工
        function delEmp(empno){
        	var url=document.delForm.action;
        	document.delForm.action=url+empno;
        	//console.log(document.delForm.action);//浏览器打印
        	//提交表单
        	document.delForm.submit();
        }
   </script>
</body>
</html>

