/**
 * 
 */
package com.renesas.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author a5143522
 *
 */
@Entity
@Table(name = "log_events")
public class LogEvents implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 18654667L;
	
	@Id
	private String eventId;
	private String eventType;
	private Timestamp timestamp;
	private Long durationMs;
	private String clientIpAddress;
	private String component;
	private String userId;
	private String userName;
	private String objectId;
	private String objectName;
	private Long statusCode;
	private String ipvId;
	private String ipvName;
	
	@OneToOne(mappedBy="logEvents", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
	private LogErrors logErrors;
	
	@Column(name = "event_id", nullable = false)
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	
	@Column(name = "event_type", nullable = false)
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	@Column(name = "duration_ms", nullable = false)
	public Long getDurationMs() {
		return durationMs;
	}
	public void setDurationMs(Long durationMs) {
		this.durationMs = durationMs;
	}
	
	@Column(name = "client_ip_address", nullable = false)
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
	
	@Column(name = "user_id", nullable = false)
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Column(name = "user_name", nullable = false)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name = "object_id", nullable = false)
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	
	@Column(name = "object_name", nullable = false)
	public String getObjectName() {
		return objectName;
	}
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	
	@Column(name = "status_code", nullable = false)
	public Long getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Long statusCode) {
		this.statusCode = statusCode;
	}

	@Column(name = "ipv_id", nullable = false)
	public String getIpvId() {
		return ipvId;
	}
	public void setIpvId(String ipvId) {
		this.ipvId = ipvId;
	}
	
	@Column(name = "ipv_name", nullable = false)
	public String getIpvName() {
		return ipvName;
	}
	public void setIpvName(String ipvName) {
		this.ipvName = ipvName;
	}
	
	
	
}
