<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
			<%@ page import="com.salon.app.model.OrderDetails"%>
			<%@ page import="java.util.List"%>
			<%@ page import="java.util.ArrayList"%>
			<%
			List<OrderDetails> listheader = new ArrayList<>();
			if(request.getAttribute("data")!=null){
				listheader = (List<OrderDetails>) request.getAttribute("data");
			}
			int currentPage = (int) request.getAttribute("currentPage");
			long totalHeaders = (long) request.getAttribute("totalHeaders");
			int totalPages = (int) request.getAttribute("totalPages");
			int pageSize = (int) request.getAttribute("pageSize");
		%>

			<div class="col-12 grid-margin stretch-card">
			
				<div class="card">
					<div class="card-body">
						<h4 class="card-title">List Of Employee</h4>
						<p class="card-description">
						<form action="/orders/search" method="get">
						    <div class="input-group">
						        <input type="text" class="form-control" name="query" placeholder="Search by order tracking id...">
						        <div class="input-group-append">
						            <button class="btn btn-primary" type="submit" >Search</button>
						        </div>
						    </div>
						</form>
						</p>
						<div class="table-responsive">
							<table class="table table-striped">
								<thead>
									<tr>
										<th>First Name</th>
										<th>Order Tracking Id</th>
										<th>Status</th>

									</tr>
								</thead>
								<tbody>
									<% if(listheader!=null && !listheader.isEmpty()){
                    		for(OrderDetails uploadHeaderData:listheader){
                    			
                    	
                    	%>
									<tr>

										<td><%=uploadHeaderData.getFullName() %></td>
										<td><%=uploadHeaderData.getOrderTrackingId() %></td>
										<td><%=uploadHeaderData.getStatus() %></td>


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
								<% if (currentPage > 0) { %>
								<li class="page-item"><a class="page-link"
									href="<c:url value="//orders/search?page=0&size=${pageSize}"/>">First</a>
								</li>
								<li class="page-item"><a class="page-link"
									href="<c:url value="//orders/search?page=${currentPage - 1}&size=${pageSize}"/>">Previous</a>
								</li>
								<% } %>

								<% int startPage = Math.max(currentPage - 5, 0);
      							 int endPage = Math.min(currentPage + 5, totalPages - 1); %>

								<%System.out.println("startPage = "+startPage); %>
								<%System.out.println("endPage = "+endPage); %>

								<% for (int i = startPage; i <= endPage; i++) { %>
								<li class="page-item <%= (currentPage == i) ? "active" : "" %>">
									<a class="page-link"
									href="/orders/search?page=<%=i %>&size=<%=pageSize%>"><%= i + 1 %></a>
								</li>
								<% } %>

								<% if (currentPage < totalPages - 1) { %>
								<li class="page-item"><a class="page-link"
									href="<c:url value="//orders/search?page=${currentPage + 1}&size=${pageSize}"/>">Next</a>
								</li>
								<li class="page-item"><a class="page-link"
									href="<c:url value="//orders/search?page=${totalPages - 1}&size=${pageSize}"/>">Last</a>
								</li>
								<% } %>
							</ul>

						</div>
					</div>

				</div>

			</div>


			<script>
	function updateId(idVal){
	 const id = idVal;
	  $.ajax({
	    url: 'editEmpView',
	    method: 'POST',
	    data: {id: id},
	    success: function(response) {
	    	 var id = response.id;
	    	 var form = document.createElement('form');
	    	 form.method = 'POST';
	    	 form.action = 'updateEmpPageView';

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