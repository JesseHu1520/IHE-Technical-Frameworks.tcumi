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
public class ResponseCode {

	@Getter @Setter
    private String code;
	@Getter @Setter
    private String codingScheme;
	@Getter @Setter
    private String display;
    
    /**
     * @param code
     * @param display
     */
    public ResponseCode(String code, String codingScheme, String display) {
        super();
        this.code = code;
        this.codingScheme = codingScheme;
        this.display = display;
    }
    
    public boolean compare(ResponseCode arg0, ResponseCode arg1) {
        if(arg0.getCode().compareTo(arg1.getCode()) == 0)
            if (arg0.getCodingScheme().compareTo(arg1.getCodingScheme()) == 0)
                return true;
        return false;
    }
    
    @Override
    public boolean equals(Object target) {
        if(this.getCode().compareTo(((ResponseCode)target).getCode()) == 0)
            if (this.getCodingScheme().compareTo(((ResponseCode)target).getCodingScheme()) == 0)
                return true;
        return false;
    }
    
    @Override
    public int hashCode() {
        return code.hashCode() * codingScheme.hashCode() * display.hashCode();
    }
}
