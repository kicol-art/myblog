package com.nm.blog.pojo;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1434529244041856748L;

	private Integer messageId;

    private String messageContent;

    private Date messageTime;

    private String messageName;

    private String messageMark;

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent == null ? null : messageContent.trim();
    }

    public Date getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(Date messageTime) {
        this.messageTime = messageTime;
    }

    public String getMessageName() {
        return messageName;
    }

    public void setMessageName(String messageName) {
        this.messageName = messageName == null ? null : messageName.trim();
    }

    public String getMessageMark() {
        return messageMark;
    }

    public void setMessageMark(String messageMark) {
        this.messageMark = messageMark == null ? null : messageMark.trim();
    }
}