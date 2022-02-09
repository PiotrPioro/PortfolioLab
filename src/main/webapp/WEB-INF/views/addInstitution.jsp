<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Document</title>
    <link rel="stylesheet" href="css/style.css" />
</head>
<body>

<header class="header--main-page">
    <nav class="container container--70">
        <ul class="nav--actions">
            <li>Witaj ${user.firstName}</li>
        </ul>
        <ul>
            <li><a href="/user/checkRole" class="btn btn--without-border active">Strona główna</a></li>
        </ul>
    </nav>

    <div class="slogan container container--90">
        <div class="slogan--item">
            <h1>
                Zacznij pomagać!<br/>
                Oddaj niechciane rzeczy w zaufane ręce
            </h1>
        </div>
    </div>
</header>

<section class="login-page">
    <h2>Dodaj fundacje</h2>
    <form:form method="post" modelAttribute="institution">
        <form:hidden path="id" value="${institution.id}"/>
        <div class="form-group">
            <form:input path="name" value="${institution.name}" placeholder="nazwa"/>
            <form:errors path="name"/>
        </div>
        <div class="form-group">
            <form:input path="description" value="${institution.description}" placeholder="opis"/>
            <form:errors path="description"/>
        </div>
        <div class="form-group form-group--buttons">
            <button class="btn" type="submit">Dodaj fundacje</button>
        </div>
    </form:form>
</section>

<%@include file="/WEB-INF/views/footer.jsp" %>

</body>
</html>