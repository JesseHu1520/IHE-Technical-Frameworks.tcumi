package edu.tcu.mi.ihe.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509KeyManager;

public class MutualAuthenticationHTTP {
    private final boolean debug = true;

    public void doitAll(String urlString, SSLSocketFactory sslSocketFactory) throws IOException {
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();
        if (connection instanceof HttpsURLConnection) {
            ((HttpsURLConnection) connection).setSSLSocketFactory(sslSocketFactory);
        }
        int x;
        InputStream is = (InputStream) connection.getContent();
        while ((x = is.read()) != -1) {
            System.out.print(new String(new byte[] {(byte) x }));
        }
    }
 
    public SSLSocketFactory initItAll(KeyManager[] keyManagers, TrustManager[] trustManagers)
        throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext context = SSLContext.getInstance("SSLv3");
        context.init(keyManagers, trustManagers, null);
        SSLSocketFactory socketFactory = context.getSocketFactory();
        return socketFactory;
    }
 
    public KeyManager[] createKeyManagers(String keyStoreFileName, String keyStorePassword, String alias, String type)
        throws CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException {
        //create Inputstream to keystore file
    	
    	Class<MutualAuthenticationHTTP> clazz = MutualAuthenticationHTTP.class;
    	ClassLoader loader = clazz.getClassLoader();
    	InputStream is = loader.getResourceAsStream(keyStoreFileName);
        //create keystore object, load it with keystorefile data
        KeyStore keyStore = KeyStore.getInstance(type);
        keyStore.load(is, keyStorePassword == null ? null : keyStorePassword.toCharArray());
        //DEBUG information should be removed
        if (debug) {
            printKeystoreInfo(keyStore);
        }
 
        KeyManager[] managers;
        if (alias != null) {
            managers = new KeyManager[] {new MutualAuthenticationHTTP().new AliasKeyManager(keyStore, alias, keyStorePassword)};
        } else {
            //create keymanager factory and load the keystore object in it 
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(keyStore, keyStorePassword == null ? null : keyStorePassword.toCharArray());
            managers = keyManagerFactory.getKeyManagers();
        }
        //return 
        return managers;
    }
 
    public TrustManager[] createTrustManagers(String trustStoreFileName, String trustStorePassword, String type)
        throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
        //create Inputstream to truststore file
    	Class<MutualAuthenticationHTTP> clazz = MutualAuthenticationHTTP.class;
    	ClassLoader loader = clazz.getClassLoader();
    	InputStream is = loader.getResourceAsStream(trustStoreFileName);
        //create keystore object, load it with truststorefile data
        KeyStore trustStore = KeyStore.getInstance(type);
        trustStore.load(is, trustStorePassword == null ? null : trustStorePassword.toCharArray());
        //DEBUG information should be removed
        if (debug) {
            printKeystoreInfo(trustStore);
        }
        //create trustmanager factory and load the keystore object in it 
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(trustStore);
        //return 
        return trustManagerFactory.getTrustManagers();
    }
 
    private void printKeystoreInfo(KeyStore keystore) throws KeyStoreException {
    	System.out.println("****************************");
        System.out.println("Provider : " + keystore.getProvider().getName());
        System.out.println("Type : " + keystore.getType());
        System.out.println("Size : " + keystore.size());
 
        Enumeration<String> en = keystore.aliases();
        while (en.hasMoreElements()) {
            System.out.println("Alias: " + en.nextElement());
        }
    }
    private class AliasKeyManager implements X509KeyManager {
 
        private KeyStore _ks;
        private String _alias;
        private String _password;
 
        public AliasKeyManager(KeyStore ks, String alias, String password) {
            _ks = ks;
            _alias = alias;
            _password = password;
        }
 
        public String chooseClientAlias(String[] str, Principal[] principal, Socket socket) {
            return _alias;
        }
 
        public String chooseServerAlias(String str, Principal[] principal, Socket socket) {
            return _alias;
        }
 
        public X509Certificate[] getCertificateChain(String alias) {
            try {
                java.security.cert.Certificate[] certificates = this._ks.getCertificateChain(alias);
                if(certificates == null){throw new FileNotFoundException("no CertificateDetailsfound for alias:" + alias);}
                X509Certificate[] x509Certificates = new X509Certificate[certificates.length];
                System.arraycopy(certificates, 0, x509Certificates, 0, certificates.length);
                return x509Certificates;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
 
        public String[] getClientAliases(String str, Principal[] principal) {
            return new String[] { _alias };
        }
 
        public PrivateKey getPrivateKey(String alias) {
            try {
                return (PrivateKey) _ks.getKey(alias, _password == null ? null : _password.toCharArray());
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
 
        public String[] getServerAliases(String str, Principal[] principal) {
            return new String[] { _alias };
        }
 
    }
}
