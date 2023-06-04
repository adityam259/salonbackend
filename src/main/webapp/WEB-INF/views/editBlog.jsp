<%@include file="header.jsp"%>
<%@ page import="com.salon.app.model.Blog"%>
<%
Blog objData = (Blog)request.getAttribute("data");

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
						<h4 class="card-title">Edit Blog</h4>
						<p class="card-description"></p>
						<form class="forms-sample" id="addHeaderForm" method="post"
							action="editBlogData" 
							modelAttribute="login"
							enctype="multipart/form-data">
							<input
									type="hidden" class="form-control" id="id"
									name="id" placeholder="Blog Title" value="<%=objData.getId() %>" />
							<div class="form-group">
								<label for="exampleInputName1">Blog Title </label> <input
									type="text" class="form-control" id="blogTitle"
									name="blogTitle" placeholder="Blog Title" value="<%=objData.getBlogTitle() %>" />
							</div>
							<div class="form-group">
								<label>Blog Image</label> <input type="file" name="blogImage"
									class="file-upload-default" />

								<div class="input-group col-xs-12">
									<input type="text" class="form-control file-upload-info"
										disabled placeholder="Upload Banner / Header" name="blogImage"
										id="blogImage" /> <span class="input-group-append">
										<button class="file-upload-browse btn btn-primary"
											type="button">Upload</button>
									</span>
								</div>

								<div class="form-group">
									<label for="exampleInputEmail3">Blog Description</label>
									<textarea class="form-control" id="editor" name="description"
										rows="4"><%=objData.getDescription() %></textarea>
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

				  if (title === "" || description === "" || rate === "" || originalPrice === "" || time === "" ) {
				    alert("Please Fill Out All The Fields.");
				    return false;
				  }

				  return true;
				}
			</script>

			<%@include file="footer.jsp"%>