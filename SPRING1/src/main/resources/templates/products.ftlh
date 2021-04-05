<!DOCTYPE html>
<html lang="en">
<head>
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <title>Document</title>
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
<body onload="document.getElementById('but1').click()">

<script type="application/javascript">
    function search() {
        let token = $("meta[name='_csrf']").attr("content");
        let header = $("meta[name='_csrf_header']").attr("content");
        let form = $("#searchForm")[0];
        let data = getFormData($("#searchForm"));
        console.log(JSON.stringify(data));

        $.ajax({
            type: "POST",
            url: "/sneakers",
            beforeSend: function (request) {
                request.setRequestHeader(header, token);
            },
            data: JSON.stringify(data),
            processData: false,
            cache: false,
            contentType: 'application/json',
            timeout: 600000,
            success: function (data) {
                if ( document.getElementById("prod")){
                    document.getElementById("prod").remove();
                }
                $('#result').append("<div id=\"prod\" class=\"back\"></div>");
                if (data.length > 0) {
                    for (var i = data.length-1;i >= 0; i--) {
                        $('#prod').append("<div class=\"main\">"+
                        "<div class=\"name\"><h2>"+ data[i].name +"</h2></div>"+
                        "<div class=\"discription\">"+ data[i].text +"</div>"+
                        "<div class=\"price\"><h2>"+ data[i].price +" руб </h2></div>"+
                        "<img class=\"image\" height=\"180px\" width=\"180px\" src=\"/img/"+ data[i].image +"\">"+
                        "</div>");
                    }
                }
            },
            error: function (err) {
                console.log(JSON.stringify(err));
                alert(JSON.stringify(err));
            }
        });
    }
    function getFormData($form){
        var unindexed_array = $form.serializeArray();
        var indexed_array = {};

        $.map(unindexed_array, function(n, i){
            indexed_array[n['name']] = n['value'];
        });

        return indexed_array;
    }
</script>

<#--<div class="line">-->
<#--</div>-->
<#--<div class="pages">-->
<#--    <a href="http://localhost:8080/sneakers">-->
<#--        <button type="button" class="but1">Кросовки</button>-->
<#--    </a>-->
<#--    <a href="http://localhost:8080/tshorts">-->
<#--        <button type="button" class="but1">Футболки</button>-->
<#--    </a>-->
<#--    <a href="http://localhost:8080/hoodie">-->
<#--        <button type="button" class="but1">Худи</button>-->
<#--    </a>-->

<#--</div>-->
<#--<div class="back">-->
<#--<#list products as prod>-->
<#--    <div class="main">-->
<#--        <div class="name"><h2>${prod.name}</h2></div>-->
<#--        <div class="discription">${prod.text}</div>-->
<#--        <div class="price"><h2>${prod.price} руб</h2></div>-->
<#--        <img class="image" height="180px" width="180px" src="/img/${prod.image}">-->
<#--    </div>-->
<#--</#list>-->
<#--</div>-->
<form id="searchForm" action="/sneakers">
<#--    <input name="name" type="text" id="name" placeholder="Введите имя пользователя"/>-->
    <select id="name" name="name">
        <option value="">all</option>
        <option value="hoodie">hoodie</option>
        <option value="sneakers">sneakers</option>
        <option value="tshort">tshort</option>
    </select>
    <input type="number" value="1" name="page" id="page" placeholder="Введите номер страницы"/>
<#--    <input type="number" name="size" id="size" placeholder="Введите размер страницы"/>-->
    <button type="button" id="but1" onclick="search()"> Поиск</button>
</form>
<div id="result">

</div>
</body>
</html>