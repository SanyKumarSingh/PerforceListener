/**
 * 
 */
package com.renesas.dto;

import java.io.Serializable;

/**
 * @author a5143522
 *
 */
public class LogErrorDTO implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 186568647L;

	private Long id;
	private String message;
	
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
	
}
