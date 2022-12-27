/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.fawranwebservice.Models;

public class Response {

	private boolean status;
	private String message;
	private Object object;

	public Response(boolean status) {
		this.status = status;
	}
	public Response(boolean status, String message) {
		this.status = status;
		this.message = message;
	}
	public Response(boolean status, Object object) {
		this.status = status;
		this.object = object;
	}
	public Response(boolean status, String message, Object object) {
		this.status = status;
		this.object = object;
		this.message = message;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setObject(Object object) {
		this.object = object;

	}

	public Object getObject() {
		return object;
	}
}
