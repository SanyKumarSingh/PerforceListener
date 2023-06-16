/**
 * 
 */
package com.renesas.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.renesas.dto.EventJSON;

/**
 * @author a5143522
 *
 */
@Component
public class JsonUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);
	
	private ObjectMapper objectMapper;
	
	@Autowired
	public JsonUtils(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}
	
    public boolean isValidJson(String jsonString) {
        try {
            objectMapper.readTree(jsonString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public EventJSON convertStringToJSON(String messageBody) {
		EventJSON eventDTO = null;
		 try {
	            eventDTO = objectMapper.readValue(messageBody, EventJSON.class);

	            // Access the JSON to read the fields of the Java object
	            System.out.println("eventId: " + eventDTO.getEvent_id());
	            System.out.println("eventType: " + eventDTO.getEvent_type());
	            System.out.println("timestamp################################# "+ eventDTO.getTimestamp());
	        } catch (Exception exception) {
	        	logger.error("The string does not contain valid JSON data. Exception while converting String To JSON.", exception.getMessage());
	        }
		 return eventDTO;
	}
	
}
