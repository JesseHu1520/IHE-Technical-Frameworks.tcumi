package edu.tcu.mi.ihe.iti.service;

/* Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.NtpUtils;
import org.apache.commons.net.ntp.NtpV3Packet;
import org.apache.commons.net.ntp.TimeInfo;
import org.apache.commons.net.ntp.TimeStamp;
import org.apache.log4j.Logger;

public final class NTPClient {
	public static Logger logger = Logger.getLogger(NTPClient.class);
	private NtpV3Packet message;
	private String refNtpTime;
	private String origNtpTime;
	private String rcvNtpTime;
	private String xmitNtpTime;
	private String destNtpTime;
	private String delay;
	private String offset;
	private int stratum;
	private String refType;

	public Date processResponse(String host) {

		TimeInfo info = null;
		try {
			NTPUDPClient client = new NTPUDPClient();
			client.setDefaultTimeout(10000);
			client.open();
			InetAddress hostAddr = InetAddress.getByName(host);
			info = client.getTime(hostAddr);
			client.close();
		} catch (NullPointerException e) {
			logger.info(e.toString());
			return null;
		} catch (IOException e) {
			logger.info(e.toString());
			e.printStackTrace();
			return null;
		}

		message = info.getMessage();
		stratum = message.getStratum(); // 層級
		if (stratum <= 0)
			refType = "(未指定或不可用)";
		else if (stratum == 1)
			refType = "(主要參考; e.g., GPS)"; // GPS, radio clock,
		else
			refType = "(次要參考; e.g. via NTP or SNTP)";
		// stratum should be 0..15...
		logger.info(" Stratum: " + stratum + " " + refType);

		this.setRefNtpTime(message.getReferenceTimeStamp());

		// Originate Time is time request sent by client (t1)
		this.setOrigNtpTime(message.getOriginateTimeStamp());
		// Receive Time is time request received by server (t2)
		this.setRcvNtpTime(message.getReceiveTimeStamp());
		// Transmit time is time reply sent by server (t3)
		this.setXmitNtpTime(message.getTransmitTimeStamp());
		// Destination time is time reply received by client (t4)
		long destTime = info.getReturnTime();
		this.setDestNtpTime(TimeStamp.getNtpTime(destTime));

		info.computeDetails(); // compute offset/delay if not already done
		Long offsetValue = info.getOffset();
		Long delayValue = info.getDelay();
		this.setDelay(delay = (delayValue == null) ? "N/A" : delayValue
				.toString());
		this.setOffset((offsetValue == null) ? "N/A" : offsetValue.toString());

		Date Date = message.getReferenceTimeStamp().getDate();
		return Date;
	}

	public void getRefAddr() {
		int refId = message.getReferenceId();
		String refAddr = NtpUtils.getHostAddress(refId);
		String refName = null;
		if (refId != 0) {
			int version = message.getVersion();
			if (refAddr.equals("127.127.1.0")) {
				refName = "LOCAL"; // This is the ref address for the Local
									// Clock
			} else if (stratum >= 2) {
				// If reference id has 127.127 prefix then it uses its own
				// reference clock
				// defined in the form 127.127.clock-type.unit-num (e.g.
				// 127.127.8.0 mode 5
				// for GENERIC DCF77 AM; see refclock.htm from the NTP software
				// distribution.
				if (!refAddr.startsWith("127.127")) {
					try {
						InetAddress addr = InetAddress.getByName(refAddr);
						String name = addr.getHostName();
						if (name != null && !name.equals(refAddr))
							refName = name;
					} catch (UnknownHostException e) {
						// some stratum-2 servers sync to ref clock device but
						// fudge stratum level higher... (e.g. 2)
						// ref not valid host maybe it's a reference clock name?
						// otherwise just show the ref IP address.
						refName = NtpUtils.getReferenceClock(message);
					}
				}
			} else if (version >= 3 && (stratum == 0 || stratum == 1)) {
				refName = NtpUtils.getReferenceClock(message);
				// refname usually have at least 3 characters (e.g. GPS, WWV,
				// LCL, etc.)
			}
			// otherwise give up on naming the beast...
		}
		if (refName != null && refName.length() > 1)
			refAddr += " (" + refName + ")";
		logger.info(" Reference Identifier:\t" + refAddr);
	}

	public String getRefNtpTime() {
		return refNtpTime;
	}

	public void setRefNtpTime(TimeStamp refNtpTime) {
		this.refNtpTime = refNtpTime.toDateString();
		logger.info(" Reference Timestamp:\t" + refNtpTime + "  "
				+ refNtpTime.toDateString());
	}

	public String getOrigNtpTime() {
		return origNtpTime;
	}

	public void setOrigNtpTime(TimeStamp origNtpTime) {
		this.origNtpTime = origNtpTime.toDateString();
		logger.info(" Originate Timestamp:\t" + origNtpTime + "  "
				+ origNtpTime.toDateString());
	}

	public String getRcvNtpTime() {
		return rcvNtpTime;
	}

	public void setRcvNtpTime(TimeStamp rcvNtpTime) {
		this.rcvNtpTime = rcvNtpTime.toDateString();
		logger.info(" Receive Timestamp:\t" + rcvNtpTime + "  "
				+ rcvNtpTime.toDateString());
	}

	public String getXmitNtpTime() {
		return xmitNtpTime;
	}

	public void setXmitNtpTime(TimeStamp xmitNtpTime) {
		this.xmitNtpTime = xmitNtpTime.toDateString();
		logger.info(" Transmit Timestamp:\t" + xmitNtpTime + "  "
				+ xmitNtpTime.toDateString());
	}

	public String getDestNtpTime() {
		return destNtpTime;
	}

	public void setDestNtpTime(TimeStamp destNtpTime) {
		this.destNtpTime = destNtpTime.toDateString();
		logger.info(" Destination Timestamp:\t" + destNtpTime + "  "
				+ destNtpTime.toDateString());
	}

	public String getDelay() {
		return delay;
	}

	public void setDelay(String delay) {
		this.delay = delay;
		logger.info(" Roundtrip delay(ms)=" + delay);
	}

	public String getOffset() {
		return offset;
	}

	public void setOffset(String offset) {
		this.offset = offset;
		logger.info(" Clock offset(ms)=" + offset); // offset in ms
	}

	public NtpV3Packet getMessage() {
		return message;
	}

	public int getStratum() {
		return stratum;
	}

	public String getRefType() {
		return refType;
	}

	public void setMessage(NtpV3Packet message) {
		this.message = message;
	}

	public void setStratum(int stratum) {
		this.stratum = stratum;
	}

	public void setRefType(String refType) {
		this.refType = refType;
	}
	

}
