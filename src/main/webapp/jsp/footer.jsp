<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.core" %>
<%@ include file="header.jsp"%>
<!doctype html>
<html lang="en">


<head>
    <title>Datepicker</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
</head>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styleForIndex.css">

<br><br><br>

<div class="container" style="background-color: #F8F8FF; padding: 20px; width: 950px; border: 3px solid #000000;">
    <h1 style="font-size: 50px"> Receive reports </h1>
    <br><br>
    <form method="post" id="receiveReportsForm" action="controller?action=selectReports">
        <h3 style="display: inline-block; margin-right: 105px">Route:</h3>
        <p style="display: inline-block; margin-right: 3px; font-size: 20px">City sender:</p>
        <input list="encodings1" value="" name="sender" id="sender" class="col-2   custom-select">
        <datalist id="encodings1">
            <option selected>To..</option>
            <c:forEach items="${listCities}" var ="city">
                <option id="${city.idRegion}" value="${city.name}"></option>
            </c:forEach>
        </datalist>

        <p style="display: inline-block; margin-right: 3px; margin-left: 25px;font-size: 20px">City receiver:</p>
        <input list="encodings" value="" name="receiver" id="receiver" class="col-2    custom-select ">
        <datalist id="encodings">
            <option selected>To..</option>
            <c:forEach items="${listCities}" var ="city">
                <option id="${city.idRegion}" value="${city.name}"></option>
            </c:forEach>
        </datalist>

        <br><br>


        <h3 style="display: inline-block; margin-right: 105px">Order register date:</h3>
        <input type="date" name="dateOfRegister" id="dateOfRegister" lang="en-GB" style="display: inline-block">

        <button type="submit" name="statusButton" class="btn btn-warning" >Press</button>
    </form>
</div>

        <div class="row form-group">
            <div class="col-sm-4">
                <div class="input-group date" id="datepicker">
                    <input type="text" class="form-control">
                    <span class="input-group-append">
                            <span class="input-group-text bg-white">
                                <i class="fa fa-calendar"></i>
                            </span>
                        </span>
                </div>
            </div>
        </div>

<div style="text-align:center;">

<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">First name</th>
        <th scope="col">Last name</th>
        <th scope="col">Description</th>
        <th scope="col">Sender city</th>
        <th scope="col">Receiver city</th>
        <th scope="col">Date of register</th>
        <th scope="col">Price</th>
        <th scope="col">Order status</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${listOrders}" var ="order">
        <tr>
            <th scope="row">${order.id}</th>
            <td>${order.userName}</td>
            <td>${order.userLastName}</td>
            <td>none</td>
            <td>${order.senderCityName}</td>
            <td>${order.receiverCityName}</td>
            <td>${order.dateOfRegister}</td>
            <td>${order.price}$</td>
            <c:set var="status" value="${order.orderStatusName}"/>
            <%
                String resp = "";
                resp = resp + String.valueOf(pageContext.getAttribute("status"));
            %>
            <%if (resp.equalsIgnoreCase("registered")) {%>
            <td id="${order.id}">
                <button type="button" name="statusButton" class="btn btn-secondary" value="${order.orderStatusName}">${order.orderStatusName}</button>
            </td>
            <%}else if(resp.equalsIgnoreCase("Waiting for payment")){%>
            <td>
                <button type="button" name="statusButton" class="btn btn-warning" value="${order.id}">${order.orderStatusName}</button>
            </td>
            <%}else if(resp.equalsIgnoreCase("Paid")){%>
            <td>
                <button type="button" name="statusButton" class="btn btn-success" value="${order.orderStatusName}">${order.orderStatusName}</button>
            </td>
            <%}else {%>
            <td>
                <button type="button" name="statusButton" class="btn btn-danger" value="${order.orderStatusName}" >${order.orderStatusName}</button>
            </td>
            <%}%>

        </tr>
    </c:forEach>
    </tbody>
</table>
</div>

<script type="text/javascript">
    $(function() {
        $('#datepicker').datepicker();
    });
</script>


</html>