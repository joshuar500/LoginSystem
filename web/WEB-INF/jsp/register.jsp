<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

    <title>Registration</title>

    <link rel="stylesheet" type="text/css" href="/loginSystem/css/register.css">
    <script type="text/javascript" src="/datePicker/WdatePicker.js"></script>
</head>

<body>
<form class="reform" action="/register" method="post">
    <p><label>Username：</label><input type="text" name="username" value="${form.username }" />${form.errors.username }</p>
    <p><label>Password：</label><input type="password" name="password" value="${form.password }" />${form.errors.password }</p>
    <p><label>Retype Password：</label><input type="password" name="password2" value="${form.password2 }" />${form.errors.password2}</p>
    <p><label>Email：</label><input type="text" name="email" value="${form.email }" />${form.errors.email}</p>
    <p><label>Birthday：</label><input type="text" name="birthday" onclick="WdatePicker()" value="${form.birthday }" />${form.errors.birthday }</p>
    <input type="submit" value="register" />
</form>
</body>
</html>