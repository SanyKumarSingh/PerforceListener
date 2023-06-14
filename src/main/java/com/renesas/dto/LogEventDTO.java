/**
 * 
 */
package com.renesas.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author a5143522
 *
 */
public class LogEventDTO implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1868647L;
	
	private Long eventId;
	private String eventType;
	private LocalDateTime timestamp;
	private Long durationMs;
	private String clientIpAddress;
	private String component;
	private String userId;
	private String userName;
	private String objectId;
	private String objectName;
	private Long statusCode;
	
	
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public Long getDurationMs() {
		return durationMs;
	}
	public void setDurationMs(Long durationMs) {
		this.durationMs = durationMs;
	}
	public String getClientIpAddress() {
		return clientIpAddress;
	}
	public void setClientIpAddress(String clientIpAddress) {
		this.clientIpAddress = clientIpAddress;
	}
	public String getComponent() {
		return component;
	}
	public void setComponent(String component) {
		this.component = component;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getObjectName() {
		return objectName;
	}
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	public Long getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Long statusCode) {
		this.statusCode = statusCode;
	}
	
}
