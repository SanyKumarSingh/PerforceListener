package com.renesas.PerforceListener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

/**
 * 
 * This Class implements message listener container and the message
 * listener adapter.
 * 
 */
public class PerforceListenerApplication implements MessageListener {

	@Override
	public void onMessage(Message message, byte[] pattern) {
		String channel = new String(message.getChannel());
		String messageBody = new String(message.getBody());
		System.out.println("Received message: " + messageBody + " from channel: " + channel);
	}

}
