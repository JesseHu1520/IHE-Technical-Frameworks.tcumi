package edu.tcu.mi.ihe.utility;

import java.sql.Timestamp;
import java.util.UUID;

import org.apache.log4j.Logger;

public class IdGenerator{
	public static Logger logger = Logger.getLogger(IdGenerator.class);

	public String createTime() {
		java.util.Date date = new java.util.Date();
		String value = new Timestamp(date.getTime()).toString();
		value = value.replaceAll("\\D+", "").substring(0, 14);
		return value;
	}

	public String createUUID() {
		UUID uid = UUID.randomUUID();
		return "urn:uuid:" + uid.toString();
	}
}
