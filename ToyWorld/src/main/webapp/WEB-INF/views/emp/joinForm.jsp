<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Join</title>

    <!-- Custom fonts for this template-->
    <link href="/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="/resources/css/sb-admin-2.min.css" rel="stylesheet">
    
        <!-- Bootstrap core JavaScript-->
    <script src="/resources/vendor/jquery/jquery.js"></script>
    <script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="/resources/js/sb-admin-2.min.js"></script>
    
    <script type="text/javascript">
    	function formCheck() {
    		let id = $("#empid").val();
    		let pw = $("#emppw").val();
    		let checkPW = $("#checkPW").val();
    		
    		if (id == "") {
    			alert("Please enter your account.");
    			return false;
    		} else if (id.trim().length < 3 || id.trim().length > 6) {
    			alert("Please enter your ID in 3-6 letters.");
    			return false;
    		}
   
    		if (pw == "") {
    			alert("Please enter your password.");
    			return false;
    		} else if (pw.trim().length < 3 || pw.trim().length > 6) {
    			alert("Please enter your PW in 3-6 letters.")
    			return false;
    		} else if (checkPW == "") {
    			alert("Please reenter your password.");
    			return false;
    		} else if (pw != checkPW) {
    			alert("password doesn't match.")
    			return false;
    		}
			return true;
		}
    </script>

</head>

<body class="bg-gradient-dark">

    <div class="container">

        <div class="card o-hidden border-0 shadow-lg my-5">
            <div class="card-body p-0">
                <!-- Nested Row within Card Body -->
                <div class="row">
                    <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                    <div class="col-lg-7">
                        <div class="p-5">
                            <div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4">Create an Account!</h1>
                            </div>
                            <hr>
                            
                            <form action="/emp/join" method="post" onsubmit="return formCheck();" class="user">
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-user" id="empid"
                                        name="empid" placeholder="ID">
                                </div>
                                <div class="form-group">
                                    <input type="password" class="form-control form-control-user"
                                        id="emppw" name="emppw" placeholder="Password">
                                </div>
                                <div class="form-group">
                                    <input type="password" class="form-control form-control-user"
                                        id="checkPW" placeholder="Repeat Password">
                                </div>
                                <input type="submit" class="btn btn-primary btn-user btn-block" value="Register Account">
                            </form>
                            
                            <hr>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>

</body>

</html>