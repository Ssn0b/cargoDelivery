<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="header.jsp" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styleForIndex.css">

<div class="container"
     style="padding:20px;background: #fff;box-shadow: 0 5px 10px rgba(0,0,0,.1);  width: 500px; text-align: center;">
    <form method="post" id="profileForm" action="controller?action=payPage" class="register-form">
        <%if (userSession == 1) {%>
        <img src="${pageContext.request.contextPath}/img/user.jpg" alt="avatar"
             class="rounded-circle img-fluid" style="width: 150px;">
        <%} else {%>
        <img src="${pageContext.request.contextPath}/img/manager.jpg" alt="avatar"
             class="rounded-circle img-fluid" style="width: 150px;">
        <%}%>

        <c:set var="userProfil" value="${currentUser}"/>
        <h5 class="my-3">${userProfil.name} ${userProfil.lastname}</h5>
        <%if (userSession == 1) {%>
        <p class="text-muted mb-1"><fmt:message key="profile.user" bundle="${lang}"/></p>
        <%} else if (userSession == 2) {%>
        <p class="text-muted mb-1"><fmt:message key="profile.manager" bundle="${lang}"/></p>
        <%}%>
        <p style="font-size: 15px;"><fmt:message key="profile.fullName"
                                                 bundle="${lang}"/> ${userProfil.name} ${userProfil.lastname}</p>
        <p style="font-size: 15px;"><fmt:message key="profile.email" bundle="${lang}"/> ${userProfil.email}</p>
        <p style="font-size: 15px;"><fmt:message key="profile.telephone" bundle="${lang}"/> ${userProfil.number}</p>
        <%if (userSession == 1) {%>
        <p style="font-size: 15px;"><fmt:message key="profile.balance" bundle="${lang}"/> ${userProfil.balance}$</p>
        <div class="d-flex justify-content-center mb-2">
            <button type="submit" class="btn btn-primary"><fmt:message key="profile.button1" bundle="${lang}"/></button>
        </div>
        <%}%>
    </form>
    <form method="post" id="formInvoce" action="controller?action=changeInfoPage">
        <button style="width: 230px;" type="submit" name="statusButton" class="btn btn-info btn-sm py-0"><fmt:message
                key="profile.button2" bundle="${lang}"/></button>
    </form>

</div>

