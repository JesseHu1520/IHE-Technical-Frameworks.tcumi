package edu.tcu.mi.ihe.utility.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLPath {
	DocumentBuilder builder;
	Document document;
	XPath xpath;

	public static Logger logger = Logger.getLogger(XMLPath.class);

	public XMLPath(File file) {
		super();
		try {
			xpath = XPathFactory.newInstance().newXPath();
			InputStream is = new FileInputStream(file);
			build(is);
		} catch (FileNotFoundException e1) {
		} catch (ParserConfigurationException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} catch (SAXException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
	}

	public XMLPath(InputStream is) {
		super();
		try {
			xpath = XPathFactory.newInstance().newXPath();
			build(is);
		} catch (ParserConfigurationException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} catch (SAXException e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
	}

	public void build(InputStream is) throws ParserConfigurationException,
			SAXException, IOException {
		builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		document = builder.parse(is);
	}

	public Node QueryNode(String expression) {
		Node node = null;
		try {
			synchronized (XMLPath.class) {
				node = (Node) xpath.evaluate(expression, document, XPathConstants.NODE);
			}
		} catch (XPathExpressionException e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		return node;
	}

	public NodeList QueryNodeList(String expression) {
		NodeList nodeList = null;
		try {
			synchronized (XMLPath.class) {
				nodeList = (NodeList) xpath.evaluate(expression, document, XPathConstants.NODESET);
			}
		} catch (XPathExpressionException e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		return nodeList;
	}
	/* http://mi.hosp.ncku.edu.tw/km/index.php/dotnet/48-netdisk/57-xml-xpath* */
}
