package com.demonchou.common.aop;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.demonchou.exception.IllegalBusinessArgumentException;

import common.domain.dto.ResponseDto;

/**
 * dubbo 统一异常拦截处理
 * 
 * copy from epay
 * 
 * @author hzmaojuntian
 */

@Component
@Aspect
@Order(1)
public class ProxyExceptionReturnAspect
{
	private Log log = LogFactory.getLog(ProxyExceptionReturnAspect.class);
	
	@Around("execution(* com.demonchou.service..*.*(..)) && @annotation(com.demonchou.common.aop.annotation.CouponProxyExceptionReturn)")
	public Object profile(ProceedingJoinPoint pjp) throws Throwable
	{
		log.info("进入aop：" + pjp.getSignature().toShortString());

		try
		{
			Object resp = pjp.proceed();
//			if (log.isDebugEnabled())
//			{
//				log.debug("wallet response:\t" + JsonUtil.objectToJson(resp));
//			}
			return resp;
		}
		catch (IllegalBusinessArgumentException ibae)
		{
			log.warn("服务业务参数非法:" + getClass().getSimpleName() + "异常信息：" + ibae.getMessage(), ibae);
			return getHandlerReuslt(pjp, "OperationResp.BIZ_PARAM_ILLEGAL.getCode()", ibae.getMessage());
		}
		catch (IllegalArgumentException iae)
		{
			log.error("服务参数非法:" + getClass().getSimpleName() + "异常信息：" + iae.getMessage(), iae);
			return getHandlerReuslt(pjp, "OperationResp.PARAM_ILLEGAL.getCode()", iae.getMessage());
		}
		catch (IllegalStateException ise)
		{
			log.error("服务业务状态异常:" + getClass().getSimpleName() + "异常信息：" + ise.getMessage(), ise);
			return getHandlerReuslt(pjp, "OperationResp.BUSINESS_STATE_EXCEPTION.getCode()", ise.getMessage());
		}
//		catch (CouponBusinessException be)
//		{
//			log.warn("业务异常:" + getClass().getSimpleName() + ",异常信息：" + be.getMessage(), be);
//			return getHandlerReuslt(pjp, be.getErrorCode().getCode(), be.getMessage());
//		}
		catch (Exception e)
		{
			log.error("系统异常:" + getClass().getSimpleName() + ",异常信息：" + e.getMessage(), e);
			return getHandlerReuslt(pjp, "OperationResp.SYSTEM_ERR.getCode()", e.getMessage());
		}
	}
	
	/**
	 * 获取异常处理结果
	 * @param pjp 切点
	 * @param errorCode 错误码
	 * @param errorMsg 错误信息
	 */
	private ResponseDto getHandlerReuslt(ProceedingJoinPoint pjp, String errorCode, String errorMsg)
	{
		ResponseDto baseResponse = constructMethodReturnInstance(pjp);
		baseResponse.setErrorCode(errorCode);
		return baseResponse;
	}

	/**
	 * 构造被拦截方法的返回对象
	 * @param pjp 切点
	 */
	private ResponseDto constructMethodReturnInstance(ProceedingJoinPoint pjp)
	{
		Signature signature = pjp.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		Method method = methodSignature.getMethod();
		try
		{
			return (ResponseDto) method.getReturnType().newInstance();
		}
		catch (InstantiationException e)
		{
			log.error("构造被拦截方法返回对象异常[class:" + method.getDeclaringClass().getName() + "method:" + method.getName()
					+ ", returnType:" + method.getReturnType().getName() + "]", e);
			return new ResponseDto();
		}
		catch (IllegalAccessException e)
		{
			log.error("构造被拦截方法返回对象异常[class:" + method.getDeclaringClass().getName() + "method:" + method.getName()
					+ ", returnType:" + method.getReturnType().getName() + "]", e);
			return new ResponseDto();
		}
	}

}
