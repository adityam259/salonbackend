<%@include file="header.jsp"%>
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
						<h4 class="card-title">Add Top Header / Banner</h4>
						<p class="card-description"></p>
						<form class="forms-sample" id="addHeaderForm" method="post" 
						enctype="multipart/form-data"
							action="submitHeaderImage"
							onsubmit="return validateForm()">
							<div class="form-group">
								<label for="exampleInputName1">Product Name / Title</label> <input
									type="text" class="form-control" id="title" name="title"
									placeholder="Product Name / Title" />
							</div>
							<div class="form-group">
								<label for="exampleInputEmail3">Product Description</label>
								<textarea class="form-control" id="editor" name="description"
									rows="4"></textarea>
							</div>
							<div class="form-group">
								<label for="exampleInputPassword4">Price</label> <input
									type="text" class="form-control" id="rate" name="rate"
									placeholder="25*.**" />
							</div>
							<div class="form-group">
								<label for="exampleInputPassword4">Original Price</label> <input
									type="text" class="form-control" id="originalPrice" name="originalPrice"
									placeholder="25*.**" />
							</div>
							<div class="form-group">
								<label for="exampleInputPassword4">Time</label> <input
									type="text" class="form-control" id="time" name="time"
									placeholder="45min / 1hr" />
							</div>
							<div class="form-group">
								<label for="exampleInputPassword4">Star Membership Price in %</label> <input
									type="text" class="form-control" id="membershipDiscount" name="membershipDiscount"
									placeholder="11" />
							</div>
							<div class="form-group">
								<label for="exampleInputPassword4">Premium Membership Price in %</label> <input
									type="text" class="form-control" id="membershipDiscount2" name="membershipDiscount2"
									placeholder="12" />
							</div>
							<div class="form-group">
							
								<label>Upload Banner / Header</label> 
								<input type="file"
									name="headerImage" class="file-upload-default" />
									
								<div class="input-group col-xs-12">
									<input type="text" class="form-control file-upload-info"
										disabled placeholder="Upload Banner / Header"
										name="headerImage" id="headerImage" /> <span
										class="input-group-append">
										<button class="file-upload-browse btn btn-primary"
											type="button">Upload</button>
									</span>
								</div>
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
				  const title = document.getElementById("title").value;
				  const description = document.getElementById("editor").value;
				  const rate = document.getElementById("rate").value;
				  const originalPrice = document.getElementById("originalPrice").value;
				  const time = document.getElementById("time").value;
				  const headerImage = document.getElementById("headerImage").value;

				  if (title === "" || description === "" || rate === "" || originalPrice === "" || time === "" || headerImage === "") {
				    alert("Please Fill Out All The Fields.");
				    return false;
				  }

				  return true;
				}
			</script>

			<%@include file="footer.jsp"%>