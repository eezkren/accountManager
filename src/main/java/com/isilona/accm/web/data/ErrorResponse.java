package com.isilona.accm.web.data;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse extends ResponseBase {

    private static final long serialVersionUID = 8794566216756271922L;
    private List<String> messageList;

    public List<String> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<String> messageList) {
        this.messageList = messageList;
    }

    public void addMessage(String message) {
        if (this.messageList == null) {
            messageList = new ArrayList<>();
        }
        messageList.add(message);
    }
}
