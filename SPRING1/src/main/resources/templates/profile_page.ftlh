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
<h3>Hello ${user}! It is your Profile</h3>
<form id="fileForm" action="/profile" method="post" enctype="multipart/form-data">
<#--    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">-->
    <input type="file" name="file">
<#--    <input type="submit" value="ENTER">-->
    <button onclick="f()"  type="button">Отправить</button>
</form>
<div>
    <#if image??>
        <img height="200px" width="200px" src="/img/${image}">
    </#if>
</div>
<a href="http://localhost:8080/logout">
    <button type="button">exit</button>
</a>


<script type="application/javascript">
    function f() {
        let token = $("meta[name='_csrf']").attr("content");
        let header = $("meta[name='_csrf_header']").attr("content");
        let data = getFormData($("#fileForm"));
        console.log(JSON.stringify(data));

        $.ajax({
            type: "POST",
            url: "/profile",
            beforeSend: function (request) {
                request.setRequestHeader(header, token);
            },
            data: JSON.stringify(data),
            processData: false,
            cache: false,
            contentType: 'application/json',
            timeout: 600000,
            success: function () {
                alert(JSON.stringify(data));
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
</body>
</html>