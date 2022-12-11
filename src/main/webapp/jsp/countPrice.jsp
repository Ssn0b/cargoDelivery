<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="header.jsp"%>
<!-- Bootstrap CSS -->
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css'>
<!-- Hierarchy Select CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/hierarchy-select.min.css">
<!-- Demo CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/demo.css">

<body>

<main>
        <h2> Delivery cost </h2>
    <br>
        <form method="post" id="countPriceForm" action="controller?action=myOrders" class="register-form">
            <h5 style="display: inline-block; margin-right: 50px">Route:</h5>
            <p style="display: inline-block; margin-right: 3px">City sender:</p>
            <input list="encodings1" value="" name="sender" id="sender" class="col-sm-2    custom-select custom-select-sm">
            <datalist id="encodings1">
                <option selected>To..</option>
                <c:forEach items="${listCategory}" var ="city">
                    <option id="${city.idRegion}" value="${city.name}"></option>
                </c:forEach>
            </datalist>

            <p style="display: inline-block; margin-right: 3px; margin-left: 25px">City receiver:</p>
            <input list="encodings" value="" name="receiver" id="receiver" class="col-sm-2    custom-select custom-select-sm">
            <datalist id="encodings">
                <option selected>To..</option>
                <c:forEach items="${listCategory}" var ="city">
                    <option id="${city.idRegion}" value="${city.name}"></option>
                </c:forEach>
            </datalist>

<br><br>
            <h5 style="display:inline-flex; margin-right: 160px">Type:</h5>
            <div class="form-check" style="display: inline-block; margin-right: 30px">
                <label class="form-check-label" for="flexRadioDefault1">
                    <input class="form-check-input" type="radio"  name="flexRadioDefault" id="flexRadioDefault1" value="Cargo" onclick="ShowHideDiv();ShowHideDiv2()" />
                    Cargo
                </label>
            </div>
            <div class="form-check" style="display: inline-block; margin-right: 30px" >
                <label class="form-check-label" for="flexRadioDefault2">
                    <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2" value="Document" onclick="ShowHideDiv();ShowHideDiv2()" />
                    Document
                </label>
            </div>
            <div class="form-check" style="display: inline-block">
                <label class="form-check-label" for="flexRadioDefault2">
                    <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault3" value="Parcel" onclick="ShowHideDiv();ShowHideDiv2()" />
                    Parcel
                </label>
            </div>
            <br><br>
            <div id="dvWeight" style="display: none;  margin-right: 10px">
                Weight:
                <input type="text" name="weight" id="txtWeight"  size="8" style = "margin-right: 6px" />
            </div>
            <div id="dvWidth" style="display: none;margin-right: 20px; margin-left: 10px">
                Width:
                <input type="text" name="width" id="txtWidth" size="8" style = "margin-right: 6px"/>
            </div>
            <div id="dvHeight" style="display: none;margin-right: 10px">
                Height:
                <input type="text" name="height" id="txtHeight"  size="8" style = "margin-right: 6px"/>
            </div>
            <div id="dvLength" style="display: none; margin-right: 10px">
                Length:
                <input type="text" name="length" id="txtLength"  size="8"/>
            </div>


            <input  type="hidden" id="priceId" name="priceName">


            <br><br>
            <div class="form-check" id="check1" style="text-align: center;">
                <input class="form-check-input" name="cargoPack" type="checkbox" value="packed" id="packUp"/>
                <label class="form-check-label" for="packUp"> Pack up </label>
            </div>
            <br>
            <div style="display: inline-block" >
                <button type="button" name="buttonCountPrice" class="btn btn-dark" style="size: 50px" onclick="countThePrice()">Count the price</button>
            </div>
            <div style="display: inline-block; margin-left: 15px" >
                <label style="size: 50px; font-style: italic" id="yourPrice"></label>
            </div>


            <%if(userSession == null) {%>
            <a href="controller?action=login"><button style="float: right" type="button" class="btn btn-primary" >Make order</button></a>
            <%}else {%>
            <input style="float: right" type="submit" class="btn btn-primary" value="Make order"/>
            <%}%>
        </form>
</main>
</body>

<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<!-- Popper Js -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<!-- Bootstrap JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha256-CjSoeELFOcH0/uxWu6mC/Vlrc1AARqbm/jiiImDGV3s=" crossorigin="anonymous"></script>
<!-- Hierarchy Select Js -->
<script src="${pageContext.request.contextPath}/js/hierarchy-select.js"></script>

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
<script>
    $(document).ready(function(){
        $('#example').hierarchySelect({
            hierarchy: false,
            width: 'auto'
        });
    });

    $(document).ready(function(){
        $('#example1').hierarchySelect({
            hierarchy: false,
            width: 'auto'
        });
    });
</script>

<script type="text/javascript">
    function ShowHideDiv() {
        var chkYes = document.getElementById(flexRadioDefault1);
        var dvWidth = document.getElementById("dvWidth");
        var dvWeight = document.getElementById("dvWeight");
        var dvHeight=document.getElementById("dvHeight");
        var dvLength=document.getElementById("dvLength");
        dvWidth.style.display = (flexRadioDefault1.checked || flexRadioDefault3.checked) ? "inline-block" : "none";
        dvWeight.style.display = (flexRadioDefault1.checked || flexRadioDefault3.checked) ? "inline-block" : "none";
        dvHeight.style.display = (flexRadioDefault1.checked || flexRadioDefault3.checked) ? "inline-block" : "none";
        dvLength.style.display = (flexRadioDefault1.checked || flexRadioDefault3.checked) ? "inline-block" : "none";
    }
</script>

</html>
