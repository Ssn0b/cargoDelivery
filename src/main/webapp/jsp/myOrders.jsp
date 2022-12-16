<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="header.jsp"%>

<div style="text-align:center;">
    <form method="post" id="myOrdersForm" action="controller?action=payPage">

    <table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Description</th>
        <th scope="col">Sender city</th>
        <th scope="col">Receiver city</th>
        <th scope="col">Date of register</th>
        <th scope="col">Price</th>
        <th scope="col">Order status</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${listCategory}" var ="order">
        <tr>
            <th scope="row">${order.id}</th>
                <td>none</td>
                <td>${order.senderCityName}</td>
                <td>${order.senderReceiverName}</td>
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
                    <button type="submit" name="statusButton" class="btn btn-warning" value="${order.id}">${order.orderStatusName}</button>
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
    </form>
</div>
</html>
