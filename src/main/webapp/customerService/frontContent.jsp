<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>客服信箱</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="icon" href="../static/images/logo.ico" type="image/x-icon">

</head>

<body>
    <header id="header"></header>

    <div class="container mt-4 p-2 mt-2 ">
        <h3 class="text-center mb-2">客服信箱</h3>


        <FORM METHOD="post" ACTION="CSMail.do" name="form1">
            <div class="mb-3">
                <label for="inputName" class="form-label" name="customer_name">您的姓名</label>
                <input type="text" class="form-control" id="inputName" placeholder="請輸入您的姓名" required>
            </div>

            <div class="mb-3">
                <label for="inputComment" class="form-label">信件標題</label>
                <textarea class="form-control" id="inputComment" rows="2" placeholder="請輸入問題內容" required></textarea>
            </div>
            <div class="mb-3">
                <label for="inputComment" class="form-label">問題內容</label>
                <textarea class="form-control" id="inputComment" rows="5" placeholder="請輸入問題內容" required></textarea>
            </div>

            <div class="d-grid gap-1 col-1 mx-auto">
                <button type="button" class="btn btn-secondary" class="btn-modify btn" style="background-color: #6B705C">提交</button>
            </div>
        </form>
    </div>

    <footer id="footer"></footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
    <script>
        $(function () {
            $("#header").load("../components/html/header.html");
            $("#footer").load("../components/html/footer.html");
        });
    </script>
</body>

</html>