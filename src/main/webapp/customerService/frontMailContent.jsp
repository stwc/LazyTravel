<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>訊息內容</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="icon" href="../static/images/logo.ico" type="image/x-icon">
</head>
<style>
        .message-container {
            margin-bottom: 20px;
        }

        .message-box {
            margin-bottom: 10px;
            padding: 10px;
            border-radius: 8px;
        }

        .my-message {
            background-color: #faf5f2;
            text-align: right;
        }

        .other-message {
            background-color: #f0f0f0;
            text-align: left;
        }
    </style>


<body>
<header id="header"></header>
    <div class="container py-5">
        <h2 class="text-center mb-4">訊息內容</h2>

        <!-- 這裡是您的訊息 -->
        <div class="message-container">
            <h5 class="text-end">黃喵咪</h5>
            <div class="message-box my-message">
                <p>這裡是您的訊息內容</p>
                <p>2023-12-21 09:30 AM</p>
            </div>
        </div>

        <!-- 這裡是對方的回覆 -->
        <div class="message-container">
            <h5>客服</h5>
            <div class="message-box other-message">
                <p>這裡是對方的回覆內容</p>
                <p>2023-12-22 10:00 AM</p>
            </div>
        </div>
        
        <div class="mb-3">
                <label for="inputComment" class="form-label">回覆內容</label>
                <textarea class="form-control" id="inputComment" rows="5" placeholder="請輸入回覆內容" required></textarea>
            </div>

    </div>
    <div class="d-grid gap-1 col-1 mx-auto">
        <button type="button" class="btn btn-secondary" style="background-color: #6B705C">回覆</button>
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