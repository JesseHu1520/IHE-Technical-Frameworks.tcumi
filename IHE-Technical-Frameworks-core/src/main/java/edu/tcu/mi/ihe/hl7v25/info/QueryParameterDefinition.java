package edu.tcu.mi.ihe.hl7v25.info;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v25.segments.QPD;

public class QueryParameterDefinition {
	private String qpd01 = "";
	private String qpd02 = "";
	private String qpd03 = "";
	private String qpd04 = "";
	private String qpd05 = "";
	private String qpd06 = "";
	private String qpd07 = "";
	private String qpd08 = "";
	private QPD qpd;
	
	
	
	/**
	 * @param qpd01 the qpd01 to set
	 */
	public void setQpd01(String qpd01) {
		this.qpd01 = qpd01;
	}

	/**
	 * @param qpd02 the qpd02 to set
	 */
	public void setQpd02(String qpd02) {
		this.qpd02 = qpd02;
	}

	/**
	 * @param qpd03 the qpd03 to set
	 */
	public void setQpd03(String qpd03) {
		this.qpd03 = qpd03;
	}

	/**
	 * @param qpd04 the qpd04 to set
	 */
	public void setQpd04(String qpd04) {
		this.qpd04 = qpd04;
	}

	/**
	 * @param qpd05 the qpd05 to set
	 */
	public void setQpd05(String qpd05) {
		this.qpd05 = qpd05;
	}

	/**
	 * @param qpd06 the qpd06 to set
	 */
	public void setQpd06(String qpd06) {
		this.qpd06 = qpd06;
	}

	/**
	 * @param qpd07 the qpd07 to set
	 */
	public void setQpd07(String qpd07) {
		this.qpd07 = qpd07;
	}

	/**
	 * @param qpd08 the qpd08 to set
	 */
	public void setQpd08(String qpd08) {
		this.qpd08 = qpd08;
	}

	public void setQpd(QPD qpd){
		this.qpd = qpd;
	}
	
	public QPD getQpd(){
		try{
//			qpd.getQpd1_MessageQueryName().getCe1_Identifier().setValue(qpd01);
//			qpd.getQpd2_QueryTag().setValue(qpd02);
			qpd.getQpd3_UserParametersInsuccessivefields().parse(qpd03);
			qpd.getQpd4_UserParametersInsuccessivefields().parse(qpd04);
			qpd.getQpd8_UserParametersInsuccessivefields().parse(qpd08);
		} catch(HL7Exception e){
			e.printStackTrace();
		}
		return qpd;
	}
	
}
