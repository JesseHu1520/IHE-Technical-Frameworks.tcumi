package edu.tcu.mi.ihe.soap;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMNamespace;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import edu.tcu.mi.ihe.utility.AxiomUtil;
import junit.framework.TestCase;

public class SWATest extends TestCase {

	public static Logger logger = Logger.getLogger(SWATest.class);

	public SWATest(String testName) {
		super(testName);
	}

	protected void setUp() {
	}

	public void test01() {
		sendSOAP();
	}

	HttpURLConnection conn;

	public void sendSOAP() {
		HashMap<String, ArrayList<Object>> attachments = null;
		if (attachments == null)
			attachments = new HashMap<String, ArrayList<Object>>();
		ArrayList<Object> attachment = new ArrayList<Object>();

		try {
			ClassLoader loader = this.getClass().getClassLoader();
			InputStream is = loader.getResourceAsStream("0001k.xml");
			byte[] bytes = IOUtils.toByteArray(is);

			attachment.add(bytes);
			attachment.add("text/plain");
			attachments
					.put("9bd09c654202d481a8ad3fa2a90f6b3c81672f4d0b26197f@apache.org",
							attachment);

			String boundary = "MIMEBoundary_65d9a8eda47c4546d5b6e58c9d77eb510d3398f7cd3dd5f5";
			String start = "0.75d9a8eda47c4546d5b6e58c9d77eb510d3398f7cd3dd5f5@apache.org";

			// http://ihexds.nist.gov:12080/tf6/services/xdsrepositoryb?wsdl
			// http://localhost:8020/axis2/services/xdsrepositoryb?wsdl
			URL url = new URL(
					"http://203.64.84.214:8020/axis2/services/xdsrepositoryb?wsdl");
			conn = (HttpURLConnection) url.openConnection();

			conn.setDoOutput(true);
			conn.setUseCaches(true);
			conn.setRequestMethod("POST");

			AxiomUtil axiom = AxiomUtil.getInstance();
			OMNamespace wsa = ((AxiomUtil) axiom).createNamespace(
					"http://www.w3.org/2005/08/addressing", "wsa");
			OMNamespace soapenv = ((AxiomUtil) axiom).createNamespace(
					"http://www.w3.org/2003/05/soap-envelope", "soapenv");
			OMElement envelope = axiom.createOMElement("Envelope", null, null);
			envelope.setNamespace(soapenv);
			envelope.setNamespace(soapenv);
			{
				OMElement header = axiom.createOMElement("Header", null, null);
				header.setNamespace(wsa);
				header.setNamespace(soapenv);
				OMElement To = axiom.createOMElement("To", null, null);
				To.setNamespace(wsa);
				To.setText(url.toURI().toString());
				header.addChild(To);
				OMElement MessageID = axiom.createOMElement("MessageID", null, null);
				MessageID.setNamespace(wsa);
				MessageID.setText("urn:uuid:512d5ceb-03ec-4e8b-a44e-d8b9723257e9");
				header.addChild(MessageID);
				OMElement Action = axiom.createOMElement("Action", null, null);
				Action.setNamespace(wsa);
				Action.setText("urn:ihe:iti:2007:ProvideAndRegisterDocumentSet-b");
				header.addChild(Action);
				envelope.addChild(header);
				
				OMElement body = axiom.createOMElement("Body", null, null);
				body.setNamespace(soapenv);
				OMElement pnr = axiom.fromResources("1387554977578_iti_41_body.xml");
				body.addChild(pnr);
				envelope.addChild(body);
			}

			HashMap<String, String> headers = new HashMap<String, String>();
			if (attachments != null)
				headers.put(
						"Content-Type",
						"multipart/related; "
								+ "boundary=\""
								+ boundary
								+ "\"; "
								+ "type=\"application/xop+xml\"; "
								+ "start=\""
								+ start
								+ "\"; "
								+ "start-info=\"application/soap+xml\"; "
								+ "action=\"urn:ihe:iti:2007:ProvideAndRegisterDocumentSet-b\"");

			else
				headers.put("Content-Type", "application/xop+xml; charset=UTF-8; type=\"application/soap+xml\"");
			headers.put("User-Agent", "Axis2");
			headers.put("Transfer-Encoding", "chunked");

			StringBuffer msg = new StringBuffer();

			if (attachments != null) {
				msg.append("--" + boundary + "\r\n");
				msg.append("Content-Type: application/xop+xml; charset=UTF-8; type=\"application/soap+xml\"\r\n");
				msg.append("Content-ID: <" + start + ">\r\n");
				msg.append("Content-Transfer-Encoding: binary\r\n");
				msg.append("\r\n");
			}

			msg.append(envelope.toString() + " \r\n");

			if (attachments != null) {
				for (String id : attachments.keySet()) {
					ArrayList<Object> attachmentX = attachments.get(id);
					byte[] content = (byte[]) attachmentX.get(0);
					String mime_type = (String) attachmentX.get(1);

					msg.append("\n--" + boundary + "\r\n");
					msg.append("Content-Type: " + mime_type + "\r\n");
					msg.append("Content-ID: <" + id + ">\r\n");
					msg.append("Content-Transfer-Encoding: binary\r\n");
					msg.append("\r\n");
					msg.append(new String(content));
					msg.append("\r\n");
				}
				msg.append("--" + boundary + "--\r\n");
			}

			String reply_str = post(headers, msg.toString());
			logger.info(reply_str);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String post(HashMap<String, String> headers, String body)
			throws Exception {
		for (String name : headers.keySet()) {
			String value = headers.get(name);
			logger.info(value);
			conn.setRequestProperty(name, value);
		}
		logger.info("\r\n" + body);
		conn.setRequestProperty("Content-Length", String.valueOf(body.length()));
		conn.connect();
		OutputStream os = conn.getOutputStream();
		os.write(body.getBytes());
		os.close();

		InputStream is = conn.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line = "";
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
		return conn.getResponseMessage();
	}
}
