/**
 * 
 */
package edu.tcu.mi.zk.model.KeyValue;

/**
 * @author Gaduo
 *
 */
public class KeyValue {
    private String key;
    private String value;
    
    
    /**
     * @param key
     * @param value
     */
    public KeyValue(String key, String value) {
        super();
        this.key = key;
        this.value = value;
    }
    public String getKey() {
        return this.key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getValue() {
        return this.value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    
    public String toString() {
        return "[" + this.key + "\t" + this.value +"]";
    }
    
    public boolean equals(KeyValue kv) {
        if(!kv.key.equals(key))
            return false;
        if(!kv.value.equals(value))
            return false;
        return true;
    }
    
}
