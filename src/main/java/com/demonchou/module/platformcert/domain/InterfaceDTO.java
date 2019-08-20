package com.demonchou.module.platformcert.domain;

import java.sql.Timestamp;

import lombok.Data;

/**
 * 商户新接口权限DTO类
 * @author hzxietao
 * @version $$ InterfaceDTO, 22/05/2017 hzxietao $$
 */
@Data
public class InterfaceDTO
{
	private String interfaceName; // 新接口权限名,主键,唯一
	private String interfaceDesc; // 新操权限描述
	private String interfaceVersion; // 新接口权限版本号
	private String dataType; // 数据类型, 如JSON/XML
	private String encode; // 编码方式,如GBK/UTF-8
	private Timestamp createTime; // 创建时间
	private Timestamp updateTime; // 更新时间
}
