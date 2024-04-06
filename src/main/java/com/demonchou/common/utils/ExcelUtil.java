package com.demonchou.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Component;

/**
 * excel工具类
 * @author hzxietao
 * @version $$ ExcelUtil, 05/09/2017 hzxietao $$
 */
@Component
public class ExcelUtil
{
	private Workbook wb;
	private List<String[]> dataList = new ArrayList<String[]>(20000);
	;

	public ExcelUtil(File file) throws InvalidFormatException, IOException
	{
		InputStream inp = null;
		try
		{
			inp = new FileInputStream(file);
			wb = WorkbookFactory.create(inp);
			dataList = this.getAllData(0);
		}
		finally
		{
			if (inp != null)
			{
				inp.close();
			}
		}
	}

	/**
	 * 取Excel所有数据，包含header
	 * @return List<String       [       ]>
	 */
	public List<String[]> getAllData(int sheetIndex)
	{
		Sheet sheet = wb.getSheetAt(sheetIndex);

		for (Row row : sheet)
		{
			int columnNum = 0;
			if (row != null)
			{
				columnNum = row.getLastCellNum() - row.getFirstCellNum();
			}

			String[] singleRow = new String[columnNum];

			int n = 0;
			for (int i = 0; i < columnNum; i++)
			{
				Cell cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
				switch (cell.getCellType())
				{
					case Cell.CELL_TYPE_BLANK:
						singleRow[n] = StringUtils.EMPTY;
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						singleRow[n] = Boolean.toString(cell.getBooleanCellValue());
						break;
					//数值
					case Cell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(cell))
						{
							singleRow[n] = String.valueOf(cell.getDateCellValue());

							SimpleDateFormat sdf;
							if (cell.getCellStyle().getDataFormat() == HSSFDataFormat
									.getBuiltinFormat("h:mm"))
							{
								sdf = new SimpleDateFormat("HH:mm");
							}
							else
							{
								// 日期
								sdf = new SimpleDateFormat("yyyy-MM-dd");
							}
							Date date = cell.getDateCellValue();
							singleRow[n] = sdf.format(date);
						}
						else
						{
							cell.setCellType(Cell.CELL_TYPE_STRING);
							String temp = cell.getStringCellValue();
							//判断是否包含小数点，如果不含小数点，则以字符串读取，如果含小数点，则转换为Double类型的字符串
							if (temp.contains("."))
							{
								singleRow[n] = String.valueOf(new Double(temp)).trim();
							}
							else
							{
								singleRow[n] = temp.trim();
							}
						}
						break;
					case Cell.CELL_TYPE_STRING:
						singleRow[n] = cell.getStringCellValue().trim();
						break;
					case Cell.CELL_TYPE_ERROR:
						singleRow[n] = StringUtils.EMPTY;
						break;
					case Cell.CELL_TYPE_FORMULA:
						cell.setCellType(Cell.CELL_TYPE_STRING);
						singleRow[n] = cell.getStringCellValue();
						if (singleRow[n] != null)
						{
							singleRow[n] = singleRow[n].replaceAll("#N/A", StringUtils.EMPTY).trim();
						}
						break;
					default:
						singleRow[n] = StringUtils.EMPTY;
						break;
				}
				n++;
			}
			if (StringUtils.EMPTY.equals(singleRow[0]))
			{
				continue;
			} //如果第一行为空，跳过
			dataList.add(singleRow);
		}

		return dataList;
	}

	/**
	 * 返回Excel最大行index值，实际行数要加1
	 * @return
	 */
	public int getRowNum(int sheetIndex)
	{
		Sheet sheet = wb.getSheetAt(sheetIndex);
		return sheet.getLastRowNum();
	}

	/**
	 * 获取除去空行的行记录数
	 * @param sheetIndex
	 * @return
	 */
	public int getRowNumExcludeBlankRow(int sheetIndex)
	{
		return dataList.size();
	}

	/**
	 * 返回数据的列数
	 * @return
	 */
	public int getColumnNum(int sheetIndex)
	{
		return getColumnNum(sheetIndex, 0);
	}

	/**
	 * 返回数据的列数
	 * @return
	 */
	public int getColumnNum(int sheetIndex,int rowNum)
	{
		if (rowNum < 0)
		{
			rowNum = 0;
		}
		Sheet sheet = wb.getSheetAt(sheetIndex);
		Row row = sheet.getRow(rowNum);
		if (row != null && row.getLastCellNum() > 0)
		{
			return row.getLastCellNum();
		}
		return 0;
	}

	/**
	 * 获取某一行数据
	 * @param rowIndex 计数从0开始，rowIndex为0代表header行
	 * @return
	 */
	public String[] getRowData(int sheetIndex, int rowIndex)
	{
		if (rowIndex > this.getRowNum(sheetIndex))
		{
			return null;
		}
		else
		{
			return this.dataList.get(rowIndex);
		}
	}

	/**
	 * 获取某一列数据
	 * @param colIndex
	 * @return
	 */
	public String[] getColumnData(int sheetIndex, int colIndex)
	{
		String[] dataArray = null;
		if (colIndex > this.getColumnNum(sheetIndex))
		{
			return null;
		}
		else
		{
			if (this.dataList != null && this.dataList.size() > 0)
			{
				dataArray = new String[this.getRowNum(sheetIndex) + 1];
				int index = 0;
				for (String[] rowData : dataList)
				{
					if (rowData != null)
					{
						dataArray[index] = rowData[colIndex];
						index++;
					}
				}
			}
		}

		return dataArray;
	}
}
