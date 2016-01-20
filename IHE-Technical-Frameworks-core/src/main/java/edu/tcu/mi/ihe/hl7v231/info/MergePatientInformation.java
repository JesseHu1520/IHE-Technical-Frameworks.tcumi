package edu.tcu.mi.ihe.hl7v231.info;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v231.segment.MRG;
import lombok.Getter;
import lombok.Setter;


public class MergePatientInformation {
	@Getter @Setter
	private String mrg01;
	@Getter @Setter
	private String mrg02;
	@Getter @Setter
	private String mrg03;
	@Getter @Setter
	private String mrg04;
	@Getter @Setter
	private String mrg05;
	@Getter @Setter
	private String mrg06;
	@Getter @Setter
	private String mrg07;
	
	private MRG mrg;
	
	public void setMrg(MRG mrg) {
		this.mrg = mrg;
	}

	public MRG getMrg(){
		try {
			mrg.getMrg1_PriorPatientIdentifierList(0).parse(mrg01);
			mrg.getMrg7_PriorPatientName(0).parse(mrg07);
		} catch (HL7Exception e) {
			e.printStackTrace();
		}
		return mrg;
	}

}
