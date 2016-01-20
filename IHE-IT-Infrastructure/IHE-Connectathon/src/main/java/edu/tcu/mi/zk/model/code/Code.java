/**
 * 
 */
package edu.tcu.mi.zk.model.code;

/**
 * @author Gaduo
 *
 */
public class Code {

    private String code;
    private String codingScheme;
    private String display;
    
    /**
     * @param code
     * @param display
     */
    public Code(String code, String codingScheme, String display) {
        super();
        this.code = code;
        this.codingScheme = codingScheme;
        this.display = display;
    }
    public String getDisplay() {
        return display;
    }
    public void setDisplay(String display) {
        this.display = display;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getCodingScheme() {
        return codingScheme;
    }
    public void setCodingScheme(String codingScheme) {
        this.codingScheme = codingScheme;
    } 
}
