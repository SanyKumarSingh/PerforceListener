/**
 * 
 */
package com.renesas.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renesas.dto.LogEventDTO;
import com.renesas.exception.DataAccessException;
import com.renesas.exception.InternalServerException;
import com.renesas.exception.InvalidRequestException;
import com.renesas.service.LogEventService;

/**
 * @author a5143522
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class EventController {
	
	private static final Logger logger = LoggerFactory.getLogger(EventController.class);
	
	@Autowired
	private LogEventService logEventService;
	
	@GetMapping("/viewLogEvents")
	public List<LogEventDTO> getAllLogEvents() throws InternalServerException {
		logger.info("Received request to fetch all Events");
		return logEventService.getAllLogEvents();
	}
	
	
	@PostMapping("/addLogEvents")
	public List<LogEventDTO> addLogEvents(@RequestBody List<LogEventDTO> logEventDTOs) throws DataAccessException, InvalidRequestException {
		logger.info("Received request to add new Products");
		
		List<LogEventDTO> logEvents = null;
		if (logEventDTOs != null && !logEventDTOs.isEmpty()) {
			for(LogEventDTO logEventDTO : logEventDTOs) {
				if ((logEventDTO.getClientIpAddress() == null || logEventDTO.getEventType().isEmpty()) 
						|| logEventDTO.getUserName() == null) {
					logger.error("Missing mandatory field Client IP, Event Type, User Name.");
					throw new InvalidRequestException("Missing mandatory field Client IP, Event Type, User Name.");
				}
			}
			
			try {
				logEvents = logEventService.addLogEvents(logEventDTOs);
			} catch (DataAccessException exception) {
				logger.error("Log Event details could not be saved, there seems to be an issue with the Database", exception.getMessage());
				throw new DataAccessException("Log Event details could not be saved, there seems to be an issue with the Database");
			}
		} else {
			logger.error("Missing Log Event data.");
			throw new InvalidRequestException("Missing Log Event data.");
		}
		return logEvents;
	}

}
