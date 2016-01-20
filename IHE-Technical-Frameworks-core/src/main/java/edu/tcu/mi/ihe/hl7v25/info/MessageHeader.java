package edu.tcu.mi.ihe.hl7v25.info;

import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.v25.segment.MSH;
import lombok.Getter;
import lombok.Setter;

public class MessageHeader {
	@Getter @Setter
	private String SendingApplication;
	@Getter @Setter
	private String SendingFacility;
	@Getter @Setter
	private String ReceivingApplication;
	@Getter @Setter
	private String ReceivingFacility;
	@Getter @Setter
	private String MessageControlID;
	
	private MSH msh;
	

	public MSH getMsh() throws DataTypeException {
		msh.getSendingApplication().getNamespaceID().setValue(SendingApplication);
		msh.getSendingFacility().getNamespaceID().setValue(SendingFacility);
		msh.getMsh5_ReceivingApplication().getHd1_NamespaceID().setValue(ReceivingApplication);
		msh.getMsh6_ReceivingFacility().getHd1_NamespaceID().setValue(ReceivingFacility);
		msh.getMsh10_MessageControlID().setValue(MessageControlID);
		
		return msh;
	}

	public void setMsh(MSH msh) {
		this.msh = msh;
	}	
}
