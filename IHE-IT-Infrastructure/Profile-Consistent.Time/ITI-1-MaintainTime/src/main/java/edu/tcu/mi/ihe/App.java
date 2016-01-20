package edu.tcu.mi.ihe;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import edu.tcu.mi.ihe.iti.service.NTPClient;

/**
 * Hello world!
 * 
 */
public class App {
	public static Logger logger = Logger.getLogger(App.class);

	public static void main(String[] args) {
		BasicConfigurator.configure();
		String host = (args.length >= 1) ? args[0] : "tock.stdtime.gov.tw"; //sake.irisa.fr
		Date date = null;
		logger.info("Host : " + host);
		if (!host.equals("")) {
			NTPClient nptc = new NTPClient();
			date = nptc.processResponse(host);
		}
		logger.info(date);
		// -------------
		if (date != null) {
			try {
				String name = System.getProperty("os.name");
				logger.info(name);
				String cmd = null;
				SimpleDateFormat sdf = null;
				if (name.contains("Windows")) { // Window 操作系统
					sdf = new SimpleDateFormat("hh:mm:ss");
					cmd = " cmd /c time " + sdf.format(date);
					logger.info(cmd);
					Runtime.getRuntime().exec(cmd); // 修改时间
					sdf = new SimpleDateFormat("yyyy-MM-dd");
					cmd = " cmd /c date " + sdf.format(date);
					logger.info(cmd);
					Runtime.getRuntime().exec(cmd); // 修改日期
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
