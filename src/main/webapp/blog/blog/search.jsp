<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.naming.*" %>
<%@ page import="javax.sql.*" %>

<%-- <%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%> --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="com.lazytravel.blog.entity.*"%>
<%@ page import="com.lazytravel.blog.dao.*"%>
<%@ page import="com.lazytravel.customer.entity.*"%>
<%@ page import="com.lazytravel.blog.service.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.Set"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>


<%
// 獲取用戶輸入的關鍵字
String keyword = request.getParameter("keyword");

// 創建連接池對象
Context initCtx = new InitialContext();
Context envCtx = (Context) initCtx.lookup("java:comp/env");
DataSource ds = (DataSource) envCtx.lookup("jdbc/LazyTravelDB"); // 替換為您的數據庫名稱

// 獲取數據庫連接
Connection conn = ds.getConnection();

// 準備 SQL 查詢
String sql = "SELECT * FROM blog WHERE title LIKE ? ";
PreparedStatement pstmt = conn.prepareStatement(sql);
pstmt.setString(1, "%" + keyword + "%");

// 執行查詢
ResultSet rs = pstmt.executeQuery();

BlogService blogSvc = new BlogServiceImpl();
BlogClService blogClSvc = new BlogClServiceImpl();
BlogLikeService blogLikeSvc = new BlogLikeServiceImpl();
Customer customer = (Customer) session.getAttribute("customer");
Integer customerId = (customer != null) ? customer.getCustomerId() : 0;
%>

<%
// 顯示搜尋結果
while (rs.next()) {
    String title = rs.getString("title");
    String content = rs.getString("content");
%>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous" />
    <link rel="icon" href="../../static/images/logo.png" type="image/x-icon" />
    <style>
        .thumds {
            width: 18px;
        }
    </style>
</head>
<body>
    <header id="header"></header>

    <main>
        <div class="container">
            <div class="row">
                <div class="col my-3">
                    <h1 class="title">搜尋結果</h1>
                    <input type="submit" class="btn btn-primary m-1 p-1" onclick="goToHomePage()" value="回文章列表"
                                  style="background: #6B705C;border-color: transparent;color: white;width: 100px;height: 30px">
                </div>
            </div>
            
            <div class="row ">
				<div class="col-9  d-inline-flex">
					<form class="input-group w-50 h-auto m-1" METHOD="post"
						ACTION="blog.do">
						<input type="text" class="form-control my-lg-auto" name="keyword"
							placeholder="輸入景點/美食" aria-label="Recipient's username"
							aria-describedby="button-addon2" /> <input type="hidden"
							name="action" value="search">
						<button class="btn btn-outline-secondary my-auto" type="submit"
							style="background: #6B705C; border-color: transparent; color: white; border-radius: 90px;"
							id="button-addon2">搜尋看看吧</button>
					</form>
				</div>
	
					<div class="col-3 d-flex justify-content-end w-25 p-0">
					<div class="btn-group" role="group"
						aria-label="Basic radio toggle button group">
						<input type="radio" class="btn-check" name="btnradio"
							id="btnradio1" autocomplete="off" checked /> <label
							class="btn btn-outline-primary" for="btnradio1" onclick="goToHomePage()"
							style="background: #6B705C;border-color: transparent; color: white; border-radius: 90px;">文章列表</label>
						<input type="radio" class="btn-check" name="btnradio"
							id="btnradio2"
							style="background: #6B705C; #CCD5AE; border-color: transparent; color: white"
							autocomplete="off" /> <label class="btn btn-outline-primary"
							for="btnradio2"
							style="background: #6B705C; border-color: transparent; color: white; border-radius: 90px;"
							onclick=" toMyBlog()">我的文章</label> <input type="radio"
							style="background: #CCD5AE; border-color: transparent; color: white"
							class="btn-check" name="btnradio" id="btnradio3"
							autocomplete="off" /> <label class="btn btn-outline-primary"
							style="background: #6B705C; border-color: transparent; color: white; border-radius: 90px;"
							onclick="toMyBlogCl()" for="btnradio3">文章收藏</label>
					</div>
				</div>
			</div>
			</div>
	
			<hr />
            <div class="container">
            <form class="row" METHOD="post" ACTION="blog.do">
                <c:forEach var="blog" items="${searchResults}">
                
                <c:if test="${blog.blogStatus ne 0}">
                    <div class="col-md-4" id="blogCard-${blog.blogId}">
	                <div class="card" style="width: 22rem;height: 450px; margin: 10px;">
	                <img class="card-img-top" src="<%=request.getContextPath()%>/blog/blog/BlogImgReader?blogId=${blog.blogId}" style="width: 351px; height: 160px;" />
	                    <div class="card-body p">
	                        <h5 class="card-title"style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">${blog.title}</h5>
	                        <div class="d-inline-flex">
	                            <p class="h6"></p>
	                        </div>
	                        <div class="d-grid gap-2 d-md-flex justify-content-md-end"></div>
	                        <c:set var="formattedDate">
    							<fmt:formatDate value="${blog.blogDate}" pattern="yyyy-MM-dd HH:mm"/>
								</c:set>
								<p class="h6">${formattedDate}</p>
	                        <p class="card-text">${fn:substring(blog.content, 0, 25)}${fn:length(blog.content) > 25 ? '...' : ''}</p>
	                        <div class="d-flex justify-content-end align-items-center">
	                            <div class="col-md-3 pr-0">
	                                <p>${blog.customer.nickname}</p>
	                            </div>
	                            <div class="col-md-5 p-0">
	                            <img class="card-img-top" src="<%=request.getContextPath()%>/customer/ImageReader?id=${blog.customer.customerId}" style="width: 70%; height: 80px;"alt="${blog.customer.customerName}" />
	                            </div>
	                            <div class="col-md-4 d-inline-flex align-items-center">
	                                
	                                
	                                <button type="button" class="blogLikeSubmit P-1"
												data-blogid="${blog.blogId}"
												style="border: none; background: none; padding: 0;">
												<c:choose>
													<c:when test="${not empty blog.blogLikes}">
														<c:set var="foundLike" value="false" />
														<c:forEach var="blogLike" items="${blog.blogLikes}">
															<c:if
 																test="${blogLike.customer.customerId eq customer.customerId and blogLike.blogLikeStatus eq '1' and foundLike eq 'false'}"> 
<!-- 																顯示已收藏的圖示 -->
																<img src="../../static/blogimages/已讚.svg" id="updateblogLike" class="thumds m-0 blogLike"
												alt="讚" />
																<c:set var="foundLike" value="true" />
															</c:if>
														</c:forEach>
<!-- 														如果找不到收藏，顯示未收藏的圖示 -->
														<c:if test="${foundLike eq 'false'}">
															<img src="../../static/blogimages/未讚.svg" id="updateblogLike" class="thumds m-0 blogLike"
												alt="讚" />
														</c:if>
													</c:when>
													<c:otherwise>
<!-- 														顯示未收藏的圖示 -->
														<img src="../../static/blogimages/未讚.svg" id="updateblogLike" class="thumds m-0 blogLike" 
												alt="讚" />
													</c:otherwise>
												</c:choose>
												<input type="hidden" id="blogId" value="${blog.blogId}" />
											</button>
											
	                                <button type="button" class="blogClSubmit P-1"
												data-blogid="${blog.blogId}"
												style="border: none; background: none; padding: 0;">
												<c:choose>
													<c:when test="${not empty blog.blogCls}">
														<c:set var="foundFavorite" value="false" />
														<c:forEach var="blogCl" items="${blog.blogCls}">
															<c:if
																test="${blogCl.customer.customerId eq customer.customerId and blogCl.blogClStatus eq '1' and foundFavorite eq 'false'}">
																<!-- 顯示已收藏的圖示 -->
																<img src="../../static/blogimages/ON收藏.svg"
																	id="updateblogCl" class="thumds m-0 blogCl" alt="收藏" />
																<c:set var="foundFavorite" value="true" />
															</c:if>
														</c:forEach>
														<!-- 如果找不到收藏，顯示未收藏的圖示 -->
														<c:if test="${foundFavorite eq 'false'}">
															<img src="../../static/blogimages/UN收藏.svg"
																id="updateblogCl" class="thumds m-0 blogCl" alt="收藏" />
														</c:if>
													</c:when>
													<c:otherwise>
														<!-- 顯示未收藏的圖示 -->
														<img src="../../static/blogimages/UN收藏.svg"
															id="updateblogCl" class="thumds m-0 blogCl" alt="收藏" />
													</c:otherwise>
												</c:choose>
												<input type="hidden" id="blogId" value="${blog.blogId}" />
											</button>
	                                
	                                <img src="../../static/blogimages/瀏覽數.svg " class="thumds" alt="view" />
	                            <p class="m-1 p-1">${blog.viewSum}</p>
	                            </div>
	                        </div>
	                        <div class=" row d-flex justify-content-end">
	                        
	                        <input type="hidden" name="blogId" value="${blog.blogId}">
										<input type="hidden" name="action" value="getOne_For_Display">
	                        <input type="submit"  value="看更多"  class="btn btn-primary m-1 p-1" style="background: #9C6644;border-color: transparent;color: white;width: 100px;height: 30px">
	                    </div>
	                    		</div>
	                </div>
	            </div>
	            </c:if>
	        </c:forEach>
	    </form>
	    </div>
    </main>

    <footer id="footer"></footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
    	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script>
        $(function () {
            $("#header").load("../../components/html/header.jsp");
            $("#footer").load("../../components/html/footer.jsp");
        });

        function redirectToNextPage(blogId) {
            window.location.href = '<%=request.getContextPath()%>/blog/blog/blog.do?blogId=' + blogId;
        }
        
        function goToHomePage() {
            window.location.href = '<%=request.getContextPath()%>/blog/blog/blogfirst.jsp';
        }
        
        
        function toMyBlog() {
		    // 導向至 "myblog.jsp" 頁面
		    window.location.href = "myblog.jsp";
		}
		
		function toMyBlogCl() {
		    // 導向至 "myblog.jsp" 頁面
		    window.location.href = "myblogcl.jsp";
		}
        
        $(document).ready(function () {
			$(document).on('click', '.blogClSubmit', function (e){
				e.stopPropagation();
		    	var customerId = parseInt("<%=customerId%>", 10);
		    	var blogId = $(this).data('blogid');
			
		        // 檢查用戶是否已登入
		        if (customerId === 0 || customerId === "") {
		        	swal("請先登入才能收藏。", "", "warning");
		            
		            console.log(customerId);
		        } else {
		            // 使用 AJAX 將收藏的資訊傳送到後端
		            var dataToSend = {
		                "action" : "toggleFavorite",
		                blogId: $(this).data('blogid'),
		                customerId: customerId
		            };

		            $.ajax({
		                type: "POST",
		                url: "<%=request.getContextPath()%>/blog/blog/blog.do",
		                data: JSON.stringify(dataToSend),
		                contentType: "application/json; charset=utf-8",
		                dataType: "json",
		                success: function (response) {
		                	if (response.result === 'success') {
		                		var updateblogCl = $('#blogCard-' + blogId + ' .blogClSubmit .blogCl');
		                		console.log(updateblogCl)
		                		if (response.blogclstatus === '0' || response.blogclstatus === 'novalue') {
		                            updateblogCl.prop('src', "../../static/blogimages/ON收藏.svg");
		                        } else {
		                            updateblogCl.prop('src', "../../static/blogimages/UN收藏.svg");
		                        }
		                        // 處理成功的邏輯
		                        console.log('成功：', response);
		                    } else {
		                        // 處理失敗的邏輯
		                        console.error('錯誤：', response.error);
		                    }
		                }
//			                error: function (xhr, s, error) {
//			                    alert(xhr.status);
//			                    alert(xhr.readyState);
//			                }
		            });
		        }
		    });
		});

		
		$(document).ready(function () {
			$(document).on('click', '.blogLikeSubmit', function (e){
				e.stopPropagation();
		    	var customerId = parseInt("<%=customerId%>", 10);
		    	var blogId = $(this).data('blogid');
		    	console.log(updateblogLike);
			
		        // 檢查用戶是否已登入
		        if (customerId === 0 || customerId === "") {
		        	swal("請先登入才能按讚。", "", "warning");
		            console.log(customerId);
		        } else {
		            // 使用 AJAX 將收藏的資訊傳送到後端
		            var dataToSend = {
		                "action" : "toggleLike",
		                blogId: $(this).data('blogid'),
		                customerId: customerId
		            };

		            $.ajax({
		                type: "POST",
		                url: "<%=request.getContextPath()%>/blog/blog/blog.do",
		                data: JSON.stringify(dataToSend),
		                contentType: "application/json; charset=utf-8",
		                dataType: "json",
		                success: function (response) {
		                	if (response.result === 'success') {
		                		var updateblogLike = $('#blogCard-' + blogId + ' .blogLikeSubmit .blogLike');
		                		console.log(updateblogLike)
		                		if (response.blogLikeStatus === '0' || response.blogLikeStatus === 'novalue') {
		                            updateblogLike.prop('src', "../../static/blogimages/已讚.svg");
		                        } else {
		                            updateblogLike.prop('src', "../../static/blogimages/未讚.svg");
		                        }
		                        // 處理成功的邏輯
		                        console.log('成功：', response);
		                    } else {
		                        // 處理失敗的邏輯
		                        console.error('錯誤：', response.error);
		                    }
		                }
//			                error: function (xhr, s, error) {
//			                    alert(xhr.status);
//			                    alert(xhr.readyState);
//			                }
		            });
		        }
		    });
		});
    </script>
</body>
</html>

<%
}

// 關閉資源
rs.close();
pstmt.close();
conn.close();
%>