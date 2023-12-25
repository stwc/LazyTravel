<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/admin/header.html"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>後台回覆客戶訊息</title>

<link rel="stylesheet"
	href="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.min.css" />
<link rel="icon" href="../../static/images/logo.ico" type="image/x-icon" />
</head>

<body>
    <header id="header"></header>

    <div class="container mt-4 p-2 mt-2 ">
        <h3 class="text-center mb-2">後台回覆客戶訊息</h3>


        <FORM METHOD="post" ACTION="CSMail.do" name="form1">
            <div class="mb-3">
                <label for="inputName" class="form-label" name="customer_name">客戶ID</label>
                <input type="text" class="form-control" id="inputName" placeholder="" required>
            </div>
            <div class="mb-3">
                <label for="inputName" class="form-label" name="customer_name">客戶姓名</label>
                <input type="text" class="form-control" id="inputName" placeholder="" required>
            </div>
            
            <div class="mb-3">
                <label for="inputComment" class="form-label">信件標題</label>
                <textarea class="form-control" id="inputComment" rows="2" placeholder="請輸入問題內容" required></textarea>
            </div>

            <div class="mb-3">
                <label for="inputComment" class="form-label">回覆內容</label>
                <textarea class="form-control" id="inputComment" rows="5" placeholder="請輸入回覆內容" required></textarea>
            </div>

            <div class="mb-3">
                <label for="inputImage" class="form-label">選擇圖片</label>
                <input type="file" class="form-control" id="inputImage" accept="image/*">
            </div>

            <div class="d-grid gap-1 col-1 mx-auto">
                <button type="button" class="btn btn-secondary" style="background-color: #6B705C">提交</button>
            </div>
        </form>
    </div>



    <script
		src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
	<script
		src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.min.js"></script>
	<script>
		$(function() {
			$("#header").load("../../admin/header.html");
			new DataTable("#example");
		});
	</script>
</body>
</html>