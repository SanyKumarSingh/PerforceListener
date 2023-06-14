/**
 * 
 */
package com.renesas.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renesas.dto.LogEventDTO;
import com.renesas.exception.DataAccessException;
import com.renesas.exception.InternalServerException;
import com.renesas.model.LogEvents;
import com.renesas.repository.LogEventRepository;
import com.renesas.service.LogEventService;

/**
 * @author a5143522
 *
 */
@Service
public class LogEventServiceImpl implements LogEventService {
	
	private static final Logger logger = LoggerFactory.getLogger(LogEventServiceImpl.class);

	@Autowired
	private LogEventRepository logEventRepository;
	
	@Override
	public List<LogEventDTO> getAllLogEvents() throws InternalServerException {
		logger.info("Processing request to fetch all Log Events");
		List<LogEvents> logEvents = null;
		try {
			// Fetch all the enlisted Log Events
			logEvents = logEventRepository.findAll();
		} catch (Exception exception) {
			logger.error("Exception while fetching All Log Events details.", exception.getMessage());
			throw new InternalServerException("Log Events details could not be fetched.");
		}
		List<LogEventDTO> logEventDTOs = new ArrayList<LogEventDTO>();
		// Iterating the Log Events fetched from DB
		logEvents.forEach((logEvent) -> {
			LogEventDTO logEventDTO = new LogEventDTO();
			logEventDTO.setEventId(logEvent.getEventId());
			logEventDTO.setEventType(logEvent.getEventType());
			logEventDTO.setTimestamp(logEvent.getTimestamp().toLocalDateTime());
			logEventDTO.setDurationMs(logEvent.getDurationMs());
			logEventDTO.setClientIpAddress(logEvent.getClientIpAddress());
			logEventDTO.setComponent(logEvent.getComponent());
			logEventDTO.setUserId(logEvent.getUserId());
			logEventDTO.setUserName(logEvent.getUserName());
			logEventDTO.setObjectId(logEvent.getObjectId());
			logEventDTO.setObjectName(logEvent.getObjectName());
			logEventDTO.setStatusCode(logEvent.getStatusCode());
			logEventDTOs.add(logEventDTO);
		});
		return logEventDTOs;
	}

	/*
	 * Used @Transactional on this method to enable container managed transaction provided by Spring Boot.
	 * 
	 * Spring Boot takes care of all the boilerplate code and integrates Hibernate’s and JPA’s transaction handling. 
	 * To activate transaction for SpringMVC, @EnableTransactionManagement is also needed but not for SpringBoot.
	 * 
	 * Default Propagation is REQUIRED :  join an active transaction or to start a new one.
	 * SUPPORTS : join an activate transaction if exists, else continue w/o transaction.
	 * MANDATORY: join an activate transaction if exists, else throw an Exception.
	 * REQUIRES_NEW : suspend an activate transaction if exists, start a new transaction.
	 * NEVER, NOT_SUPPORTED : execute w/o transaction
	 */
	@Override
	@Transactional
	public List<LogEventDTO> addLogEvents(List<LogEventDTO> logEventDTOs) throws DataAccessException {
		
		List<LogEvents> logEvents = new ArrayList<LogEvents>();
		logEventDTOs.forEach((logEventDTO) -> {
			LogEvents logEvent = new LogEvents();
			logEvent.setEventId(logEventDTO.getEventId());
			logEvent.setEventType(logEventDTO.getEventType());
			logEvent.setTimestamp(Timestamp.valueOf(logEventDTO.getTimestamp()));
			logEvent.setDurationMs(logEventDTO.getDurationMs());
			logEvent.setClientIpAddress(logEventDTO.getClientIpAddress());
			logEvent.setComponent(logEventDTO.getComponent());
			logEvent.setUserId(logEventDTO.getUserId());
			logEvent.setUserName(logEventDTO.getUserName());
			logEvent.setObjectId(logEventDTO.getObjectId());
			logEvent.setObjectName(logEventDTO.getObjectName());
			logEvent.setStatusCode(logEventDTO.getStatusCode());
			logEvents.add(logEvent);
		});
		
		// Save Log Event details and store the managed entity to fetch the eventId 
		List<LogEvents> addedLogEvents = null;
		try {
			addedLogEvents = logEventRepository.saveAll(logEvents);
		} catch (Exception exception) {
			logger.error("Exception while saving new Log Event details.", exception.getMessage());
			throw new DataAccessException("Log Event details could not be saved.");
		}

		
		logEventDTOs.clear();
		addedLogEvents.forEach((logEvent) -> {
			LogEventDTO logEventDTO = new LogEventDTO();
			logEventDTO.setEventId(logEvent.getEventId());
			logEventDTO.setEventType(logEvent.getEventType());
			logEventDTO.setTimestamp(logEvent.getTimestamp().toLocalDateTime());
			logEventDTO.setDurationMs(logEvent.getDurationMs());
			logEventDTO.setClientIpAddress(logEvent.getClientIpAddress());
			logEventDTO.setComponent(logEvent.getComponent());
			logEventDTO.setUserId(logEvent.getUserId());
			logEventDTO.setUserName(logEvent.getUserName());
			logEventDTO.setObjectId(logEvent.getObjectId());
			logEventDTO.setObjectName(logEvent.getObjectName());
			logEventDTO.setStatusCode(logEvent.getStatusCode());
			logEventDTOs.add(logEventDTO);
		});
		return logEventDTOs;
	}

}
