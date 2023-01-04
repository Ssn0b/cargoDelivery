<%@ include file="header.jsp" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<link rel="stylesheet" href="${pageContext.request.contextPath}/css/pay.css">

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styleForIndex.css">


<body>

<div class="container" style="background-color: #F8F8FF;   ">

    <form method="post" id="myOrdersForm" action="controller?action=pay">

        <div class="row">

            <div class="col">
                <h3 class="title"><fmt:message key="card.billingAddress" bundle="${lang}"/></h3>
                <div class="inputBox">
                    <span><fmt:message key="card.fullName" bundle="${lang}"/></span>
                    <input type="text" name="name" id="name" placeholder="john snow">
                </div>
                <div class="inputBox">
                    <span><fmt:message key="card.email" bundle="${lang}"/></span>
                    <input type="text" name="email" placeholder="example@example.com">
                </div>
                <div class="inputBox">
                    <span><fmt:message key="card.address" bundle="${lang}"/></span>
                    <input type="text" name="address" placeholder="street, number of building">
                </div>
                <div class="inputBox">
                    <span><fmt:message key="card.city" bundle="${lang}"/></span>
                    <input type="text" name="city" placeholder="lviv">
                </div>

                <div class="flex">
                    <div class="inputBox">
                        <span><fmt:message key="card.state" bundle="${lang}"/></span>
                        <input type="text" name="state" placeholder="ukraine">
                    </div>
                    <div class="inputBox">
                        <span><fmt:message key="card.zip" bundle="${lang}"/></span>
                        <input type="text" name="zip" placeholder="12345">
                    </div>
                </div>

            </div>

            <div class="col">
                <h3 class="title"><fmt:message key="card.payment" bundle="${lang}"/></h3>
                <div class="inputBox">
                    <span><fmt:message key="card.accepted" bundle="${lang}"/></span>
                    <img src="${pageContext.request.contextPath}/img/card_img.png" alt="">
                </div>
                <div class="inputBox">
                    <span><fmt:message key="card.nameOnCard" bundle="${lang}"/></span>
                    <input type="text" name="nameOnCard" placeholder="mr. john snow">
                </div>
                <div class="inputBox">
                    <span><fmt:message key="card.number" bundle="${lang}"/></span>
                    <input type="number" name="card" placeholder="1111-2222-3333-4444">
                </div>
                <div class="inputBox">
                    <span><fmt:message key="card.month" bundle="${lang}"/></span>
                    <input type="text" name="month" placeholder="january">
                </div>

                <div class="flex">
                    <div class="inputBox">
                        <span><fmt:message key="card.year" bundle="${lang}"/></span>
                        <input type="number" name="year" placeholder="2024">
                    </div>
                    <div class="inputBox">
                        <span>cvv :</span>
                        <input type="text" name="cvv" placeholder="123">
                    </div>
                </div>

            </div>

        </div>

        <input type="submit" value="<fmt:message key="card.button" bundle="${lang}"/>" class="btn btn-primary"
               style="width: 100%">

    </form>

</div>
<c:set var="message" value="${message}"/>
<p style="font-size: 16px;font-style: italic;color: red;  text-align: center;" id="message">${message}</p>
</body>
</html>