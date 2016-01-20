/**
 * 
 */
package edu.tcu.mi.ihe.ws.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Gaduo
 *
 */
public class RetrieveResult {
	@Getter @Setter
    private String repositoryUniqueId;
	@Getter @Setter
    private String documentUniqueId;
	@Getter @Setter
    private String mimeType;
	@Getter @Setter
    private String document;
    
    public String toString() {
        return this.repositoryUniqueId + "\t" + this.documentUniqueId + "\t" +this.mimeType;
    }
    
}
