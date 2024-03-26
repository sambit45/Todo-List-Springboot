		<%@ include file="constants/header.jspf" %>
		<%@ include file="constants/navigation.jspf" %>
		<div class="container">
			<h1>Enter Todo Details</h1>
			<form:form method="post" modelAttribute="todo">
			
				<fieldSet class="mb-3">
					<form:label path="description"> Description</form:label>
					<form:input type="text" path="description" required="required"/>
					<form:errors path="description" cssClass="text-warning"/>
				</fieldSet>
				
				
				<fieldSet class="mb-3">
					<form:label path="targetDate"> Target Date</form:label>
					<form:input type="text" path="targetDate" required="required"/>
					<form:errors path="targetDate" cssClass="text-warning"/>
				</fieldSet>
				  
				<form:input type="hidden" path="id"/>
				<form:input type="hidden" path="done"/>
				<input type="submit" class="btn btn-success" />
			</form:form>
		</div>
		<%@ include file="constants/footer.jspf" %>
		<script type="text/javascript">
			$('#targetDate').datepicker({
			    format: 'dd-mm-yyyy'
			});
		</script>