<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<c:set var="text">
    ${piano.name}
</c:set>

<div style="min-height: 100vh; padding-top: 120px">
    <ul>
        <c:forEach var="piano" items="${piano.id}" varStatus="loop">
            <li>
                    ${piano.name}
            </li>
        </c:forEach>
    </ul>
</div>

</body>
</html>
