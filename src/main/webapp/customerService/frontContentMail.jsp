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

    <div class="container py-5">
        <h2 class="text-center mb-4">客服信箱</h2>
        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">編號</th>
                        <th scope="col">發送日期</th>
                        <th scope="col">發送時間</th>
                        <th scope="col">信件標題</th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th scope="row">1</th>
                        <td>2023-12-21</td>
                        <td>09:30 AM</td>
                        <td>標題內容。</td>
                        <td><a href="#" class="btn btn-secondary" class="btn-modify btn" style="background-color: #6B705C">查看詳情</a></td>
                    </tr>
                    <!-- 可以添加更多類似上面結構的表格行 -->
                </tbody>
            </table>
             <div class="text-center mt-4">
                <a href="#" class="btn btn-secondary" style="background-color: #6B705C">新增訊息</a>
            </div>
        </div>
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