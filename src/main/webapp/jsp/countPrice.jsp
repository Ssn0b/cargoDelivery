<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="header.jsp" %>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags"%> <%--file tag--%>


<!-- Bootstrap CSS -->
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css'>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styleForIndex.css">
<br><br>
<tf:title titleName="Create order"/>
<div class="container" style="background-color: #F8F8FF; padding: 20px; width: 950px; border: 3px solid #000000;">
    <h1 style="font-size: 50px"><fmt:message key="count.deliveryCost" bundle="${lang}"/></h1>
    <br><br>
    <form method="post" id="countPriceForm" action="controller?action=myOrders" class="register-form">
        <h3 style="display: inline-block; margin-right: 20px"><fmt:message key="count.route" bundle="${lang}"/></h3>
        <p style="display: inline-block; margin-right: 3px; font-size: 20px"><fmt:message key="count.citySender" bundle="${lang}"/></p>

        <%
            String resp = "";
            resp = resp + session.getAttribute("lang");
        %>

        <%if (resp.equalsIgnoreCase("en")) {%>
        <input list="encodings1" value="" name="sender" id="sender" class="col-2    custom-select ">
        <datalist id="encodings1">
            <c:forEach items="${listCategory}" var="city">
                <option id="${city.idRegion}" value="${city.name}"></option>
            </c:forEach>
        </datalist>
        <%} else {%>
        <input list="encodings1" value="" name="sender" id="sender" class="col-2    custom-select ">
        <datalist id="encodings1">
            <c:forEach items="${listCategory}" var="city">
                <option id="${city.idRegion}" value="${city.name_ua}"></option>
            </c:forEach>
        </datalist>
        <%}%>
        <p style="display: inline-block; margin-right: 3px; margin-left: 25px;font-size: 20px"><fmt:message
                key="count.cityReceiver" bundle="${lang}"/></p>
        <%if (resp.equalsIgnoreCase("en")) {%>
        <input list="encodings" value="" name="receiver" id="receiver" class="col-2    custom-select ">
        <datalist id="encodings">
            <c:forEach items="${listCategory}" var="city">
                <option id="${city.idRegion}" value="${city.name}"></option>
            </c:forEach>
        </datalist>
        <%} else {%>
        <input list="encodings" value="" name="receiver" id="receiver" class="col-2    custom-select ">
        <datalist id="encodings">
            <c:forEach items="${listCategory}" var="city">
                <option id="${city.idRegion}" value="${city.name_ua}"></option>
            </c:forEach>
        </datalist>
        <%}%>

        <br><br>
        <h3 style="display: inline-block; margin-right: 20px"><fmt:message key="count.description" bundle="${lang}"/></h3>
        <div id="dvName" style=" margin-right: 10px;display: inline-block;">
            <p style="display: inline-block; margin-right: 3px;font-size: 16px"><fmt:message key="count.receiverNum" bundle="${lang}"/></p>
            <input style="width: 150px;display: inline-block;" type="text" class="form-control" id="numReceiver" size="10"
                   name="numReceiver" placeholder="0975333122"/>
        </div>
        <div id="dvPack" style="margin-right: 5px;display: inline-block;">
            <p style="display: inline-block; margin-right: 3px;font-size: 16px"><fmt:message key="count.packageIdentifier" bundle="${lang}"/></p>
            <input type="text" name="idenPackage" class="form-control" id="idenPackage" size="10"
                   placeholder="book,table,etc." style="margin-right: 6px;width: 150px;display: inline-block;"/>
        </div>

        <br><br>
        <h3 style="display: inline-flex;margin-right: 200px"><fmt:message key="count.type" bundle="${lang}"/></h3>
        <div class="form-check" style="display: inline-block; margin-right: 35px">
            <label class="form-check-label" for="flexRadioDefault1">
                <input class="form-check-input" type="radio" name="flexRadioDefault" style="width: 20px;height: 20px"
                       id="flexRadioDefault1" value="Cargo" onclick="ShowHideDiv();"/>
                <p style="font-size: 20px"><fmt:message key="count.cargo" bundle="${lang}"/></p>
            </label>
        </div>
        <div class="form-check" style="display: inline-block; margin-right: 35px">
            <label class="form-check-label" for="flexRadioDefault2">
                <input class="form-check-input" type="radio" name="flexRadioDefault" style="width: 20px;height: 20px"
                       id="flexRadioDefault2" value="Document" onclick="ShowHideDiv();"/>
                <p style="font-size: 20px"><fmt:message key="count.document" bundle="${lang}"/></p>
            </label>
        </div>
        <div class="form-check" style="display: inline-block">
            <label class="form-check-label" for="flexRadioDefault2">
                <input class="form-check-input" type="radio" name="flexRadioDefault" style="width: 20px;height: 20px"
                       id="flexRadioDefault3" value="Parcel" onclick="ShowHideDiv();"/>
                <p style="font-size: 20px"><fmt:message key="count.parcel" bundle="${lang}"/></p>
            </label>
        </div>
        <br><br>
        <div id="dvParameters" style="display: none; ">
            <h3 style="display: inline-flex;margin-right: 20px"><fmt:message key="count.parameters" bundle="${lang}"/></h3>
        </div>

        <div id="dvWeight" class="form-inline" style="display: none;  margin-right: 10px">
            <fmt:message key="count.weight" bundle="${lang}"/>
            <input type="text" class="form-control" name="weight" id="txtWeight" size="3"
                   style="margin-right: 6px;"/><fmt:message key="count.kg" bundle="${lang}"/>
        </div>
        <div id="dvWidth" class="form-inline" style="display: none;margin-right: 10px;">
            <fmt:message key="count.width" bundle="${lang}"/>
            <input type="text" class="form-control" name="width" id="txtWidth" size="3"
                   style="margin-right: 6px"/><fmt:message key="count.cm" bundle="${lang}"/>
        </div>
        <div id="dvHeight" class="form-inline" style="display: none;margin-right: 10px">
            <fmt:message key="count.height" bundle="${lang}"/>
            <input type="text" class="form-control" name="height" id="txtHeight" size="3"
                   style="margin-right: 6px"/><fmt:message key="count.cm" bundle="${lang}"/>
        </div>
        <div id="dvLength" class="form-inline" style="display: none; /*margin-right: 10px*/">
            <fmt:message key="count.length" bundle="${lang}"/>
            <input type="text" class="form-control" name="length" id="txtLength" size="3"/><fmt:message key="count.cm"
                                                                                                        bundle="${lang}"/>
        </div>

        <input type="hidden" id="priceId" name="priceName">
        <input type="hidden" id="arrivalDate" name="arrivalDate">

        <br><br>
        <h3 style="display: inline-flex;margin-right: 250px"><fmt:message key="count.packing" bundle="${lang}"/></h3>
        <div class="form-check" id="check1" style="display: inline-block">
            <input class="form-check-input" name="cargoPack" type="checkbox" value="packed"
                   style="width: 20px;height: 20px" id="packUp"/>
            <p style="font-size: 20px"><fmt:message key="count.packUp" bundle="${lang}"/></p>
        </div>
        <br>
        <div style="display: inline-block">
            <button type="button" name="buttonCountPrice" id="buttonCountPriceId" class="btn btn-dark"
                    style="size: 50px;" onclick="setYourPrice()"><fmt:message key="count.button1"
                                                                              bundle="${lang}"/></button>
            <label for="buttonCountPriceId" style="font-size: 20px;font-style: italic" id="yourPrice"></label>
        </div>

        <%if (userSession == null) {%>
        <a href="controller?action=loginpage">
            <button style="float: right" type="button" class="btn btn-primary"><fmt:message key="count.button2"
                                                                                            bundle="${lang}"/></button>
        </a>
        <%} else {%>
        <input style="float: right" type="submit" class="btn btn-primary"
               value="<fmt:message key="count.button2" bundle="${lang}"/>"/>
        <%}%>
    </form>
</div>
<c:set var="message" value="${message}"/>
<p style="font-size: 16px;font-style: italic;color: red;  text-align: center;" id="message">${message}</p>


<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<!-- Popper Js -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<!-- Bootstrap JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha256-CjSoeELFOcH0/uxWu6mC/Vlrc1AARqbm/jiiImDGV3s=" crossorigin="anonymous"></script>

<script>
    $('#countPriceForm').on('submit', function () {
        document.getElementById("priceId").value = countThePrice();
        return true;
    });

    function countThePrice() {
        var g = $('#sender').val();
        var idSender = $('#encodings1 option[value=' + g + ']').attr('id');
        var q = $('#receiver').val();
        var idReceiver = $('#encodings option[value=' + q + ']').attr('id');

        var weight = document.getElementById('txtWeight').value;
        var height = document.getElementById('txtHeight').value;
        var length = document.getElementById('txtLength').value;
        var width = document.getElementById('txtWidth').value;
        var volume = height * width & length;

        var price = 0;
        if (flexRadioDefault2.checked) { // Document
            price += 1;
            price = price * coefficient(idSender, idReceiver);
            if (packUp.checked) price += 0.25;
        } else if (flexRadioDefault3.checked) { //Parcel
            price += 3;
            price = price * coefficient(idSender, idReceiver);
            price += 0.1 * weight;
            price += 0.0015 * volume;
            if (packUp.checked) price += 1;
        } else if (flexRadioDefault1.checked) { //Cargo
            price += 7;
            price = price * coefficient(idSender, idReceiver);
            price += 0.1 * weight;
            price += 0.0015 * volume;
            if (packUp.checked) price += 1.8;
        }
        return price.toFixed(2);
    }

    function setYourPrice() {
        document.getElementById("yourPrice").innerHTML = "<fmt:message key="count.yourPrice" bundle="${lang}"/> " + countThePrice() + "$";
    }

    function coefficient(idSender, idReceiver) {
        if (Math.abs(idSender - idReceiver) === 2) {
            document.getElementById("arrivalDate").value = 5;
            return 1.6;
        } else if (Math.abs(idSender - idReceiver) === 1 || Math.abs(idSender - idReceiver) === 3) {
            document.getElementById("arrivalDate").value = 4;
            return 1.35;
        } else {
            document.getElementById("arrivalDate").value = 2;
            return 1;
        }

    }
</script>

<script type="text/javascript">
    function ShowHideDiv() {
        var chkYes = document.getElementById(flexRadioDefault1);
        var dvParameters = document.getElementById("dvParameters");
        var dvWidth = document.getElementById("dvWidth");
        var dvWeight = document.getElementById("dvWeight");
        var dvHeight = document.getElementById("dvHeight");
        var dvLength = document.getElementById("dvLength");
        dvParameters.style.display = (flexRadioDefault1.checked || flexRadioDefault3.checked) ? "inline-block" : "none";
        dvWidth.style.display = (flexRadioDefault1.checked || flexRadioDefault3.checked) ? "inline-block" : "none";
        dvWeight.style.display = (flexRadioDefault1.checked || flexRadioDefault3.checked) ? "inline-block" : "none";
        dvHeight.style.display = (flexRadioDefault1.checked || flexRadioDefault3.checked) ? "inline-block" : "none";
        dvLength.style.display = (flexRadioDefault1.checked || flexRadioDefault3.checked) ? "inline-block" : "none";
    }
</script>

</html>
