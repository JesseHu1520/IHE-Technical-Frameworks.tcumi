package edu.tcu.mi.ihe.hl7v231.info;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v231.segment.PID;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Gaduo
 */
public class PatientIdentification {
	@Getter @Setter
	private String pid01 = ""; // 
	@Getter @Setter
	private String pid02 = "";
	@Getter @Setter
	private String pid03 = ""; // id
	@Getter @Setter
	private String pid04 = "";
	@Getter @Setter
	private String pid05 = "";
	@Getter @Setter
	private String pid06 = "";
	private String pid07 = "";
	@Getter @Setter
	private String pid08 = "";
	@Getter @Setter
	private String pid09 = "";
	@Getter @Setter
	private String pid10 = "";
	@Getter @Setter
	private String pid11 = "";
	@Getter @Setter
	private String pid12 = "";
	@Getter @Setter
	private String pid13 = "";
	@Getter @Setter
	private String pid14 = "";
	@Getter @Setter
	private String pid15 = "";
	@Getter @Setter
	private String pid16 = "";
	@Getter @Setter
	private String pid17 = "";
	@Getter @Setter
	private String pid18 = "";
	@Getter @Setter
	private String pid19 = "";
	@Getter @Setter
	private String pid20 = "";
	@Getter @Setter
	private PID pid;
	
	public Date getPid07() {
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		try {
			return df.parse(this.pid07);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setPid07(String pid07) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			Date date = sdf.parse(pid07);
			DateFormat df = new SimpleDateFormat("yyyyMMdd");
			this.pid07 = df.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	
	public PID getPid() throws HL7Exception{
		pid.getPid1_SetIDPID().parse(pid01);
		pid.getPid2_PatientID().parse(pid02);
		pid.getPid3_PatientIdentifierList(0).parse(pid03);
		pid.getPid4_AlternatePatientIDPID(0).parse(pid04);
		pid.getPid5_PatientName(0).parse(pid05);
		pid.getPid6_MotherSMaidenName(0).parse(pid06);
		pid.getPid7_DateTimeOfBirth().parse(pid07);
		pid.getPid8_Sex().parse(pid07);
		pid.getPid9_PatientAlias(0).parse(pid09);
		pid.getPid10_Race(0).parse(pid10);
		pid.getPid11_PatientAddress(0).parse(pid11);
		pid.getPid12_CountyCode().parse(pid12);
		pid.getPid13_PhoneNumberHome(0).parse(pid13);
		pid.getPid14_PhoneNumberBusiness(0).parse(pid14);
		pid.getPid15_PrimaryLanguage().parse(pid15);
		pid.getPid16_MaritalStatus().parse(pid16);
		pid.getPid17_Religion().parse(pid17);
		pid.getPid18_PatientAccountNumber().parse(pid18);
		pid.getPid19_SSNNumberPatient().parse(pid19);
		pid.getPid20_DriverSLicenseNumberPatient().parse(pid20);
		return pid; 
	}
	
}
