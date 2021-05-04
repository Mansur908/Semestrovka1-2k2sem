<!doctype html>
<html lang="en">
<style>
    .mainPage{
        margin:20px 1100px;
        position: absolute;
        text-align:center;
        width:120px;
        height:25px;
        border:1px solid #ffffff;
        outline:none;
        color:#ffffff;
        border-radius:10px;
        background-color:transparent;
    }
</style>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body bgcolor="darkkhaki">
<a href="http://localhost:8080/admin">
    <button type="button" class="mainPage">Add product</button>
</a>
<form action="/sendSms" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <input type="text" name="number" placeholder="телефон">
    <input type="text" name="text" placeholder="текст">
    <input type="submit" value="Отправить">
</form>
</body>
</html>