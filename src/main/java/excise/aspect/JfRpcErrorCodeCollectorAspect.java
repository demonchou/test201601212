//package excise.aspect;
//
//import org.apache.commons.lang3.StringUtils;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//
//import com.netease.epay.coupon.app.enums.ErrorCodeEnum;
//import com.netease.epay.coupon.client.domain.ResponseDto;
//import com.netease.epay.coupon.client.domain.ResultDto;
//import com.netease.epay.coupon.jifen.sentry.collector.JfErrorCodeCollector;
//import com.netease.epay.coupon.jifen.util.JsonUtil;
//
//import lombok.extern.slf4j.Slf4j;
//
///**
// * 积分RPC错误码采集切面
// * @author hzzhouhongfei
// * @version $$ JfRpcErrorCodeCollectorAspect, 2022/8/10 15:19 hzzhouhongfei $$
// */
//@Aspect
//@Component
//@Slf4j
//public class JfRpcErrorCodeCollectorAspect
//{
//	@Pointcut(value = "execution (* com.netease.epay.coupon.jifen.rpc.*.* (..))")
//	public void jfRpcResponseDtoPointCut()
//	{
//		// 积分RPC响应切点
//	}
//
//	@AfterReturning(value = "jfRpcResponseDtoPointCut()", returning = "retValue")
//	public void afterJfRpcResponseDtoReturned(Object retValue)
//	{
//		try
//		{
//			String code = StringUtils.EMPTY;
//			String codeEn = StringUtils.EMPTY;
//			String msg = StringUtils.EMPTY;
//			if (retValue instanceof ResponseDto)
//			{
//				ResponseDto responseDto = (ResponseDto) retValue;
//				if (responseDto.isSuccess())
//				{
//					code = ErrorCodeEnum.SUCCESS.getCode();
//					codeEn = ErrorCodeEnum.SUCCESS.getCodeEn();
//					msg = StringUtils.isBlank(responseDto.getMsg()) ?
//							ErrorCodeEnum.SUCCESS.getDesc() :
//							responseDto.getMsg();
//				}
//				else
//				{
//					code = responseDto.getCode();
//					codeEn = responseDto.getCodeEn();
//					msg = responseDto.getMsg();
//				}
//			}
//			else if (retValue instanceof ResultDto)
//			{
//				ResultDto resultDto = (ResultDto) retValue;
//				if (resultDto.isSuccess())
//				{
//					code = ErrorCodeEnum.SUCCESS.getCode();
//					codeEn = ErrorCodeEnum.SUCCESS.getCodeEn();
//					msg = StringUtils.isBlank(resultDto.getMsg()) ?
//							ErrorCodeEnum.SUCCESS.getDesc() :
//							resultDto.getMsg();
//				}
//				else
//				{
//					code = resultDto.getCode();
//					codeEn = resultDto.getCodeEn();
//					msg = resultDto.getMsg();
//				}
//			}
//			else
//			{
//				log.error("【E1】不兼容的返回结果类，返回结果类：{}", retValue.getClass());
//			}
//
//			JfErrorCodeCollector.onCollect(code, codeEn, msg);
//
//		}
//		catch (Exception e)
//		{
//			log.error("【E2】积分RPC接口响应结果切面处理异常，接口返回值：{}，异常原因：{}", JsonUtil.objectToJson(retValue), e.getMessage(), e);
//		}
//	}
//}
