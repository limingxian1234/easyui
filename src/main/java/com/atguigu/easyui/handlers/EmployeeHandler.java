package com.atguigu.easyui.handlers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.easyui.entities.Employee;
import com.atguigu.easyui.services.EmployeeService;
import com.github.pagehelper.Page;

@Controller
public class EmployeeHandler {
	
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping("/updateEmp")
	public void updateEmp(Employee employee, HttpServletResponse response) throws IOException {
		employeeService.updateEmployee(employee);
		response.getWriter().write("success");
	}
	
	@RequestMapping("/removeEmp/{empId}")
	public void removeEmp(@PathVariable("empId") Integer empId, HttpServletResponse response) throws IOException {
		
		employeeService.removeEmployee(empId);

		response.getWriter().write("success");
	}
	
	@RequestMapping("/saveEmp")
	public void saveEmp(Employee employee, HttpServletResponse response) throws IOException {
		employeeService.saveEmployee(employee);
		response.getWriter().write("success");
	}
	
	@ResponseBody
	@RequestMapping("/getEmpPage")
	public HashMap<String, Object> getEmpPage(@RequestParam("page") Integer pageNum) {
		
		int pageSize = 5;
		
		List<Employee> list = employeeService.getEmpPageList(pageNum, pageSize);
		
		Page<Employee> page = (Page<Employee>) list;
		
		long total = page.getTotal();
	
		HashMap<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("total", total);
		jsonMap.put("rows", list);
		
		return jsonMap;
	}
	
	@ResponseBody
	@RequestMapping("/getEmpList")
	public HashMap<Object, Object> getEmpList() {
		
		List<Employee> list = employeeService.getAllEmp();
		
		HashMap<Object, Object> jsonMap = new HashMap<>();
		jsonMap.put("rows", list);
		
		return jsonMap;
	}

}
