package edu.tcu.mi.zk.model.code;

import java.util.List;



/**
 * @author Gaduo
 *
 */
public interface Codes {
    public List<Code> findAll();
    public Code get(int i);
}
