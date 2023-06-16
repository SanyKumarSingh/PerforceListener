/**
 * 
 */
package com.renesas.PerforceListener;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import com.renesas.service.LogEventService;
import com.renesas.utility.DataMapperUtils;
import com.renesas.utility.JsonUtils;

/**
 * @author a5143522
 * 
 * This Class sets up the  message listener container and the message
 * listener adapter.
 */
@Configuration
public class RedisConfig {
	
	@Autowired
	private JsonUtils jsonUtils;
	
	@Autowired 
	private DataMapperUtils dataMapperUtils;
	
	@Autowired
	private LogEventService logEventService;
	
	 /**
	 * Redis message listener container 
	 * 
	 * This method creates a RedisMessageListenerContainer bean and configures it to use the Redis connection factory obtained from the RedisTemplate. 
	 * It adds the messageListenerAdapter as the message listener for a specific channel.
	 * 
	 * To listen to Single or List of channel using Spring Boot and Spring Data Redis use ChannelTopic class, 
	 * and to listen to multiple Redis channels use the PatternTopic class, using a pattern example "*".
	 */
	@Bean
	public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory redisConnectionFactory,
			MessageListenerAdapter messageListenerAdapter) {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(redisConnectionFactory);
		// Single or List of Channel Name to subscribe the message
		// container.addMessageListener(messageListenerAdapter, new ChannelTopic("redis")); 
		
		// Pattern to subscribe to multiple channels or use "*" to subscribe to all the channels.
		container.addMessageListener(messageListenerAdapter, new PatternTopic("*")); 
		return container;
	}

	 /**
     * Redis message listener adapter.
     * 
     * This method creates a MessageListenerAdapter bean that implements the MessageListener interface. 
     * It defines the logic to process the received Redis messages.
     */
	@Bean
	public MessageListenerAdapter messageListenerAdapter() {
		return new MessageListenerAdapter(new PerforceListenerApplication(jsonUtils, dataMapperUtils, logEventService));
	}
	
	/**
     * This method configure the host and port details of the Single Instance Redis Configuration.
     * If the Redis Server runs locally then this method is not required.
     * 
     * The RedisConnectionFactory bean is created using the LettuceConnectionFactory class, 
     * which is one of the Redis connection factory implementations provided by Spring Data Redis.
     * 
     * The RedisStandaloneConfiguration provided by Spring Data Redis is used to configure the Redis host and port details.
     */
	@Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisConfiguration = new RedisStandaloneConfiguration();
        // Local Machine Redis server hostname or IP address and port to connect
        //redisConfiguration.setHostName("DEU-5CG2232PP1.adwin.renesas.com");
        //redisConfiguration.setPort(6379);
        
        // Perforce Redis server hostname or IP address and port to connect
        redisConfiguration.setHostName("10.24.29.155");
        redisConfiguration.setPort(6379);
        
        // Authentication to connect to the Redis Server. Default value is admin:admin.
        //redisConfiguration.setUsername("admin");
        //redisConfiguration.setPassword("admin");
        return new LettuceConnectionFactory(redisConfiguration);
    }
	
	/**
     * This method configure the host and port details of the High Availability (HA) Redis Configuration.
     * Redis Sentinel is a built-in solution for high availability in Redis. 
     * It involves deploying multiple Redis instances, where one acts as a master and the others as slaves. 
     * Sentinel nodes monitor the Redis instances and handle automatic failover if the master fails. 
     * The Sentinel nodes elect a new master and redirect clients to the new master.
     * 
     * The RedisConnectionFactory bean is created using the LettuceConnectionFactory class, 
     * which is one of the Redis connection factory implementations provided by Spring Data Redis.
     * 
     * The RedisSentinelConfiguration provided by Spring Data Redis is used to configure configure the HA Redis setup.
     * 
     * The .sentinel() method is used to add multiple Redis sentinel nodes to the configuration.
     */
	/*
	 * @Bean public RedisConnectionFactory redisConnectionFactory() {
	 * RedisSentinelConfiguration redisConfiguration = new
	 * RedisSentinelConfiguration() .master("mymaster") // Redis master name
	 * .sentinel("DEU-5CG2232PP1.adwin.renesas.com", 26379) // Replace with the
	 * hostname/IP and port of first sentinel
	 * .sentinel("DEU-5CG2232PP2.adwin.renesas.com", 26379); // Add more sentinels
	 * as needed return new LettuceConnectionFactory(redisConfiguration); }
	 */

	/**
     * RedisTemplate bean is defined to establish a Redis connection.
     */
	@Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }
	
}
