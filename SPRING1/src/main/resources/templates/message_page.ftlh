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
    <style type="text/css">
        body {
            background-color:#99b1c6;
            background-size:100%;
            font-family: "Helvetica Neue", Helvetica, sans-serif;
            font-size: 20px;
            font-weight: normal;
            max-width: 530px;
            margin: 20px 430px;
            display: flex;
            flex-direction: column;
            position:absolute;
        }
        p {
            max-width: 255px;
            word-wrap: break-word;
            margin-bottom: 12px;
            line-height: 24px;
            position: relative;
            padding: 10px 20px;
            border-radius: 25px;
        }
        p:before, p:after {
            content:"";
            position:absolute;
            bottom:-2px;
            height:20px;
        }
        .from-me {
            color: white;
            background: #0B93F6;
            align-self: flex-end;
            margin:10px 0px 0px 250px;
        }
        from-me:before {
            right:-7px;
            border-right:20px solid #0B93F6;
            border-bottom-left-radius: 16px 14px;
            transform:translate(0, -2px);
        }
        from-me:after {
            right:0px;
            width:26px;
            background:white;
            border-bottom-left-radius: 10px;
            transform:translate(-30px, -2px);
        }
        .from-them {
            background: #E5E5EA;
            color: black;
            margin:10px 0px 0px -50px;
        }
        from-them:before {
            left:-7px;
            border-left:20px solid #E5E5EA;
            border-bottom-right-radius: 16px 14px;
            transform:translate(0, -2px);
        }
        from-them:after {
            left:4px;
            width:26px;
            background:white;
            border-bottom-right-radius: 10px;
            transform:translate(-30px, -2px);
        }
        .date-me{
            margin:-10px 370px;
            color:#000;
            width: max-content;
            font-size: 70%;
        }
        .date-them{
            margin:-10px 85px;
            color:#000;
            width: max-content;
            font-size: 70%;
        }
        .mainPage{
            margin:17px 0px 0px 700px;
            text-align:center;
            width:120px;
            height:25px;
            border:1px solid #ffffff;
            outline:none;
            color:#ffffff;
            border-radius:10px;
            background-color:transparent;
        }
        .search{
            margin:70px -60px;
        }
        .coment{
            margin:-1px -1px;
            text-align:center;
            width:597px;
            height:100px;
            border:transparent;
            outline:none;
            border-radius:10px 10px 0px 0px;
        }
        .butt{
            margin:-2px -2px;
            width:604px;
            height:50px;
            border:1px solid #000;
            background-position:7px 7px;
            outline:none;
            border-radius:0px 0px 10px 10px;
        }
    </style>
</head>
<body>
<a href="http://localhost:8080/profile">
    <button type="button" class="mainPage">main</button>
</a>

<script type="application/javascript">
    function search() {
        let token = $("meta[name='_csrf']").attr("content");
        let header = $("meta[name='_csrf_header']").attr("content");

        $.ajax({
            type: "POST",
            url: "/message",
            beforeSend: function (request) {
                request.setRequestHeader(header, token);
            },
            contentType: 'application/json',
            timeout: 600000,
            success: function (msg) {
                if ( document.getElementById("del")){
                    document.getElementById("del").remove();
                }
                if (msg.length > 0) {
                    $('#result').append("<div id=\"del\"></div>");
                    for (var i = msg.length-1;i >= 0; i--) {
                        if (msg[i].equalCookie === "true") {
                            $('#del').append("<div ><p class=\"from-me\"><font color=\"black\"><small>" + msg[i].user + "</small></font> <br/>" + msg[i].text + "</p>\n" +
                                "<p class=\"date-me\">" + msg[i].createdAt + "</p></div>");
                        }
                        else {
                            $('#del').append("<p class=\"from-them\"><font color=\"#0B93F6\"><small>" + msg[i].user + "</small></font> <br/>" + msg[i].text + "</p>\n" +
                                "<p class=\"date-them\">" + msg[i].createdAt + "</p>");
                        }
                    }
                }
            },
            error: function (err) {
                console.log(JSON.stringify(err));
                alert(JSON.stringify(err)+"search");
            }
        });
    }
    search()

    function f() {
        let token = $("meta[name='_csrf']").attr("content");
        let header = $("meta[name='_csrf_header']").attr("content");
        let data = getFormData($("#mesForm"));
        console.log(JSON.stringify(data));

        $.ajax({
            type: "POST",
            url: "/addMess",
            beforeSend: function (request) {
                request.setRequestHeader(header, token);
            },
            data: JSON.stringify(data),
            processData: false,
            cache: false,
            contentType: 'application/json',
            timeout: 600000,
            success: function () {
                search();
            },
            error: function (err) {
                console.log(JSON.stringify(err));
                alert(JSON.stringify(err)+"f");
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


<div class ="search">
    <form id="mesForm" >
        <textarea class="coment" minlength="1" name="text" id="query" placeholder="Enter message"></textarea>
        <button class="butt" onclick="f()"  type="button">Отправить</button>
    </form>
</div>
<div id="result">

</div>
</body>
</html>