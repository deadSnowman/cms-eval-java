// hide query string shame
window.history.pushState("details", "Title", "Home");

// change to update mode for employee
function employeeUpdateMode(employeeId) {
	$.get("?action=edit&employeeId=" + employeeId, function(resp) {
		var employeeName = resp.employeeName;
		var employeeDept = resp.employeeDept;
		if(employeeName != null && employeeDept != null) {
			$('#employee_back').show();
			$('#employeeBtn').html("Update Employee");
			$('#employeeForm').attr("action", "?action=updateEmployee").attr("name", "updateEmployee");
			$('#employeeName').val(employeeName);
			$("#deptSelect").val(employeeDept);
			$("#employeeId").val(employeeId);
		}
    });
}

// change to update mode for department
function departmentUpdateMode(departmentId) {
	$.get("?action=edit&departmentId=" + departmentId, function(resp) {
		var departmentName = resp.departmentName;
		var departmentDescription = resp.departmentDescription
		if(departmentName != null && departmentDescription != null) {
			$('#department_back').show();
			$('#departmentBtn').html("Update Employee");
			$('#departmentForm').attr("action", "?action=updateDepartment").attr("name", "updateDepartment");
			$('#departmentName').val(departmentName);
			$("#departmentDescription").val(departmentDescription);
			$("#departmentId").val(departmentId);
		}
    });
}

// change back to addEmployee mode
$('#employee_back').click(function() {
	$('#employee_back').hide();
	$('#employeeBtn').html("Add Employee");
	$('#employeeForm').attr("action", "?action=addEmployee").attr("name", "addEmployee");
	$('#employeeName').val("");
	$("#deptSelect").val(1);
	$("#employeeId").val("");
});
	
// change back to addDepartment mode
$('#department_back').click(function() {
	$('#department_back').hide();
	$('#departmentBtn').html("Add Employee");
	$('#departmentForm').attr("action", "?action=addEmployee").attr("name", "addEmployee");
	$('#departmentName').val("");
	$("#departmentDescription").val("");
	$("#departmentId").val("");
});

// transfer id to modal delete button when trash delete for department is clicked
function departmentIdToModal(departmentId) {
	$('#truedelete').attr("href", "?action=delete&departmentId=" + departmentId);
}

// clear the delete query string with departmentId when modal is finished
$('#delete_confirmation_modal').on('hidden.bs.modal', function () {
	$('#truedelete').attr("href", "");
})

