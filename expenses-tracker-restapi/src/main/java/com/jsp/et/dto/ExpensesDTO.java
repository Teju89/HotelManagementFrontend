package com.jsp.et.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ExpensesDTO {

	private LocalDate date;
	private double amount;
	private String description;
	//Creating ExpenseCategoryDTO rv may create difficulties in service layer hence go with String
	private String category;
}




