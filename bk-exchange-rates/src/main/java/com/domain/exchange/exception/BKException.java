package com.domain.exchange.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.domain.exchange.dto.response.Response;



@ControllerAdvice
@RestController
public class BKException {

	@ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleNotFountExceptions(Exception ex, WebRequest request) {
        Response<Object> response = Response.exception();
        response.setTitle("Something went wrong!");
        response.setDescription(ex.getMessage());
        return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@ExceptionHandler(RequestValidation.class)
    public final ResponseEntity<Object> variableNotValidException(Exception ex, WebRequest request) {
        Response<Object> response = Response.validationException();
        response.addErrorMsgToResponse("Request failed due to incorrect Parameters", ex);
        return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
    }
	
	
	
	/**
     * Returns new RuntimeException based on EntityType, ExceptionType and args
     *
     * @param entityType
     * @param exceptionType
     * @param args
     * @return
     */
    public static RuntimeException throwExceptionWithId(VariableType variableType, ExceptionType exceptionType, String message, Object... args) {
        String messageTemplate = getMessageTemplate(variableType, exceptionType);
        return throwException(exceptionType, messageTemplate, String.format(message, args));
    }
	
	public static class RequestValidation extends RuntimeException {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private String causeMessage;

		/**
		 * @param message
		 */
		public RequestValidation(String message, String causeMessage) {
            super(message);
            this.causeMessage=causeMessage;
        }

		/**
		 * @return the causeMessage
		 */
		public String getCauseMessage() {
			return causeMessage;
		}

		/**
		 * @param causeMessage the causeMessage to set
		 */
		public void setCauseMessage(String causeMessage) {
			this.causeMessage = causeMessage;
		}
		
		@Override
		public String getLocalizedMessage() {
			return this.causeMessage;
		}
    }

	/**
     * Returns new RuntimeException based on template and args
     *
     * @param messageTemplate
     * @param args
     * @return
     */
    private static RuntimeException throwException(ExceptionType exceptionType, String message, String args) {
        if (ExceptionType.VALIDATION_EXCEPTION.equals(exceptionType)) {
            return new RequestValidation(message,args);
        } 
        return new RuntimeException(String.format(message, args));
    }

    private static String getMessageTemplate(VariableType variable, ExceptionType exceptionType) {
        return variable.name().concat(".").concat(exceptionType.getValue()).toLowerCase();
    }
    
}
