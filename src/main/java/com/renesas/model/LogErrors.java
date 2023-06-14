/**
 * 
 */
package com.renesas.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author a5143522
 *
 */
@Entity
@Table(name = "log_errors")
public class LogErrors implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 18114667L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String message;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "event_id", nullable = false)
	private LogEvents logEvents;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LogEvents getLogEvents() {
		return logEvents;
	}

	public void setLogEvents(LogEvents logEvents) {
		this.logEvents = logEvents;
	}
	
}
