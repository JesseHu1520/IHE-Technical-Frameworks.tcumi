/**
 * 
 */
package edu.tcu.mi.ihe.security;

import java.io.InputStream;

import org.apache.log4j.Logger;

/**
 * @author Gaduo
 */
public class CertificateDetails {
	public static Logger logger = Logger.getLogger(CertificateDetails.class);


	private static CertificateDetails instance = null; 
	private CertificateDetails() {
		
	}
	
	public synchronized static CertificateDetails getInstance(){
		if(instance == null) {
			instance = new CertificateDetails();
		}
		return instance;
	}

	@Deprecated
	public void setCertificate() {
		Class<CertificateDetails> clazz = CertificateDetails.class;
		ClassLoader loader = clazz.getClassLoader();
		String certificate = "";
		certificate = loader.getResource("certificate/openxds_2013/OpenXDS_2013_Keystore.p12").toString().replace("file:/", "");
		logger.info(certificate);
		System.setProperty("javax.net.ssl.keyStore", certificate);
		System.setProperty("javax.net.ssl.keyStorePassword", "123456");
		// System.setProperty("javax.net.ssl.keyStoreType", "PKCS12");
		certificate = loader.getResource("certificate/openxds_2013/OpenXDS_2013_Truststore.jks").toString().replace("file:/", "");
		logger.info(certificate);
		System.setProperty("javax.net.ssl.trustStore", certificate);
		System.setProperty("javax.net.ssl.trustStorePassword", "123456");
		System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
	}

	@Deprecated
	public void setSSLCertificate() {
		ClassLoader loader = getClass().getClassLoader();
		String certificate = "";
		certificate = loader.getResource("certificate/openxds_2013/OpenXDS_2013_Truststore.jks").toString().replace("file:/", "");
		System.setProperty("javax.net.ssl.trustStore", certificate);
		System.setProperty("javax.net.ssl.trustStorePassword", "123456");
		System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
	}
	
	private String keyStore ;
	private String keyPass ;
	private String trustStore ;
	private String trustPass ;
	private InputStream keyStoreLocation;
	private InputStream trustStoreLocation;

	public void setCertificate(String keyStore, String keyPass, String trustStore, String trustPass) {
		this.keyStore = keyStore;
		this.keyPass = keyPass;
		this.trustStore = trustStore;
		this.trustPass = trustPass;
		
		try {
			System.setProperty("javax.net.ssl.keyStore", keyStore);
			System.setProperty("javax.net.ssl.keyStorePassword", keyPass);
			System.setProperty("javax.net.ssl.keyStoreType", "JKS");
			
			System.setProperty("javax.net.ssl.trustStore", trustStore);
			System.setProperty("javax.net.ssl.trustStorePassword", trustPass);
			System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
	}
	
	/**
	 * @return the keyStore
	 */
	public String getKeyStore() {
		return keyStore;
	}
	/**
	 * @return the keyPass
	 */
	public String getKeyPass() {
		return keyPass;
	}
	/**
	 * @return the trustStore
	 */
	public String getTrustStore() {
		return trustStore;
	}
	/**
	 * @return the trustPass
	 */
	public String getTrustPass() {
		return trustPass;
	}
	/**
	 * @return the keyStoreLocation
	 */
	public InputStream getKeyStoreLocation() {
		return keyStoreLocation;
	}
	/**
	 * @return the trustStoreLocation
	 */
	public InputStream getTrustStoreLocation() {
		return trustStoreLocation;
	}
	
}
