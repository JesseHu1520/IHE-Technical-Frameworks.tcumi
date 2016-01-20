/**
 * 
 */
package edu.tcu.mi.zk.model.code;

import java.util.LinkedList;
import java.util.List;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.tcu.mi.ihe.utility.xml.XMLPath;

/**
 * @author Gaduo
 */
public class CodesImpl implements Codes {
    private List<Code> list = new LinkedList<Code>();

    public CodesImpl(XMLPath codes, String type)  {
        String expression = "Codes/CodeType[@name='" + type + "']/Code";
        NodeList nodeList = codes.QueryNodeList(expression);
        if(nodeList == null)
            return ;
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            NamedNodeMap attr = node.getAttributes();
            String code = attr.getNamedItem("code").getNodeValue();
            String codingScheme = attr.getNamedItem("codingScheme").getNodeValue();
            String display = attr.getNamedItem("display").getNodeValue();
            list.add(new Code(code, codingScheme, display));
        }

    }

    public List<Code> findAll() {
        return list;
    }

    public Code get(int i) {
        return list.get(i);
    }

}
