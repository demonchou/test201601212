package excise.aspect;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 商户信息变更的时候获取对应的商户ID
 * 目前处理了 资质表、公司表、股东信息表
 * @author hzzhouhongfei
 * @version $$ PlatformInfoUpdateAspect, 2019/12/17 00:04 hzzhouhongfei $$
 */
@Slf4j
@Aspect
@Component
public class PlatformCertUpdateAspect
{
	@Autowired
//	private PlatformService platformService;

	@Pointcut("@annotation()")
	public void saveOrUpdatePlatformCertPointCut()
	{
		// 声明切点，无需代码实现。方法名称与下面的advisor上的advice中的value保持一直
	}

	@AfterReturning(value = "saveOrUpdatePlatformCertPointCut()")
	public void saveOrUpdatePlatformCert(JoinPoint joinPoint)
	{
		// 拿到请求参数
		Object[] args = joinPoint.getArgs();
		String companyId = StringUtils.EMPTY;
		for (Object arg : args)
		{
			if (arg != null)
			{
//				// 商户资质表处理
//				if (arg instanceof PlatformCert)
//				{
//					PlatformCert platformCert = (PlatformCert) arg;
//					companyId = platformCert.getCompanyId();
//					break;
//				}
//				// 商户公司表处理
//				if (arg instanceof PlatformCompany)
//				{
//					PlatformCompany platformCompany = (PlatformCompany) arg;
//					companyId = platformCompany.getCompanyId();
//					break;
//				}
//				// 商户股东信息表处理
//				if (arg instanceof PlatformStockholder)
//				{
//					PlatformStockholder platformStockholder = (PlatformStockholder) arg;
//					companyId = platformStockholder.getCompanyId();
//					break;
//				}
			}
		}
		if (StringUtils.isNotBlank(companyId))
		{
//			List<Platform> platformList = platformService.queryPlatformListByCompanyId(companyId);
//			if (CollectionUtils.isEmpty(platformList))
//			{
//				return;
//			}
//
//			List<String> platformIdList = Lists.transform(platformList, new Function<Platform, String>()
//			{
//				@Nullable
//				@Override
//				public String apply(@Nullable Platform platform)
//				{
//					return platform.getPlatformId();
//				}
//			});
//
//			// 添加redis，有效期为2天
//			String theDateOfToday = DateUtil.dateFormat(DateUtil.getCurrentTime(), DateUtil.FMT_DATE_SPECIAL);
//			for (String platformId : platformIdList)
//			{
//				PlatformRedisUtil.addSet(PlatformRedisConstant.UPDATED_PLATFORM_ID_REDIS_PREFIX + theDateOfToday,
//						platformId, PlatformRedisConstant.TWO_DAYS_MILLISECOND_TIMEOUT);
//			}
		}
	}

}
