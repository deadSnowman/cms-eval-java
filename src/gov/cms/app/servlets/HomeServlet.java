package gov.cms.app.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dao.EntityService;
import com.test.model.Department;
import com.test.model.Employee;

@WebServlet("/Home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EntityService serv;
	
    public HomeServlet() {
    	serv = new EntityService();
    }

    /**
     * Main "Controller" for this application
     * handles requests, pulls data, and forwards data to the same page
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.getRequestDispatcher("/WEB-INF/views/cms.jsp").forward(request,response);
		
		String forward="";
        String action = "";
        boolean editflag = false;
        
        // load initial data and set forward location
        forward = "/WEB-INF/views/cms.jsp";
    	request.setAttribute("departments", serv.getDepartments());
        request.setAttribute("employees", serv.getEmployees());
        
        // handle "CRUD" functionality for Employee / Department tables
        if(request.getParameter("action") != null) { // load main cms page normally
        	action = request.getParameter("action"); // get the associated action

        	switch(action) {
	        	case "delete":
	        		if(request.getParameter("employeeId") != null) {
	            		int empID = Integer.parseInt(request.getParameter("employeeId"));
	            		if(serv.deleteEmployee(empID)) {
	            			System.out.println("deleted employee with id of \"" + empID + "\"");
	            		} else { System.out.println("delete failed"); }
	            	} else if(request.getParameter("departmentId") != null) {
	            		int depID = Integer.parseInt(request.getParameter("departmentId"));
	            		if(serv.deleteDepartment(depID)) {
	            			System.out.println("deleted department with id of \"" + depID + "\" as well as employees within the department");
	            		} else { System.out.println("delete failed"); }
	            	} else { System.out.println("missing id"); }
	        		break;
	        	case "addEmployee":
	        		if(!request.getParameter("employeeName").isEmpty() && !request.getParameter("deptSelect").isEmpty()) {
	        			String empName = request.getParameter("employeeName");
	        			Integer deptID = Integer.parseInt(request.getParameter("deptSelect"));
	        			if(serv.addEmployee(new Employee(empName, deptID)) == true) {
	        				System.out.println("added employee - name: " + empName + ", " + "dept: " + deptID);
	        			}
	        		} else System.out.println("enter it correctly... ");
	        	break;
	        	case "addDepartment":
	        		if(!request.getParameter("departmentName").isEmpty() && !request.getParameter("departmentDescription").isEmpty()) {
	        			String depName = request.getParameter("departmentName");
	        			String depDescr = request.getParameter("departmentDescription");
	        			if(serv.addDepartment(new Department(depName, depDescr)) == true) {
	        				System.out.println("added department - name: " + depName + ", " + "description: " + depDescr);
	        			}
	        		} else System.out.println("enter it correctly... ");
	        	break;
	        	case "updateEmployee":
	        		if(!request.getParameter("employeeId").isEmpty() && !request.getParameter("employeeName").isEmpty() && !request.getParameter("deptSelect").isEmpty()) {
	        			Integer empID = Integer.parseInt(request.getParameter("employeeId"));
	        			String empName = request.getParameter("employeeName");
	        			Integer deptID = Integer.parseInt(request.getParameter("deptSelect"));
	        			if(serv.updateEmployee(empID, empName, deptID)) {
	        				System.out.println("updated employee - employee_id: " + empID + ", name: " + empName + ", department_id: " + deptID);
	        			}
	        		} else System.out.println("enter it correctly... ");
	        	break;
	        	case "updateDepartment":
	        		if(!request.getParameter("departmentId").isEmpty() && !request.getParameter("departmentName").isEmpty() && !request.getParameter("departmentDescription").isEmpty()) {
	        			Integer deptID = Integer.parseInt(request.getParameter("departmentId"));
	        			String deptName = request.getParameter("departmentName");
	        			String deptDescr = request.getParameter("departmentDescription");
	        			if(serv.updateDepartment(deptID, deptName, deptDescr)) {
	        				System.out.println("updated department - department_id: " + deptID + ", name: " + deptName + ", description: " + deptDescr);
	        			}
	        		} else System.out.println("enter it correctly... ");
	        	break;
	        	case "edit":
	        		editflag = true;
	        		if(request.getParameter("employeeId") != null) {
	        			if(!request.getParameter("employeeId").isEmpty()) {
		        			System.out.println("edit employee");
		        			int empID = Integer.parseInt(request.getParameter("employeeId"));
		        			response.setContentType("application/json");
		        			response.setCharacterEncoding("utf-8");
		        			PrintWriter out = response.getWriter();
		        			out.print(serv.getEmployeeNameAndDept(empID).toString());
	        			}
	        		} else if(request.getParameter("departmentId") != null) {
	        			if(!request.getParameter("departmentId").isEmpty()) {
		        			System.out.println("edit dept");
		        			int deptID = Integer.parseInt(request.getParameter("departmentId"));
		        			response.setContentType("application/json");
		        			response.setCharacterEncoding("utf-8");
		        			PrintWriter out = response.getWriter();
		        			out.print(serv.getDeptNameAndDescription(deptID).toString());
	        			}
	        		} else { System.out.println("missing id"); }
	        	break;
	        	default:
	                System.out.println("unused action...");
        	}
        	
        }
        
        // pull new data and forward (don't do this when edit is clicked, because of ajax functionality)
        if(!editflag) {
	        request.setAttribute("departments", serv.getDepartments());
	        request.setAttribute("employees", serv.getEmployees());
	        request.removeAttribute("action");
	        RequestDispatcher view = request.getRequestDispatcher(forward);
	        view.forward(request, response);
        }
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
