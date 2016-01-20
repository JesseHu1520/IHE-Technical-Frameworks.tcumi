package edu.tcu.mi.ihe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.TimeZone;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import org.apache.log4j.Logger;
import org.junit.Test;

import edu.tcu.mi.ihe.security.CertificateDetails;

/**
 * Unit test for simple App.
 */
public class SSLAppTest {

	public static Logger logger = Logger.getLogger(SSLAppTest.class);
	
	@Test
	public void testSSLSocket(){
        try{
        	/**       
        	 *  <KeyStore>certs/serverKeyStore</KeyStore>
        	 *  <KeyPass>password</KeyPass>
        	 *  <TrustStore>certs/clientKeyStore</TrustStore>
        	 *  <TrustPass>password</TrustPass>
        	 *  */
        	
        	CertificateDetails certDetails = CertificateDetails.getInstance();
			certDetails.setCertificate("openatna_2011/clientKeyStore", "password", "openatna_2011/clientKeyStore", "password");
			String keystoreType = "JKS";
		    String algType = "SunX509";
		    KeyManager[] keymanagers = null;
	        {
	    	    KeyStore keystore = KeyStore.getInstance(keystoreType);
	    	    InputStream is = certDetails.getKeyStoreLocation();
	            String password = certDetails.getKeyPass();
	            keystore.load(is, password != null ? password.toCharArray() : null);
	        	Enumeration aliases = keystore.aliases();
	            while (aliases.hasMoreElements()) {
	                String alias = (String) aliases.nextElement();
	                Certificate[] certs = keystore.getCertificateChain(alias);
	                if (certs != null) {
	                	logger.info("CertificateDetailschain '" + alias + "':");
	                    for (int c = 0; c < certs.length; c++) {
	                        if (certs[c] instanceof X509Certificate) {
	                            X509Certificate cert = (X509Certificate) certs[c];
	                            logger.info(" CertificateDetails" + (c + 1) + ":");
	                            logger.info("  Subject DN: " + cert.getSubjectDN());
	                            logger.info("  Signature Algorithm: " + cert.getSigAlgName());
	                            logger.info("  Valid from: " + cert.getNotBefore());
	                            logger.info("  Valid until: " + cert.getNotAfter());
	                            logger.info("  Issuer: " + cert.getIssuerDN());
	                        }
	                    }
	                }
	            }
	            KeyManagerFactory kmfactory = KeyManagerFactory.getInstance(algType);
	            String password02 = certDetails.getKeyPass();
	            kmfactory.init(keystore, (password02 == null || password02.length() == 0) ? certDetails.getKeyPass().toCharArray() : password02.toCharArray());
	            keymanagers = kmfactory.getKeyManagers();
	        }
	        TrustManager[] trustmanagers = null;
	        {
	
	    	    KeyStore keystore = KeyStore.getInstance(keystoreType);
	    	    InputStream is = certDetails.getTrustStoreLocation();
	            String password = certDetails.getTrustPass();
	            keystore.load(is, password != null ? password.toCharArray() : null);
		        Enumeration aliases = keystore.aliases();
		        while (aliases.hasMoreElements()) {
		            String alias = (String) aliases.nextElement();
		            logger.info("Trusted CertificateDetails'" + alias + "':");
		            Certificate trustedcert = keystore.getCertificate(alias);
		            if (trustedcert != null && trustedcert instanceof X509Certificate) {
		                X509Certificate cert = (X509Certificate) trustedcert;
		                logger.info("  Subject DN: " + cert.getSubjectDN());
		                logger.info("  Signature Algorithm: " + cert.getSigAlgName());
		                logger.info("  Valid from: " + cert.getNotBefore());
		                logger.info("  Valid until: " + cert.getNotAfter());
		                logger.info("  Issuer: " + cert.getIssuerDN());
		            }
		        }
	
		        TrustManagerFactory tmfactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());//TrustManagerFactory.getInstance(algorithm);
		        tmfactory.init(keystore);
		        final TrustManager[] _trustmanagers = tmfactory.getTrustManagers();
		        for (int i = 0; i < _trustmanagers.length; i++) {
		            if (_trustmanagers[i] instanceof X509TrustManager) {
		            	final X509TrustManager defaultTrustManager = loadDefaultTrustManager();
						List<String> authorizedDNs = new ArrayList<String>();
//						AuthSSLX509TrustManager authSSLX509TrustManager = new AuthSSLX509TrustManager((X509TrustManager) _trustmanagers[i], defaultTrustManager, authorizedDNs);
//						trustmanagers = new TrustManager[]{authSSLX509TrustManager};
		            }
		        }
	        }

	    	
	    	HostnameVerifier hv = new HostnameVerifier() {  
	            public boolean verify(String urlHostName, SSLSession session) {  
	                System.out.println("Warning: URL Host: " + urlHostName + " vs. " + session.getPeerHost());  
	                return true;  
	            }  
	        };  
	        trustAllHttpsCertificates();
	        HttpsURLConnection.setDefaultHostnameVerifier(hv);  
	        
	        
	        SSLContext sslcontext = SSLContext.getInstance("SSL");
			sslcontext.init(keymanagers, trustmanagers, null);
			
			SSLSocketFactory socketFactory = sslcontext.getSocketFactory();
			Socket socket = socketFactory.createSocket("203.64.84.247", 2862);
	        OutputStream out = socket.getOutputStream();
	        
	        
	
			Class<? extends SSLAppTest> clazz = this.getClass();
			ClassLoader loader = clazz.getClassLoader();
			InputStream resource = loader.getResourceAsStream("openxds.xml");
			BufferedReader brResource = new BufferedReader(new InputStreamReader(resource));
			String temp = "", str = "";
			while( (temp = brResource.readLine()) != null){
				str += temp;
			}
			brResource.close();
			
			InetAddress localMachine = InetAddress.getLocalHost();
	        String version = "1";
	        String timestamp = formatDate(new java.util.Date()); //"2014-02-10T00:53:09.624-06:00";
	        String host = localMachine.getHostAddress();
	        String app = "GaduoSyslogSender";
	        String proc = "183";
	        String messageId = "IHE+RFC-3881";
	        
	        str = "<" + (85) + ">" + version + " " + timestamp + " " + host + " " + app + " " + proc + " " + messageId + " - " + str;
			str = str.replace("${timeStamp}", timestamp);
			System.out.println(str);
			byte[] bytes = str.getBytes("UTF-8");
	        
	        for (int i = 0; i < 5; i++) {
	            // add message length plus space before message
	            out.write((String.valueOf(bytes.length) + " ").getBytes("UTF-8"));
	            out.write(bytes);
	            out.flush();
	        }
	        out.close();
	        socket.close();
        } catch(KeyManagementException e){
			logger.error(e.getMessage());
        } catch (NoSuchAlgorithmException e) {
        	logger.error(e.getMessage());
		} catch (CertificateException e) {
			logger.error(e.getMessage());
		} catch (UnrecoverableKeyException e) {
			logger.error(e.getMessage());
		} catch (KeyStoreException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
        
//		SSLSocketFactory f =  (SSLSocketFactory) SSLSocketFactory.getDefault();
//		SSLSocket socket = (SSLSocket) f.createSocket("203.64.84.247", 2862);
//		printSocketInfo(socket);
//		socket.startHandshake();
//		Certificate[] serverCerts = socket.getSession().getPeerCertificates();
//		System.out.println("Retreived Server's CertificateDetailsChain");
//		 
//		System.out.println(serverCerts.length + "Certifcates Found\n\n\n");
//		for (int i = 0; i < serverCerts.length; i++) {
//			CertificateDetailsmyCert = serverCerts[i];
//			System.out.println("====Certificate:" + (i+1) + "====");
//			System.out.println("-Public Key-\n" + myCert.getPublicKey());
//			System.out.println("-CertificateDetailsType-\n " + myCert.getType());
//			System.out.println();
//		}
//		OutputStream os = socket.getOutputStream();
//		PrintWriter pw =new PrintWriter(os);
//		pw.print(bytes);
//		pw.flush();
//		InputStream is = socket.getInputStream();
//		BufferedReader br = new BufferedReader(new InputStreamReader(is));
//		String line;
//		while((line = br.readLine()) != null){
//			System.out.println(line);
//		}
//		pw.close();
//		br.close();
//		socket.close();
	}

	
	private void printSocketInfo(SSLSocket s) {
		System.out.println("   Socket class: " + s.getClass());
		System.out.println("   Remote address = " + s.getInetAddress().toString());
		System.out.println("   Remote port = " + s.getPort());
		System.out.println("   Local socket address = " + s.getLocalSocketAddress().toString());
		System.out.println("   Local address = " + s.getLocalAddress().toString());
		System.out.println("   Local port = " + s.getLocalPort());
		System.out.println("   Need client authentication = " + s.getNeedClientAuth());
		SSLSession ss = s.getSession();
		System.out.println("   Cipher suite = " + ss.getCipherSuite());
		System.out.println("   Protocol = " + ss.getProtocol());
	}
	
	private void trustAllHttpsCertificates() throws Exception {  
        javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];  
        javax.net.ssl.TrustManager tm = new miTM();  
        trustAllCerts[0] = tm;  
        javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext.getInstance("SSL");  
        sc.init(null, trustAllCerts, null);  
        javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());  
    }  

	class miTM implements javax.net.ssl.TrustManager, javax.net.ssl.X509TrustManager {
		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		public boolean isServerTrusted(java.security.cert.X509Certificate[] certs) {
			return true;
		}

		public boolean isClientTrusted(java.security.cert.X509Certificate[] certs) {
			return true;
		}

		public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) throws java.security.cert.CertificateException {
			return;
		}

		public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) throws java.security.cert.CertificateException {
			return;
		}
	}
	
	
	private X509TrustManager loadDefaultTrustManager() {
		try {
			File certs;
			String definedcerts = System
					.getProperty("javax.net.ssl.trustStore");
			String pass = System
					.getProperty("javax.net.ssl.trustStorePassword");
			if (definedcerts != null) {
				certs = new File(definedcerts);
			} else {
				String common = System.getProperty("java.home")
						+ File.separator + "lib" + File.separator + "security"
						+ File.separator;
				String cacerts = common + "cacerts";
				String jssecacerts = common + "jssecacerts";
				certs = new File(jssecacerts);
				if (!certs.exists() || certs.length() == 0) {
					certs = new File(cacerts);
				}

			}
			if (pass == null) {
				pass = "changeit";
			}
			X509TrustManager sunTrustManager;
			if (certs != null) {
				KeyStore ks = KeyStore.getInstance("jks");
				ks.load(new FileInputStream(certs), pass.toCharArray());
				TrustManagerFactory tmf = TrustManagerFactory.getInstance(
						"SunX509", "SunJSSE");
				tmf.init(ks);
				TrustManager tms[] = tmf.getTrustManagers();
				for (int i = 0; i < tms.length; i++) {
					if (tms[i] instanceof X509TrustManager) {
						logger.info(" found default trust manager.");
						sunTrustManager = (X509TrustManager) tms[i];
						return sunTrustManager;
					}
				}
			}

		} catch (KeyStoreException e) {
			logger.info("Exception thrown trying to create default trust manager:"
					+ e.getMessage());
		} catch (NoSuchAlgorithmException e) {
			logger.info("Exception thrown trying to create default trust manager:"
					+ e.getMessage());
		} catch (CertificateException e) {
			logger.info("Exception thrown trying to create default trust manager:"
					+ e.getMessage());
		} catch (NoSuchProviderException e) {
			logger.info("Exception thrown trying to create default trust manager:"
					+ e.getMessage());
		} catch (FileNotFoundException e) {
			logger.info("Exception thrown trying to create default trust manager:"
					+ e.getMessage());
		} catch (IOException e) {
			logger.info("Exception thrown trying to create default trust manager:"
					+ e.getMessage());
		}
		return null;
	}
	
	public String formatDate(Date date) {
        StringBuilder sb = new StringBuilder();
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        c.setTime(date);
        sb.append(c.get(Calendar.YEAR));
        sb.append('-');
        int f = c.get(Calendar.MONTH);
        if (f < 9) {
            sb.append('0');
        }
        sb.append(f + 1);
        sb.append('-');
        f = c.get(Calendar.DATE);
        if (f < 10) {
            sb.append('0');
        }
        sb.append(f);
        sb.append('T');
        f = c.get(Calendar.HOUR_OF_DAY);
        if (f < 10) {
            sb.append('0');
        }
        sb.append(f);
        sb.append(':');
        f = c.get(Calendar.MINUTE);
        if (f < 10) {
            sb.append('0');
        }
        sb.append(f);
        sb.append(':');
        f = c.get(Calendar.SECOND);
        if (f < 10) {
            sb.append('0');
        }
        sb.append(f);
        sb.append('.');
        f = c.get(Calendar.MILLISECOND);
        if (f < 100) {
            sb.append('0');
        }
        if (f < 10) {
            sb.append('0');
        }
        sb.append(f);
        sb.append('Z');
        return sb.toString();
    }
}
