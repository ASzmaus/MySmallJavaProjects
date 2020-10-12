package com.example.java;
import com.example.java.utilities.MathematicalOperations;
import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {
        Calculator cal = new Calculator();
        cal.calculate();
    }
    
	protected void calculate() {
		
		InputHelper helper = new InputHelper();
		String s1 = helper.getInput("Enter a numeric value: ");
        String s2 = helper.getInput("Enter a numeric value: ");
        String op = helper.getInput("Choose an operation (+ - * /):");

        double result = 0;
        try {
            switch (op) {
                case "+":
                    result = MathematicalOperations.addValues(s1, s2);
                    break;
                case "-":
                    result = MathematicalOperations.subtractValues(s1, s2);
                    break;
                case "*":
                    result = MathematicalOperations.multiplyValues(s1, s2);
                    break;
                case "/":
                    result = MathematicalOperations.divideValues(s1, s2);
                    break;
                default:
                    System.out.println("Unrecognized operation!");
                    return;
            }

            System.out.println("The answer is " + result);

        } catch (Exception e) {
            System.out.println("Number formatting exception " + e.getMessage());
        }
	}
    	
		class InputHelper{
			private String getInput(String prompt) {
	    		System.out.print(prompt);
	    		Scanner sc = new Scanner(System.in);
	    		return sc.nextLine();
	    	}
			
		}
			
}
