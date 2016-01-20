package edu.tcu.mi.ihe.iti.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import edu.tcu.mi.ihe.iti.core.MessageBuilder;
import edu.tcu.mi.ihe.iti.core.SocketTransaction;
import edu.tcu.mi.ihe.sender.socket.SocketSender;
import lombok.Setter;

@Component
public class PIXQueryService extends SocketTransaction {

	@Setter
	@Value("${pix.manager.host}")
	private String host ;

	@Setter
	@Value("${pix.manager.port}")
	private int port;

	@Override
	public String webservice(MessageBuilder builder) {
		SocketSender sender = SocketSender.getInstance();
		return sender.send(host, port, builder.getMessageFromHL7v2());
	}
	
	@Override
	public void auditLog() {
		// TODO Auto-generated method stub
		
	}

}
