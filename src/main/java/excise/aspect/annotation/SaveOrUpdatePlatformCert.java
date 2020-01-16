package excise.aspect.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于商户资质信息变更时，收集涉及到的公司ID对应的商户ID
 * 目前涉及股东信息变更、公司表信息变更、资质表变更
 * 具体看对应aspect内部实现
 * @author hzzhouhongfei
 * @version $$ SaveOrUpdatePlatformCert, 2019/12/17 10:06 hzzhouhongfei $$
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SaveOrUpdatePlatformCert
{

}
