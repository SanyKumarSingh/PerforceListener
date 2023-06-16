package com.renesas.PerforceListener;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import com.renesas.dto.EventJSON;
import com.renesas.dto.LogEventDTO;
import com.renesas.exception.DataAccessException;
import com.renesas.service.LogEventService;
import com.renesas.utility.DataMapperUtils;
import com.renesas.utility.JsonUtils;

/**
 * 
 * This Class implements message listener container and the message
 * listener adapter.
 * 
 */
@Component
public class PerforceListenerApplication implements MessageListener {
	
	private static final Logger logger = LoggerFactory.getLogger(PerforceListenerApplication.class);
	
	private JsonUtils jsonUtils;
	  
	private DataMapperUtils dataMapperUtils;
	
	private LogEventService logEventService;
	
	public PerforceListenerApplication(JsonUtils jsonUtils, DataMapperUtils dataMapperUtils, LogEventService logEventService) {
		this.jsonUtils = jsonUtils;
		this.dataMapperUtils =  dataMapperUtils;
		this.logEventService = logEventService;
	}
	 

	@Override
	public void onMessage(Message message, byte[] pattern) {
		String channel = new String(message.getChannel());
		String messageBody = new String(message.getBody());
		System.out.println("Received message: " + messageBody + " from channel: " + channel);
		
		if(pattern != null) {
			System.out.println("*********Pattern Message******************");
			if(jsonUtils.isValidJson(messageBody)) {
				List<LogEventDTO> logEventDTOs = new ArrayList<LogEventDTO>();
				
				logger.info("Converting String To JSON.");
				EventJSON eventJSON = jsonUtils.convertStringToJSON(messageBody);
				
				logger.info("Map the data from EventJSON To LogEventDTO.");
				logEventDTOs.add(dataMapperUtils.mapEventJsonToDto(eventJSON));
				
				logger.info("Insert Record into Database");
				try {
					logEventService.addLogEvents(logEventDTOs);
				} catch (DataAccessException exception) {
					logger.error("Log Event details could not be saved, there seems to be an issue with the Database", exception.getMessage());
				}
			}
		} else {
			System.out.println("$$$$$$$$$$$$$$$$$$$$ Regular Message $$$$$$$$$$$$$$$$$$");
		}
		
	}
	

}
