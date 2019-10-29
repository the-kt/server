package com.kumquat.common.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by 13260 on 2019/7/31.
 */
public class ExportExcel {

    public static void exportExcelCommon(HttpServletResponse response, String name, String[] cloumnNames, List<String[]> cloumnValues){

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet(name);
        HSSFCellStyle style = HssfHelper.getHssfCellStyle(wb, 3);

        HSSFRow rowTitle = sheet.createRow(0);
        HSSFCellStyle headstyle = HssfHelper.getHssfCellStyle(wb, 1);
        HSSFCell cellTitle = rowTitle.createCell(1);
        cellTitle.setCellValue(name);
        cellTitle.setCellStyle(headstyle);
        rowTitle.setHeight((short) 600);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, cloumnNames.length));
        HSSFRow row = sheet.createRow(1);

        int nI;
        for (nI = 0; nI < cloumnNames.length; nI++) {
            HSSFCell cell = row.createCell(nI + 1);
            cell.setCellValue(cloumnNames[nI]);
            cell.setCellStyle(style);

            sheet.setColumnWidth(nI + 1, 6000);
        }

        for (nI = 0; nI < cloumnValues.size(); nI++) {
            row = sheet.createRow(nI + 2);

            for(int k =0;k<cloumnValues.get(nI).length;k++){
                row.createCell(k+1).setCellValue(cloumnValues.get(nI)[k]);
            }
            HssfHelper.setRowStyle(row, 1, cloumnNames.length, style);
        }

        try{
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(name, "utf-8") + ".xls");
            OutputStream out = response.getOutputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            wb.write(baos);
            byte[] xlsBytes = baos.toByteArray();
            out.write(xlsBytes);
            out.close();
        }catch (Exception e){

        }

    }

    public static HSSFWorkbook getHssfWorkBook(HSSFWorkbook wb, String name, String[] cloumnNames, List<String[]> cloumnValues)
    {
        HSSFSheet sheet = wb.createSheet(name);
        HSSFCellStyle style = HssfHelper.getHssfCellStyle(wb, 3);

        HSSFRow rowTitle = sheet.createRow(0);
        HSSFCellStyle headstyle = HssfHelper.getHssfCellStyle(wb, 1);
        HSSFCell cellTitle = rowTitle.createCell(0);
        cellTitle.setCellValue(name);
        cellTitle.setCellStyle(headstyle);
        rowTitle.setHeight((short) 600);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, cloumnNames.length-1));
        HSSFRow row = sheet.createRow(1);

        int nI;
        for (nI = 0; nI < cloumnNames.length; nI++) {
            HSSFCell cell = row.createCell(nI);
            cell.setCellValue(cloumnNames[nI]);
            cell.setCellStyle(style);

            sheet.setColumnWidth(nI, 6000);
        }

        for (nI = 0; nI < cloumnValues.size(); nI++) {
            row = sheet.createRow(nI + 2);

            for(int k =0;k<cloumnValues.get(nI).length;k++){
                row.createCell(k).setCellValue(cloumnValues.get(nI)[k]);
            }
            HssfHelper.setRowStyle(row, 0, cloumnNames.length-1, style);
        }

        return  wb;

    }
}
