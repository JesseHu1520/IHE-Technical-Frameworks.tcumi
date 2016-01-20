package edu.tcu.mi.ihe.utility;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.codec.binary.Base64;


public class Codec {
	
	@SuppressWarnings("resource")
	public String encodeBase64(File file) throws Exception {
		String str = null;
		int BUFFER_SIZE = (int) file.length();
		InputStream input = new FileInputStream(file);
		byte[] buffer = new byte[BUFFER_SIZE];
		input.read(buffer, 0, BUFFER_SIZE);
		str = encodeBase64(buffer);
		return str;
	}
	
	public String encodeBase64(byte[] bytes) {
		String str = null;
		str = new String(Base64.encodeBase64(bytes));
		return str;
	}
}
