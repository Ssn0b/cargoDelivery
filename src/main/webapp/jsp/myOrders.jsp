<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="header.jsp"%>

<div style="text-align:center;">
    <table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">#</th>
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
    <c:forEach items="${listCategory}" var ="order">
        <tr>
            <th scope="row">${order.id}</th>
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
                    <button style="width: 230px;" type="button" name="statusButton" class="btn btn-secondary" value="${order.orderStatusName}">${order.orderStatusName}</button>
                </td>
                <td id="${order.id}">
                </td>
                <%}else if(resp.equalsIgnoreCase("Waiting for payment")){%>
            <td></td>

            <td>${order.price}$</td>
            <td>
                    <button style="width: 230px;" type="submit" name="statusButton" class="btn btn-primary" value="${order.id}">${order.orderStatusName}</button>
                </td>
                <td id="${order.id}">
                    <form method="post" id="formInvoce" action="controller?action=payByBalance">
                        <button style="width: 230px;"   type="submit" name="statusButton" class="btn btn-info btn-sm py-0 btn-block"  value="${order.id}">Pay from balance</button>
                    </form>
                    <form method="post" id="formInvoce2" action="controller?action=payByCard">
                        <button style="width: 230px;" type="submit" name="statusButton" class="btn btn-info btn-sm py-0 btn-block" value="${order.id}">Pay by card</button>
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
            </td>
                <%}%>

        </tr>
    </c:forEach>
    </tbody>
        </table>
</div>
</html>
