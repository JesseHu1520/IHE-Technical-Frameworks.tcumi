package edu.tcu.mi.hl7.pid;

public class AffinityDomain {
	private String name;
	private String type;
	private String namespaceID;
	private String universalID;
	private String universalIDType;

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNamespaceID() {
		return namespaceID;
	}

	public String getUniversalID() {
		return universalID;
	}

	public String getUniversalIDType() {
		return universalIDType;
	}

	public void setNamespaceID(String namespaceID) {
		this.namespaceID = namespaceID;
	}

	public void setUniversalID(String universalID) {
		this.universalID = universalID;
	}

	public void setUniversalIDType(String universalIDType) {
		this.universalIDType = universalIDType;
	}

	public String toString(){
		return this.namespaceID + "&" + this.universalID + "&" + this.universalIDType;
	}

}
