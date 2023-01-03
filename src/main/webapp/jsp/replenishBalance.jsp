<%@ include file="header.jsp"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<link rel="stylesheet" href="${pageContext.request.contextPath}/css/pay.css">

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styleForIndex.css">


<body>

<div class="container"  style="background-color: #F8F8FF;   ">

  <form method="post" id="myOrdersForm" action="controller?action=replenishBalance">

    <div class="row">

      <div class="col">
        <h3 class="title">billing address</h3>
        <div class="inputBox">
          <span>full name :</span>
          <input type="text" name="name" id="name" placeholder="john snow">
        </div>
        <div class="inputBox">
          <span>email :</span>
          <input type="text" name="email" placeholder="example@example.com">
        </div>
        <div class="inputBox">
          <span>address :</span>
          <input type="text" name="address" placeholder="street, number of building">
        </div>
        <div class="inputBox">
          <span>city :</span>
          <input type="text" name="city" placeholder="lviv">
        </div>

        <div class="flex">
          <div class="inputBox">
            <span>state :</span>
            <input type="text" name="state" placeholder="ukraine">
          </div>
          <div class="inputBox">
            <span>zip code :</span>
            <input type="text" name="zip" placeholder="12345">
          </div>
        </div>

      </div>

      <div class="col">
        <h3 class="title">payment</h3>
        <div class="flex">
        <div class="inputBox">
          <span>cards accepted :</span>
          <img src="${pageContext.request.contextPath}/img/card_img.png" alt="">
        </div>
        <div class="inputBox">
          <span>deposit amount :</span>
          <input type="number" name="deposit" placeholder="0$">
        </div>
        </div>
      <div class="inputBox">
          <span>name on card :</span>
          <input type="text" name="nameOnCard" placeholder="mr. john snow">
        </div>
        <div class="inputBox">
          <span>credit card number :</span>
          <input type="number" name="card" placeholder="1111-2222-3333-4444">
        </div>
        <div class="inputBox">
          <span>exp month :</span>
          <input type="text" name="month" placeholder="january">
        </div>

        <div class="flex">
          <div class="inputBox">
            <span>exp year :</span>
            <input type="number" name="year" placeholder="2024">
          </div>
          <div class="inputBox">
            <span>CVV :</span>
            <input type="text" name="cvv" placeholder="123">
          </div>
        </div>

      </div>

    </div>

    <input type="submit" value="proceed to checkout" class="btn btn-primary" style="width: 100%">

  </form>

</div>
<c:set var="message" value="${message}"/>
<p style="font-size: 16px;font-style: italic;color: red;  text-align: center;" id="message">${message}</p>
</body>
</html>