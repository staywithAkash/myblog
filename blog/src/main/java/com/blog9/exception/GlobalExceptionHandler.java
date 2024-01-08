package com.blog9.exception;

import com.blog9.payload.ErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorDetail> resourceNotFound(
            ResourceNotFound exc,
            WebRequest webRequest
    ){
        ErrorDetail errorDetail=new ErrorDetail(new Date(),exc.getMessage(),webRequest.getDescription(false));
       return new ResponseEntity<>(errorDetail, HttpStatus.OK);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetail> globalException(
            Exception g_exc,
            WebRequest webRequest
    ){
        ErrorDetail errorDetail=new ErrorDetail(new Date(),g_exc.getMessage(),webRequest.getDescription(true));
        return new ResponseEntity<>(errorDetail,HttpStatus.OK);
    }
}
