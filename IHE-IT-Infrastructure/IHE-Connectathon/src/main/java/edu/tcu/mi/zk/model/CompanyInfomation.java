/**
 * 
 */
package edu.tcu.mi.zk.model;

/**
 * @author Gaduo
 * 
 */
public class CompanyInfomation {
	String name;
	String patitentId;
	String repositoryEndpoint;
	String repositoryUniqueId;
	String registryEndpoint;
	String KeyStore;
	String KeyPass;
	String TrustStore;
	String TrustPass;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name.trim();
	}

	public String getPatitentId() {
		return this.patitentId;
	}

	public void setPatitentId(String patitentId) {
		this.patitentId = patitentId.trim();
	}

	public String getRepositoryEndpoint() {
		return this.repositoryEndpoint;
	}

	public void setRepositoryEndpoint(String repositoryEndpoint) {
		this.repositoryEndpoint = repositoryEndpoint.trim();
	}

	public String getRepositoryUniqueId() {
		return this.repositoryUniqueId;
	}

	public void setRepositoryUniqueId(String repositoryUniqueId) {
		this.repositoryUniqueId = repositoryUniqueId.trim();
	}

	public String getRegistryEndpoint() {
		return this.registryEndpoint;
	}

	public void setRegistryEndpoint(String RegistryEndpoint) {
		this.registryEndpoint = RegistryEndpoint.trim();
	}

	public String getKeyStore() {
		return KeyStore;
	}

	public void setKeyStore(String keyStore) {
		KeyStore = keyStore.trim();
	}

	public String getKeyPass() {
		return KeyPass;
	}

	public void setKeyPass(String keyPass) {
		KeyPass = keyPass.trim();
	}

	public String getTrustStore() {
		return TrustStore;
	}

	public void setTrustStore(String trustStore) {
		TrustStore = trustStore.trim();
	}

	public String getTrustPass() {
		return TrustPass;
	}

	public void setTrustPass(String trustPass) {
		TrustPass = trustPass.trim();
	}

}
