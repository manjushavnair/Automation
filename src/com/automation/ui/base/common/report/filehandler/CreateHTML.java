package com.automation.ui.base.common.report.filehandler;

import com.automation.ui.base.common.report.datahandler.DataMap;
import org.apache.log4j.Logger;
import org.testng.ISuite;
import org.testng.ITestResult;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.StringWriter;
import com.automation.ui.base.common.utils.*;

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
            XML_PATH = FileNameConstants.ROOT_FOLDER + File.separator + FileNameConstants.XML_FILE_NAME + "-" + suiteIndex
                    + ".xml";


            String dashboardHtmlPath = FileNameConstants.ROOT_FOLDER + File.separator + FileNameConstants.DASHBOARD_HTML + "_"+DateUtil.getCurrentDateInReportFormat()+"-"
                    + suiteIndex + ".html";
            createHTML(dashboardHtmlPath);
        } else {
            // log error
        }
    }


    synchronized public static String createHTML(String htmlPath) {
        TransformerFactory tFactory = TransformerFactory.newInstance();
        Source xslDoc = new StreamSource(DASHBOARD_XSL_PATH);
        Source xmlDoc = new StreamSource(XML_PATH);
        StringWriter writer = new StringWriter();

        Transformer transformer = null;

        //	logger.info("DASHBOARD_XSL_PATH"+DASHBOARD_XSL_PATH);
        //	logger.info("DASHBOARD_XML_PATH"+XML_PATH);
        StreamResult wr = new StreamResult(writer);
    /*
		OutputStream htmlFile = null;
		try {
			htmlFile = new FileOutputStream(htmlPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		 StreamResult sr=new StreamResult(htmlFile);
		*/
        try {
            transformer = tFactory.newTransformer(xslDoc);
            //transformer.transform(xmlDoc, sr);
            transformer.transform(xmlDoc, wr);
        } catch (TransformerException te) {
            te.printStackTrace();
        }

        String result = writer.toString();

        //	logger.info( result);

	/*	try {
			FileWriter newFile = new FileWriter(htmlPath, true);
			BufferedWriter out = new BufferedWriter(newFile);


			out.write(result);
			out.newLine();
			out.flush();
			out.close();

		} catch ( Exception e) {
			e.printStackTrace();
		}

		logger.info("result"+result);
		*/
        return result;
    }


}
