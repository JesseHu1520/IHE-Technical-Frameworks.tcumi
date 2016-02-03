package edu.tcu.mi.ihe.constants;

public interface StoredQueryConstants {
	// documents
	public static final String DE_PATIENT_ID = "$XDSDocumentEntryPatientId";
	public static final String DE_CLASS_CODE = "$XDSDocumentEntryClassCode";
	public static final String DE_CLASS_CODE_SCHEME = "$XDSDocumentEntryClassCodeScheme";
	public static final String DE_PRAC_SETTING_CODE = "$XDSDocumentEntryPracticeSettingCode";
	public static final String DE_PRAC_SETTING_CODE_SCHEME = "$XDSDocumentEntryPracticeSettingCodeScheme";
	public static final String DE_CREATION_TIME_FROM = "$XDSDocumentEntryCreationTimeFrom";
	public static final String DE_CREATION_TIME_TO = "$XDSDocumentEntryCreationTimeTo";
	public static final String DE_SERVICE_START_TIME_FROM = "$XDSDocumentEntryServiceStartTimeFrom";
	public static final String DE_SERVICE_START_TIME_TO = "$XDSDocumentEntryServiceStartTimeTo";
	public static final String DE_SERVICE_STOP_TIME_FROM = "$XDSDocumentEntryServiceStopTimeFrom";
	public static final String DE_SERVICE_STOP_TIME_TO = "$XDSDocumentEntryServiceStopTimeTo";
	public static final String DE_HC_FACILITY_CODE = "$XDSDocumentEntryHealthcareFacilityTypeCode";
	public static final String DE_HC_FACILITY_CODE_SCHEME = "$XDSDocumentEntryHealthcareFacilityTypeCodeScheme";
	public static final String DE_EVENT_CODE = "$XDSDocumentEntryEventCodeList";
	public static final String DE_EVENT_CODE_SCHEME = "$XDSDocumentEntryEventCodeListScheme";
	public static final String DE_CONF_CODE = "$XDSDocumentEntryConfidentialityCode";
	public static final String DE_CONF_CODE_SCHEME = "$XDSDocumentEntryConfidentialityCodeScheme";
	public static final String DE_FORMAT_CODE = "$XDSDocumentEntryFormatCode";
	public static final String DE_STATUS = "$XDSDocumentEntryStatus";
	public static final String DE_ENTRY_UUID = "$XDSDocumentEntryEntryUUID";
	public static final String DE_UNIQUE_ID = "$XDSDocumentEntryUniqueId";
	public static final String DE_REFERENCE_ID_LIST = "$XDSDocumentEntryReferenceIdList";
	public static final String DE_TYPE_CODE = "$XDSDocumentEntryTypeCode";
	public static final String DE_AUTHOR_PERSON = "$XDSDocumentEntryAuthorPerson";
	public static final String DE_TYPE = "$XDSDocumentEntryType";
	
	// submissionsets
	public static final String SS_PATIENT_ID = "$XDSSubmissionSetPatientId";
	public static final String SS_SOURCE_ID = "$XDSSubmissionSetSourceId";
	public static final String SS_SUBMISSION_TIME_FROM = "$XDSSubmissionSetSubmissionTimeFrom";
	public static final String SS_SUBMISSION_TIME_TO = "$XDSSubmissionSetSubmissionTimeTo";
	public static final String SS_AUTHOR_PERSON = "$XDSSubmissionSetAuthorPerson";
	public static final String SS_CONTENT_TYPE = "$XDSSubmissionSetContentType";
	public static final String SS_STATUS = "$XDSSubmissionSetStatus";
	public static final String SS_ENTRY_ID = "$XDSSubmissionSetEntryUUID";
	public static final String SS_UNIQUE_ID = "$XDSSubmissionSetUniqueId";
	
	// folders
	public static final String FOL_PATIENT_ID = "$XDSFolderPatientId";
	public static final String FOL_LAST_UPDATE_TIME_FROM = "$XDSFolderLastUpdateTimeFrom";
	public static final String FOL_LAST_UPDATE_TIME_TO = "$XDSFolderLastUpdateTimeTo";
	public static final String FOL_CODE_LIST = "$XDSFolderCodeList";
	public static final String FOL_CODE_LIST_SCHEME = "$XDSFolderCodeListScheme";
	public static final String FOL_STATUS = "$XDSFolderStatus";
	public static final String FOL_ENTRY_UUID = "$XDSFolderEntryUUID";
	public static final String FOL_UNIQUE_ID = "$XDSFolderUniqueId";
	
	// get All
	public static final String PATIENT_ID = "$patientId";
	
	// get Associations
	public static final String UUID = "$uuid";
	
	// get related documents
	public static final String ASSOCIATION_TYPES = "$AssociationTypes";
	
	public static final String HOME_COMMUNITY_ID = "$homeCommunityId";
	
	public static final String LEAFCLASS = "LeafClass";
	public static final String OBJECTREF = "ObjectRef";
	
}
