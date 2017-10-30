package com.demonchou.common.component.itext2pdfandimage;


import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;

/**
 * 根据HTML生成PDF
 * @author sars
 */
public class JavaToPdfHtmlFreeMarker
{
	private static final String DEST = "./test/电子回单.pdf";
	private static final String HTML = "<html style=\"width:650px;height:566px;margin:0px;paddnig:0px;\"> <head> <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/> <style> table { border-bottom: 1px solid #666; border-right: 1px solid #666; } td { padding: 7px 0 6px 20px; font-size: 14px; border-top: 1px solid #666; border-left: 1px solid #666; } .label { background: #fff1f1 } </style> </head> <body style=\"width:650px;height:600px;margin:0px;paddnig:0px;font-family:Microsoft YaHei\"> <div style=\"overflow:hidden;width:650px;padding:20px;height:670px;font-size:14px;font-family:Microsoft YaHei\"><p style=\"text-align:center;font-size:20px;margin-bottom:20px\">网易宝有限公司商户专用回单</p> <div style=\"overflow:hidden\"> <div style=\"float:left;margin-bottom:10px;\">回单编号：2017101623PR12345678</div> <div style=\"float:right;\">打印时间: 2017-10-16 23:59:59</div> </div> <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"650\" height=\"300\" style=\"table-layout:fixed\"> <tr> <td colspan=\"24\"> <div style=\"float:left\">业务类型：转账到银行卡</div> <div style=\"float:right;margin-right:25px;\">币种：人民币&nbsp;/&nbsp;单位：元</div> </td> </tr> <tr> <td colspan=\"3\" rowspan=\"3\" class=\"label\">付款方</td> <td colspan=\"21\">账户名：网易宝</td> </tr> <tr> <td colspan=\"21\">账号：123123123123123</td> </tr> <tr> <td colspan=\"21\">开户机构：中信银行</td> </tr> <tr> <td colspan=\"3\" rowspan=\"3\" class=\"label\">收款方</td> <td colspan=\"21\">账户名：123132</td> </tr> <tr> <td colspan=\"21\">账号：123123123</td> </tr> <tr> <td colspan=\"21\">开户机构：网易宝测试</td> </tr> <tr> <td colspan=\"7\" class=\"label\">网易支付流水号</td> <td colspan=\"11\">12312312</td> <td colspan=\"6\" rowspan=\"4\" style=\"padding: 0px\"><img style=\"margin-left:15px\" width=\"120\" height=\"120\" src=\"https://epay.nosdn.127.net/d6544a29-30b3-40da-a050-4d27c6567b17.jpg\"/></td> </tr> <tr> <td colspan=\"7\" class=\"label\">交易状态</td> <td colspan=\"11\">成功</td> </tr> <tr> <td colspan=\"7\" class=\"label\">商户名称（公司名称）</td> <td colspan=\"11\">测试商户（网易宝）</td> </tr> <tr> <td colspan=\"7\" class=\"label\">记账时间（业务发起时间）</td> <td colspan=\"11\">2017-10-16 23:59:59</td> </tr> <tr> <td colspan=\"7\" class=\"label\">付款金额</td> <td colspan=\"6\">小写：700.00</td> <td colspan=\"11\">大写：七十八元整</td> </tr> <tr> <td colspan=\"7\" class=\"label\">备注</td> <td colspan=\"17\">想你了</td> </tr> </table> <div style=\"margin:15px 0 5px;\">重要提示：</div> <div>1、本回单有任何修改或涂改的，均为无效证明。</div> <div style=\"line-height:30px;\">2、本回单仅供参考，如与用户网易宝账户记录不一致的，以网易宝账户记录为准。</div> <div>3、本回单仅证明用户在网易支付对应的支付行为。</div> </div> </body> </html>";
	private static final String FONT = "./test/msyh.ttf";

	public static void main(String[] args) throws IOException, DocumentException, com.lowagie.text.DocumentException {

		//高级货
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		ITextRenderer render = new ITextRenderer();
		ITextFontResolver fontResolver = render.getFontResolver();
		fontResolver.addFont(FONT, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		// 解析html生成pdf
		render.setDocumentFromString(HTML);
		//解决图片相对路径的问题
		render.layout();
		render.createPDF(new FileOutputStream(DEST));
		render.createPDF(outStream);
















//		//推荐的方法打开PdfDecoder
//		PdfDecoder pdfDecoder = new PdfDecoder(true);
//		FontMappings.setFontReplacements();
//		//修改图片的清晰度
//		pdfDecoder.scaling = scaling;
//		ByteArrayOutputStream out = new ByteArrayOutputStream();
//		try {
//			//打开pdf文件，生成PdfDecoder对象
//			pdfDecoder.openPdfArray(bytes); //bytes is byte[] array with PDF
//			//获取第pageNum页的pdf
//			BufferedImage img = pdfDecoder.getPageAsImage(pageNum);
//
//			ImageIO.write(img, formatName, out);
//		} catch (PdfException e) {
//			e.printStackTrace();
//		} catch (IOException e){
//			e.printStackTrace();
//		}




	}


}