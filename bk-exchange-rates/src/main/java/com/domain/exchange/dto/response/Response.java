package com.domain.exchange.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response<T> {

	public enum Status {
		OK, BAD_REQUEST, VALIDATION_EXCEPTION, EXCEPTION, NOT_FOUND
	}

	private Status status;
	private String title;
	private String description;
	private T payload;
	private Object errors;

	
	public static <T> Response<T> badRequest() {
		Response<T> response = new Response<>();
		response.setStatus(Status.BAD_REQUEST);
		return response;
	}

	public static <T> Response<T> ok() {
		Response<T> response = new Response<>();
		response.setStatus(Status.OK);
		return response;
	}

	public static <T> Response<T> validationException() {
		Response<T> response = new Response<>();
		response.setStatus(Status.VALIDATION_EXCEPTION);
		return response;
	}

	public static <T> Response<T> exception() {
		Response<T> response = new Response<>();
		response.setStatus(Status.EXCEPTION);
		return response;
	}

	public static <T> Response<T> notFound() {
		Response<T> response = new Response<>();
		response.setStatus(Status.NOT_FOUND);
		return response;
	}

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * @return the payload
	 */
	public T getPayload() {
		return payload;
	}

	/**
	 * @param payload the payload to set
	 */
	public void setPayload(T payload) {
		this.payload = payload;
	}

	/**
	 * @return the errors
	 */
	public Object getErrors() {
		return errors;
	}

	/**
	 * @param errors the errors to set
	 */
	public void setErrors(Object errors) {
		this.errors = errors;
	}

	

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	public void addErrorMsgToResponse(String message, Exception ex) {
		ResponseError error = new ResponseError();
		error.setDetails(ex.getLocalizedMessage());
		error.setMessage(message);
		error.setTimestamp(LocalDateTime.now());
		setErrors(error);
		setTitle(ex.getMessage());
		setDescription(message);
	}

}
