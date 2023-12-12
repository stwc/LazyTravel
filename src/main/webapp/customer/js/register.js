// Loading header and footer
$(function () {
  $("#header").load("../components/html/header.html");
  $("#footer").load("../components/html/footer.html");
});


function validateRequired() {
  let isRequired = true;

  // Email
  const email = $("#inputEmail");
  if (email.val() === "")
    email.removeClass("is-valid").addClass("is-invalid");
  else
    email.removeClass("is-invalid").addClass("is-valid");

  if (email.hasClass("is-invalid"))
    isRequired = false;

  // Password1
  const password1 = $("#inputPassword1");
  if (password1.val() === "")
    password1.removeClass("is-valid").addClass("is-invalid");
  else
    password1.removeClass("is-invalid").addClass("is-valid");

  if (password1.hasClass("is-invalid"))
    isRequired = false;

  // Password2
  const password2 = $("#inputPassword2");
  if (password2.val() === "")
    password2.removeClass("is-valid").addClass("is-invalid");
  else
    password2.removeClass("is-invalid").addClass("is-valid");

  if (password2.hasClass("is-invalid"))
    isRequired = false;

  // 姓名
  const name = $("#inputName");
  if (name.val() === "")
    name.removeClass("is-valid").addClass("is-invalid");
  else
    name.removeClass("is-invalid").addClass("is-valid");

  if (name.hasClass("is-invalid"))
    isRequired = false;

  // 暱稱
  const nickname = $("#inputNickname");
  if (nickname.val() === "")
    nickname.removeClass("is-valid").addClass("is-invalid");
  else
    nickname.removeClass("is-invalid").addClass("is-valid");

  if (nickname.hasClass("is-invalid"))
    isRequired = false;

  // 手機號碼
  const phone = $("#inputPhone");
  if (phone.val() === "")
    phone.removeClass("is-valid").addClass("is-invalid");
  else
    phone.removeClass("is-invalid").addClass("is-valid");

  if (phone.hasClass("is-invalid"))
    isRequired = false;

  // 身份證
  const idno = $("#inputIdno");
  if (idno.val() === "")
    idno.removeClass("is-valid").addClass("is-invalid");
  else
    idno.removeClass("is-invalid").addClass("is-valid");

  if (idno.hasClass("is-invalid"))
    isRequired = false;

  // 生日
  const birth = $("#inputBirth");
  if (birth.val() === "")
    birth.removeClass("is-valid").addClass("is-invalid");
  else
    birth.removeClass("is-invalid").addClass("is-valid");

  if (birth.hasClass("is-invalid"))
    isRequired = false;

  // 地址
  const address = $("#inputAddress");
  if (address.val() === "")
    address.removeClass("is-valid").addClass("is-invalid");
  else
    address.removeClass("is-invalid").addClass("is-valid");

  if (address.hasClass("is-invalid"))
    isRequired = false;

  return isRequired;
}

function validateCustomized() {
  let isValidated = true;

  // Email
  const email = $("#inputEmail");
  const emailRegex = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z]+$/;
  if (!email.val().match(emailRegex)) {
    email.removeClass("is-valid").addClass("is-invalid");
    isValidated = false;
  } else {
    email.removeClass("is-invalid").addClass("is-valid");
  }

  // Password
  const password1 = $("#inputPassword1");
  const password2 = $("#inputPassword2");
  // password1
  const passwordRegex = /^[a-zA-Z0-9]{8,24}$/;
  if (!password1.val().match(passwordRegex)) {
    password1.removeClass("is-valid").addClass("is-invalid");
    isValidated = false
  } else {
    password1.removeClass("is-invalid").addClass("is-valid");
  }
  // password2
  if (password2.val() !== password1.val()) {
    password2.removeClass("is-valid").addClass("is-invalid");
    isValidated = false
  } else {
    password2.removeClass("is-invalid").addClass("is-valid");
  }

  // 手機號碼
  const phone = $("#inputPhone");
  const phoneRegex = /^09[0-9]{8}$/;
  if (!phone.val().match(phoneRegex)) {
    phone.removeClass("is-valid").addClass("is-invalid");
    isValidated = false
  } else {
    phone.removeClass("is-invalid").addClass("is-valid");
  }

  // 身份證
  const idno = $("#inputIdno");
  const idnoRegex = /^[A-Z][1-2][0-9]{8}$/;
  if (!idno.val().match(idnoRegex)) {
    idno.removeClass("is-valid").addClass("is-invalid");
    isValidated = false
  } else {
    idno.removeClass("is-invalid").addClass("is-valid");
  }

  return isValidated;
}



$("#btn-signup").on("click", function (e) {
  e.preventDefault();
  // console.log(e.target.closest("form"));

  if (validateRequired() && validateCustomized()) {
    console.log("Validate form succeeded!");
    e.target.closest("form").submit();
  } else {
    console.log("Validate form failed...");
  }
});
