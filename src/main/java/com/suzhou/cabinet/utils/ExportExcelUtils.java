package com.suzhou.cabinet.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.IOException;
import java.util.*;

/**
 * @Author : SUZ
 * @Date : 2020/03/17 15:09
 * @Description : excel导出
 */
public class ExportExcelUtils {
    public static HSSFWorkbook exportExcel(String sheetName, List<String> headersName, List<String> headersId,
                                           List<Map<String, Object>> dtoList) throws IOException {
        //（一）表头--标题栏
        Map<Integer, String> headersNameMap = new HashMap<>();
        int key = 0;
        for (String s : headersName) {
            if (s != null) {
                headersNameMap.put(key, s);
                key++;
            }
        }
        //（二）字段---标题对应的字段
        Map<Integer, String> titleFieldMap = new HashMap<>();
        int value = 0;
        for (String s : headersId) {
            if (s != null) {
                titleFieldMap.put(value, s);
                value++;
            }
        }
        // (三）声明一个工作薄：包括构建工作簿、表格、样式
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet(sheetName);
        sheet.setDefaultColumnWidth(15);
        // 生成一个样式
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //合并第一行单元格标题
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell;
        cell = row.createCell(0);
        cell.setCellValue("学生基本信息表Map");
        cell.setCellStyle(style);
        //CellRangeAddress(int firstRow, int lastRow, int firstCol, int lastCol)
        wb.getSheet(sheetName).addMergedRegion(new CellRangeAddress(0, 0, 0, headersNameMap.size() - 1));
        //创建列标题栏所在行
        row = sheet.createRow(1);
        Collection c = headersNameMap.values();//拿到表格所有标题的value的集合
        Iterator<String> headersNameIt = c.iterator();//表格标题的迭代器
        //（四）导出数据：包括导出标题栏以及内容栏
        //根据选择的字段生成表头--标题
        int size = 0;
        while (headersNameIt.hasNext()) {
            cell = row.createCell(size);
            cell.setCellValue(headersNameIt.next().toString());
            cell.setCellStyle(style);
            size++;
        }
        //表格一行的字段的集合，以便拿到迭代器
        Collection headersFieldCo = titleFieldMap.values();
        Iterator<Map<String, Object>> dtoMapIt = dtoList.iterator();//总记录的迭代器
        int zdRow = 2;//内容栏 真正数据记录的行序号
        while (dtoMapIt.hasNext()) {//记录的迭代器，遍历总记录
            Map<String, Object> mapTemp = dtoMapIt.next();//拿到一条记录
            row = sheet.createRow(zdRow);
            zdRow++;
            int zdCell = 0;
            Iterator<String> headersFieldIt = headersFieldCo.iterator();//一条记录的字段的集合的迭代器
            while (headersFieldIt.hasNext()) {
                String tempField = headersFieldIt.next();//字段的暂存
                if (mapTemp.get(tempField) != null) {
                    row.createCell(zdCell).setCellValue(String.valueOf(mapTemp.get(tempField)));//写进excel对象
//                    zdCell++;
                } else {
                    //字段内容值为null时，进行处理
                    row.createCell(zdCell).setCellValue("*");//写进excel对象
                }
                zdCell++;
            }
        }
        //行列分组
        //wb.getSheet(sheetName).groupColumn(4, 7);
        /*FileOutputStream exportXls = new FileOutputStream("d://app//工单信息表Map.xls");
        wb.write(exportXls);
        exportXls.close();*/
        return wb;
    }


}
