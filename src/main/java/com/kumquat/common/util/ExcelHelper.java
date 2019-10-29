package com.kumquat.common.util;

import com.kumquat.common.annotation.BeanFieldAnnotation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 13260 on 2019/7/28.
 */
public class ExcelHelper {
    /**
     * 将excel文件流转换成指定的对象集合
     *
     * @param t 类
     * @param fileName
     *            文件全名
     * @param stream
     *            excel文件流
     * @return 指定对象集合
     */
    public static <T> List<T> convertToList(Class<T> t, String fileName, FileInputStream stream, int rowsnum,
                                            int colnum,int sheet) {
        List<T> listResult = new ArrayList<T>();
        try {
            //通过行列起始读取excel流
            String[][] arrays = readExcel(fileName, stream, rowsnum, colnum, sheet);

            if (arrays != null) {
                for (int i = 0; i < arrays.length; i++) {
                    //给类对象赋值
                    T cal = getCurrentList(t, arrays[i],true);
                    listResult.add(cal);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return listResult;
    }

    public static <T> List<T> convertToList(Class<T> t, String fileName, FileInputStream stream, int rowsnum,
                                            int colnum,int sheet,boolean isOrder) {
        List<T> listResult = new ArrayList<T>();
        try {
            //通过行列起始读取excel流
            String[][] arrays = readExcel(fileName, stream, rowsnum, colnum, sheet);

            if (arrays != null) {
                for (int i = 0; i < arrays.length; i++) {
                    //给类对象赋值
                    T cal = getCurrentList(t, arrays[i],isOrder);
                    listResult.add(cal);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return listResult;
    }

    /**
     * 读取Excel文件到二维字符串数组
     *
     * @param fileName 文件名
     * @param stream 文件流
     * @return 二维字符串数组
     * @throws IOException
     */
    private static String[][] readExcel(String fileName, FileInputStream stream, int rowsnum, int colnum,int sheet)
            throws IOException {
        if (stream == null) {
            return null;
        }

        String postfix = getPostfix(fileName);
        Workbook wb = null;

        //根据后缀初始化Workbook对象
        if (!Constant.EMPTY.equals(postfix)) {
            if (Constant.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
                wb = getXLSWorkbook(stream);
            } else if (Constant.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
                wb = getXLSXWorkbook(stream);
            }
            //读取excel文件
            return readWorkbook(wb, rowsnum, colnum,sheet);
        }

        return null;
    }

    /**
     * 获得文件后缀名
     *
     * @param path
     * @return
     */
    private static String getPostfix(String path) {
        if (path == null || Constant.EMPTY.equals(path.trim())) {
            return Constant.EMPTY;
        }

        if (path.contains(Constant.POINT)) {
            return path.substring(path.lastIndexOf(Constant.POINT) + 1, path.length());
        }

        return Constant.EMPTY;
    }

    /**
     * 获得xls格式的Workbook
     *
     * @param stream
     * @return
     * @throws IOException
     */
    private static Workbook getXLSWorkbook(FileInputStream stream) throws IOException {
        Workbook wb = new HSSFWorkbook(stream);

        return wb;
    }

    /**
     * 获得xlsx格式的Workbook
     *
     * @param stream
     * @return
     * @throws IOException
     */
    private static Workbook getXLSXWorkbook(FileInputStream stream) throws IOException {
        Workbook wb = new XSSFWorkbook(stream);

        return wb;
    }

    /**
     * 读取指定行、列的excel数据到二维字符串数组
     *
     * @param wb excel对象
     * @param rowsnum 行
     * @param colnum 列
     * @return 二维字符串数组
     * @throws IOException
     */
    private static String[][] readWorkbook(Workbook wb, int rowsnum, int colnum,int sheetnum) throws IOException {
        //过滤空行
        Sheet sheet = filterBlankLine(wb.getSheetAt(sheetnum), rowsnum);
        //获取最后一行
        int rows = sheet.getLastRowNum();
        int columns = colnum;
        //行数从0开始,计算时+1
        String[][] data = new String[rows + 1 - rowsnum][columns];

        for (int rownum = rowsnum; rownum <= sheet.getLastRowNum(); rownum++) {
            Row row = sheet.getRow(rownum);
            if (row == null) {
                continue;
            }

            String value;
            for (int cellnum = 0; cellnum < columns; cellnum++) {
                Cell cell = row.getCell(cellnum);
                if (cell == null) {
                    continue;
                } else {
                    value = "";
                }
                //给不同格式的单元格赋值
                switch (cell.getCellType()) {
                    //文本
                    case Cell.CELL_TYPE_STRING:
                        value = cell.getRichStringCellValue().getString();
                        break;
                    //数值
                    case Cell.CELL_TYPE_NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)||cell.getCellStyle().getDataFormat()==31) {
                            Date theDate = cell.getDateCellValue();
                            SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                            Calendar   calendar   =   new   GregorianCalendar();
                            calendar.setTime(theDate);
                            calendar.add(Calendar.DATE,1);
                            theDate=calendar.getTime();
                            
                            value = dff.format(theDate);
                        }else{
                            //为数值赋值，避免大数据变成科学表达式
                            DecimalFormat df = new DecimalFormat("0");
                            value = df.format(cell.getNumericCellValue());
//						value = Double.toString((int) cell.getNumericCellValue());
                        }
                        break;
                    //bool
                    case Cell.CELL_TYPE_BOOLEAN:
                        value = Boolean.toString(cell.getBooleanCellValue());
                        break;
                    //公式
                    case Cell.CELL_TYPE_FORMULA:
                        value = cell.getCellFormula().toLowerCase();
                        break;
                    default:
                        value = "";
                }

                data[rownum - rowsnum][cellnum] = value;
            }
        }

        return data;
    }

    /**
     * 给当前传入实际类型的实例赋值
     *
     * @param t
     * @param args
     * @return
     */
    private static <T> T getCurrentList(Class<T> t, String[] args,boolean isOrder) {
        T bean = null;
        Field field = null;

        try {
            bean = t.newInstance();
            Field[] fields = t.getFields();

            List<Field> fl=new ArrayList<>();

            if(isOrder){
                fl = getOrderedField(fields);
            }else{
                fl = Arrays.asList(fields);
            }

            for (int i = 0; i < fl.size(); i++) {
                // 根据变量名获得变量对象
                field = fl.get(i);
                if (field != null) {
                    field.setAccessible(true);
                    // 赋值给bean对象对应的值

                    if(field.getType()==String.class){
                        field.set(bean, args[i]);
                    }else if (field.getType()==Date.class){

                        if(args[i].isEmpty()){
                            field.set(bean, null);
                        }else {
                            SimpleDateFormat formatter = null;
                            if (!args[i].isEmpty() && args[i].indexOf(":") > -1) {
                                formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            } else {
                                formatter = new SimpleDateFormat("yyyy-MM-dd");
                            }

                            if (args[i].length() == 4) {
                                args[i] = args[i] + "-01-01";
                            }
                            Date date = formatter.parse(args[i]);
                            field.set(bean, date);
                        }


                    }else if(field.getType()==Integer.class){
                        field.set(bean, Integer.parseInt(args[i]));
                    }

                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return bean;
    }

    /**
     * 过滤空行
     *
     * @return
     */
    private static Sheet filterBlankLine(Sheet sheet, int rowsnum) {

        boolean flag = false;
        for (int i = rowsnum; i <= sheet.getLastRowNum();) {
            Row r = sheet.getRow(i);
            if (r == null) {
                // 如果是空行（即没有任何数据、格式），直接把它以下的数据往上移动
                sheet.shiftRows(i + 1, sheet.getLastRowNum(), -1);
                continue;
            }
            flag = false;
            //遍历单元格
            for (Cell c : r) {
                //判断单元格是否为空
                if (c.getCellType() != Cell.CELL_TYPE_BLANK) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                i++;
                continue;
            } else {
                // 如果是空白行（即可能没有数据，但是有一定格式）
                // 如果到了最后一行，直接将那一行remove掉
                if (i == sheet.getLastRowNum())
                    sheet.removeRow(r);
                else {
                    // 如果还没到最后一行，则数据往上移一行
                    sheet.shiftRows(i + 1, sheet.getLastRowNum(), -1);
                }
            }
        }
        return sheet;
    }

    /**
     * 获取当前类型中定义的变量名称
     *
     * @param t 类
     * @return 变量名称的集合
     */
    private static List<String> getFieldName(Class<?> t) {
        List<String> list = new ArrayList<>();
        // 循环此字段数组，获取属性的值
        for (Field field : t.getDeclaredFields()) {
            //设置
            field.setAccessible(true);
            String name = field.getName();
            list.add(name);
        }

        return list;
    }

    private static List<Field> getOrderedField(Field[] fields){
        // 用来存放所有的属性域
        List<Field> fieldList = new ArrayList<>();

        boolean flag =true;
        // 过滤带有注解的Field
        for(Field f:fields){
            if(f.getAnnotation(BeanFieldAnnotation.class)!=null){
              fieldList.add(f);
                flag = false;
            }
        }

        if(flag){
            return Arrays.asList(fields);
        }

        fieldList.sort(Comparator.comparingInt(m -> m.getAnnotation(BeanFieldAnnotation.class).order()));
        return fieldList;
    }

}
