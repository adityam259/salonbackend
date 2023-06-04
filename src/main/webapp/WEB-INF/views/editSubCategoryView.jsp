<%@include file="header.jsp"%>
<%@ page import="com.salon.app.model.SubCategoryMaster"%>
<%@page import="java.util.List"%>
<%@page import="com.salon.app.model.CategoryListData"%>
<%
	SubCategoryMaster objData = (SubCategoryMaster)request.getAttribute("data");
	List<CategoryListData> listOfData = (List<CategoryListData>)request.getAttribute("dataCatList");
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
						<h4 class="card-title">Edit Top Header / Banner</h4>
						<p class="card-description"></p>
						<form class="forms-sample" id="addHeaderForm" method="post"
							enctype="multipart/form-data" action="updateSubCategoryImageData"
							onsubmit="return validateForm()">

							<input type="hidden" value="<%=objData.getId()%>" name="id"
								id="id">

							<div class="form-group">
								<label for="exampleInputName1">Product Name / Title</label> <input
									type="text" class="form-control" id="title" name="title"
									placeholder="Product Name / Title"
									value="<%=objData.getTitle()%>" />
							</div>
							<div class="form-group">
								<label for="exampleInputEmail3">Product Description</label>
								<textarea class="form-control" id="editor" name="description"
									rows="4"><%=objData.getDescription()%></textarea>
							</div>
							<div class="form-group">
								<label for="exampleInputPassword4">Price</label> <input
									type="text" class="form-control" id="price" name="price"
									placeholder="25*.**" value="<%=objData.getPrice()%>" />
							</div>
							<div class="form-group">
								<label for="exampleInputPassword4">Original Price</label> <input
									type="text" class="form-control" id="originalPrice"
									name="originalPrice" placeholder="25*.**"
									value="<%=objData.getOriginalPrice()%>" />
							</div>
							
							<div class="form-group">
								<label for="exampleInputPassword4">Star Membership Price in %</label> <input
									type="text" class="form-control" id="membershipDiscount" name="membershipDiscount"
									placeholder="11" value="<%=objData.getMembershipDiscount()%>"/>
							</div>
							<div class="form-group">
								<label for="exampleInputPassword4">Premium Membership Price in %</label> <input
									type="text" class="form-control" id="membershipDiscount2" name="membershipDiscount2"
									placeholder="12" value="<%=objData.getMembershipDiscount2()%>"/>
							</div>
							
							
							
							<div class="form-group">
								<label for="exampleInputPassword4">Time</label> <input
									type="text" class="form-control" id="serviceTime"
									name="serviceTime" placeholder="45min / 1hr"
									value="<%=objData.getServiceTime()%>" />
							</div>
							<div class="form-group">

								<label>Upload Image 1</label> <input type="file"
									name="subCategoryImage1" class="file-upload-default" />

								<div class="input-group col-xs-12">
									<input type="text" class="form-control file-upload-info"
										disabled placeholder="Upload Banner / Header"
										name="subCategoryImage1" id="subCategoryImage1" /> <span
										class="input-group-append">
										<button class="file-upload-browse btn btn-primary"
											type="button">Upload</button>
									</span>
								</div>
							</div>

							<div class="form-group">

								<label>Upload Image 2</label> <input type="file"
									name="subCategoryImage2" class="file-upload-default" />

								<div class="input-group col-xs-12">
									<input type="text" class="form-control file-upload-info"
										disabled placeholder="Upload Banner / Header"
										name="subCategoryImage2" id="subCategoryImage2" /> <span
										class="input-group-append">
										<button class="file-upload-browse btn btn-primary"
											type="button">Upload</button>
									</span>
								</div>
							</div>

							<div class="form-group">

								<label>Upload Image 3</label> <input type="file"
									name="subCategoryImage3" class="file-upload-default" />

								<div class="input-group col-xs-12">
									<input type="text" class="form-control file-upload-info"
										disabled placeholder="Upload Banner / Header"
										name="subCategoryImage3" id="subCategoryImage3" /> <span
										class="input-group-append">
										<button class="file-upload-browse btn btn-primary"
											type="button">Upload</button>
									</span>
								</div>
							</div>

							<div class="form-group">

								<label>Upload Image 4</label> <input type="file"
									name="subCategoryImage4" class="file-upload-default" />

								<div class="input-group col-xs-12">
									<input type="text" class="form-control file-upload-info"
										disabled placeholder="Upload Banner / Header"
										name="subCategoryImage4" id="subCategoryImage4" /> <span
										class="input-group-append">
										<button class="file-upload-browse btn btn-primary"
											type="button">Upload</button>
									</span>
								</div>
							</div>

							<div class="form-group">
								<label><b style="color: red">*</b> Select Category </label> <select
									name="categoryId" class="form-control" style="width: 100%;">
									<% for(CategoryListData data : listOfData) { %>
									<option value="<%= data.getId() %>"><%= data.getCategoryName() %></option>
									<% } %>
								</select>
							</div>

							<div class="form-group">

								<label>Upload Image 5</label> <input type="file"
									name="subCategoryImage5" class="file-upload-default" />

								<div class="input-group col-xs-12">
									<input type="text" class="form-control file-upload-info"
										disabled placeholder="Upload Banner / Header"
										name="subCategoryImage5" id="subCategoryImage5" /> <span
										class="input-group-append">
										<button class="file-upload-browse btn btn-primary"
											type="button">Upload</button>
									</span>
								</div>
							</div>
							
							<div class="form-group">
								<label for="exampleInputName1"> Brand </label> <input
									type="text" class="form-control" id="brand" name="brand"
									placeholder="Product Brand" />
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
				  const rate = document.getElementById("price").value;
				  const originalPrice = document.getElementById("originalPrice").value;
				  const time = document.getElementById("serviceTime").value;

				  if (title === "" || description === "" || rate === "" || originalPrice === "" || time === "" ) {
				    alert("Please Fill Out All The Fields.");
				    return false;
				  }

				  return true;
				}
			</script>

			<%@include file="footer.jsp"%>