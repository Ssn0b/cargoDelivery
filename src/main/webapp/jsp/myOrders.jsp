<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="header.jsp" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags"%>
<tf:title titleName="My orders"/>

<div style="text-align:center;">
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col"><fmt:message key="myOrders.number" bundle="${lang}"/></th>
            <th scope="col"><fmt:message key="myOrders.description" bundle="${lang}"/></th>
            <th scope="col"><fmt:message key="myOrders.senderCity" bundle="${lang}"/></th>
            <th scope="col"><fmt:message key="myOrders.receiverCity" bundle="${lang}"/></th>
            <th scope="col"><fmt:message key="myOrders.dateOfRegister" bundle="${lang}"/></th>
            <th scope="col"><fmt:message key="myOrders.dateOfArrival" bundle="${lang}"/></th>
            <th scope="col"><fmt:message key="myOrders.price" bundle="${lang}"/></th>
            <th scope="col"><fmt:message key="myOrders.status" bundle="${lang}"/></th>
            <th scope="col"><fmt:message key="myOrders.action" bundle="${lang}"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listCategory}" var="order">
            <tr>
                <th scope="row">${order.id}</th>
                <td style="max-width: 200px">${order.receiverNum}</td>
                <td style="max-width: 200px">${order.description}</td>

                <%
                    String lang = "";
                    lang = lang + session.getAttribute("lang");
                %>

                <%if (lang.equalsIgnoreCase("en")){%>
                <td>${order.senderCityName}</td>
                <%}else{%>
                <td>${order.senderCityNameUa}</td>
                <%}%>
                <%if (lang.equalsIgnoreCase("en")){%>
                <td>${order.receiverCityName}</td>
                <%}else{%>
                <td>${order.receiverCityNameUa}</td>
                <%}%>
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
                    <button style="width: 230px;" type="button" name="statusButton" class="btn btn-secondary"
                            value="${order.orderStatusName}"><fmt:message key="myOrders.registered"
                                                                          bundle="${lang}"/></button>
                </td>
                <td id="${order.id}">
                </td>
                <%} else if (resp.equalsIgnoreCase("Waiting for payment")) {%>
                <td></td>

                <td>${order.price}$</td>
                <td>
                    <button style="width: 230px;" type="submit" name="statusButton" class="btn btn-primary"
                            value="${order.id}"><fmt:message key="myOrders.waitingForPayment"
                                                             bundle="${lang}"/></button>
                </td>
                <td id="${order.id}">
                    <form method="post" id="formInvoce" action="controller?action=payByBalance">
                        <button style="width: 230px;" type="submit" name="statusButton"
                                class="btn btn-info btn-sm py-0 btn-block" value="${order.id}"><fmt:message
                                key="myOrders.payFromBalance" bundle="${lang}"/></button>
                    </form>
                    <form method="post" id="formInvoce2" action="controller?action=payByCard">
                        <button style="width: 230px;" type="submit" name="statusButton"
                                class="btn btn-info btn-sm py-0 btn-block" value="${order.id}"><fmt:message
                                key="myOrders.payByCard" bundle="${lang}"/></button>
                    </form>
                </td>
                <%} else if (resp.equalsIgnoreCase("Paid")) {%>
                <td>${(order.dateOfArrival).toLocalDateTime().toLocalDate()}</td>

                <td>${order.price}$</td>
                <td>
                    <button style="width: 230px;" type="button" name="statusButton" class="btn btn-success"
                            value="${order.orderStatusName}"><fmt:message key="myOrders.paid"
                                                                          bundle="${lang}"/></button>
                </td>
                <td id="${order.id}">
                </td>
                <%} else if (resp.equalsIgnoreCase("Delivered")) {%>
                <td>${(order.dateOfArrival).toLocalDateTime().toLocalDate()}</td>
                <td>${order.price}$</td>
                <td>
                    <button style="width: 230px;" type="button" name="statusButton" class="btn btn-success"
                            value="${order.orderStatusName}"><fmt:message key="myOrders.delivered"
                                                                          bundle="${lang}"/></button>
                </td>
                <td id="${order.id}">
                </td>
                <%} else {%>
                <td></td>
                <td>${order.price}$</td>
                <td>
                    <button style="width: 230px;" type="button" name="statusButton" class="btn btn-danger"
                            value="${order.orderStatusName}"><fmt:message key="myOrders.declined"
                                                                          bundle="${lang}"/></button>
                </td>
                <td id="${order.id}">
                </td>
                <%}%>

            </tr>
        </c:forEach>
        </tbody>
    </table>
    <%--For displaying Previous link except for the 1st page --%>
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
            <c:if test="${currentPage != 1}">
                <li class="page-item"><a style=" color: black;" class="page-link"
                                         href="controller?action=myOrdersPage&page=${currentPage - 1}"><fmt:message
                        key="pagination.previous" bundle="${lang}"/></a></li>
            </c:if>

            <%--For displaying Page numbers. The when condition does not display
                        a link for the current page--%>

            <table>
                <tr>
                    <c:forEach begin="1" end="${noOfPages}" var="i">
                        <c:choose>
                            <c:when test="${currentPage eq i}">
                                <li class="page-item"><a class="page-link">${i}</a></li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item"><a style=" color: black;" class="page-link"
                                                         href="controller?action=myOrdersPage&page=${i}">${i}</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </tr>
            </table>

            <%--For displaying Next link --%>

            <c:if test="${currentPage lt noOfPages}">
                <li class="page-item">
                    <a style=" color: black;" class="page-link"
                       href="controller?action=myOrdersPage&page=${currentPage + 1}"><fmt:message key="pagination.next"
                                                                                                  bundle="${lang}"/></a>
                </li>
            </c:if>


        </ul>
    </nav>
</div>
</html>
