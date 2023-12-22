<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="com.lazytravel.foodscape.dao.*"%>
<%@page import="com.lazytravel.foodscape.entity.*"%>

<%@ include file="/admin/header.html" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>後臺-標籤(查詢)</title>

  <link rel="stylesheet" href="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.min.css">
  <link rel="icon" href="../static/images/logo.ico" type="image/x-icon">
</head>
<style>
    button.btn-modify {
      background-color: #9C6644;
      color: white;
    }

    a#add {
      background-color: #B7B7A4;
      color: white;
    }
   ㄧ 
    a#add:hover {
      background-color: #6B705C;
      color: white;
    }
  </style>
</head>

<body>
  <div id="header"></div>

  <div id="main" class="p-3">
    <div class="mx-3 mb-4 d-flex align-items-start">
        <h3 class="d-inline-block me-3">標籤</h3>
        <a href="${pageContext.request.contextPath}/foodscape/jsp/tag_on.jsp" id="add" class="btn">新增</a>
    </div>

        
    <div class="table-responsive mx-3">
      <table id="example" class="table table-striped" style="width:100%">
        <thead>
          <tr>
            <th scope="col">修改</th>
            <th scope="col">ID</th>            
            <th scope="col">名稱</th>
            <th scope="col">更新時間</th>
          </tr>

        </thead>
        <tbody>
          <tr>
            <td>${tag.tagId}</td>
            <td>${tag.tagName}</td>
            <td>${tag.updateTime}</td>
            <td>
              <form method="post" action="<%=request.getContextPath()%>/foodscape/jsp/tagModify.jsp">
                <button type="submit" class="btn-modify btn">修改</button>
                <input type="hidden" name="tag_id" value="">
                <input type="hidden" name="action" value="modify">
              </form>
            </td>
<!--             <td>日式</td> -->
<!--             <td>美食</td> -->
<!--             <td>2023/12/05/21:32</td> -->
<!--           </tr> -->
<!--           <tr> -->
<!--             <td> -->
<!--               <form method="post" action=""> -->
<!--                 <button type="submit" class="btn-modify btn">修改</button> -->
<!--                 <input type="hidden" name="customer_id" value=""> -->
<!--                 <input type="hidden" name="action" value="modify"> -->
<!--               </form> -->
<!--             </td> -->
<!--             <td>夜景</td> -->
<!--             <td>景點</td> -->
<!--             <td>2023/12/06/20:22</td> -->
          </tr>
        </tbody>
      </table>
    </div>

  </div>


  <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
  <script src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.min.js"></script>
  <script>
    $(function () {
      $("#header").load("header.html");
      new DataTable('#example');
    });
  </script>
</body>

</html>