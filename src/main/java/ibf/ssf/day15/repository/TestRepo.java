package ibf.ssf.day15.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import ibf.ssf.day15.utils.Util;

@Repository
public class TestRepo {
    
    @Autowired // Instantiate RedisTemplate object from configuration
    @Qualifier(Util.REDIS_ONE) // Tells SB which bean to use based on its name
    RedisTemplate<String, String> redisTemplate;

    // Concept: Change retrieving, removing, inserting methods into functions for manipulation

    // Store value
    public void storeValueData(String key, String value) {
        ValueOperations<String, String> valueOps = redisTemplate.opsForValue(); // access value operations
        valueOps.set(key, value);
    }

    // Retrieve value
    public String retrieveValueData(String key) {
        ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
        return valueOps.get(key);
    }

    public void addToList(String key, String value) {
        ListOperations<String, String> listOps = redisTemplate.opsForList();
        listOps.rightPush(key, value);
    }
    

    public List<String> getList(String key) {
        ListOperations<String, String> listOps = redisTemplate.opsForList();
        // Retireve list from index 0 to end of list
        return listOps.range(key, 0, -1); 
    }

    public Long getListSize(String key) {
        return redisTemplate.opsForList().size(key);
    }

    public Long getIndex(String key, String value) {
        ListOperations<String, String> listOps = redisTemplate.opsForList();
        return listOps.indexOf(key, value);
    }

    public void incrementValueDate(String key, Long valueToIncrease){
        ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
        valueOps.increment(key, valueToIncrease);
    }

    public Boolean keyPresent(String key) {
        Boolean isDeleted = false;
        if (redisTemplate.hasKey(key)) {
            redisTemplate.delete(key);
            isDeleted = true;
        }
        return isDeleted;
    }

    public void addToHash(String key, String hashkey, String value) {
        redisTemplate.opsForHash().put(key, hashkey, value);
    }

}
