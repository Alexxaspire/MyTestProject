package alex.javatest.excelRead;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by Alex on 22.05.2017.
 */
public class excelRead {

    public static void main(String[] args) throws IOException {

        FileInputStream file = new FileInputStream("E:/Java/test.xls");
        Workbook wb = new HSSFWorkbook(file);

        String value = wb.getSheetAt(0).getRow(1).getCell(2).getStringCellValue();  // получить 1 элемени

        System.out.println(value);

        readFileData(wb);

        file.close();
    }

    public static void readFileData(Workbook wb) {
        // все ниже пример из https://poi.apache.org/spreadsheet/quick-guide.html#CellContents
        //DataFormatter formatter = new DataFormatter();
        Sheet sheet1 = wb.getSheetAt(0);
        for (Row row : sheet1) {
            for (Cell cell : row) {
                /* Пишет еще и нназвание ячейки
                CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
                System.out.print(cellRef.formatAsString());
                System.out.print(" - ");
                 */
                // get the text that appears in the cell by getting the cell value and applying any data formats (Date, 0.00, 1.23e9, $1.23, etc)
                /* Вроде делает тоже что и код ниже
                String text = formatter.formatCellValue(cell);
                System.out.println(text);
                 */
//                if (cell.getCellTypeEnum().equals(CellType.STRING)) {
//                    System.out.println(cell.getRichStringCellValue().getString());
//                    break;
//                } else {
//                    if (cell.getCellTypeEnum().equals(CellType.NUMERIC)) {
//                        if (DateUtil.isCellDateFormatted(cell)) {
//                            System.out.println(cell.getDateCellValue());
//                        } else {
//                            System.out.println(cell.getNumericCellValue());
//                        }
//                        break;
//                    } else {
//                        if (cell.getCellTypeEnum().equals(CellType.BOOLEAN)) {
//                            System.out.println(cell.getBooleanCellValue());
//                            break;
//                        } else {
//                            if (cell.getCellTypeEnum().equals(CellType.FORMULA)) {
//                                System.out.println(cell.getCellFormula());
//                                break;
//                            } else {
//                                if (cell.getCellTypeEnum().equals(CellType.BLANK)) {
//                                    System.out.println();
//                                    break;
//                                } else {
//                                    System.out.println();
//                                }
//
//                            }
//                        }
//                    }
//                }
                // Alternatively, get the value and format it yourself
                CellType cellType = cell.getCellTypeEnum();
                switch (cellType) {
                    case STRING:
                        System.out.println(cell.getRichStringCellValue().getString());
                        break;
                    case NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)) {
                            System.out.println(cell.getDateCellValue());
                        } else {
                            System.out.println(cell.getNumericCellValue());
                        }
                        break;
                    case BOOLEAN:
                        System.out.println(cell.getBooleanCellValue());
                        break;
                    case FORMULA:
                        System.out.println(cell.getCellFormula());
                        break;
                    case BLANK:
                        System.out.println();
                        break;
                    default:
                        System.out.println();
                }

            }
        }

        String result = "";

        Iterator<Row> it = sheet1.iterator();
        while (it.hasNext()) {
            Row row = it.next();
            Iterator<Cell> cells = row.iterator();
            while (cells.hasNext()) {
                Cell cell = cells.next();
                int cellType = cell.getCellType();
                switch (cellType) {
                    case Cell.CELL_TYPE_STRING:
                        result += cell.getStringCellValue() + " ";
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        result += cell.getNumericCellValue() + " ";
                        break;

                    case Cell.CELL_TYPE_FORMULA:
                        result += cell.getNumericCellValue() + " ";
                        break;
                    default:
                        result += "|";
                        break;
                }
            }
            result += "\n";
        }

        System.out.println(result);
    }
}
