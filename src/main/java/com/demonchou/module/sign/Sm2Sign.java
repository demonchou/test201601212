//package com.demonchou.module.sign;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import cfca.svs.api.ClientEnvironment;
//import cfca.svs.api.SVBusiness;
//import cfca.svs.api.util.IOUtil;
//import cfca.svs.api.util.XmlUtil;
//import lombok.extern.slf4j.Slf4j;
//
///**
// *
// * @author hzzhouhongfei
// * @version $$ Sm2Sign, 2018/12/26 21:19 hzzhouhongfei $$
// */
//@Slf4j
//public class Sm2Sign
//{
//
//	public static void main(String[] args) throws Exception
//	{
//		String filePath = "/Users/sars/Desktop/temp/CCMSZDT0414_20181225.XML";
////		String filePath = "";
//		init();
//		SVBusiness svBusiness = new SVBusiness();
//
//		// 批量上传证书
//		String uploadCerts = IOUtil.readFile2(filePath);
//		String typeInfo = "批量上传公钥证书";
//		String response = svBusiness.batchUploadUserCerts(uploadCerts);
//		boolean verifyRes = dealResponse(response, typeInfo);
//		if (!verifyRes)
//		{
//			System.out.println(typeInfo + "不成功");
//		}
//		else
//		{
//			System.out.println(typeInfo + "成功");
//		}
//
//	}
//
//	static void init()
//	{
//		Map<String, String> clientParameters = new HashMap<>(8);
//		clientParameters.put("receiveTimeout", "5000");
//		clientParameters.put("logbackConfigPath", "5000");
//		try
//		{
//			ClientEnvironment
//					.initClientEnvironmentByParam(new String[] { "115.238.122.131" }, "8000", "5000", clientParameters);
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//	}
//
//	static boolean dealResponse(String response, String typeInfo)
//	{
//		if ("".equals(response))
//		{
//			System.out.println("可能发生通讯异常");
//		}
//		String errorCode = XmlUtil.getNodeText(response, "ErrorCode");
//		if ("0".equals(errorCode))
//		{
//			System.out.println(typeInfo + "成功");
//			System.out.println(XmlUtil.getNodeText(response, "SignatureBase64"));
//			return true;
//		}
//		else
//		{
//			System.out.println(typeInfo + "失败");
//			System.out.println("失败信息为:" + errorCode + " 错误信息为:" + XmlUtil.getNodeText(response, "ErrorDesc"));
//			return false;
//		}
//	}
//}
