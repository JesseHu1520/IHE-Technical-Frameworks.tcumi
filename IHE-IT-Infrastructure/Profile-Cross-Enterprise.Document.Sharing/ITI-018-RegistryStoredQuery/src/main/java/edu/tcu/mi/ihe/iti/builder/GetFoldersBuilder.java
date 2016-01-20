package edu.tcu.mi.ihe.iti.builder;

import com.google.common.collect.Lists;

import edu.tcu.mi.ihe.constants.RegistryStoredQueryUUIDs;
import edu.tcu.mi.ihe.constants.StoredQueryConstants;


public class GetFoldersBuilder extends QueryBuilder {
	public GetFoldersBuilder(){
		super(RegistryStoredQueryUUIDs.GET_FOLDERS_UUID);
	}
	
		public GetFoldersBuilder andEntryUuid(String ... val){
			this.parameters.put(StoredQueryConstants.FOL_ENTRY_UUID, Lists.newArrayList(val));
			return this;
		}
		public GetFoldersBuilder andUniqueId(String ... val){
			this.parameters.put(StoredQueryConstants.FOL_UNIQUE_ID, Lists.newArrayList(val));
			return this;
		}
		public GetFoldersBuilder andHomeCommunityId(String val){
			this.parameter.put(StoredQueryConstants.HOME_COMMUNITY_ID, val);
			return this;
		}

		@Override
		public String getMessageFromHL7v2() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected boolean validate() {
			return true;
		}
}
