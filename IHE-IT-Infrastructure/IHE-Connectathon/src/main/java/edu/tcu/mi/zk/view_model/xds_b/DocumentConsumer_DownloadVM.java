/**
 * 
 */
package edu.tcu.mi.zk.view_model.xds_b;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

import edu.tcu.mi.ihe.actor.XDSDocumentConsumer;
import edu.tcu.mi.ihe.iti.builder.RetrieveBuilder;
import edu.tcu.mi.ihe.security.CertificateDetails;
import edu.tcu.mi.ihe.sender.ws.NonBlockCallBack;
import edu.tcu.mi.ihe.ws.response.Response_ITI_43;
import edu.tcu.mi.ihe.ws.response.RetrieveResult;
import edu.tcu.mi.zk.model.CompanyInfomation;
import edu.tcu.mi.zk.view_model.CompanyInfoVM;
import edu.tcu.mi.zk.view_model.ViewModel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Gaduo
 */
public class DocumentConsumer_DownloadVM extends ViewModel {
	@Autowired
	private XDSDocumentConsumer consumer;
	@Getter @Setter
	private Response_ITI_43 ITI_43;
	@Getter @Setter
	private CompanyInfomation companyRepository;
	@Getter @Setter
	private String uniqueId;
	@Setter
	private String homeCommunityId;
	@Getter @Setter
	private Set<String> list;
	@Getter 
	private List<RetrieveResult> retrieveResultList;
	@Getter @Setter
	private List<CompanyInfomation> companyInfomations;

//	private RetrieveDocumentSet rd;

	@Init
	public void init() {
		this.setList(new TreeSet<String>());
		// http://ihewiki.wustl.edu/wiki/index.php/NA2010-XDS_homeCommunityId_OIDs
		setHomeCommunityId("");

//		rd = new RetrieveDocumentSet();
		CompanyInfoVM c = new CompanyInfoVM();
		c.init();
		this.setCompanyInfomations(c.getCompanyInfomations());
		System.gc();
	}

	@NotifyChange({ "ITI_43", "retrieveResultList" })
	@Command
	public void submit() {
		if (companyRepository == null) {
			logger.warn("Choice Repository.");
			return;
		}
		CertificateDetails cert = CertificateDetails.getInstance();
		cert.setCertificate();
		
		consumer = new XDSDocumentConsumer();
		NonBlockCallBack callback = new NonBlockCallBack();
		RetrieveBuilder builder = new RetrieveBuilder();

		builder.setEndpoint(companyRepository.getRepositoryEndpoint());
		builder.setRepositoryUniqueId(companyRepository.getRepositoryUniqueId());
		for(RetrieveResult retrieveResult:retrieveResultList){
			builder.addDocumentId(retrieveResult.getDocumentUniqueId());
		}
		
		consumer.retrieveDocumentSet(builder, callback);
		ITI_43 = new Response_ITI_43();
		ITI_43.parser(callback.getContext());
		setRetrieveResultList(((Response_ITI_43) ITI_43).getRetrieveResultlist());
	}

	@NotifyChange({ "list", "uniqueId" })
	@Command
	public void addDocumentItem() {
		if (uniqueId.length() > 0 && !list.contains(uniqueId)) {
			list.add(uniqueId);
		}
		logger.info("Add : " + uniqueId);
		this.setUniqueId("");
	}

	@NotifyChange({ "list" })
	@Command
	public void removeDocumentItem(@BindingParam("each") String item) {
		logger.info("Remove:" + item);
		if (list.contains(item)) {
			list.remove(item);
		}
	}

	@Command
	public void download(@BindingParam("each") RetrieveResult rr) {
		byte[] array = org.apache.commons.codec.binary.Base64.decodeBase64(rr
				.getDocument().getBytes());
//		Filedownload.save(array, rr.getMimeType(), rr.getDocumentUniqueId()
//				+ "." + rd.extractExtension(rr.getMimeType()));
	}

	public void setRetrieveResultList(List<RetrieveResult> retrieveResultList) {
		if (retrieveResultList != null)
			logger.trace(retrieveResultList.size());
		this.retrieveResultList = retrieveResultList;
	}

	public String getHomeCommunityId() {
		return homeCommunityId != null ? homeCommunityId : "";
	}

}
