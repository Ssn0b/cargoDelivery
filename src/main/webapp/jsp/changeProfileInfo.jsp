<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="header.jsp"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styleForIndex.css">

<div class="container" style="padding:20px;background: #fff;box-shadow: 0 5px 10px rgba(0,0,0,.1);  width: 500px; text-align: center;">
    <form method="post" id="changeInfo" action="controller?action=changeInfo">
    <%if(userSession == 1){%>
    <img src="${pageContext.request.contextPath}/img/user.jpg" alt="avatar"
         class="rounded-circle img-fluid" style="width: 150px;">
    <%}else{%>
    <img src="${pageContext.request.contextPath}/img/manager.jpg" alt="avatar"
         class="rounded-circle img-fluid" style="width: 150px;">
    <%}%>

    <c:set var="userProfil" value="${currentUser}"/>
    <h5 class="my-3">${userProfil.name} ${userProfil.lastname}</h5>
    <br>
    <h5 class="my-3"style="text-align: left;margin-left: 15px">Please input changes:</h5>

    <p style="font-size: 15px;text-align: left;margin-left: 15px">Full Name:<em> ${userProfil.name} ${userProfil.lastname}</em></p>

    <div id="dvChangeName" style="text-align: left;margin-left: 15px">
      <p style="display: inline-block;margin-right: 10px;font-size: 14px">First name:</p>
      <input style="width: 100px;display: inline-block;" type="text" class="form-control" id="changeName" name="changeName" placeholder="John"/>
      <p style="display: inline-block;margin-left: 15px; margin-right: 3px;font-size: 14px">Last name:</p>
      <input style="width: 100px;display: inline-block;" type="text" class="form-control" id="changeLastName" name="changeLastName" placeholder="Snow"/>
    </div>
    <br>
    <p style="font-size: 15px;text-align: left;margin-left: 15px">Email: <em>${userProfil.email}</em></p>

    <div id="dvChangeEmail" style="text-align: left;margin-left: 15px">
      <p style="display: inline-block; margin-right: 3px;font-size: 16px">New email:</p>
      <input style="width: 200px;display: inline-block;" type="text" class="form-control" id="changeEmail" name="changeEmail" placeholder="example@gmail.com"/>
    </div>
    <br>
    <p style="font-size: 15px;text-align: left;margin-left: 15px">Phone: <em>${userProfil.number}</em></p>

    <div id="dvChangePhone" style="text-align: left;margin-left: 15px">
      <p style="display: inline-block; margin-right: 3px;font-size: 16px">New phone number:</p>
      <input style="width: 200px;display: inline-block;" type="text" class="form-control" id="changePhone" name="changePhone" placeholder="+380690000000"/>
    </div>
    <br>
    <div id="dvChangePassword" style="text-align: left;margin-left: 15px">
      <p style="display: inline-block; margin-right: 3px;font-size: 16px">New password:</p>
      <input style="width: 200px;display: inline-block;" type="text" class="form-control" id="changePassword" name="changePassword"/>
    </div>
<br>
    <div id="dvCurrPassword">
        <p style="display: inline-block; margin-right: 3px;font-size: 14px">To confirm the changes you must specify the current password</p>
        <input style="width: 200px;display: inline-block;" type="text" class="form-control" id="currentPass" name="currentPass" required/>
    </div>
    <br>
        <button style="width: 230px;" type="submit" name="statusButton" class="btn btn-info btn-sm py-0">Change account information</button>
    </form>
</div>

