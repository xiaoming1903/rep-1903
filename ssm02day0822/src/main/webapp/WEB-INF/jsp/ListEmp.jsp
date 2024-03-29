<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
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
  <!--  在页面渲染数据到一个table中 -->
   <h2 align="center">员工列表</h2>
   <h3 align="center">
    <!-- /工程名  /webdemo 
       跳转到添加员工的页面
    -->
      <a href="${pageContext.request.contextPath}/emp/toAdd">添加员工</a>
   </h3>
     <!--  
        使用js操作表单发送切换页面的请求
      -->
      <div align="center">
     <form name="searchForm"  
        action="${pageContext.request.contextPath}/emp/conditionList" method="POST">
        <input name="pageNo" type="hidden" value="1"/>
         <input name="condition"  value="${condition}"/>
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
            <a href="#">删除</a>
            <a href="#">修改</a>
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
    
    
   <script type="text/javascript">
        function changePage(no){
          location.href="${pageContext.request.contextPath}/emp/list?pageNo="+no+"&pageSize=3";
        }
        //删除员工
        
   </script>
</body>
</html>