package ibf.ssf.day15.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import ibf.ssf.day15.utils.Util;

// Please ensure that redis is running before running application

@Configuration
public class RedisConfig {
    
    // Inject application properties into configuration
    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private Integer redisPort;

    @Value("${spring.data.redis.username}")
    private String redisUser;

    @Value("${spring.data.redis.password}")
    private String redisPassword;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {

        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();
        // Configure host name and port
        redisConfig.setHostName(redisHost);
        redisConfig.setPort(redisPort);

        // Configure username and password
        if (!"NOT_SET".equals(redisUser.trim())) {
            redisConfig.setUsername(redisUser);
            redisConfig.setPassword(redisPassword);
        }

        // Create client config object and set connection
        JedisClientConfiguration jedisClient = JedisClientConfiguration.builder().build();
        JedisConnectionFactory jedisConnFac = new JedisConnectionFactory(redisConfig, jedisClient);
        jedisConnFac.afterPropertiesSet();
        return jedisConnFac;
    }
    
    // Create a template for the data structures that you want to use 
    // You can determine the data type that is stored in template with <String, Object>
    // Give the bean or function a name - good to have constants under utils (minimise area for change)
    @Bean(Util.REDIS_ONE)
    public RedisTemplate<String, String> redisTemplate() {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new StringRedisSerializer());

        return template;
    }

    @Bean(Util.REDIS_TWO)
    public RedisTemplate<String, Object> redisObjectTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());

        return template;
    }
}
