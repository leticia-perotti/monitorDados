package com.arduino.monitordados.exceptions

import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler
import java.net.http.HttpRequest
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.client.HttpClientErrorException.BadRequest

@ControllerAdvice
class ControllerAdvice {
    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(ex: NotFoundException, request: HttpRequest): ResponseEntity<ErrorResponse> {
        val erro = ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.message
        )
        return ResponseEntity(erro, HttpStatus.NOT_FOUND)
    }


    @ExceptionHandler(FieldIncorrectException::class)
    fun handleFieldIncorrectException(ex: FieldIncorrectException): ResponseEntity<ErrorResponse>{
        val erro = ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.message
        )

        return ResponseEntity(erro, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(): ResponseEntity<ErrorResponse>{
        val erro = ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Existem campos inválidos. Verifique!"
        )

        return ResponseEntity(erro, HttpStatus.BAD_REQUEST)
    }
}