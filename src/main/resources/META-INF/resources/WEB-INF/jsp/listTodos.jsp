		<%@ include file="constants/header.jspf" %>
		<%@ include file="constants/navigation.jspf" %>
		<div class="container">
			<h2>Your Todos are </h2>
			<table class="table">
				<thead>
					<tr>
						<th>Description</th>
						<th>Target date</th>
						<th>Is Done?</th>
						<th>Update</th>
						<th>Delete</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${todos}" var="todo">
						<tr>
							<td>${todo.description}</td>
							<td>${todo.targetDate}</td>
							<td>${todo.done}</td>	
							<td><a href="update-todo?id=${todo.id}" class="btn btn-success">UPDATE</a></td>	
							<td><a href="delete-todo?id=${todo.id}" class="btn btn-warning">DELETE</a></td>			
						</tr>
					</c:forEach>
				</tbody>
				 
			</table>
			<a href="add-todo" class="btn btn-success">Add todo</a>
		</div>
		<%@ include file="constants/footer.jspf" %>