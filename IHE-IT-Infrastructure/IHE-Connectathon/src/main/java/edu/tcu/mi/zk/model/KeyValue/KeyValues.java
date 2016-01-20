/**
 * 
 */
package edu.tcu.mi.zk.model.KeyValue;

import java.util.List;

/**
 * @author Gaduo
 *
 */
public interface KeyValues {
    public List<KeyValue> findAll();
    public KeyValue get(int i);
    public void add(KeyValue type);

}
