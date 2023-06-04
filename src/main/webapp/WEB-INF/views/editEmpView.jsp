<%@include file="header.jsp"%>
<%@ page import="com.salon.app.model.Login"%>
<%
	Login objData = (Login)request.getAttribute("data");

%>
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
			<div class="col-12 grid-margin stretch-card">
				<div class="card">
					<div class="card-body">
						<h4 class="card-title">Add Employee</h4>
						<p class="card-description"></p>
						<form class="forms-sample" id="addHeaderForm" method="post" 
							action="editUpdateEmployeeData"
							onsubmit="return validateForm()" modelAttribute="login">
							<input type="hidden" id="id" name="id" value="<%=objData.getId() %>">
							<div class="form-group">
								<label for="exampleInputName1">First Name </label> <input
									type="text" class="form-control" id="firstName" name="firstName"
									placeholder="First Name" value="<%=objData.getFirstName() %>" />
							</div>
							<div class="form-group">
								<label for="exampleInputName1">Last Name </label> <input
									type="text" class="form-control" id="lastName" name="lastName"
									placeholder="Last Name"  value="<%=objData.getLastName() %>"/>
							</div>
							<div class="form-group">
								<label for="exampleInputName1">Mobile Number </label> <input
									type="text" class="form-control" id="phoneNo" name="phoneNo"
									placeholder="Mobile Number" value="<%=objData.getPhoneNo() %>"/>
							</div>
							<div class="form-group">
								<label for="exampleInputName1">Email Id </label> <input
									type="text" class="form-control" id="emailId" name="emailId"
									placeholder="Email Id" value="<%=objData.getEmailId() %>"/>
							</div>
							<div class="form-group">
								<label>Select Gender </label>
			                      <select  name="gender"  class="form-control" style="width: 100%;">
			                      		<option value="male"  <%= objData.getGender().equals("male") ? "selected" : "" %>>Male</option>
			                      		<option value="female"  <%= objData.getGender().equals("female") ? "selected" : "" %>>FeMale</option>
			                      </select>
							</div>
							<div class="form-group">
								<label for="exampleInputName1">Address</label> <input
									type="text" class="form-control" id="address" name="address"
									placeholder="Address" value="<%=objData.getAddress() %>"/>
							</div>
							
							

							<button type="submit" class="btn btn-primary mr-2">Submit</button>
							<button class="btn btn-light" onclick="clearForm()">Clear</button>
						</form>
					</div>
				</div>
			</div>
			<%
			String status = (String) request.getAttribute("status");
			String message = (String) request.getAttribute("message");
			if (status != null && status.equals("SUCCESS")) {
			%>
			<script>
			    Swal.fire({
			      title: "<%=status%>",
			      text: "<%=message%>",
			      icon: "success",
			      confirmButtonText: "OK"
			    });
  			</script>
			<%
			} else if (status != null && status.equals("error")) {
			%>
			<script>
			    Swal.fire({
			      title: ""<%=status%>"",
			      text: "<%=message%>",
			      icon: "error",
			      confirmButtonText: "OK"
			    });
			  </script>
			<%
			}
			%>
			<script>
                        ClassicEditor
                                .create( document.querySelector( '#editor' ) )
                                .then( editor => {
                                        console.log( editor );
                                } )
                                .catch( error => {
                                        console.error( error );
                                } );
                </script>

			<script>
				function clearForm() {
				  document.getElementById("addHeaderForm").reset();
				}
			</script>
			
			<script type="text/javascript">
			function validateForm() {
				  const firstName = document.getElementById("firstName").value;
				  const lastName = document.getElementById("lastName").value;
				  const phoneNo = document.getElementById("phoneNo").value;
				  const emailId = document.getElementById("emailId").value;
				  const Address = document.getElementById("Address").value;

				  if (firstName === "" || lastName === "" || phoneNo === "" || 
						  emailId === "" || Address === "" ) {
				    alert("Please Fill Out All The Fields.");
				    return false;
				  }

				  return true;
				}
			</script>

			<%@include file="footer.jsp"%>