<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
 <html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <title>单文件上传</title>
</head>
<body>
<form method="post" action="/upload" enctype="multipart/form-data">
    <input type="file" name="file"><br>
    <input type="submit" value="提交">
</form>
</body>
</html>
<%--
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
<meta charset="UTF-8" />
<title>Insert title here</title>
</head>
<body>
    <h1 th:inlines="text">文件上传</h1>
    <form action="multifileUpload" method="post" enctype="multipart/form-data" >
        <p>选择文件1: <input type="file" name="fileName"/></p>
        <p>选择文件2: <input type="file" name="fileName"/></p>
        <p>选择文件3: <input type="file" name="fileName"/></p>
        <p><input type="submit" value="提交"/></p>
    </form>
</body>
</html>--%>
