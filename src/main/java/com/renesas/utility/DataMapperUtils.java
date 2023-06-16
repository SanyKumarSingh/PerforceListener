/**
 * 
 */
package com.renesas.utility;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.renesas.dto.EventJSON;
import com.renesas.dto.LogEventDTO;
import com.renesas.model.LogEvents;

/**
 * @author a5143522
 *
 */
@Component
public class DataMapperUtils {

	private static final Logger logger = LoggerFactory.getLogger(DataMapperUtils.class);

	public LogEventDTO mapEventJsonToDto(EventJSON eventJSON) {
		logger.info("Map the data from EventJSON To LogEventDTO.");
		
		LogEventDTO logEventDTO = new LogEventDTO();
		logEventDTO.setEventId(eventJSON.getEvent_id());
		logEventDTO.setEventType(eventJSON.getEvent_type());
		logEventDTO.setTimestamp(LocalDateTime.ofInstant(Instant.ofEpochMilli(eventJSON.getTimestamp()), ZoneOffset.UTC));
		logEventDTO.setDurationMs(eventJSON.getDuration_ms());
		logEventDTO.setClientIpAddress(eventJSON.getClient_ip_address());
		logEventDTO.setComponent(eventJSON.getComponent());
		logEventDTO.setUserId(eventJSON.getUser_id());
		logEventDTO.setUserName(eventJSON.getUser_name());
		logEventDTO.setObjectId(eventJSON.getObject_id());
		logEventDTO.setObjectName(eventJSON.getObject_name());
		logEventDTO.setStatusCode(eventJSON.getStatus_code());
		logEventDTO.setIpvId(eventJSON.getIpv_id());
		logEventDTO.setIpvName(eventJSON.getIpv_name());
		System.out.println("Event Id###&&&&&&&&&&#####&#&#&#&#&#&&#& " + logEventDTO.getEventId());
		
		return logEventDTO;
	}
	
	public LogEventDTO mapEntityToDto(LogEvents logEvent) {
		logger.info("Map the data from LogEvents To LogEventDTO.");
		
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
		return logEventDTO;
	}
	
	public LogEvents mapDtoToEntity(LogEventDTO logEventDTO) {
		logger.info("Map the data from LogEventDTO To LogEvents.");
		
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
		return logEvent;
	}

}
