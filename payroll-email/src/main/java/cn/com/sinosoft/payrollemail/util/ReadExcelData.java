package cn.com.sinosoft.payrollemail.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by fangzy on 2018/8/10 22:22
 */
public class ReadExcelData {

    /**
     * @return 读出的Excel中数据的内容
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String[][] getData(String fileName, MultipartFile file) throws Exception {
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new Exception("上传文件格式不正确");
        }
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = file.getInputStream();
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }
        String[][] dataArr = getWorkbookData(wb);
        is.close();
        return dataArr;
    }
    public static String[][] getWorkbookData(Workbook wb) throws Exception {
        List<String[]> result = new ArrayList<String[]>();
        int rowSize = 0;
        int ignoreRows = 1;
        Cell cell = null;
        for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
            Sheet st = wb.getSheetAt(sheetIndex);
            // 第一行为标题，不取
            for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {
                Row row = st.getRow(rowIndex);
                if (row == null) {
                    continue;
                }
                int tempRowSize = row.getLastCellNum() + 1;
                if (tempRowSize > rowSize) {
                    rowSize = tempRowSize;
                }
                String[] values = new String[rowSize];
                Arrays.fill(values, "");
                boolean hasValue = false;
                for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
                    String value = "";
                    cell = row.getCell(columnIndex);
                    if (cell != null) {
                        switch (cell.getCellType()) {
                            case HSSFCell.CELL_TYPE_STRING:
                                value = cell.getStringCellValue();
                                break;
                            case HSSFCell.CELL_TYPE_NUMERIC:
                                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                    Date date = cell.getDateCellValue();
                                    if (date != null) {
                                        value = new SimpleDateFormat("yyyy-MM-dd").format(date);
                                    } else {
                                        value = "";
                                    }
                                } else {
                                    value = new DecimalFormat("0").format(cell.getNumericCellValue());
                                }
                                break;
                            case HSSFCell.CELL_TYPE_FORMULA:
                                // 导入时如果为公式生成的数据则无值
                                if (!cell.getStringCellValue().equals("")) {
                                    value = cell.getStringCellValue();
                                } else {
                                    value = cell.getNumericCellValue() + "";
                                }
                                break;
                            case HSSFCell.CELL_TYPE_BLANK:
                                break;
                            case HSSFCell.CELL_TYPE_ERROR:
                                value = "";
                                break;
                            case HSSFCell.CELL_TYPE_BOOLEAN:
                                value = (cell.getBooleanCellValue() == true ? "Y" : "N");
                                break;
                            default:
                                value = "";
                        }
                    }
                    if (columnIndex == 0 && value.trim().equals("")) {
                        break;
                    }
                    values[columnIndex] = rightTrim(value);
                    hasValue = true;
                }
                if (hasValue) {
                    result.add(values);
                }
            }
        }
        String[][] returnArray = new String[result.size()][rowSize];
        for (int i = 0; i < returnArray.length; i++) {
            returnArray[i] = (String[]) result.get(i);
        }
        return returnArray;
    }
//    public static String[][] getXSSFWorkbookData(String fileName, MultipartFile file) throws Exception {
//        List<String[]> result = new ArrayList<String[]>();
//        int rowSize = 0;
//        int ignoreRows = 1;
//        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
//            throw new Exception("上传文件格式不正确");
//        }
//        boolean isExcel2003 = true;
//        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
//            isExcel2003 = false;
//        }
//        InputStream is = file.getInputStream();
//        Workbook wb = null;
//        if (isExcel2003) {
//            wb = new HSSFWorkbook(is);
//        } else {
//            wb = new XSSFWorkbook(is);
//        }
//        HSSFCell cell = null;
//        for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
//            HSSFSheet st = wb.getSheetAt(sheetIndex);
//            // 第一行为标题，不取
//            for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {
//                HSSFRow row = st.getRow(rowIndex);
//                if (row == null) {
//                    continue;
//                }
//                int tempRowSize = row.getLastCellNum() + 1;
//                if (tempRowSize > rowSize) {
//                    rowSize = tempRowSize;
//                }
//                String[] values = new String[rowSize];
//                Arrays.fill(values, "");
//                boolean hasValue = false;
//                for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
//                    String value = "";
//                    cell = row.getCell(columnIndex);
//                    if (cell != null) {
//                        switch (cell.getCellType()) {
//                            case HSSFCell.CELL_TYPE_STRING:
//                                value = cell.getStringCellValue();
//                                break;
//                            case HSSFCell.CELL_TYPE_NUMERIC:
//                                if (HSSFDateUtil.isCellDateFormatted(cell)) {
//                                    Date date = cell.getDateCellValue();
//                                    if (date != null) {
//                                        value = new SimpleDateFormat("yyyy-MM-dd").format(date);
//                                    } else {
//                                        value = "";
//                                    }
//                                } else {
//                                    value = new DecimalFormat("0").format(cell.getNumericCellValue());
//                                }
//                                break;
//                            case HSSFCell.CELL_TYPE_FORMULA:
//                                // 导入时如果为公式生成的数据则无值
//                                if (!cell.getStringCellValue().equals("")) {
//                                    value = cell.getStringCellValue();
//                                } else {
//                                    value = cell.getNumericCellValue() + "";
//                                }
//                                break;
//                            case HSSFCell.CELL_TYPE_BLANK:
//                                break;
//                            case HSSFCell.CELL_TYPE_ERROR:
//                                value = "";
//                                break;
//                            case HSSFCell.CELL_TYPE_BOOLEAN:
//                                value = (cell.getBooleanCellValue() == true ? "Y" : "N");
//                                break;
//                            default:
//                                value = "";
//                        }
//                    }
//                    if (columnIndex == 0 && value.trim().equals("")) {
//                        break;
//                    }
//                    values[columnIndex] = rightTrim(value);
//                    hasValue = true;
//                }
//                if (hasValue) {
//                    result.add(values);
//                }
//            }
//        }
//        in.close();
//        String[][] returnArray = new String[result.size()][rowSize];
//        for (int i = 0; i < returnArray.length; i++) {
//            returnArray[i] = (String[]) result.get(i);
//        }
//        return returnArray;
//    }
    /**
     * 去掉字符串右边的空格
     * @param str 要处理的字符串
     * @return 处理后的字符串
     */
    public static String rightTrim(String str) {
        if (str == null) {
            return "";
        }
        int length = str.length();
        for (int i = length - 1; i >= 0; i--) {
            if (str.charAt(i) != 0x20) {
                break;
            }
            length--;
        }
        return str.substring(0, length);
    }


}
