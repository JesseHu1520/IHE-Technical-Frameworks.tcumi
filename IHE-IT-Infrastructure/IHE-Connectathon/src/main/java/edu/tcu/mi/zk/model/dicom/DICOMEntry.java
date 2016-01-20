/**
 * 
 */
package edu.tcu.mi.zk.model.dicom;

/**
 * @author Gaduo
 */
public class DICOMEntry {
    private String type;
    private String id;

    public DICOMEntry(String type, String id) {
        this.type = type;
        this.id = id;
    }

    public String toString() {
        return type + "\t" +id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
