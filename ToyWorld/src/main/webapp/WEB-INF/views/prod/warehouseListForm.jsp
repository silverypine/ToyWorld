<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

    <title>WarehouseListForm</title>

    <!-- Custom fonts for this template-->
    <link href="/resources/vendor/fontawesome-free/css/all.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="/resources/css/sb-admin-2.css" rel="stylesheet">
    
        <!-- Bootstrap core JavaScript-->
    <script src="/resources/vendor/jquery/jquery.js"></script>
    
	<script type="text/javascript">
	let arr = [];
	
/* 	$(function () {
		let prodSelect = $(".prodSelect");
 		console.log(prodSelect);
		console.log(prodSelect[0].checked);
		console.log(prodSelect[0].value);
		for (i=0; i < prodSelect.length; i++) {
			if (prodSelect[i].checked == true) {
				arr.push(prodSelect[i].value);
			}
		}
	}); */
	$(function () {
		let prodSelect = $(".prodSelect");
		$.each($(".prodSelect"), function (i) {
			
/* 			$(this).on("click", function () {
				if ($(this).is(":checked") == true) {
					arr.push($(this).val());
					console.log(arr);
				}
			});
			$(this).on("click", function () {
				console.log($(this).is(":checked"));
				if ($(this).is(":checked") == false) {
					arr.pop();
					console.log(arr);
				}
			}); */
			
			$(this).on("click", function () {
				let index = arr.indexOf($(this).val());
				if (index == -1) {
					arr.push($(this).val());
					console.log(arr);
				} else {
					arr.splice(index, 1);
					console.log(arr);
				}
			});
		});
	});
	
	console.log(arr);
	
/* 	function sendProdNum() {
		let prodSelect = $(".prodSelect");
		for (i=0; i < prodSelect.length; i++) {
			if (prodSelect[i].checked == true) {
				arr.push(prodSelect[i].value);
			}
		}
	} */
	
$(function () {
	$("#SaveProdNum").on("click", function () {
		
		let cnt1 = 0;
		let prodNumCheckedArr = [];
		
		$.each($(".prodSelect"), function (index) {
			if ($(this).is(":checked") == true) {
				cnt1 = cnt1 + 1;
				prodNumCheckedArr.push($(this).val());
			} else {
				index + 1;
			}
		});
		
		if (cnt1 == 0) {
			alert("진열할 상품을 선택해주세요");
			return false;
		}
		
		let stockArr = [];
		let cnt2 = 0;
		
		let minusCnt = 0;
		$.each($(".inputStock"), function (index) {
			if ($(this).val() > 0) {
				stockArr.push(parseInt($(this).val()));
				cnt2 = cnt2 + 1;
				console.log(stockArr);
			} else if ($(this).val() < 0) {
				alert("수량에 음수를 입력할 수 없습니다!");
				minusCnt++;
				return false;
			}
		});
		
		if (minusCnt > 0) {
			$(".prodSelect").prop("checked", false);
			$.each($(".inputStock"), function () {
				$(this).val("0");
			});
			arr = [];
			return false;
		}
		
		if (cnt2 == 0) {
			alert("재고수량을 입력해주세요!");
			return false;
		}
		
		if (cnt1 != cnt2) {
			alert("선택된 상품과 수량이 일치하지 않습니다!");
			$(".prodSelect").prop("checked", false);
			$.each($(".inputStock"), function () {
				$(this).val("0");
			});
			arr = [];
			return false;
		}
		
		let inputStockArr = [];
		$.each($(".inputStock"), function () {
			inputStockArr.push(parseInt($(this).val()));
		});
		
		let inputStockIndexArr = [];
		for (let i = 0; i < inputStockArr.length; i++) {
			if (inputStockArr.indexOf(stockArr[i]) != -1) {
				inputStockIndexArr.push(inputStockArr.indexOf(stockArr[i]));
			}
		}
		
		let prodNumListArr = [];
		$.each($(".prodSelect"), function () {
			prodNumListArr.push($(this).val());
		});
		
		let prodNumListIndexArr = [];
		for (let i = 0; i < prodNumListArr.length; i++) {
			if (prodNumListArr.indexOf(prodNumCheckedArr[i]) != -1) {
				prodNumListIndexArr.push(prodNumListArr.indexOf(prodNumCheckedArr[i]));
			}
		}
		
		for (let i = 0; i < prodNumListIndexArr.length; i++) {
			if (prodNumListIndexArr[i] != inputStockIndexArr[i]) {
				alert("선택된 상품과 수량의 위치가 일치하지 않습니다!");
				$(".prodSelect").prop("checked", false);
				$.each($(".inputStock"), function () {
					$(this).val("0");
				});
				arr = [];
				return false;
			}
		}
		
		let AllposInfoNumArr = [];
		$.each($(".posInfoNum"), function () {
			AllposInfoNumArr.push(parseInt($(this).val()));
		});
		
		let posInfoNumArr = [];
		for (let i = 0; i < prodNumListIndexArr.length; i++) {
			posInfoNumArr.push(AllposInfoNumArr[prodNumListIndexArr[i]]);
		}
		
		$.ajax({
			url: "/prod/SavePosInfo"
			,type: "GET"
			,contentType: "application/json; charset=utf-8"
			,data: {
				"prodNumList" : arr
				,"inputStockList" : stockArr
				,"posInfoNumList" : posInfoNumArr
			}
			,dataType : "json"
			,success : function (data) {
				if (data) {
					alert("상품 정보가 저장되었습니다. 위치 등록 페이지로 이동합니다!");
					window.location.href = "/prod/WarehouseToStoreMap";
				} else {
					alert("현위치 재고를 초과해서 입력할 수 없습니다!");
					$(".prodSelect").prop("checked", false);
					$.each($(".inputStock"), function () {
						$(this).val("0");
					});
					arr = [];
				}
			}
			,error : function (e) {
				console.log(e);
			}
		});
	});
});
	</script>
</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-dark sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="/">
                <div class="sidebar-brand-text mx-3">ToyWorld</div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
                    aria-expanded="true" aria-controls="collapseTwo">
                    <i class="fas fa-fw fa-cog"></i>
                    <span>상품 관리</span>
                </a>
                <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Menu</h6>
                        <a class="collapse-item" href="/prod/listForm">상품 리스트</a>
                        <a class="collapse-item" href="/prod/insertForm">상품 등록</a>
                    </div>
                </div>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider d-none d-md-block">

            <!-- Sidebar Toggler (Sidebar) -->
            <div class="text-center d-none d-md-inline">
                <button class="rounded-circle border-0" id="sidebarToggle"></button>
            </div>

        </ul>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- Sidebar Toggle (Topbar) -->
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>

                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">

                        <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                        <li class="nav-item dropdown no-arrow d-sm-none">
                            <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-search fa-fw"></i>
                            </a>
                            <!-- Dropdown - Messages -->
                            <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                                aria-labelledby="searchDropdown">
                                <form class="form-inline mr-auto w-100 navbar-search">
                                    <div class="input-group">
                                        <input type="text" class="form-control bg-light border-0 small"
                                            placeholder="Search for..." aria-label="Search"
                                            aria-describedby="basic-addon2">
                                        <div class="input-group-append">
                                            <button class="btn btn-dark" type="button">
                                                <i class="fas fa-search fa-sm"></i>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </li>

                        <div class="topbar-divider d-none d-sm-block"></div>
				<c:choose>
					<c:when test="${empty sessionScope.loginId }">
						<li class="nav-item dropdown no-arrow">
                            <a class="nav-link" href="/emp/loginForm">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">로그인</span>
                            </a>
						</li>
						
						<div class="topbar-divider d-none d-sm-block"></div>
						
						<li class="nav-item dropdown no-arrow">
                            <a class="nav-link" href="/emp/joinForm">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">회원가입</span>
                            </a>
						</li>
					</c:when>
					<c:otherwise>
						<!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">${sessionScope.loginId }</span>
                                <img class="img-profile rounded-circle"
                                    src="/resources/img/undraw_profile.svg">
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">
                                <div class="dropdown-item"></div>
                                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    로그아웃
                                </a>
                            </div>
                        </li>
					</c:otherwise>
				</c:choose>
                        

                    </ul>

                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">창고 상품 목록</h1>
                    <button id="SaveProdNum" class="btn btn-info">창고 -> 매장 이동</button>
                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3"></div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                        	<th>선택</th>
                                            <th>번호</th>
                                            <th>이름</th>
                                            <th>분류</th>
                                            <th>제조사</th>
                                            <th>가격</th>
                                            <th>재고</th>
                                            <th>매장 재고</th>
                                            <th>창고 재고</th>
                                            <th>현위치 재고</th>
                                            <th>매장 이동 수량</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                        	<th>선택</th>
                                            <th>번호</th>
                                            <th>이름</th>
                                            <th>분류</th>
                                            <th>제조사</th>
                                            <th>가격</th>
                                            <th>재고</th>
                                            <th>매장 재고</th>
                                            <th>창고 재고</th>
                                            <th>현위치 재고</th>
                                            <th>매장 이동 수량</th>
                                        </tr>
                                    </tfoot>
                                    <tbody>
 										<c:forEach var="p" items="${posList}">
 											<tr>
 												<td><input type="checkbox" value="${p.PRODNUM }" name="SaveNum" class="prodSelect"></td>
 												<td>${p.PRODNUM }</td>
 												<td><a href="/prod/readForm?prodNum=${p.PRODNUM }">${p.PRODNAME }</a></td>
 												<td>${p.PRODCATEGORY }</td>
 												<td>${p.PRODMANUFACTURER }</td>
 												<td>${p.PRODPRICE }</td>
 												<td><b>${p.PRODSTOCK }</b></td>
 												<td><span style="color: #4e73df;"><b>${p.PRODSTORESTOCK }</b></span></td>
 												<td><span style="color: #1cc88a;"><b>${p.PRODWAREHOUSESTOCK }</b></span></td>
 												<td><span style="color: #e74a3b;"><b>${p.POSSTOCK }</b></span></td>
 												<td><input type="text" value="0" class="inputStock" style="width: 50px;"></td>
 												<input type="hidden" value="${p.POSINFONUM }" class="posInfoNum">
 											</tr>
 										</c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>Copyright &copy; ToyWolrd 2021</span>
                    </div>
                </div>
            </footer>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">알림</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">로그아웃 하시겠습니까?</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">취소</button>
                    <a class="btn btn-success" href="/emp/logout">로그아웃</a>
                </div>
            </div>
        </div>
    </div>


	<script src="/resources/vendor/bootstrap/js/bootstrap.bundle.js"></script>
    <!-- Page level plugins -->
    <script src="/resources/vendor/datatables/jquery.dataTables.min.js"></script>
    <script src="/resources/vendor/datatables/dataTables.bootstrap4.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="/resources/js/demo/datatables-demo.js"></script>
</body>

</html>