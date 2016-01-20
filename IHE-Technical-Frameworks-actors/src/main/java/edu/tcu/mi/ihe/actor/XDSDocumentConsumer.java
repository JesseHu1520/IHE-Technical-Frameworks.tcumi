package edu.tcu.mi.ihe.actor;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.axiom.om.OMElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import edu.tcu.mi.ihe.constants.Namespace;
import edu.tcu.mi.ihe.iti.builder.QueryBuilder;
import edu.tcu.mi.ihe.iti.builder.RetrieveBuilder;
import edu.tcu.mi.ihe.iti.ebxml.ihe.DocumentResponseType;
import edu.tcu.mi.ihe.iti.ebxml.ihe.RetrieveDocumentSetResponseType;
import edu.tcu.mi.ihe.iti.ebxml.query.AdhocQueryResponseType;
import edu.tcu.mi.ihe.iti.ebxml.rim.ExtrinsicObjectType;
import edu.tcu.mi.ihe.iti.ebxml.rim.RegistryObjectListType;
import edu.tcu.mi.ihe.iti.ebxml.rim.SlotType;
import edu.tcu.mi.ihe.iti.ebxml.rim.finder.SlotFinder;
import edu.tcu.mi.ihe.iti.service.RegistryStoredQueryService;
import edu.tcu.mi.ihe.iti.service.RetrieveDocumentSetService;
import edu.tcu.mi.ihe.sender.ws.NonBlockCallBack;
import edu.tcu.mi.ihe.utility.AxiomUtil;

@Component
public class XDSDocumentConsumer extends Actor {
	
	@Autowired
	private RegistryStoredQueryService registryStoredQuery;
	
	@Autowired
	private RetrieveDocumentSetService retrieveDocumentSet;

	public XDSDocumentConsumer(){
		if(registryStoredQuery == null) this.registryStoredQuery = new RegistryStoredQueryService();
		if(retrieveDocumentSet == null) this.retrieveDocumentSet = new RetrieveDocumentSetService();
	}
	
	public OMElement registryStoredQuery(QueryBuilder builder, NonBlockCallBack callback){
		if(builder == null) return null;
		if(callback == null) callback = new NonBlockCallBack();
		String stirng = registryStoredQuery.transaction(builder, callback);
		AxiomUtil axiom = AxiomUtil.getInstance();
		return axiom.fromString(stirng);
	}
	
	public OMElement retrieveDocumentSet(RetrieveBuilder builder, NonBlockCallBack callback) {
		if(builder == null) return null;
		if(callback == null) callback = new NonBlockCallBack();
		String stirng = retrieveDocumentSet.transaction(builder, callback);
		AxiomUtil axiom = AxiomUtil.getInstance();
		return axiom.fromString(stirng);
	}
	
	public List<DocumentResponseType> registryStoredQueryAndRetrieveDocumentSet(QueryBuilder builder, NonBlockCallBack callback){
		List<DocumentResponseType> documents = Lists.newArrayList();
		
		OMElement response = registryStoredQuery(builder, callback);
		AxiomUtil axiom = AxiomUtil.getInstance();
		AdhocQueryResponseType adhocQueryResponse = axiom.fromXML(response, AdhocQueryResponseType.class);
		String status = adhocQueryResponse.getStatus();
		if (status.equals(Namespace.SUCCESS.getNamespace())) {
			Map<String, List<String>> map = Maps.newHashMap();
			RegistryObjectListType objectList = adhocQueryResponse.getRegistryObjectList();
			List<ExtrinsicObjectType> docEntries = objectList.getExtrinsicObjects();
			SlotFinder finder = new SlotFinder();
			for(ExtrinsicObjectType docEntry : docEntries){
				String docId = docEntry.getId();
				String repositoryUniqueId = finder.findByDocumentEntryRepositoryUniqueId(docEntry.getSlots());
				if(map.containsKey(repositoryUniqueId)){
					List<String> list = map.get(repositoryUniqueId);
					list.add(docId);
					map.put(repositoryUniqueId, list);
				} else {
					map.put(repositoryUniqueId, Lists.newArrayList(docId));
				}
			}
			
			Set<String> repositoryUniqueIds = map.keySet();
			for(String repositoryUniqueId : repositoryUniqueIds){
				RetrieveBuilder retrieveBuilder = new RetrieveBuilder();
				retrieveBuilder.setRepositoryUniqueId(repositoryUniqueId);
				List<String> docIds = map.get(repositoryUniqueId);
				retrieveBuilder.setDocumentIds(Sets.newTreeSet(docIds));
				// TODO query repository endpoint
				retrieveBuilder.setEndpoint("");
				OMElement _response = retrieveDocumentSet(retrieveBuilder , callback);
		        RetrieveDocumentSetResponseType retrieveDocumentSetResponse = axiom.fromXML(_response, RetrieveDocumentSetResponseType.class);
		        List<DocumentResponseType> docs = retrieveDocumentSetResponse.getDocumentResponses();
		        documents.addAll(docs);
			}
		} 
		return documents;
	}	
}
