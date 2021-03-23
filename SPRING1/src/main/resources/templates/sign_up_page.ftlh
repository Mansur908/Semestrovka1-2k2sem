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
            height:260px;
            border:1px solid #ffffff;
            border-radius:10px;
            background-color:transparent;
        }
        .name{
            text-align:center;
            margin:20px 50px;
            width:200px;
            height:30px;
            border:1px solid #000;
            background-position:7px 7px;
            outline:none;
            border-radius:10px;
        }
        .email{
            text-align:center;
            margin:-10px 50px;
            width:200px;
            height:30px;
            border:1px solid #000;
            background-position:7px 7px;
            outline:none;
            border-radius:10px;
        }
        .password{
            text-align:center;
            margin:25px 50px;
            width:200px;
            height:30px;
            border:1px solid #000;
            outline:none;
            border-radius:10px;
        }
        .enter{
            margin:-20px 110px;
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
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <h2 class="head">REGISTRATION</h2>
        <#if message?has_content>
            <p class="message" ><font color="red"><b>${message}</b></font></p>
        </#if>
        <input type="text" name="name" minlength="1" placeholder="username" class="name"/>
        <input type="email" name="email" minlength="1" placeholder="email" class="email"/>
        <input type="password" name="password" minlength="7" placeholder="password" class="password"/>
        <input type="submit" value="ENTER" class="enter"/>
    </form>
</div>
</body>
</html>