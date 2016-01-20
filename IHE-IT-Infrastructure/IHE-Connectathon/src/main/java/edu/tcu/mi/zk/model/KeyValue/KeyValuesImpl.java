/**
 * 
 */
package edu.tcu.mi.zk.model.KeyValue;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gaduo
 *
 */
public class KeyValuesImpl implements KeyValues {

    List<KeyValue> list = new ArrayList<KeyValue>();

    public List<KeyValue> findAll() {
        return list;
    }

    public KeyValue get(int i) {
        return list.get(i);
    }

    public void add(KeyValue type) {
        list.add(type);
    }

}
