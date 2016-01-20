/**
 * 
 */
package edu.tcu.mi.zk.view_model.xds_b;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.soap.SOAPBody;
import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.axis2.context.MessageContext;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zul.Filedownload;

import edu.tcu.mi.ihe.actor.XDSDocumentConsumer;
import edu.tcu.mi.ihe.iti.builder.QueryBuilder;
import edu.tcu.mi.ihe.security.CertificateDetails;
import edu.tcu.mi.ihe.sender.ws.NonBlockCallBack;
import edu.tcu.mi.ihe.ws.leafclass.MetadataParser;
import edu.tcu.mi.ihe.ws.response.Response_ITI_43;
import edu.tcu.mi.ihe.ws.response.RetrieveResult;
import edu.tcu.mi.zk.model.CompanyInfomation;
import edu.tcu.mi.zk.model.QueryGenerator;
import edu.tcu.mi.zk.model.KeyValue.KeyValue;
import edu.tcu.mi.zk.model.KeyValue.KeyValuesImpl;
import edu.tcu.mi.zk.view_model.CompanyInfoVM;
import edu.tcu.mi.zk.view_model.ViewModel;
import edu.tcu.mi.zk.view_model.xds_b.iti_18.GetDocuments;
import edu.tcu.mi.zk.view_model.xds_b.iti_18.GetFolders;
import edu.tcu.mi.zk.view_model.xds_b.iti_18.GetSubmissionsetAndContents;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Gaduo
 */
public class DocumentConsumer_LeafClassVM extends ViewModel {

	@Autowired
	private XDSDocumentConsumer consumer;
	
	@Getter @Setter
	private String uuid;
	@Getter 
	private QueryGenerator query = null;
	@Getter @Setter
	private List<KeyValue> list = null;
	@Getter @Setter
	private CompanyInfomation companyRegistry;
	@Getter @Setter
	private String filename;
	@Getter @Setter
	private String repositoryUniqueId;

	@Init
	public void init(@ExecutionArgParam("query") QueryGenerator query,
			@ExecutionArgParam("uuid") String uuid,
			@ExecutionArgParam("registry") CompanyInfomation registry,
			@ExecutionArgParam("response") OMElement response) {
		setQuery(query); // 配置 QueryGenerator 記憶體，取得前一個查詢類型是什麼
							// FindDocument、FindFolder、FindSubmissionSet
		setUuid(uuid);
		setCompanyRegistry(registry);
		if (query != null) {
			submit(response);
		}
		System.gc();
	}

	public void submit(OMElement response) {
		query.setCompanyRegistry(companyRegistry);
		query.setReturnType("LeafClass");

		boolean resubmit = reSubmit();
		boolean isBuild = query.build(); // 新的 Query
		logger.warn("isBuild is " + isBuild);
		logger.warn("resubmit is " + resubmit);
		if (isBuild && resubmit) { // 再做一次 Query

			consumer = new XDSDocumentConsumer();
			QueryBuilder builder = null;
			NonBlockCallBack callback = new NonBlockCallBack();
			consumer.registryStoredQuery(builder, callback);
			
			
			// 解析回應的訊息
			MessageContext context = callback.getContext();
			SOAPEnvelope envelope = (context != null) ? context.getEnvelope()
					: null;
			SOAPBody body = (envelope != null) ? envelope.getBody() : null;
			OMElement response02 = (body != null) ? body.getFirstElement()
					: null;
			if (response02 != null)
				responseParser(response02);
			return;
		}
		if (query.getQueryType().equals(
				new KeyValue("GetDocuments",
						"urn:uuid:5c4f972b-d56b-40ac-a5fc-c8ca9b40b9d4"))) {
			// first query's response
			responseParser(response);
		}
		if (query.getQueryType().equals(
				new KeyValue("GetDocumentsAndAssociations",
						"urn:uuid:bab9529a-4a10-40b3-a01f-f68a615d247a"))) {
			// first query's response
			responseParser(response);
		}
		if (query.getQueryType().equals(
				new KeyValue("GetFolders",
						"urn:uuid:5737b14c-8a1a-4539-b659-e03a34a5e1e4"))) {
			// first query's response
			responseParser(response);
		}
		if (query.getQueryType().equals(
				new KeyValue("GetFolderAndContents",
						"urn:uuid:b909a503-523d-4517-8acf-8e5834dfc4c7"))) {
			// first query's response
			responseParser(response);
		}
		if (query.getQueryType().equals(
				new KeyValue("GetFoldersForDocument",
						"urn:uuid:10cae35a-c7f9-4cf5-b61e-fc3278ffb578"))) {
			// first query's response
			responseParser(response);
		}
		if (query.getQueryType().equals(
				new KeyValue("GetFoldersForDocument",
						"urn:uuid:10cae35a-c7f9-4cf5-b61e-fc3278ffb578"))) {
			// first query's response
			responseParser(response);
		}
		if (query.getQueryType().equals(
				new KeyValue("GetSubmissionSets",
						"urn:uuid:51224314-5390-4169-9b91-b1980040715a"))) {
			// first query's response
			responseParser(response);
		}
		if (query.getQueryType().equals(
				new KeyValue("GetSubmissionsetAndContents",
						"urn:uuid:e8e3cb2c-e39c-46b9-99e4-c12f57260b83"))) {
			// first query's response
			responseParser(response);
		}
		if (query.getQueryType().equals(
				new KeyValue("GetAssociations",
						"urn:uuid:a7ae438b-4bc2-4642-93e9-be891f7bb155"))) {
			// first query's response
			responseParser(response);
		}
		if (query.getQueryType().equals(
				new KeyValue("GetRelatedDocuments",
						"urn:uuid:d90e5407-b356-4d91-a89f-873917b4b0e6"))) {
			// first query's response
			responseParser(response);
		}
		System.gc();
	}

	public boolean reSubmit() {
		boolean resubmit = false;
		if (query.getQueryType() != null) {
			if (query.getQueryType().equals(
					new KeyValue("FindDocuments",
							"urn:uuid:14d4debf-8f97-4251-9a74-a90016b0af0d"))) {
				query.setQueryType(new KeyValue("Get Documents",
						"urn:uuid:5c4f972b-d56b-40ac-a5fc-c8ca9b40b9d4"));
				GetDocuments gd = new GetDocuments();
				gd.setType("EntryUUID");
				gd.setId(uuid);
				query.setParameter(gd);
				logger.trace("QueryType is GetDocuments");
				resubmit = true;
			} else if (query.getQueryType().equals(
					new KeyValue("FindFolders",
							"urn:uuid:958f3006-baad-4929-a4de-ff1114824431"))) {
				query.setQueryType(new KeyValue("Get Folders",
						"urn:uuid:5737b14c-8a1a-4539-b659-e03a34a5e1e4"));
				GetFolders gf = new GetFolders();
				gf.setType("EntryUUID");
				gf.setId(uuid);
				query.setParameter(gf);
				logger.trace("QueryType is GetFolders");
				resubmit = true;
			} else if (query.getQueryType().equals(
					new KeyValue("FindSubmissionSets",
							"urn:uuid:f26abbcb-ac74-4422-8a30-edb644bbc1a9"))) {
				query.setQueryType(new KeyValue(
						"Get Submissionset And Contents",
						"urn:uuid:e8e3cb2c-e39c-46b9-99e4-c12f57260b83"));
				GetSubmissionsetAndContents gs = new GetSubmissionsetAndContents();
				gs.setId(uuid);
				query.setParameter(gs);
				logger.trace("QueryType is Get Submissionset And Contents");
				resubmit = true;
			}
		} else {
			logger.error("QueryType is NULL");
		}
		return resubmit;
	}

	public void responseParser(OMElement response) {
//		try {
			// logger.info("responseParser : " + response);
			MetadataParser mp = new MetadataParser(response);
			Map<String, KeyValuesImpl> map = mp.getMapArray();
			if (map != null) {
				KeyValuesImpl keyValueList = map.get(uuid);
				if (keyValueList != null) {
					List<KeyValue> all = keyValueList.findAll();
					setList(all);
					Iterator<KeyValue> iterator = all.iterator();
					while (iterator.hasNext()) {
						KeyValue kv = iterator.next();
						String key = kv.getKey();
						// logger.info(key);
						if (key.equalsIgnoreCase("Name")) {
							filename = kv.getValue();
						}
						if (key.equalsIgnoreCase("repositoryUniqueId")) {
							setRepositoryUniqueId(kv.getValue());
						}
					}
				}
			}
//		} catch (NullPointerException e) {
//			logger.error(e.toString());
//			return;
//		}
	}

	// -------------------
	@Command
	public void download() {
		String uniqueId = "", repositoryUniqueId = "", filename = "";
		logger.info(list);
		if (list == null)
			return ;
		Iterator<KeyValue> iterator01 = list.iterator();
		while (iterator01.hasNext()) {
			KeyValue next = iterator01.next();
			if (next.getKey().equals("DOC_ENTRY_UNIQUE_IDENTIFICATION_SCHEME")) {
				uniqueId = next.getValue();
			}
			if (next.getKey().equals("repositoryUniqueId")) {
				repositoryUniqueId = next.getValue();
			}
			if (next.getKey().equals("Name")) {
				filename = next.getValue();
			}
		}
		Set<String> documentIdList = new TreeSet<String>();
		documentIdList.add(uniqueId);
		String repEndpoint = repositoryMapping();
		CertificateDetails cert = CertificateDetails.getInstance();
		cert.setCertificate();
//		RetrieveDocumentSet rds = new RetrieveDocumentSet();
//		rds.RetrieveGenerator(documentIdList, repEndpoint, repositoryUniqueId, "");
		Response_ITI_43 ITI_43 = new Response_ITI_43();
//		ITI_43.parser(rds.getContext());

		List<RetrieveResult> RetrieveResultList = ((Response_ITI_43) ITI_43) .getRetrieveResultlist();
		if (RetrieveResultList != null) {
			Iterator<RetrieveResult> iterator = RetrieveResultList.iterator();
			while (iterator.hasNext()) {
				RetrieveResult rr = iterator.next();
				try {
					byte[] array = Base64.decodeBase64(rr.getDocument().getBytes("UTF-8"));
					Filedownload.save(array, rr.getMimeType(), URLEncoder.encode(filename, "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		System.gc();
	}

	private String repositoryMapping() {
		CompanyInfoVM c = new CompanyInfoVM();
		c.init();
		// /要將整個 CompanyInfo List 傳進來，在依照 uniqueId 查詢。
		List<CompanyInfomation> companys = c.getCompanyInfomations();
		Iterator<CompanyInfomation> iterator = companys.iterator();
		while (iterator.hasNext()) {
			CompanyInfomation company = iterator.next();
			String repUniqueId = company.getRepositoryUniqueId();
			if (repUniqueId.equals(repositoryUniqueId)) {
				return company.getRepositoryEndpoint();
			}
		}
		logger.info("Repository UniqueId was not maping any Repository Endpoint");
		return "";
	}

	// -------------------

	public void setQuery(QueryGenerator q) {
		this.query = q.clone();
	}


}
