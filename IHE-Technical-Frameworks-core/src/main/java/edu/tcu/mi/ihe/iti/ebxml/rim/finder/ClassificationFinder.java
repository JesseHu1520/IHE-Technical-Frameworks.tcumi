package edu.tcu.mi.ihe.iti.ebxml.rim.finder;

import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import edu.tcu.mi.ihe.constants.ProvideAndRegistryDocumentSet_B_UUIDs;
import edu.tcu.mi.ihe.iti.ebxml.rim.ClassificationType;

public class ClassificationFinder {
	
	public ClassificationType findByContentTypeCodeScheme(List<ClassificationType> list){
		return findOne(list, ProvideAndRegistryDocumentSet_B_UUIDs.SUBMISSION_SET_CONTENT_TYPE_CODE);
	}
	
	public ClassificationType findByTypeCodeScheme(List<ClassificationType> list){
		return findOne(list, ProvideAndRegistryDocumentSet_B_UUIDs.DOC_ENTRY_TYPE_CODE);
	}

	public ClassificationType findByClassCodeScheme(List<ClassificationType> list){
		return findOne(list, ProvideAndRegistryDocumentSet_B_UUIDs.DOC_ENTRY_CLASS_CODE);
	}
	
	public ClassificationType findByFormatCodeScheme(List<ClassificationType> list){
		return findOne(list, ProvideAndRegistryDocumentSet_B_UUIDs.DOC_ENTRY_FORMAT_CODE);
	}
	
	public ClassificationType findByHealthcareFacilityTypeCodeScheme(List<ClassificationType> list){
		return findOne(list, ProvideAndRegistryDocumentSet_B_UUIDs.DOC_ENTRY_HEALTH_CARE_FACILITY_CODE);
	}
	
	public ClassificationType findByPracticeSettingCodeScheme(List<ClassificationType> list){
		return findOne(list, ProvideAndRegistryDocumentSet_B_UUIDs.DOC_ENTRY_PRACTICE_SETTING_CODE);
	}
	
	public List<ClassificationType> findByConfidentialityCodeScheme(List<ClassificationType> list){
		return find(list, ProvideAndRegistryDocumentSet_B_UUIDs.DOC_ENTRY_CONFIDENTIALITY_CODE);
	}
	
	public List<ClassificationType> findByEventCodeListScheme(List<ClassificationType> list){
		return find(list, ProvideAndRegistryDocumentSet_B_UUIDs.DOC_ENTRY_EVENT_CODE);
	}
	
	public List<ClassificationType> findByFolderCodeListScheme(List<ClassificationType> list){
		return find(list, ProvideAndRegistryDocumentSet_B_UUIDs.FOLDER_CODE);
	}
	
	private ClassificationType findOne(List<ClassificationType> list, final String name){
		list = find(list, name);
		ClassificationType one = list.get(0);
		return one;
	}
	
	private List<ClassificationType> find(List<ClassificationType> list, final String name){
		Predicate<ClassificationType> predicate = new Predicate<ClassificationType>() {
			@Override
			public boolean apply(ClassificationType input) {
				return input.getClassificationScheme().equals(name);
			}
		};
		Iterables.filter(list, predicate);
		return list;
	}
}
