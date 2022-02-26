package api.management.task.presentation.handler

import api.management.task.presentation.converter.ProblemConverter
import api.management.task.presentation.model.response.ProblemResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.validation.BindException
import spock.lang.Specification

import javax.validation.ConstraintViolationException

class GlobalExceptionHandlerSpec extends Specification {

    GlobalExceptionHandler target

    def problemConverter = Mock(ProblemConverter)

    def setup() {
        target = new GlobalExceptionHandler(problemConverter)
    }

    def "正常系_handleBindException"() {
        given:
        1 * problemConverter.convert(_ as BindException) >> ProblemResponse
                .builder().title("mock").build()
        when:
        def actual = target.handleBindException(Mock(BindException))

        then:
        verifyAll(actual) {
            statusCode == HttpStatus.BAD_REQUEST
            headers.getContentType() == MediaType.APPLICATION_PROBLEM_JSON
            body != null
        }
    }

    def "正常系_handleConstraintViolationException"() {
        given:
        1 * problemConverter.convert(_ as ConstraintViolationException) >> ProblemResponse
                .builder().title("mock").build()
        when:
        def actual = target.handleConstraintViolationException(
                Mock(ConstraintViolationException)
        )

        then:
        verifyAll(actual) {
            statusCode == HttpStatus.BAD_REQUEST
            headers.getContentType() == MediaType.APPLICATION_PROBLEM_JSON
            body != null
        }
    }

    def "正常系_handleRuntimeException"() {
        given:
        1 * problemConverter.convert(_ as RuntimeException) >> ProblemResponse
                .builder().title("mock").build()
        when:
        def actual = target.handleRuntimeException(
                Mock(RuntimeException)
        )

        then:
        verifyAll(actual) {
            statusCode == HttpStatus.INTERNAL_SERVER_ERROR
            headers.getContentType() == MediaType.APPLICATION_PROBLEM_JSON
            body != null
        }
    }
}
