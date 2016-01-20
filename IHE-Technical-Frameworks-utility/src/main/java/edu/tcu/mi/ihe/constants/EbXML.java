package edu.tcu.mi.ihe.constants;

public enum EbXML {
	ProvideAndRegisterDocumentSetRequest("ProvideAndRegisterDocumentSetRequest"), RegistryObjectList(
			"RegistryObjectList"), SubmitObjectsRequest("SubmitObjectsRequest"), RegistryPackage(
			"RegistryPackage"), ExtrinsicObject("ExtrinsicObject"), Association(
			"Association"), Document("Document"),

	AdhocQueryRequest("AdhocQueryRequest"), ResponseOption("ResponseOption"), AdhocQuery(
			"AdhocQuery"), AdhocQueryResponse("AdhocQueryResponse"), 
	RequestSlotList("RequestSlotList"),

	LocalizedString("LocalizedString"), Name("Name"), Description("Description"), VersionInfo("VersionInfo"),

	Slot("Slot"), ValueList("ValueList"), Value("Value"),

	ObjectRef("ObjectRef"),

	Classification("Classification"),

	ExternalIdentifier("ExternalIdentifier"),

	;

	private String tag;

	EbXML(String tag) {
		this.tag = tag;
	}

	public String getTag() {
		return this.tag;
	}
}
