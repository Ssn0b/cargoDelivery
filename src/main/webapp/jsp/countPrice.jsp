<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="header.jsp"%>

<!-- Bootstrap CSS -->
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css'>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styleForIndex.css">
<br><br>

<div class="container" style="background-color: #F8F8FF; padding: 20px; width: 950px; border: 3px solid #000000;">
<h1 style="font-size: 50px"> Delivery cost </h1>
    <br><br>
        <form method="post" id="countPriceForm" action="controller?action=myOrders" class="register-form">
            <h3 style="display: inline-block; margin-right: 105px">Route:</h3>
            <p style="display: inline-block; margin-right: 3px; font-size: 20px">City sender:</p>
            <input list="encodings1" value="" name="sender" id="sender" class="col-2    custom-select ">
            <datalist id="encodings1">
                <c:forEach items="${listCategory}" var ="city">
                    <option id="${city.idRegion}" value="${city.name}"></option>
                </c:forEach>
            </datalist>

            <p style="display: inline-block; margin-right: 3px; margin-left: 25px;font-size: 20px">City receiver:</p>
            <input list="encodings" value="" name="receiver" id="receiver" class="col-2    custom-select ">
            <datalist id="encodings">
                <c:forEach items="${listCategory}" var ="city">
                    <option id="${city.idRegion}" value="${city.name}"></option>
                </c:forEach>
            </datalist>
            <br><br>
            <h3 style="display: inline-block; margin-right: 20px">Description:</h3>
            <div id="dvName" style=" margin-right: 10px;display: inline-block;">
                <p style="display: inline-block; margin-right: 3px;font-size: 16px">Receiver name:</p>
                <input style="width: 200px;display: inline-block;" type="text" class="form-control" id="nameReceiver" name="nameReceiver" placeholder="john snow"/>
            </div>
            <div id="dvPack" style="margin-right: 5px;display: inline-block;">
                <p style="display: inline-block; margin-right: 3px;font-size: 16px">Package identifier:</p>
                <input type="text" name="idenPackage" class="form-control" id="idenPackage" size="15" placeholder="book,table,etc." style = "margin-right: 6px;width: 200px;display: inline-block;"/>
            </div>

<br><br>
            <h3 style="display: inline-flex;margin-right: 200px">Type:</h3>
            <div class="form-check" style="display: inline-block; margin-right: 35px">
                <label class="form-check-label" for="flexRadioDefault1">
                    <input class="form-check-input" type="radio"  name="flexRadioDefault" style="width: 20px;height: 20px" id="flexRadioDefault1" value="Cargo" onclick="ShowHideDiv();" />
                    <p style="font-size: 20px">Cargo</p>
                </label>
            </div>
            <div class="form-check" style="display: inline-block; margin-right: 35px" >
                <label class="form-check-label" for="flexRadioDefault2">
                    <input class="form-check-input" type="radio" name="flexRadioDefault" style="width: 20px;height: 20px" id="flexRadioDefault2" value="Document" onclick="ShowHideDiv();" />
                    <p style="font-size: 20px">Document</p>
                </label>
            </div>
            <div class="form-check" style="display: inline-block">
                <label class="form-check-label" for="flexRadioDefault2">
                    <input class="form-check-input" type="radio" name="flexRadioDefault" style="width: 20px;height: 20px" id="flexRadioDefault3" value="Parcel" onclick="ShowHideDiv();" />
                    <p style="font-size: 20px">Parcel</p>
                </label>
            </div>
            <br><br>
            <div id="dvParameters" style="display: none; ">
                <h3 style="display: inline-flex;margin-right: 20px">Parameters:</h3>
            </div>

            <div id="dvWeight" class="form-inline" style="display: none;  margin-right: 10px">
                Weight:
                <input type="text" class="form-control" name="weight" id="txtWeight"  size="4" style = "margin-right: 6px;" />kg
            </div>
            <div id="dvWidth" class="form-inline" style="display: none;margin-right: 10px;">
                Width:
                <input type="text" class="form-control" name="width" id="txtWidth" size="4" style = "margin-right: 6px"/>cm
            </div>
            <div id="dvHeight" class="form-inline" style="display: none;margin-right: 10px">
                Height:
                <input type="text" class="form-control" name="height" id="txtHeight"  size="4" style = "margin-right: 6px"/>cm
            </div>
            <div id="dvLength" class="form-inline" style="display: none; /*margin-right: 10px*/">
                Length:
                <input type="text" class="form-control" name="length" id="txtLength"  size="4"/>cm
            </div>

            <input  type="hidden" id="priceId" name="priceName">
            <input  type="hidden" id="arrivalDate" name="arrivalDate">

            <br><br>
            <h3 style="display: inline-flex;margin-right: 300px">Packing:</h3>
            <div class="form-check" id="check1" style="display: inline-block" >
                <input class="form-check-input" name="cargoPack" type="checkbox" value="packed" style="width: 20px;height: 20px" id="packUp"/>
                <p style="font-size: 20px">Pack up</p>
            </div>
            <br>
            <div style="display: inline-block" >
                <button type="button" name="buttonCountPrice" id="buttonCountPriceId" class="btn btn-dark" style="size: 50px;" onclick="setYourPrice()">Count the price</button>
                <label for="buttonCountPriceId" style="font-size: 20px;font-style: italic" id="yourPrice"></label>
            </div>

            <%if(userSession == null) {%>
            <a href="controller?action=loginpage"><button style="float: right" type="button" class="btn btn-primary" >Make order</button></a>
            <%}else {%>
            <input style="float: right" type="submit" class="btn btn-primary" value="Make order"/>
            <%}%>
        </form>
</div>
<c:set var="message" value="${message}"/>
<p style="font-size: 16px;font-style: italic;color: red;  text-align: center;" id="message">${message}</p>

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<!-- Popper Js -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<!-- Bootstrap JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha256-CjSoeELFOcH0/uxWu6mC/Vlrc1AARqbm/jiiImDGV3s=" crossorigin="anonymous"></script>

<script>
    $('#countPriceForm').on('submit', function() {
        document.getElementById("priceId").value = countThePrice();
        return true;
    });

    function countThePrice() {
        var g = $('#sender').val();
        var idSender = $('#encodings1 option[value=' + g +']').attr('id');
        var q = $('#receiver').val();
        var idReceiver = $('#encodings option[value=' + q +']').attr('id');

        var weight = document.getElementById('txtWeight').value;
        var height = document.getElementById('txtHeight').value;
        var length = document.getElementById('txtLength').value;
        var width = document.getElementById('txtWidth').value;
        var volume = height*width&length;

        var price = 0;
        if (flexRadioDefault2.checked){ // Document
            price+=1;
            price = price * coefficient(idSender,idReceiver);
            if(packUp.checked)price+=0.25;
        }
        else if(flexRadioDefault3.checked){ //Parcel
            price+=3;
            price= price * coefficient(idSender,idReceiver);
            price+=0.1*weight;
            price+=0.0015*volume;
            if(packUp.checked)price+=1;
        }
        else if(flexRadioDefault1.checked){ //Cargo
            price+=7;
            price= price * coefficient(idSender,idReceiver);
            price+=0.1*weight;
            price+=0.0015*volume;
            if(packUp.checked)price+=1.8;
        }
        return price.toFixed(2);
    }

    function setYourPrice() {
        document.getElementById("yourPrice").innerHTML = "Your price: " + countThePrice()+"$";
    }

    function coefficient(idSender,idReceiver){
        if (Math.abs(idSender - idReceiver) === 2){
            document.getElementById("arrivalDate").value = 5;
            return 1.6;
        }
        else if(Math.abs(idSender - idReceiver) === 1 || Math.abs(idSender - idReceiver) === 3){
            document.getElementById("arrivalDate").value = 4;
            return 1.35;
        }
        else{
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
        var dvHeight=document.getElementById("dvHeight");
        var dvLength=document.getElementById("dvLength");
        dvParameters.style.display = (flexRadioDefault1.checked || flexRadioDefault3.checked) ? "inline-block" : "none";
        dvWidth.style.display = (flexRadioDefault1.checked || flexRadioDefault3.checked) ? "inline-block" : "none";
        dvWeight.style.display = (flexRadioDefault1.checked || flexRadioDefault3.checked) ? "inline-block" : "none";
        dvHeight.style.display = (flexRadioDefault1.checked || flexRadioDefault3.checked) ? "inline-block" : "none";
        dvLength.style.display = (flexRadioDefault1.checked || flexRadioDefault3.checked) ? "inline-block" : "none";
    }
</script>

</html>
