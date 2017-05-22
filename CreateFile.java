package alex.javatest.excel;

import jdk.nashorn.internal.codegen.CompilerConstants;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;


import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Created by Alex on 22.05.2017.
 */
public class CreateFile {


    public static void main(String[] args) throws IOException {

        Workbook  wb = new HSSFWorkbook(); // Workbook интерфей, создали объект класса HSSFWorkbook для Excel 97-2003 годов

        Sheet list1 = wb.createSheet("Лист111"); // создали Лист1 в Excel c новым названием через объект wb
        Sheet list2 = wb.createSheet("Лист222");
        Sheet list3 = wb.createSheet("Лист333");

        Sheet list4 = wb.createSheet(WorkbookUtil.createSafeSheetName("fgsufg^%%0808@#&&")); // помогает создавать вкладки со спец символами, т.к. при методе выше с таки названием, компилятор выдаст ошибку



        Row row1 = list1.createRow(1);  //Создали строку и указали ее позицию. Подсчет с 0. Т.е. 1 в Excel это 0 в Java
        Cell cell1 = row1.createCell(2); // Создали столбец в этой строке

        cell1.setCellValue("Good Job Alex!"); //заносим данные в ячейку по заданной строке и столбцу

        FileOutputStream file = new FileOutputStream("E:/Java/test.xls");   //создаем поток для создания файла в который мы будем записывать созданную нами книгу


        wb.write(file); //открываем поток для записи в созданный файл
        file.close(); // закрываем поток
    }



}
