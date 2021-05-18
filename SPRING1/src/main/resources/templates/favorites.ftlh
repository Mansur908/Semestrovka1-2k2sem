<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <style>
        .loginPage {
            margin: -50px 1100px;
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
        .head{
            margin: 100px 580px;
            text-align: center;
        }
        .main {
            margin: 20px 325px;
            height: 200px;
            width: 600px;
            border-radius: 10px;
            background-color: #99b1c6;
        }

        .image {
            border-radius: 15px;
            margin: 10px 10px;
        }

        .name {
            margin: -10px 200px;
            height: 30px;
            width: 400px;
            position: absolute;
            text-align: center;
        }

        .discription {
            margin: 50px 200px;
            height: 90px;
            width: 400px;
            position: absolute;
            text-align: center;
        }

        .price {
            margin: 130px 200px;
            height: 40px;
            width: 400px;
            position: absolute;
            text-align: center
        }
        .favorites{
            margin: -190px 550px;
            position: absolute;
            border: 1px solid #ffffff;
            outline: none;
            color: green;
            border-radius: 5px;
            background-color: transparent;
        }
    </style>
</head>


<body bgcolor="darkkhaki">
<a href="http://localhost:8080/products">
    <button type="button" class="loginPage">main</button>
</a>
<h1 class="head">Избранное</h1>
<#if favorites?has_content>
    <#list favorites as product>
        <div class="main">
            <div class="name"><h2> ${product.name }</h2></div>
            <div class="discription">${product.text }</div>
            <div class="price"><h2>${product.price } руб </h2></div>
            <img class="image" height="180px" width="180px" src="/img/ ${product.image }">
            <form action="/favorites/delete" method='post'>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <input type="hidden" name="productId" value="${product.id}"/>
                <input class="favorites" type="submit" value="&#10006"/>
            </form>
        </div>
    </#list>
<#else>
    <h3 align="center">Вы ничнго не добавили в избранное</h3>
</#if>
</body>
</html>