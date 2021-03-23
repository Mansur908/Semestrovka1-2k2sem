<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        body{
            background-color: darkkhaki;
        }
        .back{
            margin: 200px;
        }
        .line{
            margin: -30px -8px;
            height: 70px;
            background-color: white;
        }
        .main{
            margin: 20px 150px;
            height: 200px;
            width: 600px;
            border-radius: 10px ;
            background-color:#99b1c6;
        }
        .image{
            border-radius: 15px;
            margin: 10px 10px;
        }
        .name{
            margin: -10px 200px;
            height: 30px;
            width: 400px;
            /*border: 3px solid #000000;*/
            position: absolute;
            text-align: center;
        }
        .discription{
            margin: 50px 200px;
            height: 90px;
            width: 400px;
            /*border: 1px solid #000000;*/
            position: absolute;
            text-align: center;
        }
        .price{
            margin: 130px 200px;
            height: 40px;
            width: 400px;
            /*border: 1px solid #000000;*/
            position: absolute;
            text-align: center
        }
        .pages{
            margin: 100px 300px;
            height: 30px;
            width: 700px;
            position: absolute;
        }
        .but1{
            margin: 0px -3px;
            height: 30px;
            width: 233px;
            border-left: 1px solid #000000;
            border-top: 2px solid #000000;
            border-bottom: 2px solid #000000;
            border-right: 1px solid #000000 ;
            background-color:transparent;
            outline:none;
        }
    </style>
</head>
<body>
<div class="line">
</div>
<div class="pages">
    <a href="http://localhost:8080/sneakers">
        <button type="button" class="but1">Кросовки</button>
    </a>
    <a href="http://localhost:8080/tshorts">
        <button type="button" class="but1">Футболки</button>
    </a>
    <a href="http://localhost:8080/hoodie">
        <button type="button" class="but1">Худи</button>
    </a>

</div>
<div class="back">
<#list products as prod>
    <div class="main">
        <div class="name"><h2>${prod.name}</h2></div>
        <div class="discription">${prod.text}</div>
        <div class="price"><h2>${prod.price} руб</h2></div>
        <img class="image" height="180px" width="180px" src="/img/${prod.image}">
    </div>
</#list>
</div>
</body>
</html>