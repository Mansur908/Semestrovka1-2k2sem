<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        .d {
            margin: 100px 450px;
            height: 200px;
            width: 400px;
            border: 1px solid black;
        }

        .d1 {
            margin: -50px 30px;
            height: 300px;
            width: 1250px;
            text-align: center;
            /*border: 1px solid black;*/
        }

        .e1 {
            margin: 15px 100px 10px;
            text-align: center;
            width: 200px;
            height: 30px;
            border: 1px solid #000;
            background-position: 7px 7px;
            outline: none;
            border-radius: 10px;
        }

        .sub {
            margin: 30px 160px;
            text-align: center;
            width: 80px;
            height: 25px;
            border: 1px solid #ffffff;
            outline: none;
            color: #ffffff;
            border-radius: 10px;
            background-color: transparent
        }

        .mes {
            position: center;
            text-align: center;
        }

        .mainPage {
            margin: -80px 1100px;
            position: absolute;
            text-align: center;
            width: 120px;
            height: 25px;
            border: 1px solid #ffffff;
            outline: none;
            color: #ffffff;
            border-radius: 10px;
            background-color: transparent;
        }
    </style>
</head>
<body bgcolor="darkkhaki">
<a href="http://localhost:8080/admin">
    <button type="button" class="mainPage">Add Products</button>
</a>
<div class="d">
    <#if message??>
        <h3 class="mes">${message}</h3>
    </#if>
    <form action="/admin/delete" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input class="e1" required type="text" name="id" placeholder="Id товара"/>
        <input class="sub" type="submit" value="DELETE"/>
    </form>
</div>
<div class="d1">
    ${json}
</div>
</body>
</html>