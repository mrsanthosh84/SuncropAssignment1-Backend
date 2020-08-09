/**
 * This data model used to represent the result of response entity from API  
 */
package com.suncrop.assignment1.data.model;

/**
 * @author manickas
 *
 */
public class ApiResponse<T> {
	
	private int status;
    private String message;
    private Object result;
    
    public ApiResponse() {}

    public ApiResponse(int status, String message, Object result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

}
