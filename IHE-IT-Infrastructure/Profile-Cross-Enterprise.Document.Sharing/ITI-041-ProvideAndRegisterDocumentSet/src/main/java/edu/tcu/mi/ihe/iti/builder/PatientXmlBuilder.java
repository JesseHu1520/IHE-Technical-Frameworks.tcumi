package edu.tcu.mi.ihe.iti.builder;

import org.apache.axiom.om.OMElement;

import edu.tcu.mi.ihe.iti.model.Patient;
import lombok.Setter;

public class PatientXmlBuilder extends EntityXmlBuilder {

	@Setter
	private Patient patient;
	
	public PatientXmlBuilder() {
		super(null, null);
	}
	
	@Override
	public OMElement getMessageFromXML() {
		OMElement slot = generateSlot("sourcePatientInfo", 
				new String[] {
					"PID-3|" + patient.getId(), 
					"PID-5|" + patient.getName(), 
					"PID-7|" + patient.getBirthday(),
					"PID-8|" + patient.getGender(),
					"PID-11|" + patient.getAddress() ,
					"PID-13|" + patient.getPhoneNumber() });
		return slot;
	}

	@Override
	protected boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected String getId() {
		return patient.getId();
	}

}
