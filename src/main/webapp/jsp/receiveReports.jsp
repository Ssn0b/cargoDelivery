<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.core" %>
<%@ include file="header.jsp"%>

<!-- Bootstrap CSS -->
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css'>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styleForIndex.css">
<br><br><br>

<div class="container" style="background-color: #F8F8FF; padding: 20px; width: 950px; border: 3px solid #000000;">
    <h1 style="font-size: 50px"> Receive reports </h1>
    <br><br>
    <form method="post" id="receiveReportsForm" action="">
        <h3 style="display: inline-block; margin-right: 105px">Route:</h3>
        <p style="display: inline-block; margin-right: 3px; font-size: 20px">City sender:</p>
        <input list="encodings1" value="" name="sender" id="sender" class="col-2   custom-select">
        <datalist id="encodings1">
            <option selected>To..</option>
            <c:forEach items="${listCategory}" var ="city">
                <option id="${city.idRegion}" value="${city.name}"></option>
            </c:forEach>
        </datalist>

        <p style="display: inline-block; margin-right: 3px; margin-left: 25px;font-size: 20px">City receiver:</p>
        <input list="encodings" value="" name="receiver" id="receiver" class="col-2    custom-select ">
        <datalist id="encodings">
            <option selected>To..</option>
            <c:forEach items="${listCategory}" var ="city">
                <option id="${city.idRegion}" value="${city.name}"></option>
            </c:forEach>
        </datalist>

        <br><br>

        <h3 style="display: inline-block; margin-right: 105px">Date:</h3>
        <input type="date" name="dateofbirth" id="dateofbirth">

        <input style="border: 1px solid #c4c4c4;
  border-radius: 5px;
  background-color: #fff;
  padding: 3px 5px;
  box-shadow: inset 0 3px 6px rgba(0,0,0,0.1);
  width: 190px;" type="hidden" id="priceId" name="priceName">

        <%if(userSession == null) {%>
        <a href="controller?action=login"><button style="float: right" type="button" class="btn btn-primary" >Make order</button></a>
        <%}else {%>
        <input style="float: right" type="submit" class="btn btn-primary" value="Make order"/>
        <%}%>
    </form>
</div>


<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<!-- Popper Js -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<!-- Bootstrap JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha256-CjSoeELFOcH0/uxWu6mC/Vlrc1AARqbm/jiiImDGV3s=" crossorigin="anonymous"></script>

<script>
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
            price+=5;
            price*=coefficient(idSender,idReceiver);
            if(packUp.checked)price+=1;
        }
        else if(flexRadioDefault3.checked){
            price+=10;
            price*=coefficient(idSender,idReceiver);
            price+=0.1*weight;
            price+=0.0015*volume;
            if(packUp.checked)price+=2;
        }
        else if(flexRadioDefault1.checked){
            price+=20;
            price*=coefficient(idSender,idReceiver);
            price+=0.1*weight;
            price+=0.0015*volume;
            if(packUp.checked)price+=5;
        }
        document.getElementById("yourPrice").innerHTML = "Your price: " + price.toFixed(2);
        document.getElementById("priceId").value = price.toFixed(2);
    }

    function coefficient(idSender,idReceiver){
        if (Math.abs(idSender - idReceiver) === 2){
            return 1.6;
        }
        else if(Math.abs(idSender - idReceiver) === 1 || Math.abs(idSender - idReceiver) === 3){
            return 1.35;
        }
        else return 1;
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
