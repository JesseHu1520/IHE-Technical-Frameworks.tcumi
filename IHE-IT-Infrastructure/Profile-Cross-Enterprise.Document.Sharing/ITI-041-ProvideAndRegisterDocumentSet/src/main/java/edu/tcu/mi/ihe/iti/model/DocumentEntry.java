package edu.tcu.mi.ihe.iti.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.activation.DataHandler;
import javax.mail.util.ByteArrayDataSource;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMText;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.gson.annotations.Expose;

import edu.tcu.mi.ihe.constants.DocumentEntryConstants;
import edu.tcu.mi.ihe.iti.builder.MetadataBuilder;
import edu.tcu.mi.ihe.sender.ws.Soap;
import edu.tcu.mi.ihe.sender.ws.SoapWithAttachment;
import lombok.Getter;
import lombok.Setter;

public class DocumentEntry extends BaseModel {
	@Getter @Setter
	private Patient patient; 
	
	@Expose @Getter @Setter
	private String title;
	@Expose @Getter @Setter
	private String description;
	@Expose @Getter @Setter
	private String creationTime;
	@Expose @Setter
	private String mimeType;
	@Expose @Getter 
	private String content;
	@Expose @Getter @Setter
	private String classCode;
	@Expose @Getter @Setter
	private String formatCode;
	@Expose @Getter @Setter
	private String healthcareFacilityTypeCode;
	@Expose @Getter @Setter
	private String practiceSettingCode;
	@Expose @Getter @Setter
	private String typeCode;
	@Expose @Getter
	private Set<String> confidentialityCode;
	@Expose @Getter @Setter
	private Set<String> eventCodeList;
	@Expose @Getter
	private List<Author> authors;

	@Expose @Getter @Setter
	private boolean existing;
	@Expose @Getter @Setter
	private String appendDocumentId;
	@Expose @Getter @Setter
	private String replaceDocumentId;
	@Expose @Getter @Setter
	private String transformDocumentId;
	@Expose @Getter @Setter
	private String transformAndReplaceDocumentId;
	@Expose @Getter @Setter
	private String signatureDocumentId;
	
	@Setter @Getter
	private Soap soap;
	@Getter
	private long size;
	@Getter
	private String hash;
	
	public DocumentEntry(){
		authors = Lists.newArrayList();
		confidentialityCode = Sets.newTreeSet();
		eventCodeList = Sets.newTreeSet();
	}

	public DocumentEntry(String id) {
		this.id = id;
	}
	
	public Author addAuthor(){
		if(authors == null)
			authors = Lists.newArrayList();
		Author author = new Author();
		author.setParentId(this.getId());
		authors.add(author);
		return author;
	}
	
	public void addConfidentialityCode(String e){
		if(confidentialityCode == null) confidentialityCode = Sets.newConcurrentHashSet();
		this.confidentialityCode.add(e);
	}
	public void addEventCodeList(String e){
		if(eventCodeList == null) confidentialityCode = Sets.newConcurrentHashSet();
		this.eventCodeList.add(e);
	}
	
	public String getMimeType(){
		if(mimeType == null) mimeType = extractMimeType(title);
		return mimeType;
	}
	
	/**
	 * 
	 * @param content
	 * Content 可以是檔案.
	 * Content can be a File.
	 */
	public void setContent(File file) {
		FileInputStream fis;
		try {
			this.setTitle(file.getName());
			this.setDescription(file.getName());
			fis = new FileInputStream(file);
			setContent(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		creationTime = extractCreationTime(file);
	}

	
	/**
	 * 
	 * @param content
	 * Content 可以是 FileInputStream.
	 * Content can be a FileInputStream.
	 */
	public void setContent(InputStream fis) {
		try {
			byte[] input = IOUtils.toByteArray(fis);
			setContent(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 
	 * @param base64
	 * Content 可以是 Base64 字串.
	 * Content can be a base64 String.
	 */
	public void setContent(String base64) {
		byte[] input = Base64.decodeBase64(base64.getBytes());
		setContent(input);
		/**
		 * To validate creationTime, title, description
		 */
	}
	
	/**
	 * 
	 * @param input
	 * Content 可以是 byte 陣列.
	 * Content can be a byte array.
	 */
	public void setContent(byte[] input){
		if(mimeType == null)
			mimeType = extractMimeType(title);
		size = extractSize(input);
		hash = extractHash(input);
		
		DataHandler handler = new DataHandler(new ByteArrayDataSource(input, mimeType));
		boolean isSWA = (soap != null) ?  soap.isSwa() : false;
		if (!isSWA) {
			OMFactory fac = OMAbstractFactory.getOMFactory();
			OMText binaryData = fac.createOMText(handler, true);
			binaryData.setOptimize(true);
			this.content = binaryData.getText();
		} else if(isSWA && soap instanceof SoapWithAttachment){
			this.content = ((SoapWithAttachment)soap).addAttachment(input, mimeType);
		} else if(isSWA && soap instanceof Soap){
			this.content = ((Soap)soap).addAttachment(handler);
		}
	}
	
	private String extractCreationTime(File file) {
		String value = null;
		if (file != null) {
			value = new Timestamp(file.lastModified()).toString();
			value = value.replaceAll("\\D+", "").substring(0, 14);
		}
		return value;
	}
	
	private long extractSize(byte[] bytes) {
		return bytes.length;
	}
	
	private String extractHash(byte[] bytes) {
		String hash_value = null;
		try {
			hash_value = getSha1(bytes);
		} catch (Exception e) {
		}
		return hash_value;
	}

	private String getSha1(byte[] bytes) throws Exception {
		if (bytes == null)
			throw new Exception("Sha1Bean:getSha1: byteStream is null");
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA1");
		} catch (Exception e) {
		}
		byte[] sha1 = md.digest(bytes);
		
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < sha1.length; i++) {
			String h = Integer.toHexString(sha1[i] & 0xff);
			if (h.length() == 1)
				h = "0" + h;
			buf.append(h);
		}
		return new String(buf);
	}
	
	private String extractMimeType(String uri) {
		if(uri == null) return null;
		String type = null;
		int dotPos = uri.lastIndexOf(".");
		String extension = uri.substring(dotPos + 1).toLowerCase();
		Node node = MetadataBuilder.codes.QueryNode("Codes/CodeType[@name='mimeType']/Code[@ext='" + extension + "']");
		if (node != null) {
			if (node != null) {
				NamedNodeMap attrs = node.getAttributes();
				if (attrs != null) {
					type = attrs.getNamedItem("code").getNodeValue();
					return type;
				}
			}
		}
		node = MetadataBuilder.web.QueryNode("web-app/mime-mapping/extension[text()='" + extension + "']");
		if (node != null) {
			node = node.getParentNode();
			NodeList nodes = node.getChildNodes();
			for (int i = 0; i < nodes.getLength(); i++) {
				node = nodes.item(i);
				if (node.getNodeName().equalsIgnoreCase(DocumentEntryConstants.MIME_TYPE)) {
					return node.getTextContent();
				}
			}
		}
		return null;
	}

}
