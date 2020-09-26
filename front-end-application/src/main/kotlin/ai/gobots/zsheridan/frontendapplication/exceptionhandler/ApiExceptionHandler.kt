package ai.gobots.zsheridan.frontendapplication.exceptionhandler

import ai.gobots.zsheridan.frontendapplication.exception.BusinessException
import org.springframework.http.HttpStatus
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.OffsetDateTime

@ControllerAdvice
class ApiExceptionHandler: ResponseEntityExceptionHandler() {

    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(ex: BusinessException, request: WebRequest, model: Model): String {
        val status = HttpStatus.BAD_REQUEST
        val problem = Problem(status.value(), ex.message!!, OffsetDateTime.now())
        model["problem"] = problem
        model["path"] = "/playlist"
        return "custom_error"
    }

}