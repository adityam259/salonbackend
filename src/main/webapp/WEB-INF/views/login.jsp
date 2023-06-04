<%@include file="header.jsp"%>
<%
String message = "";
if (request.getAttribute("message") != null) {
	message = (String) request.getAttribute("message");
}
%>
<div class="container-scroller">
	<div class="content-wrapper">
		<div class="parent-container">
			<div class="row" style="width: 400px">
				<div class="col-12 grid-margin stretch-card">
					<div class="card">
						<div class="card-body">
							<h4 class="card-title"
								style="position: absolute; left: 0; right: 0; margin: auto; width: 200px; text-align: center;">Login</h4>
							<br> <br>
							<p class="card-description"
								style="position: absolute; left: 0; right: 0; margin: auto; width: 200px; text-align: center;">
								Hey Kudi</p>
							<br> <br>
							<form class="forms-sample" method="post" action="submitLoginView">
								<div class="form-group">
									<label for="exampleInputName1">Mobile</label> <input
										type="number" class="form-control" name="mobileNo"
										id="mobileNo" placeholder="99********" autocomplete="off"/>
								</div>
								<div class="form-group">
									<span
										style="position: absolute; left: 0; right: 0; margin: auto; text-align: center; color: red;"><%=message%></span>
								</div>
								<br> <br>
								<div class="form-group">
									<button type="submit" class="btn btn-primary mr-2">
										Submit</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
    var mobileNo = document.getElementById("mobileNo");
    mobileNo.addEventListener("input", function() {
        this.value = this.value.replace(/\D/g, '');
        if (this.value.length > 10) {
            this.value = this.value.slice(0, 10);
        }
    });
</script>
<%@include file="footer.jsp"%>