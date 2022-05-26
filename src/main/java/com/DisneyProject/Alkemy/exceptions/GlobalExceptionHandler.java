
package com.DisneyProject.Alkemy.exceptions;

import com.DisneyProject.Alkemy.dto.ErrorDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> manejarResourceNotFoundException(ResourceNotFoundException exception,WebRequest webRequest){
        
        ErrorDetails errorDetails = new ErrorDetails(new Date(),exception.getMessage(),webRequest.getDescription(false));
        
        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorDetails> manejarAppException(AppException exception,WebRequest webRequest){
        
        ErrorDetails errorDetails = new ErrorDetails(new Date(),exception.getMessage(),webRequest.getDescription(false));
        
        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }
    
     @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> manejarGlobalException(Exception exception,WebRequest webRequest){
        
        ErrorDetails errorDetails = new ErrorDetails(new Date(),exception.getMessage(),webRequest.getDescription(false));
        
        return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        
    Map<String, String> errores = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
        String nombreCampo = ((FieldError)error).getField();
        String mensaje = error.getDefaultMessage();
        
        errores.put(nombreCampo,mensaje);
    });
    
    
    return new ResponseEntity<>(errores,HttpStatus.BAD_REQUEST);
    
    }
    
    

}
