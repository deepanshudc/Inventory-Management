package com.project.Inventory.Management.App.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	    @ExceptionHandler(ClassNotFoundException.class)
	    @ResponseStatus(HttpStatus.NOT_FOUND)
	    public String handleNotFoundException(Exception ex, Model model) {
	        // You can customize the error message or use a view resolver to show a specific 404 page
	        model.addAttribute("errorMessage", "The requested page was not found");
	        return "error/404";
	    
	}
	    
	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    public String handleValidationException(MethodArgumentNotValidException ex, Model model) {
	        // Get the validation errors from the exception
	        BindingResult bindingResult = ex.getBindingResult();

	        // Add the errors to the model so they can be displayed in the view
	        model.addAttribute("errors", bindingResult.getFieldErrors());

	        // Return the name of the view that displays validation errors
	        return "show-form";
	    }
}