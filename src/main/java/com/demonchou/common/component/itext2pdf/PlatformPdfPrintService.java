package com.demonchou.common.component.itext2pdf;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * 生成PDF demo
 * @author hzzhangzhipan
 * @version 1.0
 */
public class PlatformPdfPrintService
{

	//	private Logger logger = Logger.getLogger(PlatformPdfPrintService.class);
	private Logger logger = Logger.getLogger(getClass());

	public static final String DEST = "/tmp/productPdf.PDF";

	public static final String IMG = "/tmp/newLogo.png";

	public static final float LOW_HEIGHT = 25;

	public static final float MIDDLE_HEIGHT = 50;

	public static final float HIGHE_HEIGHT = 80;

	/**
	 * 商户产品签约申请表（PDF）
	 * @throws Exception
	 * @throws DocumentException
	 */

	public void printPdf() throws Exception, DocumentException
	{
		Document document = new Document(PageSize.A4, 80, 79, 40, 20);
		BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font subBoldFontChinese = new Font(bfChinese, 10, Font.BOLD);
		Font titleChinese = new Font(bfChinese, 20, Font.BOLD); // 模板抬头
		PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(DEST));//输出页面
		// ------------打开文档-------------------
		document.open();
		// ------------页眉-------------------
		initHead(document);
		// ------------标题-------------------
		initTitle(document, titleChinese);

		PdfPCell cell = null;
		// ------------主表格1-------------------
		float[] widths = { 15f, 30f, 15f, 25f };// 设置表格的列宽和列数 默认是4列
		PdfPTable table = new PdfPTable(widths);// 建立一个pdf表格
		table.setSpacingBefore(20f);// 设置表格上面空白宽度
		table.setTotalWidth(700);// 设置表格的宽度
		table.setWidthPercentage(100);// 设置表格宽度为%100
		table.setHeaderRows(1);//每页都输出表头 

		// ------------初始化表头-------------------
		cell = new PdfPCell(new Paragraph("编号：", subBoldFontChinese));// 编号
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setPaddingTop(0f);
		cell.setPaddingRight(20f);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		cell = new PdfPCell(new Paragraph("", subBoldFontChinese));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setPaddingTop(0f);
		cell.setPaddingRight(20f);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		cell = new PdfPCell(new Paragraph("申请时间：", subBoldFontChinese));// 申请时间
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setPaddingTop(0f);
		cell.setPaddingRight(0f);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		cell = new PdfPCell(new Paragraph(" 年                   月                日", subBoldFontChinese));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setPaddingTop(0f);
		cell.setPaddingLeft(20f);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		// ------------嵌套表格2-------------------
		PdfPTable table2 = new PdfPTable(2);// 建立一个pdf表格
		table2.setWidths(new int[] { 3, 7 });
		table2.setSpacingBefore(2f);// 设置表格上面空白宽度
		table2.setTotalWidth(100);// 设置表格的宽度
		table2.setWidthPercentage(100);// 设置表格宽度为%100
		// ------------嵌套表格2 单元格-------------------
		cell = new PdfPCell(new Paragraph("□符合      □不符合   ", subBoldFontChinese));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorder(Rectangle.NO_BORDER);
		table2.addCell(cell);
		cell = new PdfPCell(new Paragraph("", subBoldFontChinese));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(3);
		table2.addCell(cell);

		cell = new PdfPCell(new Paragraph("", subBoldFontChinese));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorder(Rectangle.NO_BORDER);
		table2.addCell(cell);
		cell = new PdfPCell(
				new Paragraph("签字 ：                                年             月            日", subBoldFontChinese));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBorder(Rectangle.NO_BORDER);
		table2.addCell(cell);

		// ------------初始化主表格单元格-------------------
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("申请商户", "");
		map.put("商户号", "");
		map.put("电子钱包账号", "");
		map.put("申请人手机", "");
		map.put("商户类型", "");
		map.put("商户产品名称", "");
		map.put("申请产品服务", "");
		map.put("商户业务用途说明(说明网关及权限需要)", "");
		map.put("需额外分配的接口或网关", "");
		map.put("商务意见", "");
		map.put("合规意见", "");
		map.put("财务意见", "");
		map.put("风控意见", "");
		map.put("负责人审批意见", "");
		map.put("技术支持", "");
		map.put("配置日期", "");

		float height = LOW_HEIGHT;
		int colspan = 0;
		PdfPTable nest_table = null;
		for (Map.Entry<String, String> entry : map.entrySet())
		{
			logger.info("Key = " + entry.getKey() + ", Value = " + entry.getValue());
			if (StringUtils.equals(entry.getKey(), "申请产品服务") || StringUtils.contains(entry.getKey(), "意见"))
			{
				height = MIDDLE_HEIGHT;
				colspan = 3;
			}
			else if (StringUtils.equals(entry.getKey(), "商户业务用途说明(说明网关及权限需要)") || StringUtils
					.equals(entry.getKey(), "需额外分配的接口或网关"))
			{
				height = HIGHE_HEIGHT;
				colspan = 3;
			}
			else
			{
				height = LOW_HEIGHT;
				colspan = 0;
			}
			if (StringUtils.contains(entry.getKey(), "意见"))
			{
				nest_table = table2;
			}
			else
			{
				nest_table = null;
			}
			cell = createPdfCell(entry.getKey(), height, BaseColor.LIGHT_GRAY, 0, null);
			table.addCell(cell);
			cell = createPdfCell(entry.getValue(), height, null, colspan, nest_table);
			table.addCell(cell);
		}

		document.add(table);
		logger.info("打印页数:" + pdfWriter.getPageNumber());
		document.close();
	}

	/**
	 * 页眉
	 */
	private void initHead(Document document) throws BadElementException,
			MalformedURLException, IOException, DocumentException
	{
		PdfPTable table1 = new PdfPTable(1);
		Image img = Image.getInstance(IMG);
//		PdfPCell cell1 = new PdfPCell(img, true);
//		cell1.setBorder(Rectangle.NO_BORDER);
//		table1.addCell(cell1);
		table1.setSpacingAfter(20f);
		table1.setTotalWidth(700);
		table1.setWidthPercentage(100);// 设置表格宽度为%100
		document.add(table1);
	}

	/**
	 * 标题
	 */
	private void initTitle(Document document, Font titleChinese)
			throws DocumentException
	{
		Paragraph title = new Paragraph("商户产品签约申请表", titleChinese);// 抬头
		title.setAlignment(Element.ALIGN_CENTER); // 居中设置
		title.setLeading(1f);// 设置行间距//设置上面空白宽度
		title.setFont(titleChinese);
		title.setSpacingBefore(10f);
		document.add(title);
	}

	/**
	 * 单元格
	 */
	private PdfPCell createPdfCell(String string, float fixedHeight, BaseColor backgroundColor, int colspan,
			PdfPTable table) throws DocumentException, IOException
	{
		BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font subBoldFontChinese = new Font(bfChinese, 10, Font.BOLD);
		PdfPCell cell = null;
		cell = new PdfPCell(new Paragraph(string, subBoldFontChinese));// 描述 or 显示内容
		cell.setFixedHeight(fixedHeight);
		if (backgroundColor != null)
		{
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		}
		if (colspan > 0)
		{
			cell.setColspan(colspan);
		}
		if (table != null)
		{
			cell.addElement(table);
		}
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
		return cell;
	}

	public static void main(String[] args)
	{
		try
		{
			new PlatformPdfPrintService().printPdf();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
