/**
 * 
 */
package com.renesas.service;

import java.util.List;

import com.renesas.dto.LogEventDTO;
import com.renesas.exception.DataAccessException;
import com.renesas.exception.InternalServerException;

/**
 * @author a5143522
 *
 */
public interface LogEventService {
	
	public List<LogEventDTO> getAllLogEvents() throws InternalServerException;
	
	public List<LogEventDTO> addLogEvents(List<LogEventDTO> logEventDTOs) throws DataAccessException;

}
