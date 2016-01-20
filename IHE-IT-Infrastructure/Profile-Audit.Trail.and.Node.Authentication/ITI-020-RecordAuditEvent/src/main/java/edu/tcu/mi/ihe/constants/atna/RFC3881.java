package edu.tcu.mi.ihe.constants.atna;

public enum RFC3881 {
	AuditMessage("AuditMessage"),
	
	EventIdentification("EventIdentification"),
	EventID("EventID"),
	EventTypeCode("EventTypeCode"),
	
	ActiveParticipant("ActiveParticipant"),
	RoleIDCode("RoleIDCode"),
	
	AuditSourceIdentification("AuditSourceIdentification"),
	AuditSourceTypeCode("AuditSourceTypeCode"),
	
	ParticipantObjectIdentification("ParticipantObjectIdentification"),
	ParticipantObjectIDTypeCode("ParticipantObjectIDTypeCode"),
	ParticipantObjectName("ParticipantObjectName"),
	ParticipantObjectQuery("ParticipantObjectQuery"),
	ParticipantObjectDetail("ParticipantObjectDetail");
	
	private String tag;

	RFC3881(String tag) {
		this.tag = tag;
	}

	public String getTag() {
		return this.tag;
	}
}
