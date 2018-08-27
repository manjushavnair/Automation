package com.automation.ui.base.common.utils;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {

    private static Logger logger = Logger.getLogger(ExcelUtil.class);
    private Sheet datatypeSheet;


    /**
     * This method is to read the test data from the Excel cell, in this we are
     * // passing parameters as Row num and Col num
     */

    public void setExcelFile(String filePath, String sheetName)
            throws Exception {

        try {

            // Open the Excel file
            File f = new File(filePath);
            FileInputStream excelFile = new FileInputStream(filePath);

            // Access the required test data sheet
            Workbook workbook = null;
            if (filePath.endsWith("xlsx")) {
                workbook = new XSSFWorkbook(excelFile);
            } else if (filePath.endsWith("xls")) {
                workbook = new HSSFWorkbook(excelFile);
            } else {
                throw new IllegalArgumentException(
                        "The specified file is not Excel file");
            }


            this.datatypeSheet = workbook.getSheet(sheetName);

            //logger.info("Reading the excel file ::" + filePath + " and sheetname ::" + sheetName + "datatypeSheet" + datatypeSheet);

        } catch (Exception e) {
            e.printStackTrace();

            throw (e);

        }

    }

    public void setExcelSheetById(String filePath, int sheetNo)
            throws Exception {

        try {

            // Open the Excel file
            File f = new File(filePath);
            FileInputStream excelFile = new FileInputStream(filePath);

            // Access the required test data sheet
            Workbook workbook = null;
            HSSFSheet mySheet = null;
            if (filePath.endsWith("xlsx")) {
                workbook = new XSSFWorkbook(excelFile);
            } else if (filePath.endsWith("xls")) {
                workbook = new HSSFWorkbook(excelFile);
                /** Get the first sheet from workbook**/
                mySheet = (HSSFSheet) workbook.getSheetAt(sheetNo);
            } else {
                throw new IllegalArgumentException(
                        "The specified file is not Excel file");
            }


            this.datatypeSheet = mySheet;

            logger.info("Reading the excel file ::" + filePath + " and sheetNo ::" + sheetNo + "datatypeSheet" + datatypeSheet);

        } catch (Exception e) {
            e.printStackTrace();

            throw (e);

        }

    }

    public List<String> getNumberOfSheetsinSuite(String filePath) {
        List<String> listOfSheets = new ArrayList<String>();

        try {
            Workbook workbook = WorkbookFactory
                    .create(new File(filePath));
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {

            }

        } catch (InvalidFormatException | IOException e) {

            logger.info(e.getMessage());
        }
        return listOfSheets;

    }


    public String getCellData(int rowNum, int colNum) throws Exception {

        String cellData = "";
        try {


            if (null != this.datatypeSheet.getRow(rowNum).getCell(colNum))
                cellData = this.datatypeSheet.getRow(rowNum).getCell(colNum).getStringCellValue();

            return cellData;

        } catch (Exception e) {

            logger.info("InvalidFormatException");
        }
        return cellData;

    }

}