<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="header.jsp" %>
<html lang=en-GB>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styleForIndex.css">

<%--
<br><br><br>
--%>
    <%
  String senderParameter = session.getAttribute("senderParameter").toString();
  String receiverParameter = session.getAttribute("receiverParameter").toString();
  String dateParameter = session.getAttribute("dateParameter").toString();
%>
<form method="post" id="receiveReportsForm" action="controller?action=selectReports">
    <div class="container"
         style="max-width: 1515px;padding:20px;width: 1515px;background: #fff;box-shadow: 0 5px 10px rgba(0,0,0,.1);">
        <p style="display: inline-block; margin-right: 45px; font-size: 30px"><fmt:message key="processOrders.receiveReports" bundle="${lang}"/></p>
        <%
            String lang = "";
            lang = lang + session.getAttribute("lang");
        %>
        <p style="display: inline-block; margin-right: 3px; font-size: 20px"><fmt:message key="processOrders.citySender" bundle="${lang}"/></p>
        <%if (!senderParameter.isEmpty()) {%>
        <input list="encodings1" style="width: 150px" value="${senderParameter}" name="sender" id="sender"
               class="col-2   custom-select" size="1">
        <%} else {%>
        <input list="encodings1" style="width: 150px" value="" name="sender" id="sender" class="col-2   custom-select"
               size="1">
        <%}%>

        <%if (lang.equalsIgnoreCase("en")) {%>
        <datalist id="encodings1">
            <c:forEach items="${listCities}" var="city">
                <option id="${city.idRegion}" value="${city.name}"></option>
            </c:forEach>
        </datalist>
        <%}else{%>
        <datalist id="encodings1">
            <c:forEach items="${listCities}" var="city">
                <option id="${city.idRegion}" value="${city.name_ua}"></option>
            </c:forEach>
        </datalist>
        <%}%>
        <p style="display: inline-block; margin-right: 3px; margin-left: 25px;font-size: 20px"><fmt:message key="processOrders.cityReceiver" bundle="${lang}"/></p>
        <%if (!receiverParameter.isEmpty()) {%>
        <input list="encodings" value="${receiverParameter}" style="width: 150px" name="receiver" id="receiver"
               class="col-2    custom-select " size="1">
        <%} else {%>
        <input list="encodings" value="" style="width: 150px" name="receiver" id="receiver"
               class="col-2    custom-select " size="1">
        <%}%>
        <%if (lang.equalsIgnoreCase("en")) {%>
        <datalist id="encodings">
            <c:forEach items="${listCities}" var="city">
                <option id="${city.idRegion}" value="${city.name}"></option>
            </c:forEach>
        </datalist>
        <%}else{%>
        <datalist id="encodings">
            <c:forEach items="${listCities}" var="city">
                <option id="${city.idRegion}" value="${city.name_ua}"></option>
            </c:forEach>
        </datalist>
        <%}%>
        <p style="display: inline-block; margin-right: 3px; margin-left: 25px;font-size: 20px"><fmt:message
                key="processOrders.registerDate" bundle="${lang}"/></p>
        <%if (!dateParameter.isEmpty()) {%>
        <input type="date" name="dateOfRegister" id="dateOfRegister" lang="en-GB" style="display: inline-block"
               value="${dateParameter}">
        <%} else {%>
        <input type="date" name="dateOfRegister" id="dateOfRegister" lang="en-GB" style="display: inline-block">
        <%}%>
        <button type="submit" name="statusButton" class="btn btn-primary"
                style="margin-left: 10px;display: inline-block;top: 8px;position: relative"><fmt:message key="processOrders.button" bundle="${lang}"/></button>
    </div>

    <%--
    <br><br>
    --%>
    <div style="text-align:center;">

        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col"><fmt:message key="processOrders.firstName" bundle="${lang}"/></th>
                <th scope="col"><fmt:message key="processOrders.lastName" bundle="${lang}"/></th>
                <th scope="col"><fmt:message key="processOrders.receiverNum" bundle="${lang}"/></th>
                <th scope="col"><fmt:message key="processOrders.description" bundle="${lang}"/></th>
                <th scope="col"><fmt:message key="processOrders.senderCity" bundle="${lang}"/></th>
                <th scope="col"><fmt:message key="processOrders.receiverCity" bundle="${lang}"/></th>
                <th scope="col"><fmt:message key="processOrders.registerDate2" bundle="${lang}"/></th>
                <th scope="col"><fmt:message key="processOrders.arrivalDate" bundle="${lang}"/></th>
                <th scope="col"><fmt:message key="processOrders.price" bundle="${lang}"/></th>
                <th scope="col"><fmt:message key="processOrders.status" bundle="${lang}"/></th>
                <th scope="col"><fmt:message key="processOrders.action" bundle="${lang}"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listOrders}" var="order">
                <tr>
                    <th scope="row">${order.id}</th>
                    <td>${order.userName}</td>
                    <td>${order.userLastName}</td>
                    <td style="max-width: 200px">${order.receiverNum}</td>
                    <td>${order.description}</td>
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
                                value="${order.id}"><fmt:message key="processOrders.registered"
                                                                 bundle="${lang}"/></button>
                    </td>
                    <td id="${order.id}">
                        <form method="post" id="blank3" action="controller?action=invoiceForPayment">
                        </form>
                        <form method="post" id="formInvoice" action="controller?action=invoiceForPayment">
                            <button style="width: 230px;" type="submit" name="statusButton"
                                    class="btn btn-info btn-sm py-0" value="${order.id}"><fmt:message
                                    key="processOrders.formInvoice" bundle="${lang}"/></button>
                        </form>
                        <form method="post" id="rejectOrder" action="controller?action=rejectOrder">
                            <button style="width: 230px;" type="submit" name="statusButton"
                                    class="btn btn-danger btn-sm py-0" style="display: inline-block"
                                    value="${order.id}"><fmt:message key="processOrders.reject"
                                                                     bundle="${lang}"/></button>
                        </form>
                    </td>
                    <%} else if (resp.equalsIgnoreCase("Waiting for payment")) {%>
                    <td></td>

                    <td>${order.price}$</td>

                    <td>
                        <button style="width: 230px;" type="button" name="statusButton" class="btn btn-primary"
                                value="${order.id}"><fmt:message key="processOrders.waitingForPayment"
                                                                 bundle="${lang}"/></button>
                    </td>
                    <td id="${order.id}">
                        <form method="post" id="blank2" action="controller?action=invoiceForPayment">
                        </form>
                        <form method="post" id="rejectOrder2" action="controller?action=rejectOrder">
                            <button style="width: 230px;" type="submit" name="statusButton"
                                    class="btn btn-danger btn-sm py-0" value="${order.id}"><fmt:message
                                    key="processOrders.reject" bundle="${lang}"/></button>
                        </form>
                    </td>
                    <%} else if (resp.equalsIgnoreCase("Paid")) {%>
                    <td>${(order.dateOfArrival).toLocalDateTime().toLocalDate()}</td>

                    <td>${order.price}$</td>

                    <td>
                        <button style="width: 230px;" type="button" name="statusButton" class="btn btn-success"
                                value="${order.orderStatusName}"><fmt:message key="processOrders.paid"
                                                                              bundle="${lang}"/></button>
                    </td>
                    <td id="${order.id}">
                    </td>
                    <%} else if (resp.equalsIgnoreCase("Delivered")) {%>
                    <td>${(order.dateOfArrival).toLocalDateTime().toLocalDate()}</td>

                    <td>${order.price}$</td>

                    <td>
                        <button style="width: 230px;" type="button" name="statusButton" class="btn btn-success"
                                value="${order.orderStatusName}"><fmt:message key="processOrders.delivered"
                                                                              bundle="${lang}"/></button>
                    </td>
                    <td id="${order.id}">
                    </td>
                    <%} else {%>
                    <td></td>

                    <td>${order.price}$</td>

                    <td>
                        <button style="width: 230px;" type="button" name="statusButton" class="btn btn-danger"
                                value="${order.orderStatusName}"><fmt:message key="processOrders.declined"
                                                                              bundle="${lang}"/></button>
                    </td>
                    <td id="${order.id}">
                        <form method="post" id="blank" action="controller?action=rejectOrder">
                        </form>
                        <form method="post" id="restoreOrder" action="controller?action=invoiceForPayment">
                            <button style="width: 230px;" type="submit" name="statusButton"
                                    class="btn btn-success btn-sm py-0" value="${order.id}"><fmt:message
                                    key="processOrders.restore" bundle="${lang}"/></button>
                        </form>
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
                    <li class="page-item">
                        <button type="submit" name="action1" value="${currentPage - 1}" style=" color: black;"
                                formaction="controller?action=selectReports&page=${currentPage - 1}" class="page-link">
                            <fmt:message key="pagination.previous" bundle="${lang}"/></button>
                            <%--
                                                <a style=" color: black;" class="page-link" href="controller?action=selectReports&page=${currentPage - 1}">Previous</a>
                            --%>
                    </li>
                </c:if>

                <%--For displaying Page numbers. The when condition does not display
                            a link for the current page--%>
                <table>
                    <tr>
                        <c:forEach begin="1" end="${noOfPages}" var="i">
                            <c:choose>
                                <c:when test="${currentPage eq i}">
                                    <li class="page-item">
                                            <%--
                                                                                <a  class="page-link">${i}</a>
                                            --%>
                                        <button type="submit" name="action1" value="${i}"
                                                class="page-link">${i}</button>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item">
                                            <%--
                                                                                <a style=" color: black;" class="page-link" href="controller?action=selectReports&page=${i}">${i}</a>

                                            --%>
                                        <button type="submit" name="action1" value="${i}" style=" color: black;"
                                                formaction="controller?action=selectReports&page=${i}"
                                                class="page-link">${i}</button>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </tr>
                </table>

                <%--For displaying Next link --%>

                <c:if test="${currentPage lt noOfPages}">
                    <li class="page-item">
                        <button type="submit" name="action1" value="${currentPage + 1}" style=" color: black;"
                                formaction="controller?action=selectReports&page=${currentPage + 1}" class="page-link">
                            <fmt:message key="pagination.next" bundle="${lang}"/></button>
                            <%--
                                                <a style=" color: black;" class="page-link" href="controller?action=selectReports&page=${currentPage + 1}">Next</a>
                            --%>
                    </li>
                </c:if>
            </ul>
        </nav>
    </div>
</form>

<script type="text/javascript">
    $(function () {
        $('#datepicker').datepicker();
    });
</script>



