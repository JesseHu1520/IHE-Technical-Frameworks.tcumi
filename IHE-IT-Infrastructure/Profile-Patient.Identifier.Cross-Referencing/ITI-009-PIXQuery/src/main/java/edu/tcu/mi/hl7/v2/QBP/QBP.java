package edu.tcu.mi.hl7.v2.QBP;

import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.v25.segment.MSH;
import ca.uhn.hl7v2.model.v25.segment.RCP;
import ca.uhn.hl7v2.model.v25.segments.QPD;
import edu.tcu.mi.ihe.hl7.HL7v2Message;

public abstract class QBP extends HL7v2Message {

    protected MSH msh;
    protected QPD qpd;
    protected RCP rcp;

    /**
	 * @return the rcp
	 */
	public RCP getRcp(RCP rcp) {
		this.rcp = rcp;
 		try {
			rcp.getRcp1_QueryPriority().setValue("I");
		} catch (DataTypeException e) {
			e.printStackTrace();
		}
		return rcp;
	}
    
}
