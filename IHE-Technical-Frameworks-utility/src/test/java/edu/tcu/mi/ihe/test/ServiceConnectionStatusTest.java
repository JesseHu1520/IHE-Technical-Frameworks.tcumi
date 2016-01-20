package edu.tcu.mi.ihe.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Enumeration;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509KeyManager;
import javax.security.cert.X509Certificate;

import org.apache.log4j.Logger;
import org.junit.Test;

public class ServiceConnectionStatusTest {
	public static Logger logger = Logger.getLogger(ServiceConnectionStatusTest.class);

	@Test
	public void testWebService() {
		HttpURLConnection conn;
		URL url;
		try {
			url = new URL("http://203.64.84.214:8020/axis2/services/xdsrepositoryb?wsdl");
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setUseCaches(true);
			conn.setRequestMethod("POST");
			conn.connect();
		} catch(java.net.ConnectException e){
			logger.error(e.getMessage());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	@Test
	public void testSecurityWebService() {
        String url = "https://203.64.84.214:8021/axis2/services/xdsrepositoryb?wsdl";
        String keyStoreFileName = "OpenXDS_2013_Truststore.jks";
        String keyStorePassword = "password";
        String trustStoreFileName = "OpenXDS_2013_Truststore.jks";
        String trustStorePassword = "password";
        String alias = "mir";
 
        try {
        	MutualAuthenticationHTTP ma = new MutualAuthenticationHTTP();
        	
            //create key and trust managers
            KeyManager[] keyManagers = ma.createKeyManagers(keyStoreFileName, keyStorePassword, alias, "JKS");
            TrustManager[] trustManagers = ma.createTrustManagers(trustStoreFileName, trustStorePassword, "JKS");
            //init context with managers data   
            SSLSocketFactory factory = ma.initItAll(keyManagers, trustManagers);
            //get the url and display content
            ma.doitAll(url, factory);
 
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
		
	}
	

	@Test
	public void testSocket(){
		try {
			Socket socket = new Socket("", 8020);
		} catch(java.net.ConnectException e){
			logger.error(e.getMessage());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testSecuritySocket(){
		
	}
}
