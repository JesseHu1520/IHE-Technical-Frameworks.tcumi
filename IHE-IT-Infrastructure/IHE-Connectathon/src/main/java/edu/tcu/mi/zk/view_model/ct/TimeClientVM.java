package edu.tcu.mi.zk.view_model.ct;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

import edu.tcu.mi.ihe.iti.service.NTPClient;

public class TimeClientVM {
	private String host;
	private NTPClient ntp;
	@Init
	public void init() {
		host = "tock.stdtime.gov.tw";		
	}

	@NotifyChange({ "ntp"})
	@Command
	public void submit(){
		ntp = new NTPClient();
		ntp.processResponse(host);
	}
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}

	public NTPClient getNtp() {
		return ntp;
	}

	public void setNtp(NTPClient ntp) {
		this.ntp = ntp;
	}
	
}
