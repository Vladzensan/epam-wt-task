<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>

<%--@elvariable id="test" type="entity.TestEntity"--%>
<c:set var="text">
    ${piano.name}
</c:set>
<body>

<div style="min-height: 100vh; padding-top: 120px">
    <form action="${pageContext.request.contextPath}/app/save-cart.html?id=${piano.id}" method="post">
        <c:forEach var="piano" items="${test.questions}" varStatus="loop">
            <div>
                <label for="piano-${piano.id}">${piano.name}</label>
                <input id="piano-${piano.id}" name="piano-${piano.id}" class="input-block">
            </div>
        </c:forEach>

        <button>submit</button>
    </form>
</div>

</body>
</html>
