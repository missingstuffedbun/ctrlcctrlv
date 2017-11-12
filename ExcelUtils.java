package paper;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.*;

/**
 * Description:
 * User: temp
 * Date: 2016/11/16.
 * Time: 20:51
 */
public class ExcelUtils {

    HSSFWorkbook wb;
    public ExcelUtils() throws IOException {
//        File file = new File("C:\\Users\\temp\\Desktop\\lily\\lily.xls");
        File file = new File("C:\\Users\\temp\\Desktop\\lily\\input.xls");
        this.wb = new HSSFWorkbook(new FileInputStream(file));
    }

    public String[] readRow(int sheetIndex, int rowIndex) {
        String[] cells = null;
        try {
            Sheet sheet = this.getSheet(sheetIndex);
            Row row = sheet.getRow(rowIndex);
            if (row==null) {
                return cells;
            }
            cells = new String[row.getLastCellNum()];
            for (int c = 0; c < row.getLastCellNum(); c++) {
                Cell cell = row.getCell(c);
                if (cell==null) {
                    cells[c] = "";
                } else {
                    cells[c] = getValueFromCell(cell);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cells;
    }

    Sheet createSheet() {
        return this.wb.createSheet();
    }

    Sheet createSheet(String sheetName) {
        return this.wb.createSheet(sheetName);
    }

    Sheet getSheet(int index) {
        return this.wb.getSheetAt(index);
    }

    Sheet getSheet(String sheetName) {
        return this.wb.getSheet(sheetName);
    }

    int getSheetIndex(String sheetName) {
        return this.wb.getSheetIndex(sheetName);
    }

    String getValueFromCell(Cell cell) {
        String value = null;
        if (cell!=null) {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    value = cell.getStringCellValue();
                    break;
                case Cell.CELL_TYPE_STRING:
                    value = cell.getStringCellValue();
                    break;
                case Cell.CELL_TYPE_ERROR:
                    value = String .valueOf(cell.getErrorCellValue());
                    break;
                default:
                    value = "";
                    break;
            }
        }
        return value;
    }

    Object toObject(String[] attr, String type) {
        switch (type) {
            case "station":
                return new Station(attr);
            case "sewer":
                return new Sewer(attr);
            case "pipe":
                return new Pipe(attr);
            default:
                return null;
        }
    }

    boolean writeRow(int sheetIndex, int rowIndex, int rowColumnNum, String[] rowValues) {
        Sheet sheet = this.getSheet(sheetIndex);
        return writeRow(sheet, rowIndex, rowColumnNum, rowValues);
    }

    boolean writeRow(Sheet sheet, int rowIndex, int rowColumnNum, String[] rowValues) {
        boolean result = false;
        try {
            Row row = sheet.createRow(rowIndex);
            for (int i = 0; i < rowColumnNum; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(rowValues[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    boolean writeExcelFile(String filePath) {
        FileOutputStream fileOutputStream = null;
        boolean result = false;
        try {
            fileOutputStream = new FileOutputStream(filePath);
            this.wb.write(fileOutputStream);
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream!=null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
