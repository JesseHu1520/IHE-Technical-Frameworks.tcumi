/**
 * 
 */
package edu.tcu.mi.zk.model;

import java.util.Iterator;
import java.util.List;

import org.apache.axiom.om.OMElement;
import org.apache.log4j.Logger;

import edu.tcu.mi.ihe.utility.AxiomUtil;
import edu.tcu.mi.zk.model.KeyValue.KeyValue;
import edu.tcu.mi.zk.view_model.xds_b.iti_18.StoredQuery;
import lombok.Getter;
import lombok.Setter;


/**
 * @author Gaduo
 */
public class QueryGenerator implements Cloneable {
	public static Logger logger = Logger.getLogger(QueryGenerator.class);
    @Getter @Setter
    private CompanyInfomation companyRegistry;
    @Getter @Setter
    private KeyValue queryType;
    @Getter @Setter
    private String returnType;
    @Getter @Setter
    private String queryUUID;
    @Getter @Setter
    private StoredQuery parameter;
    @Getter @Setter
    private AxiomUtil axiom;
    
    public QueryGenerator() {
    	axiom = AxiomUtil.getInstance();
    }
    public boolean build() {
//    	if(companyRegistry == null){
//        	logger.warn("companyRegistry is null");
//        	return false;
//    	}
//        this.query = axiom.createOMElement("QueryGenerator", null, null);
//        this.query.addChild(axiom.createOMElement("RegistryUrl", companyRegistry.getRegistryEndpoint()));
//        this.query.addChild(axiom.createOMElement("QueryUUID", this.getQueryType().getValue()));
//        this.query.addChild(axiom.createOMElement("ReturnType", this.getReturnType()));
////        query.setReturnType("LeafClass");
//        if(parameter == null){
//        	logger.warn("parameter is null");
//        	return false;
//        }
//        List<OMElement> list = parameter.getParameters();
//        if(list != null) {
//            Iterator<OMElement> iterator = list.iterator();
//            while(iterator.hasNext()) {
//                OMElement element = iterator.next();
//                this.query.addChild(element);
//            }
//        }else {
//        	logger.warn("list is null");
//            return false;
//        }
        return true;
    }

    public QueryGenerator clone() {
        QueryGenerator q = new QueryGenerator();
        q.queryType = this.queryType;
        q.companyRegistry = this.companyRegistry;
        q.queryUUID = this.queryUUID;
        return q;
    }
    
}
