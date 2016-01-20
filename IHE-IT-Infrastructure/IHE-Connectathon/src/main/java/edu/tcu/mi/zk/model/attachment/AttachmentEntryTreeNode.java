/**
 * 
 */
package edu.tcu.mi.zk.model.attachment;

import java.util.Collection;
import java.util.List;

import org.zkoss.zul.DefaultTreeNode;
import org.zkoss.zul.TreeNode;

/**
 * @author Gaduo
 * @param <E>
 *
 */
public class AttachmentEntryTreeNode<E> extends DefaultTreeNode<AttachmentEntry> {

    private static final long serialVersionUID = 1L;

    public AttachmentEntryTreeNode(AttachmentEntry data) {
        super(data);
    }
    public AttachmentEntryTreeNode(AttachmentEntry data, Collection< ? extends TreeNode<AttachmentEntry>> children) {
        super(data, children);
    }
    public AttachmentEntryTreeNode(AttachmentEntry data, Collection< ? extends TreeNode<AttachmentEntry>> children, boolean nullAsMax) {
        super(data, children, nullAsMax);
    }
    
    public AttachmentEntry getAttachmentEntry() {
        return super.getData();
    }
    
    public List<TreeNode<AttachmentEntry>> getAttachmentEntryList() {
        return super.getChildren();
    }

}
