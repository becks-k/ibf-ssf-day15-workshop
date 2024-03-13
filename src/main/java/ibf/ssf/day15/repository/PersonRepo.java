package ibf.ssf.day15.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import ibf.ssf.day15.utils.Util;

@Repository
public class PersonRepo {
    
    @Autowired
    @Qualifier(Util.REDIS_ONE)
    RedisTemplate<String, String> template;

    // Create a list
    public void addToList(String key, String value) {
        template.opsForList().rightPush(key, value);
    }

    // Get list
    public List<String> getList(String key) {
        return template.opsForList().range(key, 0, -1);
    }

    // Update list
    

    // Get index
    public Long getIndex(String key, String value) {
        Long index = template.opsForList().indexOf(key, value);
        if (index == null) {
            index = -1L;
            return index;
        }
        return index;
    }

    // Delete
    public Boolean removeFromList(String key, String value) {
        Boolean isDeleted = false;
        if (getIndex(key, value) != -1) {
            template.opsForList().remove(key, 0, value); // Removes first 1 element from start of list
            isDeleted = true;
        }

        return isDeleted;
    }
}
