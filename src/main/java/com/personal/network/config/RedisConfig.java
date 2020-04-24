package com.personal.network.config;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.util.Assert;



import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author 607890509
 *
 */
@Configuration

//@PropertySource("file:${APP_PROPERTIES}")
public class RedisConfig {
	
	
	@Autowired
	private Environment env;
	
	@Value("#{${Nodes}}")
	private String[] Nodes;
	
	public RedisConfig()
	{
	    
	}
	@Bean
	public JedisCluster getRedisCluster(){
		
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("127.0.0.1",6379));
		/*for(String ipPort:Nodes)
		{
			String[] ipPortPair  = ipPort.split(":");
			if(!(ipPortPair [0].isEmpty()) && !(ipPortPair [1].isEmpty()))
			{
				nodes.add(new HostAndPort(ipPortPair [0].trim(),Integer.valueOf(ipPortPair [1].trim())));
				
			}
			else
			{
				System.out.println("Host Name/Port for the Cluster was found Null");
				
			}
		}*/
		
		Jedis jedis = new Jedis(new HostAndPort("127.0.0.1",6379));
		jedis.sadd("remove", "please-remove");
		JedisPoolConfig config = new JedisPoolConfig();
		return new JedisCluster(nodes, config);
		
		
	    
	}
	
	

}
