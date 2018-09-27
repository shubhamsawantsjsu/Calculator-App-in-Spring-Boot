package com.example.demo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Models.Calculator;


@Controller
public class CalculatorController {
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("operator", "+");
		model.addAttribute("view", "views/calculatorForm");
		return "base-layout";
	}
	
	@PostMapping("/")
	public String index(
			@RequestParam String leftOperand,
			@RequestParam String operator,
			@RequestParam String rightOperand,
			Model model
	) {
		double leftNumber;
		double rightNumber;

		try {
			leftNumber = Double.parseDouble(leftOperand);
		}
		catch (NumberFormatException ex) {
			leftNumber = 0;
		}

		try {
			rightNumber = Double.parseDouble(rightOperand);
		}
		catch (NumberFormatException ex) {
			rightNumber = 0;
		}
		
		Calculator calculator = new Calculator(
				leftNumber,
				rightNumber,
				operator
		);
		
		double result = calculator.calculateResult();
		model.addAttribute("leftOperand", leftNumber);
		model.addAttribute("operator", operator);
		model.addAttribute("rightOperand", rightNumber);
		model.addAttribute("result", result);

		model.addAttribute("view", "views/calculatorForm");
		return "base-layout";
	}
}
