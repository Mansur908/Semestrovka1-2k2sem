<#--<!doctype html>-->
<#--<html lang="en">-->
<#--<head>-->
<#--    <meta charset="UTF-8">-->
<#--    <meta name="viewport"-->
<#--          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">-->
<#--    <meta http-equiv="X-UA-Compatible" content="ie=edge">-->
<#--    <title>Document</title>-->
<#--</head>-->
<#--<body>-->
<#--<form action="/signup" method="post">-->
<#--    <input name="email" type="email" placeholder="EMAIL">-->
<#--    <input name="password" type="password" placeholder="PASSWORD">-->
<#--    <input type="submit" value="SIGN UP">-->
<#--</form>-->
<#--<form action="/signin" method="get">-->
<#--    <input type="submit" value="LOGIN">-->
<#--</form>-->
<#--</body>-->
<#--</html>-->
<#--<#ftl encoding="utf-8">-->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
    <style>
        .main{
            margin:0px 0px;
            background-size:100%;
            background-color: cadetblue;
        }
        .regB{
            margin:160px 500px;
            width:300px;
            height:220px;
            border:1px solid #ffffff;
            border-radius:10px;
            background-color:transparent;
        }
        .name{
            text-align:center;
            margin:30px 50px;
            width:200px;
            height:30px;
            border:1px solid #000;
            background-position:7px 7px;
            outline:none;
            border-radius:10px;
        }
        .password{
            text-align:center;
            margin:-10px 50px;
            width:200px;
            height:30px;
            border:1px solid #000;
            outline:none;
            border-radius:10px;
        }
        .enter{
            margin:25px 110px;
            text-align:center;
            width:80px;
            height:25px;
            border:1px solid #ffffff;
            outline:none;
            color:#ffffff;
            border-radius:10px;
            background-color:transparent
        }
        .head{
            margin:0px;
            text-align:center;
            color:#ffffff
        }
        .message{
            margin:5px;
            text-align:center;
        }
        .loginPage{
            margin:17px 0px 0px 1100px;
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
</head>
<body class="main">
<a href="http://localhost:8080/signin">
    <button type="button" class="loginPage">login</button>
</a>
<div class = "regB">
    <form action="/signup" method="post">
        <h2 class="head">REGISTRATION</h2>
        <#if message?has_content>
            <p class="message" ><font color="red"><b>${message}</b></font></p>
        </#if>
        <input type="email" name="email" placeholder="email" class="name"/>
        <input type="password" name="password" placeholder="password" class="password"/>
        <input type="submit" value="ENTER" class="enter"/>
    </form>
</div>
</body>
</html>