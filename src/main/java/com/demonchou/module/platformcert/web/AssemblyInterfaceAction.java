package com.demonchou.module.platformcert.web;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.demonchou.common.utils.ExcelUtil;
import com.demonchou.module.platformcert.domain.InterfaceDTO;

/**
 *
 * @author hzzhouhongfei
 * @version $$ AssemblyInterfaceAction, 2019-06-02 20:13 hzzhouhongfei $$
 */
public class AssemblyInterfaceAction
{
	public static void main(String[] args)
	{
		ExcelUtil excelUtil = null;
		try
		{
			excelUtil = new ExcelUtil(new File("/Users/sars/Desktop/temp/线下批量数据处理 (190531).xlsx"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		int totalRowNum = excelUtil.getRowNumExcludeBlankRow(0) - 1;
		int totalLogicRowNum = excelUtil.getRowNum(0);
		String[] productPowerDetail = null;

		List<InterfaceDTO> interfaceDTOList = new ArrayList<>();

		try
		{
			for (int i = 1; i <= totalRowNum; i++)
			{
				productPowerDetail = excelUtil.getRowData(0, i);


				String interfaceName= StringUtils.trimToEmpty(productPowerDetail[0]);
				String interfaceDesc = StringUtils.trimToEmpty(productPowerDetail[1]);
				String interfaceVersion = StringUtils.trimToEmpty(productPowerDetail[2]);
				String dataType = StringUtils.trimToEmpty(productPowerDetail[3]);
				String encode = StringUtils.trimToEmpty(productPowerDetail[4]);

				InterfaceDTO interfaceDTO = new InterfaceDTO();
				interfaceDTO.setInterfaceName(interfaceName);
				interfaceDTO.setInterfaceDesc(interfaceDesc);
				interfaceDTO.setInterfaceVersion(interfaceVersion);
				interfaceDTO.setDataType(dataType);
				interfaceDTO.setEncode(encode);

				interfaceDTOList.add(interfaceDTO);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		System.out.println("===>totalRowNum:" + totalRowNum);

		System.out.println("===>totalRowNumIncludeBlank:" + totalLogicRowNum);


		String interfaceDTOListStr = JSONObject.toJSONString(interfaceDTOList);

		System.err.println("===>interfaceDTOListStr：" + interfaceDTOListStr);

		List<InterfaceDTO> interfaceDTOList1 = JSONObject.parseArray(interfaceDTOListStr, InterfaceDTO.class);


		System.err.println(JSONObject.toJSONString(interfaceDTOList1));
	}
}
