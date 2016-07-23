package com.isilona.accm.web.data;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

/**
 * 
 * Base response to be extended by all classes returned from Controllers. Allows to add common
 * fields to responses
 * 
 * @author iivanov
 *
 */
public class ResponseBase implements Serializable {

    private static final long serialVersionUID = -7939495302379106295L;

    private HttpStatus status;

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public int getStatusCode() {
        return status.value();
    }

    public String getReasonPhrase() {
        return status.getReasonPhrase();
    }

}
