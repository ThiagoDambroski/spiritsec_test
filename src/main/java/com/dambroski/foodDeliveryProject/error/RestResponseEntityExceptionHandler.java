package com.dambroski.foodDeliveryProject.error;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorMessage> userNotFound(UserNotFoundException exception, WebRequest request){
		
		ErrorMessage error = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		
	}
	
	@ExceptionHandler(NotEnoughFoodException.class)
	public ResponseEntity<ErrorMessage> notEnoughFood(NotEnoughFoodException exception, WebRequest request){
		
		ErrorMessage erro = new ErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
		
	}
	
	@ExceptionHandler(RestaurantNotFoundException.class)
	public ResponseEntity<ErrorMessage> restaurantNotFound(RestaurantNotFoundException exception,WebRequest request){
		
		ErrorMessage erro = new ErrorMessage(HttpStatus.NOT_FOUND,exception.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(RestaurantDontDeliveryException.class)
	public ResponseEntity<ErrorMessage> restaurantDontDelivery(RestaurantDontDeliveryException exception, WebRequest request){
		
		ErrorMessage erro = new ErrorMessage(HttpStatus.BAD_GATEWAY,exception.getMessage());
		
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(erro);
		
	}
	
	@ExceptionHandler(AddressNotFoundException.class)
	public ResponseEntity<ErrorMessage> addressNotFound(AddressNotFoundException exception, WebRequest request){
		
		ErrorMessage erro = new ErrorMessage(HttpStatus.NOT_FOUND,exception.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
		
	}
	
	@ExceptionHandler(DeliveryBoyNotFoundException.class)
	public ResponseEntity<ErrorMessage> deliveryBoyNotFound(DeliveryBoyNotFoundException exception, WebRequest request){
		
		ErrorMessage erro = new ErrorMessage(HttpStatus.NOT_FOUND,exception.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(MissMatchException.class)
	public ResponseEntity<ErrorMessage> missMatchException(MissMatchException exception, WebRequest request){
		
		
		ErrorMessage erro = new ErrorMessage(HttpStatus.EXPECTATION_FAILED,exception.getMessage());
		
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(erro);
	}

}
