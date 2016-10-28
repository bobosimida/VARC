package com.varc.domain;

import java.util.Date;

public class Email {
    private Integer id;

    private Date sendTime;

    private String isread;

    private byte[] content;

    private Integer sendId;

    private Integer getId;
    
    private String title;
    
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getIsread() {
        return isread;
    }

    public void setIsread(String isread) {
        this.isread = isread == null ? null : isread.trim();
    }


	/**
	 * @return the content
	 */
	public byte[] getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(byte[] content) {
		this.content = content;
	}

	public Integer getSendId() {
        return sendId;
    }

    public void setSendId(Integer sendId) {
        this.sendId = sendId;
    }

    public Integer getGetId() {
        return getId;
    }

    public void setGetId(Integer getId) {
        this.getId = getId;
    }

	public String toString() {
		return "Email [id=" + id + ", sendTime=" + sendTime + ", isread="
				+ isread + ", content=" + content
				+ ", sendId=" + sendId + ", getId=" + getId + "]";
	}
    
}