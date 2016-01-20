/**
 * 
 */
package edu.tcu.mi.zk.model.dicom;

import java.util.Collection;
import java.util.List;

import org.zkoss.zul.DefaultTreeNode;
import org.zkoss.zul.TreeNode;

/**
 * @author Gaduo
 * @param <E>
 *
 */
public class DICOMEntryTreeNode extends DefaultTreeNode<DICOMEntry> {

    private static final long serialVersionUID = 1L;

    public DICOMEntryTreeNode(DICOMEntry data) {
        super(data);
    }
    public DICOMEntryTreeNode(DICOMEntry data, Collection< ? extends TreeNode<DICOMEntry>> children) {
        super(data, children);
    }
    public DICOMEntryTreeNode(DICOMEntry data, Collection< ? extends TreeNode<DICOMEntry>> children, boolean nullAsMax) {
        super(data, children, nullAsMax);
    }
    
    public DICOMEntry getDicomEntry() {
        return super.getData();
    }
    
    public List<TreeNode<DICOMEntry>> getDicomEntryList() {
        return super.getChildren();
    }
}
