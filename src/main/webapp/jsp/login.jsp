<%@ include file="header.jsp"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<body class="text-center">
<main class="form-signin w-100 m-auto">
<br><br>
	<h1 class="h1 mb-3 fw-normal" style="font-size: 40px">Please sign in</h1>
		<form method="post" action="controller?action=login" class="register-form"
		  id="login-form">
		<div class="form-floating">
			<input type="text" class="form-control" id="email" name="email" placeholder="name@example.com">
			<label for="email">Email address</label>
		</div>
		<div class="form-floating">
			<input type="password" class="form-control" name="password" id="password" placeholder="Password">
			<label for="password">Password</label>
		</div>
			<br>
		<div class="form-group form-button">
			<input type="submit" name="signin" id="signin"
				   class="w-100 btn btn-lg btn-primary" value="Log in" />
		</div>
	</form>
	<c:set var="message" value="${message}"/>
	<p style="font-size: 16px;font-style: italic;color: red" id="message">${message}</p>
</main>


<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.min.js" integrity="sha384-IDwe1+LCz02ROU9k972gdyvl+AESN10+x7tBKgc9I5HFtuNz0wWnPclzo6p9vxnk" crossorigin="anonymous"></script>
</body>
</html>
