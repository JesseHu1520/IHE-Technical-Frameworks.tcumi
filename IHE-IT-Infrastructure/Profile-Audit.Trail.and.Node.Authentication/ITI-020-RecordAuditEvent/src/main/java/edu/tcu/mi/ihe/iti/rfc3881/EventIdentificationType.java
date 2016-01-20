package edu.tcu.mi.ihe.iti.rfc3881;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMNamespace;

import edu.tcu.mi.ihe.constants.atna.EventOutcomeIndicator;
import edu.tcu.mi.ihe.constants.atna.RFC3881;

@XmlType(name="EventIdentificationType")
@XmlAccessorType (XmlAccessType.FIELD)
public class EventIdentificationType extends RFC3881Message {

	@XmlElement(name="EventID")
	protected CodedValueType eventID; // [1 ... 1]
	
	@XmlElement(name="EventTypeCode")
	protected List<CodedValueType> eventTypeCode; // [0 ... *]
	
	@XmlAttribute(name="EventActionCode")
	protected String eventActionCode; // C R U D E
	@XmlAttribute(name="EventDateTime", required=true)
	protected String eventDateTime;
	@XmlAttribute(name="EventOutcomeIndicator", required=true)
	protected String eventOutcomeIndicator;
	
	
	public EventIdentificationType(){
		
	}
	
	public EventIdentificationType(EventOutcomeIndicator eventOutcomeIndicator){
		eventDateTime = formatDate(new java.util.Date());
		eventTypeCode = new ArrayList<CodedValueType>();
		if(eventOutcomeIndicator != null)
			this.eventOutcomeIndicator = eventOutcomeIndicator.getValue();
	}
	
	/* --------------------------------------------------------- */

	/**
	 * @param eventID the eventID to set
	 */
	public void setEventID(CodedValueType eventID) {
		this.eventID = eventID;
	}

	/**
	 * @param eventTypeCode the eventTypeCode to set
	 */
	public void setEventTypeCode(List<CodedValueType> eventTypeCode) {
		this.eventTypeCode = eventTypeCode;
	}

	
	/* --------------------------------------------------------- */
	
	@Override
	public OMElement buildRFC3881() {
    	OMNamespace namespace = null;
		OMElement root = axiom.createOMElement(RFC3881.EventIdentification.getTag(), namespace);
		if(eventActionCode != null)
			root.addAttribute("EventActionCode", eventActionCode, null);
		if(eventDateTime != null)
			root.addAttribute("EventDateTime", eventDateTime, null);
		if(eventOutcomeIndicator != null)	
			root.addAttribute("EventOutcomeIndicator", eventOutcomeIndicator + "", null);
		
		if(eventID != null){
			OMElement element = eventID.buildRFC3881();
			root.addChild(element);
		}
		if(eventTypeCode != null){
			Iterator<CodedValueType> iterator = eventTypeCode.iterator();
			while(iterator.hasNext()){
				CodedValueType next = iterator.next();
				OMElement element = next.buildRFC3881();
				root.addChild(element);
			}
		}
		return root;
	}
	
	/* --------------------------------------------------------- */
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
