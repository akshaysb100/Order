package com.menucard.menucard.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class GlobalException : ResponseEntityExceptionHandler() {
//    @ExceptionHandler(value = [(OrderNotCreate::class)])
//    fun handleUserAlreadyExists(ex: Response, request: WebRequest): ResponseEntity<Response> {
//        val errorDetails = Response(ex.message)
//        return ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST)
//    }

    @ExceptionHandler(value = [(OrderNotCreate::class)])
    fun handleUserAlreadyExist(ex: OrderNotCreate, request: WebRequest): ResponseEntity<Response> {
        val errorDetails = Response("wrong id please try again")
        return ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST)
    }
}