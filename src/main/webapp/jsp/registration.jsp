<%@ include file="header.jsp" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags"%>
<tf:title titleName="Registration"/>

<body class="text-center">
<input type="hidden" id="status" value="<%=request.getAttribute("status")%>">

<main class="form-signin w-100 m-auto">
    <h1 class="h3 mb-3 fw-normal"><fmt:message key="signup.signup" bundle="${lang}"/></h1>
    <form method="post" action="controller?action=register" class="register-form"
          id="login-form" lang="en">
        <div class="form-floating">
            <input type="text" class="form-control" id="name" name="name" placeholder="name"/>
            <label for="name"><fmt:message key="signup.name" bundle="${lang}"/></label>
        </div>
        <div class="form-floating">
            <input type="text" class="form-control" id="lastName" name="lastName" placeholder="name"/>
            <label for="lastName"><fmt:message key="signup.lastName" bundle="${lang}"/></label>
        </div>
        <div class="form-floating">
            <input type="text" class="form-control" id="email" name="email" placeholder="Email"/>
            <label for="email"><fmt:message key="signup.emailAddress" bundle="${lang}"/></label>
        </div>
        <div class="form-floating">
            <input type="password" class="form-control" name="pass" id="pass" placeholder="Password"/>
            <label for="pass"><fmt:message key="signup.password" bundle="${lang}"/></label>
        </div>
        <div class="form-floating">
            <input type="password" class="form-control" name="confirm_pass" id="confirm_pass"
                   placeholder="Confirm password"/>
            <label for="confirm_pass"><fmt:message key="signup.confirmPassword" bundle="${lang}"/></label>
        </div>
        <div class="form-floating">
            <input type="text" class="form-control" name="contact" id="contact" placeholder="Contact number">
            <label for="contact"><fmt:message key="signup.telephone" bundle="${lang}"/></label>
        </div>
        <br>
        <div class="form-group form-button">
            <input type="submit" name="signup" id="signup"
                   class="w-100 btn btn-lg btn-primary" value="<fmt:message key="signup.button" bundle="${lang}"/>"/>
        </div>
    </form>
    <c:set var="message" value="${message}"/>
    <p style="font-size: 16px;font-style: italic;color: red" id="message">${message}</p>

</main>


<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
        integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.min.js"
        integrity="sha384-IDwe1+LCz02ROU9k972gdyvl+AESN10+x7tBKgc9I5HFtuNz0wWnPclzo6p9vxnk"
        crossorigin="anonymous"></script>
</body>
</html>
