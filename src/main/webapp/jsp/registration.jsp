<%@ include file="header.jsp"%>

<body class="text-center">
<input type="hidden" id ="status" value="<%=request.getAttribute("status")%>">

<main class="form-signin w-100 m-auto">
	<h1 class="h3 mb-3 fw-normal">Sign up</h1>
	<form method="post" action="controller?action=register" class="register-form"
		  id="login-form">
		<div class="form-floating">
			<input type="text" class="form-control" id="name" name="name" placeholder="name@example.com"/>
			<label for="name">Name</label>
		</div>
		<div class="form-floating">
			<input type="text" class="form-control" id="lastName" name="lastName" placeholder="name@example.com"/>
			<label for="lastName">Last name</label>
		</div>
		<div class="form-floating">
			<input type="email" class="form-control" id="email" name="email" placeholder="Email"/>
			<label for="email">Email address</label>
		</div>
		<div class="form-floating">
			<input type="password" class="form-control" name="pass" id="pass" placeholder="Password">
			<label for="pass">Password</label>
		</div>
		<div class="form-floating">
			<input type="text" class="form-control" name="contact" id="contact" placeholder="Contact number">
			<label for="contact">Contact number</label>
		</div>
<br>
		<div class="form-group form-button">
			<input type="submit" name="signup" id="signup"
				   class="w-100 btn btn-lg btn-primary" value="Register" />
		</div>

	</form>
</main>


<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.min.js" integrity="sha384-IDwe1+LCz02ROU9k972gdyvl+AESN10+x7tBKgc9I5HFtuNz0wWnPclzo6p9vxnk" crossorigin="anonymous"></script>
</body>
</html>