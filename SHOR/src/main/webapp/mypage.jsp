<%--
  Created by IntelliJ IDEA.
  User: Петро
  Date: 21.06.2016
  Time: 18:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <title>SHORTY</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

</head>

<form name="test" method="POST" action="/">
    <input name="shorten_url" type="text" size ="40"  >
    <input name="submit_short" type="submit" value="Short" >
    <input type="text" readonly ="readonly" name="short_LINK" value=${tinylink}  >


</form>


</html>
