<!doctype html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
	<meta name="description" content="">
	<meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
	<meta name="generator" content="Hugo 0.104.2">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>Login</title>

	<link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/sign-in/">





	<link href="../assets/dist/css/bootstrap.min.css" rel="stylesheet">

	<style>
		.bd-placeholder-img {
			font-size: 1.125rem;
			text-anchor: middle;
			-webkit-user-select: none;
			-moz-user-select: none;
			user-select: none;
		}

		@media (min-width: 768px) {
			.bd-placeholder-img-lg {
				font-size: 3.5rem;
			}
		}

		.b-example-divider {
			height: 3rem;
			background-color: rgba(0, 0, 0, .1);
			border: solid rgba(0, 0, 0, .15);
			border-width: 1px 0;
			box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
		}

		.b-example-vr {
			flex-shrink: 0;
			width: 1.5rem;
			height: 100vh;
		}

		.bi {
			vertical-align: -.125em;
			fill: currentColor;
		}

		.nav-scroller {
			position: relative;
			z-index: 2;
			height: 2.75rem;
			overflow-y: hidden;
		}

		.nav-scroller .nav {
			display: flex;
			flex-wrap: nowrap;
			padding-bottom: 1rem;
			margin-top: -1px;
			overflow-x: auto;
			text-align: center;
			white-space: nowrap;
			-webkit-overflow-scrolling: touch;
		}
	</style>


	<!-- Custom styles for this template -->
	<link href="css/signin.css" rel="stylesheet">

</head>
<body class="text-center">
<input type="hidden" id ="status" value="<%=request.getAttribute("status")%>">

<main class="form-signin w-100 m-auto">
	<h1 class="h3 mb-3 fw-normal">Sign up</h1>
	<form method="post" action="register" class="register-form"
		  id="login-form">
		<div class="form-floating">
			<input type="text" class="form-control" id="name" name="name" placeholder="name@example.com"/>
			<label for="name">Name</label>
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
		<div class="form-floating">
			<input type="text" class="form-control" name="city" id="city" placeholder="city" />
			<label for="city">City</label>
		</div>
		<div class="form-group form-button">
			<input type="submit" name="signup" id="signup"
				   class="w-100 btn btn-lg btn-primary" value="Register" />
		</div>
		<a href="login.jsp" class="signup-image-link">I am already
			member</a>

	</form>
</main>


<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.min.js" integrity="sha384-IDwe1+LCz02ROU9k972gdyvl+AESN10+x7tBKgc9I5HFtuNz0wWnPclzo6p9vxnk" crossorigin="anonymous"></script>
</body>
</html>