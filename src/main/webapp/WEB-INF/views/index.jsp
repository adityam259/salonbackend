<!DOCTYPE html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<title>Admin DashBoard</title>
<link rel="stylesheet"
	href="assets/vendors/mdi/css/materialdesignicons.min.css" />
<link rel="stylesheet"
	href="assets/vendors/flag-icon-css/css/flag-icon.min.css" />
<link rel="stylesheet" href="assets/vendors/css/vendor.bundle.base.css" />
<link rel="stylesheet"
	href="assets/vendors/font-awesome/css/font-awesome.min.css" />
<link rel="stylesheet"
	href="assets/vendors/bootstrap-datepicker/bootstrap-datepicker.min.css" />
<link rel="stylesheet" href="assets/css/style.css" />
<link rel="shortcut icon" href="assets/images/favicon.png" />
</head>
<body onload="setUserDetails()">
	<%@ page import="com.salon.app.model.Login"%>
	<%
    	Login logedInUser = (Login) session.getAttribute("loggedInUser");
    	String userName = "";
    	if(logedInUser!=null){
    		userName = logedInUser.getFirstName().toString() +" "+ logedInUser.getLastName().toString();
    	}
    %>
	<script>
	function setUserDetails() {
		  var userName = '<%=userName%>';
		  document.getElementById("loggedInUserName").textContent = userName;
		  document.getElementById("loggedInUserName1").textContent = userName;
	}
    </script>


	<div class="container-scroller">

		<%@include file="navbar.jsp"%>

		<div class="container-fluid page-body-wrapper">
			<div id="theme-settings" class="settings-panel">
				<i class="settings-close mdi mdi-close"></i>
				<p class="settings-heading">SIDEBAR SKINS</p>
				<div class="sidebar-bg-options selected" id="sidebar-default-theme">
					<div class="img-ss rounded-circle bg-light border mr-3"></div>
					Default
				</div>
				<div class="sidebar-bg-options" id="sidebar-dark-theme">
					<div class="img-ss rounded-circle bg-dark border mr-3"></div>
					Dark
				</div>
				<p class="settings-heading mt-2">HEADER SKINS</p>
				<div class="color-tiles mx-0 px-4">
					<div class="tiles light"></div>
					<div class="tiles dark"></div>
				</div>
			</div>

			<%@include file="sidebar.jsp"%>

			<div class="main-panel">
				<div class="content-wrapper pb-0">
					<div class="page-header flex-wrap">
						<h3 class="mb-0">
							Hi, welcome back! <span
								class="pl-0 h6 pl-sm-2 text-muted d-inline-block">Your
								web analytics</span>
						</h3>

					</div>
					<div class="row">
						<div class="col-xl-12 col-lg-12 stretch-card grid-margin">
							<div class="row">
								<div
									class="col-xl-12 col-md-6 stretch-card grid-margin grid-margin-sm-0 pb-sm-3">
									<div class="card bg-warning">
										<div class="card-body px-3 py-4">
											<div class="d-flex justify-content-between align-items-start">
												<div class="color-card">
													<p class="mb-0 color-card-head">Users Count</p>
													<h2 class="text-white">
														${userCount}<span class="h5"></span>
													</h2>
												</div>
												
											</div>
											<h6 class="text-white">Since last month</h6>
										</div>
									</div>
								</div>
								<div
									class="col-xl-12 col-md-6 stretch-card grid-margin grid-margin-sm-0 pb-sm-3">
									<div class="card bg-danger">
										<div class="card-body px-3 py-4">
											<div class="d-flex justify-content-between align-items-start">
												<div class="color-card">
													<p class="mb-0 color-card-head">Employee Count</p>
													<h2 class="text-white">
														${empCount}<span class="h5"></span>
													</h2>
												</div>
												
											</div>
											<h6 class="text-white">Since last month</h6>
										</div>
									</div>
								</div>
								<div
									class="col-xl-12 col-md-6 stretch-card grid-margin grid-margin-sm-0 pb-sm-3 pb-lg-0 pb-xl-3">
									<div class="card bg-primary">
										<div class="card-body px-3 py-4">
											<div class="d-flex justify-content-between align-items-start">
												<div class="color-card">
													<p class="mb-0 color-card-head">Completed Orders Count</p>
													<h2 class="text-white">
														${totalOrderStatusCompleted}<span class="h5"></span>
													</h2>
												</div>
												
											</div>
											<h6 class="text-white">Since last month</h6>
										</div>
									</div>
								</div>
								<div class="col-xl-12 col-md-6 stretch-card pb-sm-3 pb-lg-0">
									<div class="card bg-success">
										<div class="card-body px-3 py-4">
											<div class="d-flex justify-content-between align-items-start">
												<div class="color-card">
													<p class="mb-0 color-card-head">Pending Orders Count</p>
													<h2 class="text-white">
													${totalOrderStatusPending}<span class="h5"></span>
													</h2>
												</div>
												
											</div>
											<h6 class="text-white">Since last month</h6>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-xl-9 stretch-card grid-margin"></div>
					</div>



				</div>
				<footer class="footer">
					<div
						class="d-sm-flex justify-content-center justify-content-sm-between">
						<span
							class="text-muted d-block text-center text-sm-left d-sm-inline-block">Copyright
							© HeyKudi 2020</span>
					</div>
				</footer>
			</div>
			<!-- main-panel ends -->
		</div>
		<!-- page-body-wrapper ends -->
	</div>
	<!-- container-scroller -->
	<!-- plugins:js -->
	<script src="assets/vendors/js/vendor.bundle.base.js"></script>
	<!-- endinject -->
	<!-- Plugin js for this page -->
	<script src="assets/vendors/chart.js/Chart.min.js"></script>
	<script
		src="assets/vendors/bootstrap-datepicker/bootstrap-datepicker.min.js"></script>
	<script src="assets/vendors/flot/jquery.flot.js"></script>
	<script src="assets/vendors/flot/jquery.flot.resize.js"></script>
	<script src="assets/vendors/flot/jquery.flot.categories.js"></script>
	<script src="assets/vendors/flot/jquery.flot.fillbetween.js"></script>
	<script src="assets/vendors/flot/jquery.flot.stack.js"></script>
	<script src="assets/vendors/flot/jquery.flot.pie.js"></script>
	<!-- End plugin js for this page -->
	<!-- inject:js -->
	<script src="assets/js/off-canvas.js"></script>
	<script src="assets/js/hoverable-collapse.js"></script>
	<script src="assets/js/misc.js"></script>
	<!-- endinject -->
	<!-- Custom js for this page -->
	<script src="assets/js/dashboard.js"></script>
	<!-- End custom js for this page -->
</body>
</html>