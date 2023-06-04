<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page import="java.util.ArrayList"%>
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
			<%@ page import="com.salon.app.model.ProductImages"%>
			<%@ page import="java.util.List"%>
			<%@ page import="java.util.ArrayList"%>
			<%
			List<ProductImages> listheader = new ArrayList<>();
			if(request.getAttribute("data")!=null){
				listheader = (List<ProductImages>) request.getAttribute("data");
			}
			int currentPage = (int) request.getAttribute("currentPage");
			long totalHeaders = (long) request.getAttribute("totalHeaders");
			int totalPages = (int) request.getAttribute("totalPages");
			int pageSize = (int) request.getAttribute("pageSize");
		%>

			<div class="col-12 grid-margin stretch-card">
				<div class="card">
					<div class="card-body">
						<h4 class="card-title">List Of Headers</h4>
						<p class="card-description"></p>
						<div class="table-responsive">
							<table class="table table-striped">
								<thead>
									<tr>
										<th>Header IMG</th>
										<th>TITLE</th>
										<th>Price</th>
										<th>Original Price</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<% if(listheader!=null && !listheader.isEmpty()){
                    		for(ProductImages uploadHeaderData:listheader){
                    			
                    	
                    	%>
									<tr>
										<td class="py-1"><img src="uploads/<%=uploadHeaderData.getImageName() %>" alt="image" /></td>
										<td><%=uploadHeaderData.getTitle() %></td>
										<td><%=uploadHeaderData.getRate() %></td>
										<td>
											<%if(uploadHeaderData.getOriginalPrice()!=null){ %> <%=uploadHeaderData.getOriginalPrice() %>
											<%} else{ %> <%="-" %> <%} %>
										</td>
										<td><a class="btn btn-info"
											href="javascript:updateId('<%=uploadHeaderData.getId()%>')">Edit</a>
											<a class="btn btn-danger"
											href="javascript:deleteId('<%=uploadHeaderData.getId()%>')">Delete</a></td>
									</tr>
									<%	}
                    	}%>
								</tbody>
							</table>
						</div>
					</div>
					<div class="card-footer">
						<div class="d-flex justify-content-center">
							<ul class="pagination">
								<% if(currentPage > 0){ %>
								<li class="page-item"><a class="page-link"
									href="<c:url value="/listOfProductsView?page=0&size=${pageSize}"/>">First</a>
								</li>
								<li class="page-item"><a class="page-link"
									href="<c:url value="/listOfProductsView?page=${currentPage - 1}&size=${pageSize}"/>">Previous</a>
								</li>
								<% } %>
								<% int startPage = currentPage - 5 > 0 ? currentPage - 5 : 0;
               						int endPage = currentPage + 5 < totalPages ? currentPage + 5 : totalPages - 1; %>
								<% for(int i = startPage; i <= endPage; i++){ %>
								<li class="page-item <%= (currentPage == i) ? "active" : "" %>">
									<a class="page-link"
									href="listOfProductsView?page=<%=i %>&size=<%=pageSize%>"><%= i + 1 %></a>
								</li>
								<% } %>
								<% if(currentPage < totalPages - 1){ %>
								<li class="page-item"><a class="page-link"
									href="<c:url value="/listOfProductsView?page=${currentPage + 1}&size=${pageSize}"/>">Next</a>
								</li>
								<li class="page-item"><a class="page-link"
									href="<c:url value="/listOfProductsView?page=${totalPages - 1}&size=${pageSize}"/>">Last</a>
								</li>
								<% } %>
							</ul>
						</div>
					</div>

				</div>

			</div>
			<script>
function deleteId(idVal){
	 const id = idVal;
	  $.ajax({
	    url: 'deleteProductPage',
	    method: 'POST',
	    data: {id: id},
	    success: function(response) {
		      var status = response.status;
		      var message = response.message;
		      Swal.fire({
		    	    title: status,
		    	    text: message,
		    	    icon: "success",
		    	    confirmButtonText: "OK"
		    	}).then(function(result) {
		    	    if (result.isConfirmed) {
		    	        window.location.href = "dashboard";
		    	    }
		    	});

	    },
	    error: function(xhr, status, error) {
	      console.error(error);
	    }
	  });
	
}
</script>

			<script>
function updateId(idVal){
	 const id = idVal;
	  $.ajax({
	    url: 'editProductView',
	    method: 'POST',
	    data: {id: id},
	    success: function(response) {
	    	 var id = response.id;
	    	 var form = document.createElement('form');
	    	 form.method = 'POST';
	    	 form.action = 'updateProductView';

	    	 var input = document.createElement('input');
	    	 input.type = 'hidden';
	    	 input.name = 'id';
	    	 input.value = id;

	    	 form.appendChild(input);

	    	 // Add the form to the document and submit it
	    	 document.body.appendChild(form);
	    	 form.submit();
	    	 
	    },
	    error: function(xhr, status, error) {
	      console.error(error);
	    }
	  });
	
}
</script>
			<%@include file="footer.jsp"%>