package edu.tcu.mi.ihe.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Iterator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.om.impl.builder.StAXOMBuilder;
import org.apache.commons.io.IOUtils;

import edu.tcu.mi.ihe.constants.EbXML;
import edu.tcu.mi.ihe.constants.Namespace;

public class AxiomUtil {
	private static AxiomUtil instance = null;

	private AxiomUtil() {

	}

	public synchronized static AxiomUtil getInstance() {
		if (instance == null) {
			instance = new AxiomUtil();
		}
		return instance;
	}

	@SuppressWarnings("unchecked")
	public Iterator<OMElement> getChildrenWithName(OMElement request, Namespace namespace, String tag) {
		Iterator<OMElement> element = null;
		QName qname = null;
		if (namespace != null) {
			qname = new QName(namespace.getNamespace(), tag, namespace.getPrefix());
			element = request.getChildrenWithName(qname);
			if (element == null) {
				qname = new QName(namespace.getNamespace(), tag);
				element = request.getChildrenWithName(qname);
			}
		}
		if (element == null) {
			qname = new QName(tag);
			element = request.getChildrenWithName(qname);
		}
		return element;
	}

	public OMElement getFirstChildWithName(OMElement request, Namespace namespace, String tag) {
		OMElement element = null;
		QName qname = null;
		if (namespace != null) {
			qname = new QName(namespace.getNamespace(), tag, namespace.getPrefix());
			element = request.getFirstChildWithName(qname);
			if (element == null) {
				qname = new QName(namespace.getNamespace(), tag);
				element = request.getFirstChildWithName(qname);
			}
		}
		if (element == null) {
			qname = new QName(tag);
			element = request.getFirstChildWithName(qname);
		}
		return element;
	}

	public OMElement createOMElement(EbXML ebxml, Namespace namespace) {
		OMElement element = null;
		OMFactory factory = OMAbstractFactory.getOMFactory();
		OMNamespace omNamespace = null;
		if (namespace != null) {
			omNamespace = factory.createOMNamespace(namespace.getNamespace(), namespace.getPrefix());
		}
		if (omNamespace != null)
			element = createOMElement(ebxml.getTag(), omNamespace);
		return element;
	}

	public OMElement createOMElement(String tag, OMNamespace namespace) {
		OMElement element;
		OMFactory factory = OMAbstractFactory.getOMFactory();
		if (namespace != null) {
			element = factory.createOMElement(tag, namespace);
		} else {
			element = factory.createOMElement(new QName(tag));
		}
		return element;
	}

	public OMElement createOMElement(String tag, String namespace, String prefix) {
		OMElement element;
		OMFactory factory = OMAbstractFactory.getOMFactory();
		OMNamespace Namespace = null;
		if (namespace != null) {
			Namespace = factory.createOMNamespace(namespace, prefix);
			element = factory.createOMElement(tag, Namespace);
		} else {
			element = factory.createOMElement(tag, null);
		}
		return element;
	}

	public OMNamespace createNamespace(String namespace, String prefix) {
		OMFactory factory = OMAbstractFactory.getOMFactory();
		OMNamespace Namespace = factory.createOMNamespace(namespace, prefix);
		return Namespace;
	}

	public OMElement createOMElement(String tag, String value) {
		OMFactory factory = OMAbstractFactory.getOMFactory();
		OMElement element = factory.createOMElement(tag, null);
		if (value != null) {
			element.setText(value);
		}
		return element;
	}

	public String getValueOfType(String type, OMElement request) {
		String value;
		QName qname = new QName(type);
		OMElement e = request.getFirstChildWithName(qname);
		value = (e != null) ? e.getText().trim() : null;
		return value;
	}

	public OMElement fromResources(String resources) {
		Class<AxiomUtil> clazz = AxiomUtil.class;
		ClassLoader loader = clazz.getClassLoader();
		InputStream is = loader.getResourceAsStream(resources);
		return fromResources(is);
	}

	public OMElement fromResources(InputStream is) {
		OMElement element = null;
		try {
			element = new StAXOMBuilder(is).getDocumentElement();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		return element;
	}

	public OMElement fromString(String entity) {
		InputStream is = IOUtils.toInputStream(entity);
		return fromResources(is);
	}

	public <T> T fromXML(OMElement entity, Class<T> classOfT) {
		return fromXML(entity.toString(), classOfT);
	}

	public <T> T fromXML(String entity, Class<T> classOfT) {
		try {
			InputStream is = IOUtils.toInputStream(entity);
			JAXBContext jaxbContext = JAXBContext.newInstance(classOfT);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			return (T) jaxbUnmarshaller.unmarshal(is);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	public File resourcesToFile(String resources) {
		Class<AxiomUtil> clazz = AxiomUtil.class;
		ClassLoader loader = clazz.getClassLoader();
		URL resource = loader.getResource(resources);
		File file = null;
		try {
			file = new File(resource.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return file;
	}

	public byte[] resourcesToByteArray(String resources) {
		File file = resourcesToFile(resources);
		try {
			InputStream is = new FileInputStream(file);

			long length = file.length();
			if (length > Integer.MAX_VALUE) {
				// File is too large
			}
			byte[] bytes = new byte[(int) length];

			int offset = 0;
			int numRead = 0;
			while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
				offset += numRead;
			}
			is.close();
			return bytes;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
