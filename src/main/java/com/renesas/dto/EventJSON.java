/**
 * 
 */
package com.renesas.dto;

import java.io.Serializable;

/**
 * @author a5143522
 *
 */
public class EventJSON implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 18685346647L;
	
	private String event_id;
    private Long timestamp;
    private Long duration_ms;
    private String client_ip_address;
    private String component;
    private String user_id;
    private String user_name;
    private Long status_code;
    private String event_type;
    private String object_id;
	private String object_name;
	private String ipv_id;
	private String ipv_name;
    
	public String getEvent_id() {
		return event_id;
	}
	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	public Long getDuration_ms() {
		return duration_ms;
	}
	public void setDuration_ms(Long duration_ms) {
		this.duration_ms = duration_ms;
	}
	public String getClient_ip_address() {
		return client_ip_address;
	}
	public void setClient_ip_address(String client_ip_address) {
		this.client_ip_address = client_ip_address;
	}
	public String getComponent() {
		return component;
	}
	public void setComponent(String component) {
		this.component = component;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public Long getStatus_code() {
		return status_code;
	}
	public void setStatus_code(Long status_code) {
		this.status_code = status_code;
	}
	public String getEvent_type() {
		return event_type;
	}
	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}
	public String getObject_id() {
		return object_id;
	}
	public void setObject_id(String object_id) {
		this.object_id = object_id;
	}
	public String getObject_name() {
		return object_name;
	}
	public void setObject_name(String object_name) {
		this.object_name = object_name;
	}
	public String getIpv_id() {
		return ipv_id;
	}
	public void setIpv_id(String ipv_id) {
		this.ipv_id = ipv_id;
	}
	public String getIpv_name() {
		return ipv_name;
	}
	public void setIpv_name(String ipv_name) {
		this.ipv_name = ipv_name;
	}
    
}
