package com.ghox.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ExcelReader {
	
	private static String BASE_HOST = "http://image.9skg.com/";
	private static String BASE_RESP = "D:/SPPIC/";
	
    private HSSFWorkbook wb;
    private HSSFSheet sheet;
    private HSSFRow row;

    @SuppressWarnings("deprecation")
	public String[] readExcel2(File file, String basePath) {
    	if(!file.exists()) return null;
    	
    	InputStream is = null;
        try {
        	is = new FileInputStream(file);
            wb = new HSSFWorkbook(is);
            
            sheet = wb.getSheetAt(0);
            // 标题总列数
            int rowNum = sheet.getLastRowNum();
            for(int idx=0;idx < rowNum;idx++){
            	row = sheet.getRow(idx);
            	//System.out.print("value=" + getStringCellValue(row.getCell(0)));
            	//条码
            	try{
	            	String barcode = getStringCellValue(row.getCell(0));
	            	String picurl = getStringCellValue(row.getCell(1));
	            	//System.out.println(barcode + "=>" + picurl);
	            	if(barcode != null && !StringUtils.isEmpty(barcode)
	            			&& picurl != null && !StringUtils.isEmpty(picurl)){
	            		String from = picurl;
	        			String to = BASE_RESP + barcode + "-" + 0 + ".jpg";
	        			if(!(new File(to)).exists()){
	        				PictureDownloader.download(from, to);
	            			System.out.println(from + "=>" + to);
	        			}
	            	}else{
	            		System.out.println("barcode is null");
	            	}
            	}catch(Exception ex){
            		ex.printStackTrace();
            	}
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
        	try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        
        return null;
    }
    public String[] readExcel3(File file, String basePath) {
    	if(!file.exists()) return null;
    	
    	InputStream is = null;
        try {
        	is = new FileInputStream(file);
            wb = new HSSFWorkbook(is);
            
            sheet = wb.getSheetAt(0);
            // 标题总列数
            int rowNum = sheet.getLastRowNum();
            GoodsSpider spider = new GoodsSpider();
            for(int idx=0;idx < rowNum;idx++){
            	String barcode = null;
            	String srcurl = null;
            	try{
            		row = sheet.getRow(idx);
            		
	            	barcode = getStringCellValue(row.getCell(1));
	            	srcurl = getStringCellValue(row.getCell(2));
	            	System.out.println(idx + ", barcode=" + barcode + ", url=" + srcurl);
	            	if(barcode != null && !StringUtils.isEmpty(barcode)
	            			&& srcurl != null && !StringUtils.isEmpty(srcurl)){
	            		spider.fetch(barcode, srcurl, basePath);
	            		System.out.println("fetch: " + srcurl);
	            	}else{
	            		System.out.println("barcode is null");
	            	}
            	}catch(Exception ex){
            		System.out.println(idx + ", barcode=" + barcode + ", url=" + srcurl);
            		System.err.println("=>error:" + ex.getMessage());
            		ex.printStackTrace();
            		System.err.println("=>error");
            	}
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
        	try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        
        return null;
    }
    /**
     * 读取Excel表格表头的内容
     * @param InputStream
     * @return String 表头内容的数组	
     */
    @SuppressWarnings("deprecation")
	public String[] readExcelTitle(File file, String basePath) {
    	if(!file.exists()) return null;
    	
    	InputStream is = null;
        try {
        	is = new FileInputStream(file);
            wb = new HSSFWorkbook(is);
            
            sheet = wb.getSheetAt(0);
            // 标题总列数
            int rowNum = sheet.getLastRowNum();
            for(int idx=0;idx < rowNum;idx++){
            	row = sheet.getRow(idx);
            	//System.out.print("value=" + getStringCellValue(row.getCell(0)));
            	//条码
            	String barcode = getStringCellValue(row.getCell(1));
            	if(barcode != null && !StringUtils.isEmpty(barcode)){
            		String pics = getStringCellValue(row.getCell(2));
                	if(pics != null && !StringUtils.isEmpty(pics)){
                		String[] piclist = StringUtils.split(pics, ",");
                		for(int i=0;i<piclist.length;i++){
                			String from = BASE_HOST + piclist[i];
                			String to = BASE_RESP + barcode + "-" + i + ".jpg";
                			if(!(new File(to)).exists()){
                				PictureDownloader.download(from, to);
                    			System.out.println(from + "=>" + to);
                			}
                		}
                	}else{
                		System.out.println("pics is null");
                	}
            	}else{
            		System.out.println("barcode is null");
            	}
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
        	try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        
        return null;
    }
    
	/**
     * 获取单元格数据内容为字符串类型的数据
     * 
     * @param cell Excel单元格
     * @return String 单元格数据内容
     */
    private String getStringCellValue(HSSFCell cell) {
    	if(cell == null) return null;
    	
        String strCell = "";
        switch (cell.getCellType()) {
        case HSSFCell.CELL_TYPE_STRING:
            strCell = cell.getStringCellValue();
            break;
        case HSSFCell.CELL_TYPE_NUMERIC:
        	DecimalFormat format = new DecimalFormat("#");
            strCell = format.format(cell.getNumericCellValue());
            break;
        case HSSFCell.CELL_TYPE_BOOLEAN:
            strCell = String.valueOf(cell.getBooleanCellValue());
            break;
        case HSSFCell.CELL_TYPE_BLANK:
            strCell = "";
            break;
        default:
            strCell = "";
            break;
        }
        if (strCell.equals("") || strCell == null) {
            return "";
        }
        return strCell;
    }

}
