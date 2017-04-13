package ru.yalymar.nonblock.nonblockcache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.log4j.Logger;

/**
 * @author slavalymar
 * @since 14.04.2017
 * @version 1
 */
public class NonBlockCache {

    /**
     * map to contain task
     */
    private ConcurrentHashMap<Integer, Task> map;

    /**
     * logger
     */
    private static final Logger log = Logger.getLogger(NonBlockCache.class);

    public NonBlockCache() {
        this.map = new ConcurrentHashMap<>();
    }

    public Map<Integer, Task> getMap() {
        return this.map;
    }

    /** add task to map
     * @param key
     * @param value
     */
    public void add(Integer key, Task value){
        this.map.put(key, value);
    }

    /** update map
     * @param key
     * @param newValue
     * @return
     */
    public Task update(Integer key, int newValue){
        int version = this.map.get(key).getVersion();

        // here is atomic block
        Task task = this.map.computeIfPresent(key, (k,v) ->
                this.atomicUpdate(key, version, newValue, v));

        return task;
    }

    /** if tasks` versions are equals return old Task
     * else throws exception
     * @param key
     * @param version
     * @param newValue
     * @param oldValue
     * @return Task
     */
    private Task atomicUpdate(Integer key, int version, int newValue, Task oldValue) {
        if (version == this.map.get(key).getVersion()) {
            final Task result = oldValue;
            oldValue.setValue(newValue);
            oldValue.setVersion(oldValue.getVersion() + 1);
            return result;
        }
        else {
            try {
                throw new OplimisticException("Throws exception");
            } catch (OplimisticException e) {
                System.out.println(String.format("Version was changed!"));
                log.error("Version was changed!");
            }
            return this.atomicUpdate(key, this.map.get(key).getVersion(), newValue, this.map.get(0));
        }
    }

    /** delete task from map
     * @param key
     * @return delete
     */
    public Task delete(Integer key){
        log.info(String.format("%s is executing!", Thread.currentThread().getName()));
        return this.map.remove(key);
    }
}
