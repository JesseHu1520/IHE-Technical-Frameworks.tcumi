package edu.tcu.mi.ihe.iti.ebxml.rim.finder;

import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import edu.tcu.mi.ihe.constants.ProvideAndRegistryDocumentSet_B_UUIDs;
import edu.tcu.mi.ihe.iti.ebxml.rim.ExternalIdentifierType;

public class ExternalIdentifierFinder {

	public String findByDocumentEntryPatientId(List<ExternalIdentifierType> list){
		return findOne(list, ProvideAndRegistryDocumentSet_B_UUIDs.DOC_ENTRY_PATIENT_IDENTIFICATION_SCHEME);
	}

	public String findByDocumentEntryUniqueId(List<ExternalIdentifierType> list){
		return findOne(list, ProvideAndRegistryDocumentSet_B_UUIDs.DOC_ENTRY_UNIQUE_IDENTIFICATION_SCHEME);
	}

	public String findBySubmissionSetPatientId(List<ExternalIdentifierType> list){
		return findOne(list, ProvideAndRegistryDocumentSet_B_UUIDs.SUBMISSION_SET_PATIENT_IDENTIFICATION_SCHEME);
	}

	public String findBySubmissionSetUniqueId(List<ExternalIdentifierType> list){
		return findOne(list, ProvideAndRegistryDocumentSet_B_UUIDs.SUBMISSION_SET_UNIQUE_IDENTIFICATION_SCHEME);
	}

	public String findBySubmissionSetSourceId(List<ExternalIdentifierType> list){
		return findOne(list, ProvideAndRegistryDocumentSet_B_UUIDs.SUBMISSION_SET_SOURCE_IDENTIFICATION_SCHEME);
	}

	public String findByFolderPatientId(List<ExternalIdentifierType> list){
		return findOne(list, ProvideAndRegistryDocumentSet_B_UUIDs.FOLDER_PATIENT_IDENTIFICATION_SCHEME);
	}

	public String findByFolderUniqueId(List<ExternalIdentifierType> list){
		return findOne(list, ProvideAndRegistryDocumentSet_B_UUIDs.FOLDER_UNIQUE_IDENTIFICATION_SCHEME);
	}
	
	private String findOne(List<ExternalIdentifierType> list, final String name){
		Predicate<ExternalIdentifierType> predicate = new Predicate<ExternalIdentifierType>() {
			@Override
			public boolean apply(ExternalIdentifierType input) {
				return input.getIdentificationScheme().equals(name);
			}
		};
		ExternalIdentifierType externalIdentifier = Iterables.find(list, predicate, null);
		return externalIdentifier.getValue();
	}
}
