<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <style>
        .image {
            margin: 10px 10px;
            border-radius: 15px;
            border: 1px solid black;
        }

        .image-box {
            border-radius: 15px;
            margin: 10px 10px;
            height: 200px;
            width: 200px;
        }

        .image_button {
            margin: 0px 80px;
        }

        .prof {
            margin: 100px 150px;
            height: 300px;
            width: 600px;
            border-radius: 10px;
            background-color: white;
        }

        .back {
            margin: 200px;
        }

        .prof_head {
            margin: -200px 380px;
        }

        .loginPage {
            margin: -170px 1000px;
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

        .exit {
            margin: -170px 1140px;
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

        .example-1 .form-group {
            padding: 1em;
            margin: 1em
        }

        .example-1 input[type=file] {
            outline: 0;
            opacity: 0;
            pointer-events: none;
            user-select: none
        }

        .example-1 .label {
            width: 120px;
            border: 2px dashed grey;
            border-radius: 5px;
            display: block;
            padding: 1.2em;
            transition: border 300ms ease;
            cursor: pointer;
            text-align: center
        }

        .example-1 .label i {
            display: block;
            font-size: 42px;
            padding-bottom: 16px
        }

        .example-1 .label i, .example-1 .label .title {
            color: grey;
            transition: 200ms color
        }

        .example-1 .label:hover {
            border: 2px solid #000
        }

        .example-1 .label:hover i, .example-1 .label:hover .title {
            color: #000
        }
    </style>
</head>


<body bgcolor="darkkhaki">
<a href="http://localhost:8080/products">
    <button type="button" class="loginPage">main</button>
</a>
<a href="http://localhost:8080/logout">
    <button type="button" class="exit">exit</button>
</a>
<div class="back">

    <div class="prof">
        <form method="post" enctype="multipart/form-data">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <div class="image_box">
                <#if image??>
                    <img class="image" height="200px" width="200px" src="/img/${image}">
                <#else>
                    <div class="example-1">
                        <div class="form-group">
                            <label class="label">
                                <i class="material-icons">attach</i>
                                <span class="title">Добавить фото</span>
                                <input type="file" name="file" required>
                            </label>
                        </div>
                    </div>
                    <input class="image_button" type="submit" value="ENTER"/>
                </#if>
            </div>
        </form>
        <h2 class="prof_head">${message}</h2>
        <form style="margin: 400px 330px"  method="post" action="/subscription">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <#if subscription>
                <input type="hidden" name="subscription" value=false>
                <input type="submit" value="Отписаться от рассылки">
            <#else>
                <input type="hidden" name="subscription" value=true>
                <input type="submit" value="Подписаться на рассылку">
            </#if>
        </form>

    </div>
</div>
</body>
</html>