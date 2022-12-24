<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.core" %>
<%@ include file="header.jsp"%>
<html lang=en-GB>
<head>
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

<div class="container" style="padding:20px;background: #fff;box-shadow: 0 5px 10px rgba(0,0,0,.1);">
    <h1 style="font-size: 50px"> Receive reports </h1>
    <br><br>
    <form method="post" id="receiveReportsForm" action="controller?action=selectReports">
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

        <p style="display: inline-block; margin-right: 3px; margin-left: 25px;font-size: 20px">Order register date:</p>
        <input type="date" name="dateOfRegister" id="dateOfRegister" lang="en-GB" style="display: inline-block">
        <br><br>
        <button type="submit" name="statusButton" class="btn btn-primary"style="margin-left: 460px" >Show orders list</button>
    </form>
</div>

<br><br>
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
        <th scope="col">Date of arrival</th>
        <th scope="col">Price</th>
        <th scope="col">Order status</th>
        <th scope="col">Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${listOrders}" var ="order">
        <tr>
            <th scope="row">${order.id}</th>
            <td>${order.userName}</td>
            <td>${order.userLastName}</td>
            <td style="max-width: 200px">${order.description}</td>
            <td>${order.senderCityName}</td>
            <td>${order.receiverCityName}</td>
            <td>${order.dateOfRegister}</td>
            <c:set var="status" value="${order.orderStatusName}"/>
            <%
                String resp = "";
                resp = resp + String.valueOf(pageContext.getAttribute("status"));
            %>
            <%if (resp.equalsIgnoreCase("registered")) {%>
            <td></td>
            <td>${order.price}$</td>

            <td id="${order.id}">
                <button style="width: 230px;" type="button" name="statusButton" class="btn btn-secondary" value="${order.id}">${order.orderStatusName}</button>
            </td>
            <td id="${order.id}">
                <form method="post" id="formInvoce" action="controller?action=invoiceForPayment">
                    <button style="width: 230px;" type="submit" name="statusButton" class="btn btn-info btn-sm py-0" value="${order.id}">Form invoice for payment</button>
                </form>
                <form method="post" id="rejectOrder" action="controller?action=rejectOrder">
                    <button style="width: 230px;" type="submit" name="statusButton" class="btn btn-danger btn-sm py-0" style="display: inline-block" value="${order.id}">Reject order</button>
                </form>
            </td>
            <%}else if(resp.equalsIgnoreCase("Waiting for payment")){%>
            <td></td>

            <td>${order.price}$</td>

            <td>
                <button style="width: 230px;" type="button" name="statusButton" class="btn btn-primary" value="${order.id}">${order.orderStatusName}</button>
            </td>
            <td id="${order.id}">
                <form method="post" id="rejectOrder2" action="controller?action=rejectOrder">
                    <button style="width: 230px;" type="submit" name="statusButton" class="btn btn-danger btn-sm py-0" value="${order.id}">Reject order</button>
                </form>
            </td>
            <%}else if(resp.equalsIgnoreCase("Paid")){%>
            <td>${(order.dateOfArrival).toLocalDateTime().toLocalDate()}</td>

            <td>${order.price}$</td>

            <td>
                <button style="width: 230px;" type="button" name="statusButton" class="btn btn-success" value="${order.orderStatusName}">${order.orderStatusName}</button>
            </td>
            <td id="${order.id}">
            </td>
            <%}else if(resp.equalsIgnoreCase("Delivered")){%>
            <td>${(order.dateOfArrival).toLocalDateTime().toLocalDate()}</td>

            <td>${order.price}$</td>

            <td>
                <button style="width: 230px;" type="button" name="statusButton" class="btn btn-success" value="${order.orderStatusName}" >${order.orderStatusName}</button>
            </td>
            <td id="${order.id}">
            </td>
            <%}else {%>
            <td></td>

            <td>${order.price}$</td>

            <td>
                <button style="width: 230px;" type="button" name="statusButton" class="btn btn-danger" value="${order.orderStatusName}" >${order.orderStatusName}</button>
            </td>
            <td id="${order.id}">
                <form method="post" id="restoreOrder" action="controller?action=invoiceForPayment">
                    <button style="width: 230px;" type="submit" name="statusButton" class="btn btn-success btn-sm py-0" value="${order.id}">Restore order</button>
                </form>
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



