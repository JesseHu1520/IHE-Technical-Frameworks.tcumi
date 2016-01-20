/**
 * 
 */
package edu.tcu.mi.zk.view_model.xds_b.iti_18;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.axiom.om.OMElement;
import org.apache.log4j.Logger;

import edu.tcu.mi.ihe.utility.AxiomUtil;
import edu.tcu.mi.zk.view_model.ViewModel;


/**
 * @author Gaduo
 *
 */
public abstract class StoredQuery extends ViewModel {
	AxiomUtil axiom;
    List<OMElement> list;
    public StoredQuery() {
        axiom = AxiomUtil.getInstance();
        list = new ArrayList<OMElement>();
    }
    
    public OMElement addParameter(String name, String value) {
        OMElement parameter = axiom.createOMElement("Parameter", null, null);
        parameter.addAttribute("name", name, null);
        if(isNumeric_Pattern(value)) {
            
        }else if(hasQuotes(value)){
            value = value.replaceAll("\'", "\'\'");
        }else {
            value = "'" + value + "'";
        }
        parameter.addChild(axiom.createOMElement("Value", value));
        return parameter;
    }     
    
    public boolean isNumeric_Pattern(String str) {
        Pattern pattern = Pattern.compile("[0-9]+");
        return pattern.matcher(str).matches();
    }
    
    public boolean hasQuotes(String str) {
        return str.contains("\'");
    }

    public abstract List<OMElement> getParameters();
}
