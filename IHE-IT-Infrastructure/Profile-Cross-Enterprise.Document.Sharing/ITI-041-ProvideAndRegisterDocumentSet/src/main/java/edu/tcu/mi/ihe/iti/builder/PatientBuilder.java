package edu.tcu.mi.ihe.iti.builder;

import org.apache.axiom.om.OMElement;

import edu.tcu.mi.ihe.iti.model.Patient;
import lombok.Setter;

public class PatientBuilder extends EntityBuilder {
	@Setter
	private Patient patient;
	
	@Override
	public OMElement getMessageFromXML() {
		OMElement slot = generateSlot("sourcePatientInfo", 
				new String[] {
					"PID-3|" + patient.getPatientId(), 
					"PID-5|" + patient.getPatientName(), 
					"PID-7|" + patient.getPatientBirthday(),
					"PID-8|" + patient.getPatientGender(),
					"PID-11|" + patient.getPatientAddress() ,
					"PID-13|" + patient.getPatientPhoneNumber() });
		return slot;
	}

	@Override
	protected boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getMessageFromHL7v2() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getId() {
		return patient.getId();
	}

}
