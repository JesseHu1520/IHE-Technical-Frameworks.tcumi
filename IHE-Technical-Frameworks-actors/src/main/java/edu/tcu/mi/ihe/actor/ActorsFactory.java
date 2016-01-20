package edu.tcu.mi.ihe.actor;

import edu.tcu.mi.ihe.actor.constant.ActorTypes;

public class ActorsFactory {

	
	public static Actor getActor(ActorTypes actorType){
		switch(actorType){
		case PIXSource:
			return new PIXSource();
		case PIXConsumer:
			return new PIXConsumer();
		case XDSDocumentSource:
			return new XDSDocumentSource();
		case XDSDocumentConsumer:
			return new XDSDocumentConsumer();
		default :
			return null;
		}
	}
}
