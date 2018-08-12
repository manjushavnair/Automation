package com.automation.ui.base.common.report.filehandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.automation.ui.base.pageobjectsfactory.pageobject.BasePageObject;
import org.apache.log4j.Logger;
import org.testng.ISuite;
import org.testng.ITestResult;

import com.automation.ui.base.common.report.datahandler.*;

public class CreateHTML extends CreateFiles {

	private static final String DASHBOARD_XSL_PATH = FileNameConstants.RESOURCE_FOLDER + File.separator
			+ FileNameConstants.XSL_FOLDER + File.separator + FileNameConstants.DASHBOARD_XSL;

	private static Logger logger = Logger.getLogger(CreateHTML.class);


	private static String XML_PATH = null;

	synchronized public static void createHtmlFiles(ITestResult tr) {
		ISuite iSuite = tr.getTestContext().getSuite();
		int suiteIndex = 0;
		if (DataMap.suiteMap.containsKey(iSuite)) {
			suiteIndex = DataMap.suiteMap.get(iSuite);
			XML_PATH = FileNameConstants.ROOT_FOLDER +File.separator + FileNameConstants.XML_FILE_NAME + "-" + suiteIndex
					+ ".xml";


			String dashboardHtmlPath = FileNameConstants.ROOT_FOLDER + File.separator+ FileNameConstants.DASHBOARD_HTML + "-"
					+ suiteIndex + ".html";
 			createHTML(dashboardHtmlPath);
		} else {
			// log error
		}
	}

	synchronized private static void createHTML(String htmlPath) {
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Source xslDoc = new StreamSource(DASHBOARD_XSL_PATH);
		Source xmlDoc = new StreamSource(XML_PATH);
		Transformer transformer = null;
		OutputStream htmlFile = null;
		try {
			htmlFile = new FileOutputStream(htmlPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			transformer = tFactory.newTransformer(xslDoc);
			transformer.transform(xmlDoc, new StreamResult(htmlFile));
		} catch (TransformerException te) {
			te.printStackTrace();
		}
	}
}
