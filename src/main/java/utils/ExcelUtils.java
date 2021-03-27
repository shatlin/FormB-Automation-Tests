package utils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.SystemOutLogger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ExcelUtils {

    public void CreateAndWriteToExcel(String filename, String sheetname, Map<String, Object[]> data) {
        try {

            //Blank workbook
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet(sheetname);


            //Iterate over data and write to sheet
            Set<String> keyset = data.keySet();
            int rownum = 0;
            for (String key : keyset) {
                Row row = sheet.createRow(rownum++);
                Object[] objArr = data.get(key);
                int cellnum = 0;
                for (Object obj : objArr) {
                    Cell cell = row.createCell(cellnum++);
                    if (obj instanceof String)
                        cell.setCellValue((String) obj);
                    else if (obj instanceof Integer)
                        cell.setCellValue((Integer) obj);
                }
            }

            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File(filename));
            workbook.write(out);
            out.close();
            System.out.println("File Created and written successfully on disk.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void AddDataToExistingExcel(String filename, String sheetname, Map<String, Object[]> data) {
        try {

            //Blank workbook

            FileInputStream file = new FileInputStream(new File(filename));

            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet(sheetname);

            //Iterate over data and write to sheet
            Set<String> keyset = data.keySet();
            int rownum = sheet.getLastRowNum();
            for (String key : keyset) {
                Row row = sheet.createRow(rownum++);
                Object[] objArr = data.get(key);
                int cellnum = 0;
                for (Object obj : objArr) {
                    Cell cell = row.createCell(cellnum++);
                    if (obj instanceof String)
                        cell.setCellValue((String) obj);
                    else if (obj instanceof Integer)
                        cell.setCellValue((Integer) obj);
                }
            }

            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File(filename));
            workbook.write(out);
            out.close();
            System.out.println("Data written on disk.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public String GetCellValue(String filename, String sheetname,int rownum, int cellnum)
    {
        try {
            FileInputStream file = new FileInputStream(new File(filename));

            XSSFWorkbook workbook = new XSSFWorkbook(file);

            XSSFSheet sheet = workbook.getSheet(sheetname);
            Cell cell2Update = sheet.getRow(rownum).getCell(cellnum);
            return cell2Update.getStringCellValue();
        }
        catch (Exception e)
        {

        }

        return null;

    }



    public void SetCellValue(String filename, String sheetname,int rownum, int cellnum,String value)
    {
        try {
            FileInputStream file = new FileInputStream(new File(filename));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet(sheetname);
            Cell cell2Update = sheet.getRow(rownum).getCell(cellnum);
            cell2Update.setCellValue(value);
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File(filename));
            workbook.write(out);
            out.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }



    public Object[][] getExcelData(String filename, String sheetname) {
        try {
            FileInputStream file = new FileInputStream(new File(filename));

            XSSFWorkbook workbook = new XSSFWorkbook(file);


            XSSFSheet sheet = workbook.getSheet(sheetname);

            Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

            for (int i = 0; i < sheet.getLastRowNum(); i++)
                for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {

                    try {
                        XSSFCell cell = sheet.getRow(i + 1).getCell(k);
                        switch (cell.getCellType())
                        {
                            case Cell.CELL_TYPE_NUMERIC:
                                data[i][k] = cell.getNumericCellValue();
                                break;
                            case Cell.CELL_TYPE_STRING:
                                data[i][k]=cell.getStringCellValue();
                                break;
                        }

                    }
                    catch (NullPointerException NPE) {
                        k++;
                    }
                    catch (Exception ex)
                    {
                        System.out.println("Error reading excel");
                    }
                }


            file.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public Object[] ReadExcelRow(String filename, String sheetname,int rowNum) {
        try {
            FileInputStream file = new FileInputStream(new File(filename));

            XSSFWorkbook workbook = new XSSFWorkbook(file);


            XSSFSheet sheet = workbook.getSheet(sheetname);

            Object[] data = new Object[sheet.getRow(0).getLastCellNum()];

            for (int k = 0; k < sheet.getRow(rowNum+1).getLastCellNum(); k++) {
                try {
                    XSSFCell cell = sheet.getRow(rowNum + 1).getCell(k);
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                            data[k] = cell.getNumericCellValue();
                            break;
                        case Cell.CELL_TYPE_STRING:
                            data[k] = cell.getStringCellValue();
                            break;
                    }

                } catch (NullPointerException NPE) {
                    k++;
                }
            }

            file.close();
            return data;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

}