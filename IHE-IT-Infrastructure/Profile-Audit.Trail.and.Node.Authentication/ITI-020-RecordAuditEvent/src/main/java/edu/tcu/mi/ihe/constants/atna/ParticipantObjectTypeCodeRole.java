package edu.tcu.mi.ihe.constants.atna;


/** 
 * ParticipantObjectTypeCodeRole
 *  
 * @author Gaduo 
 */  
public enum ParticipantObjectTypeCodeRole {

	/**
	 * Patient(1)
	 * */
	Patient(1),
	/**
	 * Location(2)
	 * */
	Location(2),
	/**
	 * Report(3)
	 * */
	
	/**
	 * Report(3)
	 */
	Report(3),
	
	/**
	 * Resource(4)
	 */
	Resource(4),
	MasterFile(5),
	User(6),
	List(7),
	Doctor(8),
	Subscriber(9),
	Guarantor(10),
	SecurityUserEntity(11),
	SecurityUserGroup(12),
	SecurityResource(13),
	SecurityGranularityDefinition(14),
	Provider(15),
	DataDestination(16),
	DataRepository(17),
	Schedule(18),
	Customer(19),
	/**
	 * Job(20)
	 * */
	Job(20),
	JobStream(21),
	Table(22),
	RoutingCriteria(23),
	Query(24);
	
	private int value;
	ParticipantObjectTypeCodeRole(int value){
		this.setValue(value);
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
}
