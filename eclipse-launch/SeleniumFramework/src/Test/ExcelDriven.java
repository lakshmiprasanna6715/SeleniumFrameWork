package Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDriven {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		
		//File input stream argument
				ArrayList<String> a= new ArrayList<String>();
						
						FileInputStream File= new FileInputStream("C:\\Users\\Admin\\Downloads\\ExampleFormat.xlsx");
						XSSFWorkbook Workbook=new XSSFWorkbook(File);
						
						int sheets= Workbook.getNumberOfSheets();
						for(int i=0;i<sheets;i++) {
							if(Workbook.getSheetName(i).equalsIgnoreCase("Datasheet")) {
								
								XSSFSheet sheet= Workbook.getSheetAt(i);
								
								//Identify Testcases coloum by scanning the entire 1st row
								Iterator<Row> rows= sheet.iterator(); //sheet is collecting number of rows
								Row firstrow= rows.next();
								
								Iterator<Cell> cel= firstrow.cellIterator(); //sheet is collecting number of coloumns
								
								int k=0, coloumn=0;
								
								while(cel.hasNext()) {
									
									Cell value=cel.next();
									if(value.getStringCellValue().equalsIgnoreCase("Testcases"))
									{
										coloumn=k;	
									}
									k++;
								}
								System.out.println(coloumn);
								
								//once coloumn is identified then scan entire testcase coloumn to identify purchasse tetscase row
								while(rows.hasNext()) 
								{
									Row r=rows.next();
									if(r.getCell(coloumn).getStringCellValue().equalsIgnoreCase("Purchase")) 
									{
									//after you grab purchase testcase row=pull all the data of that row and feed into test
										
										Iterator<Cell> cv=r.cellIterator();
										while(cv.hasNext())
										{
											//System.out.println(cv.next().getStringCellValue());
											
											a.add(cv.next().getStringCellValue());
										}
									}
									
								}
							}
						}
						

					}

					

				

			



	}


