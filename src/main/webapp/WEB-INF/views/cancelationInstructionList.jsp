<%@include file="header.jsp"%>
<%@page import="java.util.List"%>
<%@page import="com.salon.app.model.CancelationInstruction"%>
<%
CancelationInstruction objData = (CancelationInstruction)request.getAttribute("data");
	
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
						<h4 class="card-title">Change The Cancelation Instruction Details</h4>
						<p class="card-description"></p>
						<form class="forms-sample" id="addHeaderForm" method="post" 
							action="editCancelationInstruction"
							onsubmit="return validateForm()" modelAttribute="deliveryInstruction">
							<input type="hidden" value="<%=objData.getId()%>" name="id"
								id="id">
						
							
							<div class="form-group">
									<label for="exampleInputEmail3">Cancellation Instruction</label>
									<textarea class="form-control" id="editor" name="cancelationInstruction"
										rows="4"><%=objData.getCancelationInstruction()%></textarea>
								</div>
							<button type="submit" class="btn btn-primary mr-2">Change</button>
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