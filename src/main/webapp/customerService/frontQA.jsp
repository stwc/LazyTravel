<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

  <!DOCTYPE html>
  <html lang="en">

  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>問題Q&A</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="icon" href="../static/images/logo.ico" type="image/x-icon">

  </head>

  <body>
    <header id="header"></header>

    <div class="container mt-5">
      <div class="row">
        <div class="col-md-12 text-center mb-3">
          <h2>常見問題Q&A</h2>
        </div>
      </div>
      <div class="row align-items-center">
        <div class="col-md-6 offset-md-3 mb-2">
          <form class="search-container d-flex">
            <input class="form-control me-2" type="search" placeholder="搜尋問題" aria-label="Search">
            <button type="button" class="btn btn-secondary" class="btn-modify btn" style="background-color: #6B705C">go</button>
          </form>
        </div>
      </div>
      <!-- 其他內容... -->
    </div>


    </h2>
    <div class="row">
      <div class="col-md-8 offset-md-2">
        <div class="accordion" id="faqAccordion">
          <!-- 問題及答案項目 1 -->
          <div class="accordion-item">
            <h2 class="accordion-header" id="headingOne">
              <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne"
                aria-expanded="true" aria-controls="collapseOne">
                問題 1: 是否可以先付訂金？
              </button>
            </h2>
            <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne"
              data-bs-parent="#faqAccordion">
              <div class="accordion-body">
                答案 1: 國內旅遊商品須繳付全額款項。
              </div>
            </div>
          </div>

          <!-- 問題及答案項目 2 -->
          <div class="accordion-item">
            <h2 class="accordion-header" id="headingTwo">
              <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                問題 2: 可以帶寵物同行嗎？
              </button>
            </h2>
            <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo"
              data-bs-parent="#faqAccordion">
              <div class="accordion-body">
                答案 2:為維護全體旅客權益，恕不開放寵物共乘。
              </div>

            </div>
          </div>
          <!-- 問題及答案項目 3 -->
          <div class="accordion-item">
            <h2 class="accordion-header" id="headingThree">
              <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                問題 3: 是否有特殊膳食需要？
              </button>
            </h2>
            <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree"
              data-bs-parent="#faqAccordion">
              <div class="accordion-body">
                答案 3: 如果有特殊膳食需求，請在預訂前與我們聯繫，我們會盡力滿足您的需求。
              </div>
            </div>
          </div>

          <!-- 問題及答案項目 4 -->
          <div class="accordion-item">
            <h2 class="accordion-header" id="headingFour">
              <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                data-bs-target="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                問題 4: 退款政策是什麼？
              </button>
            </h2>
            <div id="collapseFour" class="accordion-collapse collapse" aria-labelledby="headingFour"
              data-bs-parent="#faqAccordion">
              <div class="accordion-body">
                答案 4: 我們提供特定條件下的退款政策。請參閱我們的網站上的退款政策頁面以獲取詳細信息。
              </div>
            </div>
          </div>

          <!-- 問題及答案項目 5 -->
          <div class="accordion-item">
            <h2 class="accordion-header" id="headingFive">
              <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                data-bs-target="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
                問題 5: 旅程中的裝備提供情況？
              </button>
            </h2>
            <div id="collapseFive" class="accordion-collapse collapse" aria-labelledby="headingFive"
              data-bs-parent="#faqAccordion">
              <div class="accordion-body">
                答案 5: 我們會提供部分必要的旅行裝備。其他個人裝備需要您自行攜帶。詳細信息可在旅行前與我們聯繫以了解。
              </div>
            </div>
          </div>

        </div>
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
        $("#header").load("../components/html/header.jsp");
        $("#footer").load("../components/html/footer.jsp");
      });
    </script>
  </body>

  </html>