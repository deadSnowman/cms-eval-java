<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- CDN -->
<!-- <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"> -->

<!-- CSS includes -->
<link href="./bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<link href="./style.css" />

<title>CMS Evaluation Application</title>
</head>
<body>
<div class="container-fluid">

	<h1>CMS Evaluation Application</h1>

	<div class="row">
		<div class="modal fade" id="delete_confirmation_modal" tabindex="-1" role="dialog" aria-labelledby="delete_confimration_modal" aria-hidden="true">
		   <div class="modal-dialog modal-sm">
		     <div class="modal-content">
		       <div class="modal-header">
		         <button type="button" id="modal_close" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		         <h4 class="modal-title" id="myModalLabel">Delete Department</h4>
		       </div>
		       <div class="modal-body">
		         <p>Are you sure you want to delete this department?  All employees in the department will be removed as well.</p>
		       </div>
		       <div class="modal-footer">
		         <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		         <a id="truedelete" class="btn btn-danger" href="" role="button">Delete</a>
		       </div>
		     </div>
		   </div>
		 </div>
	
	
		<div class="col-sm-6">
			<table class="table table-bordered table-striped">
				<tr>
					<th>Name</th>
					<th>Department - Description</th>
					<th style="width: 110px;"></th>
				</tr>
			<c:forEach items="${employees}" var="employee">
				<tr>
					<td><c:out value="${employee.name}" /></td>
					<td><c:out value="${employee.depName}" /> - <c:out value="${employee.depDescription}" /></td>
					<td><a class="btn btn-default" onclick="employeeUpdateMode(<c:out value="${employee.employeeID}"/>);" role="button"><span class="glyphicon glyphicon-pencil"></span></a> <a class="btn btn-danger" href="?action=delete&employeeId=<c:out value="${employee.employeeID}"/>" role="button"><span class="glyphicon glyphicon-trash"></span></a></td>
				</tr>
			</c:forEach>
			</table>
			<form class="form-inline" id="employeeForm" method="POST" action="?action=addEmployee" name="addEmployee">
				<div class="form-group"><input type="text" class="form-control mb-2 mr-sm-2" id="employeeName" name="employeeName" placeholder="Name"></div>
				<div class="form-group"><select class="form-control mb-2 mr-sm-2" id="deptSelect" name="deptSelect">
			<c:forEach items="${departments}" var="department">
				<option id="deptID" name="deptID" value="<c:out value="${department.departmentID}" />">${department.name}</option>
			</c:forEach>
			    </select></div>
			    <div class="form-group"><button id="employeeBtn" class="btn btn-primary" type="submit" value="Submit">Add Employee</button></div>
			    <div class="form-group"><button type="button" class="btn btn-default" id="employee_back" style="display: none;">Back</button></div>
			    <input type="hidden" id="employeeId" name="employeeId" value="" />
			</form>
		</div>
		<div class="col-sm-6">
			<table class="table table-bordered table-striped">
			<tr>
				<th>Name</th>
				<th>Description</th>
				<th style="width: 110px;"></th>
			</tr>
		<c:forEach items="${departments}" var="department">
			<tr>
				<td><c:out value="${department.name}" /></td>
				<td><c:out value="${department.description}" /></td>
				<!-- <td><a class="btn btn-default" onclick="departmentUpdateMode(<c:out value="${department.departmentID}"/>);"  role="button"><span class="glyphicon glyphicon-pencil"></span></a> <a class="btn btn-danger" href="?action=delete&departmentId=<c:out value="${department.departmentID}"/>" role="button"><span class="glyphicon glyphicon-trash"></span></a></td> -->
				<td><a class="btn btn-default" onclick="departmentUpdateMode(<c:out value="${department.departmentID}"/>);"  role="button"><span class="glyphicon glyphicon-pencil"></span></a> <a onclick="departmentIdToModal(<c:out value="${department.departmentID}"/>);" class="btn btn-danger" data-toggle="modal" data-target="#delete_confirmation_modal" role="button"><span class="glyphicon glyphicon-trash"></span></a></td>
			</tr>
		</c:forEach>
			</table>
			<form class="form-inline" id="departmentForm" method="POST" action="?action=addDepartment" name="addDepartment">
				<div class="form-group"><input type="text" class="form-control mb-2 mr-sm-2" id="departmentName" name="departmentName" placeholder="Name"></div>
				<div class="form-group"><input type="text" class="form-control mb-2 mr-sm-2" id="departmentDescription" name="departmentDescription" placeholder="Description"></div>
			    <div class="form-group"><button id="departmentBtn" class="btn btn-primary" type="submit" value="Submit">Add Department</button></div>
			    <div class="form-group"><button type="button" class="btn btn-default" id="department_back" style="display: none;">Back</button></div>
			    <input type="hidden" id="departmentId" name="departmentId" value="" />
			</form>
		</div>
	</div>


</div>



</body>

<!-- ending javascript CDN -->
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> -->
<!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->

<!-- ending javascript -->
<script src="./jquery/jquery.min.js"></script>
<script src="./bootstrap/js/bootstrap.min.js"></script>
<script src="./script.js"></script>
</html>