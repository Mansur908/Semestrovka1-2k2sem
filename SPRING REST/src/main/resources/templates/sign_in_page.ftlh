<!DOCTYPE html>
<html lang="en">
<#import "spring.ftl" as spring/>
<head>
    <meta charset="UTF-8">
    <title>login</title>
    <style>
        .main {
            margin: 0px 0px;
            background-size: 100%;
            background-color: cornflowerblue;
        }

        .login {
            margin: 200px 500px;
            width: 300px;
            height: 220px;
            border: 1px solid #ffffff;
            border-radius: 10px;
            background-color: transparent;
        }

        .name {
            text-align: center;
            margin: 15px 50px;
            width: 200px;
            height: 30px;
            border: 1px solid #000;
            background-position: 7px 7px;
            outline: none;
            border-radius: 10px;
        }

        .password {
            text-align: center;
            margin: -10px 50px;
            width: 200px;
            height: 30px;
            border: 1px solid #000;
            outline: none;
            border-radius: 10px;
        }

        .enter {
            margin: 17px 110px 10px;
            text-align: center;
            width: 80px;
            height: 25px;
            border: 1px solid #ffffff;
            outline: none;
            color: #ffffff;
            border-radius: 10px;
            background-color: transparent
        }

        .sign {
            margin: 0px;
            text-align: center;
            color: #ffffff
        }

        .message {
            margin: 5px;
            text-align: center;
        }

        .reg {
            margin: 0px 94px;
            text-align: center;
            width: 115px;
            height: 25px;
            border: 1px solid #ffffff;
            outline: none;
            color: #ffffff;
            border-radius: 10px;
            background-color: transparent
        }

        .enter:active {
            margin: 16px 110px;
        }

        .reg:active {
            margin: -12px 94px;
        }
    </style>
</head>
<body class="main">
<h3><a href="?lang=ru">РУ</a></h3>
<h3><a href="?lang=en">EN</a></h3>
<div class="login">
    <form action="/signin" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <h2 class="sign"><@spring.message 'sign_in_page.login.header'/></h2>
        <#if message?has_content>
            <p class="message"><font color="red"><b>${message}</b></font></p>
        </#if>
        <input id="login1" type="email" minlength="1" name="email"
               placeholder=<@spring.message 'sign_in_page.login.placeholder'/> class="name"/>
        <input type="password" name="password" minlength="1"
               placeholder=<@spring.message 'sign_in_page.password.placeholder'/> class="password"/>
        <label>
            <input type="checkbox" name="remember-me"><@spring.message 'sign_in_page.remember_me'/>
        </label>
        <input type="submit" value=<@spring.message 'sign_in_page.enter'/> class="enter"/>
        <a href="http://localhost:8080/signup">
            <button type="button" class="reg"><@spring.message 'sign_in_page.registration'/></button>
        </a>
    </form>
</div>
<script type="text/javascript">
    function max() {
        document.getElementById('login1').setAttribute("maxlength", "30");
    }

    max()
</script>
</body>
</html>