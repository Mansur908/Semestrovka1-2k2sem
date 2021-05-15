<!doctype html>
<html lang="en">
<style>
    .mainPage {
        margin: 20px 1100px;
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
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Чат</title>
    <script
            src="https://code.jquery.com/jquery-3.4.1.js"
            integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
            crossorigin="anonymous"></script>
    <script>
        let webSocket;


        function connect() {
            webSocket = new WebSocket('ws://localhost:8080/chat');
            // setTimeout(t,300);
            // document.cookie = 'AUTH=' + '1234' + ';path=/';
            //
            webSocket.onmessage = function receiveMessage(response) {
                var json = JSON.parse(response['data']);
                if (document.getElementById("del")) {
                    document.getElementById("del").remove();
                }
                $('#messagesList').append("<div id=\"del\"></div>");
                for (i = json.length - 1; i >= 0; --i) {
                    if (json[i].isAdmin === "true") {
                        $('#del').append("<li>" + "Admin:" + json[i].text + "</li>");
                    } else {
                        $('#del').append("<li>" + "You:" + json[i].text + "</li>");
                    }
                }
            }
        }

        function t() {
            if ($('#to_user').val().length < 1) {
                if (document.getElementById("del")) {
                    document.getElementById("del").remove();
                }
                $('#messagesList').append("<div id=\"del\"></div>");
                $('#del').append("<h4>Вы не ввели пользователя</h4>");
            } else {
                webSocket.send(JSON.stringify({
                    "text": "/connected",
                    "user_id": ${userid},
                    "to_user": $('#to_user').val()
                }));
            }
        }

        // function sendMessage(text, pageId) {
        //     let message = {
        //         "text" : text,
        //         "from": pageId
        //     };
        function sendMessage(text) {
            if ($('#to_user').val().length < 1) {
                if (document.getElementById("del")) {
                    document.getElementById("del").remove();
                }
                $('#messagesList').append("<div id=\"del\"></div>");
                $('#del').append("<h4>Вы не ввели пользователя</h4>");
            } else {
                let message = {
                    "text": text,
                    "user_id": ${userid},
                    "to_user": $('#to_user').val()
                };

                webSocket.send(JSON.stringify(message));
            }
        }
    </script>
</head>
<body bgcolor="darkkhaki" onload="connect()">
<a href="http://localhost:8080/admin">
    <button type="button" class="mainPage">Add product</button>
</a>
<div>
    <input id="to_user" placeholder="Введите id получателя" minlength="1" required>
    <button onclick="t()">Показать сообщения</button>
    <label for="message">Текст сообщения</label>
    <input name="message" id="message" placeholder="Сообщение">
    <button onclick="sendMessage($('#message').val())">Отправить</button>
    <h3>Сообщения</h3>
    <ul id="messagesList">

    </ul>
</div>
</body>
</html>
