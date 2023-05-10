package com.anywr.test.utils;

import org.springframework.http.HttpStatus;

public class HttpResponse<T> {
	private HttpStatus status;
	private T payload;
	public HttpResponse(HttpStatus status, T payload) {
		super();
		this.status = status;
		this.payload = payload;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public T getPayload() {
		return payload;
	}
	public void setPayload(T payload) {
		this.payload = payload;
	}
	
}
