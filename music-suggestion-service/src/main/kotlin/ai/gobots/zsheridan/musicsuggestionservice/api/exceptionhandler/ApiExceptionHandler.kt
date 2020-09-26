package ai.gobots.zsheridan.musicsuggestionservice.api.exceptionhandler

import ai.gobots.zsheridan.musicsuggestionservice.domain.exception.BusinessException
import org.springframework.beans.TypeMismatchException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.OffsetDateTime
import javax.validation.ConstraintViolationException
import javax.validation.ValidationException

@ControllerAdvice
class ApiExceptionHandler: ResponseEntityExceptionHandler() {

    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(ex: BusinessException, request: WebRequest): ResponseEntity<Any> {
        val status = HttpStatus.BAD_REQUEST
        val problem = Problem(status.value(), ex.message!!, OffsetDateTime.now())
        return handleExceptionInternal(ex, problem, HttpHeaders(), status, request)
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolationException(ex: ValidationException, request: WebRequest): ResponseEntity<Any> {
        val status = HttpStatus.BAD_REQUEST
        val problem = Problem(status.value(), "Parameter 'city' must not be blank.", OffsetDateTime.now())
        return handleExceptionInternal(ex, problem, HttpHeaders(), status, request)
    }

    override fun handleTypeMismatch(ex: TypeMismatchException, headers: HttpHeaders,
                                    status: HttpStatus, request: WebRequest): ResponseEntity<Any> {

        val problem = Problem(
                status.value(),
                "Parameters must not be blank.",
                OffsetDateTime.now())

        return super.handleExceptionInternal(ex, problem, headers, status, request)
    }

    override fun handleMissingServletRequestParameter(ex: MissingServletRequestParameterException,
                                                      headers: HttpHeaders, status: HttpStatus,
                                                      request: WebRequest): ResponseEntity<Any> {

        val fields = mutableListOf<Field>()
        fields.add(Field(ex.parameterName, ex.message))

        val problem = Problem(
                status.value(),
                "Missing parameter. Try again.",
                OffsetDateTime.now(),
                fields)

        return super.handleExceptionInternal(ex, problem, headers, status, request)
    }

}