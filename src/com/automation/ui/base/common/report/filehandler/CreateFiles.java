package com.automation.ui.base.common.report.filehandler;

import com.automation.ui.base.common.core.exceptions.TestEnvInitFailedException;
import com.automation.ui.base.common.report.datahandler.DataMap;
import com.automation.ui.base.common.report.datahandler.DataPreparator;
import com.automation.ui.base.common.report.datahandler.DataSuite;
import com.google.common.io.Files;
import org.apache.log4j.Logger;
import org.testng.ISuite;
import com.automation.ui.base.common.utils.*;


import java.io.*;

public class CreateFiles {

    private static Logger logger = Logger
            .getLogger(CreateFiles.class);

    synchronized public static void createRequiredFolders(ISuite iSuite) {
        /*
		 * STEP 1: If RootResult folder, i.e - 'RealtimeReport', does not exist,
		 * create it
		 */
        try {
            if (!new File(FileNameConstants.ROOT_FOLDER).exists()) {
                new File(FileNameConstants.ROOT_FOLDER).mkdir();
            }
        } catch (Throwable e) {
            logger.info(e.getMessage());
            throw new TestEnvInitFailedException("Report specifc root folder is missing");
        }
		/*
		 * STEP 2: create css, fonts, image, js folders under RootResult folder,
		 * i.e - 'RealtimeReport'
		 */
        //logger.info("FileNameConstants.ROOT_FOLDER"+FileNameConstants.ROOT_FOLDER);
        //logger.info("FileNameConstants.CSS_FOLDER"+FileNameConstants.CSS_FOLDER );

        createFolderUnder(FileNameConstants.CSS_FOLDER, FileNameConstants.ROOT_FOLDER);
        createFolderUnder(FileNameConstants.FONT_FOLDER, FileNameConstants.ROOT_FOLDER);
        createFolderUnder(FileNameConstants.IMAGE_FOLDER, FileNameConstants.ROOT_FOLDER);
        createFolderUnder(FileNameConstants.JS_FOLDER, FileNameConstants.ROOT_FOLDER);

		/*
		 * STEP 3: get all the resource files from 'html-rsc' folder
		 */
        File[] css_files = getFilesUnder(FileNameConstants.RESOURCE_FOLDER + "/" + FileNameConstants.CSS_FOLDER);
        File[] font_files = getFilesUnder(FileNameConstants.RESOURCE_FOLDER + "/" + FileNameConstants.FONT_FOLDER);
        File[] img_files = getFilesUnder(FileNameConstants.RESOURCE_FOLDER + "/" + FileNameConstants.IMAGE_FOLDER);
        File[] js_files = getFilesUnder(FileNameConstants.RESOURCE_FOLDER + "/" + FileNameConstants.JS_FOLDER);

		/*
		 * STEP 4: put all the resource files to the respective folders under
		 * RootResult folder, i.e - 'RealtimeReport' which is already created at
		 * step 2.
		 */
        copyFilesToDestination(css_files, FileNameConstants.ROOT_FOLDER + "/" + FileNameConstants.CSS_FOLDER);
        copyFilesToDestination(font_files, FileNameConstants.ROOT_FOLDER + "/" + FileNameConstants.FONT_FOLDER);
        copyFilesToDestination(img_files, FileNameConstants.ROOT_FOLDER + "/" + FileNameConstants.IMAGE_FOLDER);
        copyFilesToDestination(js_files, FileNameConstants.ROOT_FOLDER + "/" + FileNameConstants.JS_FOLDER);

		/*
		Find all files in report  Folder
		*/
        File f=new File("./" + FileNameConstants.ROOT_FOLDER);


        File[] files = f.listFiles(new FilenameFilter(){
           @Override
           public boolean accept(File directory, String fileName) {
             if (fileName.matches("[log0-9_-]+\\.html")) {
                  return true;
              }
                return false;
            }

       } );





        /*
		 * STEP 5: Put values related to current suite at a set, 'suiteSet'
		 */
        if (DataMap.suiteMap.containsKey(iSuite)) {
            int suiteIndex = DataMap.suiteMap.get(iSuite);
            String suiteName = DataPreparator.prepareSuiteName(iSuite);

           // Create the file link in the index.html
            DataSuite ds = new DataSuite(suiteIndex, suiteName,
                    FileNameConstants.DASHBOARD_HTML + "_"+DateUtil.getCurrentDateInReportFormat()+ "-" + suiteIndex + ".html");
            DataMap.suiteSet.add(ds);
            for(File f1 : files) {
                  ds = new DataSuite(suiteIndex, suiteName, f1.getName());
                     DataMap.suiteSet.add(ds);
            }



        }

		/*
		 * STEP 6: Finally create index.html page
		 */
        createIndexPage();

    }

    synchronized private static void copyFilesToDestination(File[] files, String parentFolderPath) {
        if (files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                try {
                    Files.copy(files[i], new File("." + File.separator + parentFolderPath + File.separator + files[i].getName()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    synchronized private static void createFolderUnder(String newFolderName, String parentFolderPath) {
        if (!new File("." + File.separator + parentFolderPath + File.separator + newFolderName).exists()) {
            new File("." + File.separator + parentFolderPath + File.separator + newFolderName).mkdir();
        }
    }

    synchronized private static File[] getFilesUnder(String parentDirectoryPath) {
        File[] files = null;
        try {
            if (new File("./" + parentDirectoryPath).exists()) {
                files = new File("./" + parentDirectoryPath).listFiles();
            }
        } catch (Throwable e) {
            logger.info(e.getMessage());
            throw new TestEnvInitFailedException("Report specifc resources directory are missing");

        }
        return files;
    }

    synchronized private static void createIndexPage() {
        if (new File("./" + FileNameConstants.ROOT_FOLDER + "/" + FileNameConstants.INDEX_HTML).exists()) {
            new File("./" + FileNameConstants.ROOT_FOLDER + "/" + FileNameConstants.INDEX_HTML).delete();
        }
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileOutputStream(
                    new File("./" + FileNameConstants.ROOT_FOLDER + "/" + FileNameConstants.INDEX_HTML), false));
        } catch (FileNotFoundException e) {
        }
        if (pw != null) {
            pw.write(FileNameConstants.INDEX_HEADER);
            pw.write(FileNameConstants.INDEX_BODY_PRE);

            for (DataSuite ds : DataMap.suiteSet) {

                pw.write("<a class='btn btn-link' href='" + ds.getSuiteHTMLPath()
                        + "' style='font-size:24px;'><i class='fa fa-dashboard'></i> " + ds.getSuiteName() + " Report " +ds.getSuiteHTMLPath()
                        + "</a><br/>");
            }
            pw.write(FileNameConstants.INDEX_BODY_POST);
            pw.flush();
            pw.close();
        }
    }
}
