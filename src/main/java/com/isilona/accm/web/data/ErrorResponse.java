package com.isilona.accm.web.data;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {

    private String url;

    private List<ErrorHolder> errorHolderList;

    public String getUrl() {
	return url;
    }

    public void setUrl(String url) {
	this.url = url;
    }

    public void addErrorHolder(ErrorHolder errorHolder) {
	if (this.errorHolderList == null) {
	    errorHolderList = new ArrayList<>();
	}
	errorHolderList.add(errorHolder);
    }

    public List<ErrorHolder> getErrorHolderList() {
	return errorHolderList;
    }

    public void setErrorHolderList(List<ErrorHolder> errorHolder) {
	this.errorHolderList = errorHolder;
    }

}
