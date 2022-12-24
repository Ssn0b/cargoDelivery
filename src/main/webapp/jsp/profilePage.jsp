<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="header.jsp"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styleForIndex.css">

<div class="container" style="padding:20px;background: #fff;box-shadow: 0 5px 10px rgba(0,0,0,.1);  width: 500px; text-align: center;">
        <form method="post" id="profileForm" action="controller?action=payPage" class="register-form">
        <%if(userSession == 1){%>
        <img src="${pageContext.request.contextPath}/img/user.jpg" alt="avatar"
               class="rounded-circle img-fluid" style="width: 150px;">
        <%}else{%>
        <img src="${pageContext.request.contextPath}/img/manager.jpg" alt="avatar"
             class="rounded-circle img-fluid" style="width: 150px;">
        <%}%>

        <c:set var="userProfil" value="${currentUser}"/>
        <h5 class="my-3">${userProfil.name} ${userProfil.lastname}</h5>
        <%if(userSession == 1){%>
        <p class="text-muted mb-1">Best user</p>
        <%}else if (userSession == 2){%>
        <p class="text-muted mb-1">Best manager</p>
        <%}%>
        <p class="text-muted mb-4">Bay Area, San Francisco, CA</p>

        <p style="font-size: 15px;">Full Name: ${userProfil.name} ${userProfil.lastname}</p>
        <p style="font-size: 15px;">Email: ${userProfil.email}</p>
        <p style="font-size: 15px;">Phone: ${userProfil.number}</p>
        <p style="font-size: 15px;">Address: Bay Area, San Francisco, CA</p>
        <p style="font-size: 15px;">Balance: ${userProfil.balance}$</p>
        <div class="d-flex justify-content-center mb-2">
                <button type="submit" class="btn btn-primary">Replenish the balance</button>
        </div>
        </form>
</div>

