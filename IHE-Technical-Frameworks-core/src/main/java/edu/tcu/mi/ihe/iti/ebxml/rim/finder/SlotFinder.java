package edu.tcu.mi.ihe.iti.ebxml.rim.finder;

import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import edu.tcu.mi.ihe.constants.DocumentEntryConstants;
import edu.tcu.mi.ihe.constants.FolderConstants;
import edu.tcu.mi.ihe.constants.SubmissionSetConstants;
import edu.tcu.mi.ihe.iti.ebxml.rim.SlotType;
import edu.tcu.mi.ihe.iti.ebxml.rim.ValueListType;
import edu.tcu.mi.ihe.iti.ebxml.rim.ValueType;

public class SlotFinder {
	
	public String findByDocumentEntryCreationTime(List<SlotType> list){
		return findOne(list, DocumentEntryConstants.CREATION_TIME);
	}
	
	public String findByDocumentEntryServiceStartTime(List<SlotType> list){
		return findOne(list, DocumentEntryConstants.SERVICE_START_TIME);
	}

	public String findByDocumentEntryServiceStopTime(List<SlotType> list){
		return findOne(list, DocumentEntryConstants.SERVICE_STOP_TIME);
	}

	public String findByDocumentEntryLanguageCode(List<SlotType> list){
		return findOne(list, DocumentEntryConstants.LANGUAGE_CODE);
	}

	public String findByDocumentEntrySourcePatientId(List<SlotType> list){
		return findOne(list, DocumentEntryConstants.SOURCE_PATIENT_ID);
	}

	public SlotType findByDocumentEntrySourcePatientInfo(List<SlotType> list){
		return find(list, DocumentEntryConstants.SOURCE_PATIENT_INFO);
	}
	
	public String findByDocumentEntryHash(List<SlotType> list){
		return findOne(list, DocumentEntryConstants.HASH);
	}

	public String findByDocumentEntrySize(List<SlotType> list){
		return findOne(list, DocumentEntryConstants.SIZE);
	}
	
	public String findByDocumentEntryRepositoryUniqueId(List<SlotType> list){
		return findOne(list, DocumentEntryConstants.REPOSITORY_UNIQUE_ID);
	}
	
	public String findBySubmissionSetSubmissionTime(List<SlotType> list){
		return findOne(list, SubmissionSetConstants.SUBMISSION_TIME);
	}
	
	public String findByFolderLastUpdateTime(List<SlotType> list){
		return findOne(list, FolderConstants.LAST_UPDATE_TIME);
	}
	
	private String findOne(List<SlotType> list, final String name){
		SlotType slot = find(list, name);
		ValueListType valueList = slot.getValueList();
		List<ValueType> values = valueList.getValues();
		ValueType value = values.get(0);
		return value.getText();
	}
	
	private SlotType find(List<SlotType> list, final String name){
		Predicate<SlotType> predicate = new Predicate<SlotType>() {
			@Override
			public boolean apply(SlotType input) {
				return input.getName().equals(name);
			}
		};
		return Iterables.find(list, predicate , null);
	}
	
}
