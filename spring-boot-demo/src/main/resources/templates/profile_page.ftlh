<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<h3>Hello ${message}! It is your Profile</h3>
<form action="/profile" method="post" enctype="multipart/form-data">
<input type="file" name="file">
<input type="submit" value="ENTER">
</form>
<div>
    <#if image??>
    <img height="200px" width="200px" src="/img/${image}">
    </#if>
</div>
</body>
</html>