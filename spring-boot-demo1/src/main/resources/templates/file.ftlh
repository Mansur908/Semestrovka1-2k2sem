<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Поле загрузки файлов, которое мы заслужили</title>
    <link rel="stylesheet" href="style.css">
    <script type="text/javascript" src="jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="script.js"></script>
    <style>
        body {
            padding: 0;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        #upload-container {
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            width: 200px;
            height: 200px;
            outline: 2px dashed #5d5d5d;
            outline-offset: -12px;
            background-color: #e0f2f7;
            font-family: 'Segoe UI';
            color: #1f3c44;
        }
        #upload-container label {
            font-weight: bold;
        }

        #upload-container label:hover {
            cursor: pointer;
            text-decoration: underline;
        }

        #upload-container input[type=file] {
            width: 0.1px;
            height: 0.1px;
            opacity: 0;
            position: absolute;
            z-index: -10;
        }

    </style>
</head>
<body>
<form id="upload-container" method="POST" action="send.php">
    <div>
        <input id="file-input" type="file" name="file" multiple>
        <label for="file-input">&nbsp&nbsp&nbsp&nbsp&nbspВыберите файл</label>
        <span>или &nbsp&nbsp&nbsp&nbsp&nbspперетащите его сюда</span>
        <img height="200px" width="200px" src="C:\Users\mansu\IdeaProjects\spring-boot-demo\uploads\3dd847f6-e700-42d7-a083-affa41bb4286.DataMining1.png">
    </div>
</form>
</body>
</html>