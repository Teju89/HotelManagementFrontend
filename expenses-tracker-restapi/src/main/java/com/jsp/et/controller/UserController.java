package com.jsp.et.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.et.dto.ExpensesDTO;
import com.jsp.et.dto.TotalDTO;
import com.jsp.et.dto.UserDTO;
import com.jsp.et.service.ExpenseService;
import com.jsp.et.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ExpenseService expenseService;

	@PostMapping("/register")
	public ResponseEntity<String> registration(@RequestBody UserDTO userDTO) {
		//return userService.registration(userDTO);
		int id = userService.registration(userDTO);
		if(id != 0) {
		return ResponseEntity.status(HttpStatus.CREATED).body("UserId = " + id);
		}
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Invalid");
	}

	@PostMapping("/login")
	public ResponseEntity<UserDTO> login(@RequestBody UserDTO userDTO) {
		UserDTO dto = userService.login(userDTO);
		if(dto != null) {
			return ResponseEntity.status(HttpStatus.OK).body(dto);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	
	@PostMapping("/addexpense/{id}")
	public ResponseEntity<Integer> addExpense(@RequestBody ExpensesDTO dto,@PathVariable("id") int userId) {
		int id = expenseService.addExpense(dto, userId);
		if(id != 0) {
			return ResponseEntity.status(HttpStatus.CREATED).body(id);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id);
	}
	
	@GetMapping("/viewexpense/{id}")
	public  ResponseEntity<List<ExpensesDTO>> viewExpense(@PathVariable("id") int userId){
		List<ExpensesDTO> expenses = expenseService.viewExpense(userId);
		if(expenses != null) {
			return ResponseEntity.status(HttpStatus.OK).body(expenses);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	
	@PutMapping("/updateexpense/{eid}")
	public ResponseEntity<ExpensesDTO> updateExpense(@RequestBody ExpensesDTO dto,@PathVariable("eid") int expenseId){
		ExpensesDTO expense = expenseService.updateExpense(dto, expenseId);
		if(expense != null) {
			return ResponseEntity.status(HttpStatus.OK).body(expense);
		}
		return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(null);
	}
	
	@DeleteMapping("/deleteexpense/{eid}")
	public ResponseEntity<Integer> deleteExpense(@PathVariable("eid") int expenseId){
		int id = expenseService.deleteExpense(expenseId);
		if(id != 0) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(id);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id);
	}
	
	@GetMapping("/expense/{eid}")
	public ResponseEntity<ExpensesDTO> findByExpenseId(@PathVariable("id") int expenseId) {
		ExpensesDTO dto = expenseService.findByExpenseId(expenseId);
		if(dto != null) {
			return ResponseEntity.status(HttpStatus.OK).body(dto);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	
	@GetMapping("/filter/expense1/{userid}")
	public ResponseEntity<List<ExpensesDTO>> filterExpenseByCategoryDateAmount(@RequestBody ExpensesDTO dto, @PathVariable("userid") int userid){
		List<ExpensesDTO> expenses = expenseService.filterBasedOnDateCategoryAmount(dto, userid);
		if(expenses != null) {
			return ResponseEntity.status(HttpStatus.OK).body(expenses);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	
	@GetMapping("/filter/expense2/{userid}")
	public ResponseEntity<List<ExpensesDTO>> filterExpenseByDate(@RequestBody ExpensesDTO dto, @PathVariable("userid") int userid){
		List<ExpensesDTO> expenses = expenseService.filterBasedOnDate(dto, userid);
		if(expenses != null) {
			return ResponseEntity.status(HttpStatus.OK).body(expenses);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	
	@GetMapping("/filter/expense3/{userid}/{range}")
	public ResponseEntity<List<ExpensesDTO>>  filterExpenseByAmount(@PathVariable("range") String range, @PathVariable("userid") int userid){
		List<ExpensesDTO> expenses = expenseService.filterBasedOnAmount(range, userid);
		if(expenses != null) {
			return ResponseEntity.status(HttpStatus.OK).body(expenses);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	
	@GetMapping("/filter/expense4/{userid}")
	public ResponseEntity<List<ExpensesDTO>>  filterExpenseByCategory(@RequestBody ExpensesDTO dto, @PathVariable("userid") int userid){
		List<ExpensesDTO> expenses = expenseService.filterBasedOnCategory(dto, userid);
		if(expenses != null) {
			return ResponseEntity.status(HttpStatus.OK).body(expenses);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	
	@GetMapping("/total/{userId}")
	public ResponseEntity<String> getTotalOfExpense(@RequestBody TotalDTO total, @PathVariable("userId") int userId) {
		double totalAmount = expenseService.getTotalExpenseBasedOnDate(total.getStart(), total.getEnd(), userId);
		if(totalAmount > 0) {
			return ResponseEntity.status(HttpStatus.OK).body("Amount = " + totalAmount);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	
	@GetMapping("/getuser/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable("id") int userId){
		UserDTO user = userService.findByUserId(userId);
		if(user != null) {
			return ResponseEntity.status(HttpStatus.OK).body(user);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	
	@DeleteMapping("/deleteuser/{id}")
	public ResponseEntity<Integer> deleteUser(@PathVariable("id") int id){
		int status = userService.deleteUserProfile(id);
		if(status != 0) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(status);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(0);
	}
	
	@PutMapping("/updateuser/{id}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable("id") int id, @RequestBody UserDTO dto){
		UserDTO updated = userService.updateUserProfile(id, dto);
		if(updated != null) {
			return ResponseEntity.status(HttpStatus.OK).body(updated);
		}
		return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(null);
	}
}
























