package com.fabio.springmongodb.resources.errors;

import com.fabio.springmongodb.services.ObjectNotFoundException;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<MsgError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
        MsgError msgError = new MsgError();
        msgError.setTimestamp(System.currentTimeMillis());
        msgError.setError("Object not found.");
        msgError.setStatus(HttpStatus.NOT_FOUND.value());
        msgError.setMessage(e.getMessage());
        msgError.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msgError);
    }
}
