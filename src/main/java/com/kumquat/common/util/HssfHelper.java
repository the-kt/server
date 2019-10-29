package com.kumquat.common.util;

import org.apache.poi.hssf.usermodel.*;

/**
 * Created by 13260 on 2019/7/31.
 */
public class HssfHelper {
    public static HSSFRow setCellValueAndStyle(HSSFRow row, int index, String value, HSSFCellStyle style) {
        HSSFCell cell1 = row.createCell(index);
        cell1.setCellValue(value);
        cell1.setCellStyle(style);
        return row;
    }

    public static HSSFRow setMergedStyle(HSSFRow row, int start, int end, HSSFCellStyle style) {
        for(int i = start; i <= end; ++i) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
        }

        return row;
    }

    public static HSSFCellStyle getHssfCellStyle(HSSFWorkbook wb, int type) {
        HSSFCellStyle style = wb.createCellStyle();
        switch(type) {
            case 1:
                HSSFFont headfont = wb.createFont();
                headfont.setFontName("黑体");
                headfont.setFontHeightInPoints((short)18);
                headfont.setBoldweight((short)700);
                style.setFont(headfont);
                style.setAlignment((short)2);
                style.setVerticalAlignment((short)1);
                break;
            case 2:
                HSSFFont headfontSmall = wb.createFont();
                headfontSmall.setFontName("黑体");
                headfontSmall.setFontHeightInPoints((short)14);
                headfontSmall.setBoldweight((short)700);
                style.setFont(headfontSmall);
                style.setAlignment((short)2);
                style.setVerticalAlignment((short)1);
                style.setBorderLeft((short)1);
                style.setBorderRight((short)1);
                style.setBorderTop((short)1);
                style.setBorderBottom((short)1);
                break;
            case 3:
                style.setAlignment((short)2);
                style.setBorderLeft((short)1);
                style.setBorderRight((short)1);
                style.setBorderTop((short)1);
                style.setBorderBottom((short)1);
                style.setWrapText(true);
        }

        return style;
    }

    public static HSSFRow setRowStyle(HSSFRow row, int start, int end, HSSFCellStyle style) {
        for(int i = start; i <= end; ++i) {
            row.getCell(i).setCellStyle(style);
        }

        return row;
    }
}
