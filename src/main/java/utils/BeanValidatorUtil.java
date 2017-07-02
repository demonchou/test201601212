package utils;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.hibernate.validator.HibernateValidator;
import org.springframework.util.CollectionUtils;

/**
 * Description:
 * Created by hzzhouhongfei.
 * 2017/6/9 下午11:16
 */
public final class BeanValidatorUtil
{
	private static final Validator validator = Validation.byProvider(HibernateValidator.class).configure()
			.failFast(true).buildValidatorFactory().getValidator();
//	private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	public static <T> ValidateResult validateObject(T t, Class<?>... group)
	{
		Set<ConstraintViolation<T>> violations = validator.validate(t, group);
		return translateViolations(violations);
	}

	public static <T> ValidateResult validateProperty(T object, String propertyName, Class<?>... groups)
	{
		Set<ConstraintViolation<T>> violations = validator.validateProperty(object, propertyName, groups);
		return translateViolations(violations);
	}

	private static <T> ValidateResult translateViolations(Set<ConstraintViolation<T>> violations)
	{
		ValidateResult errors = new ValidateResult();

		if (!CollectionUtils.isEmpty(violations))
		{
			for (ConstraintViolation<T> violation : violations)
			{
				String messageId = violation.getMessage();
				String propertyName = violation.getPropertyPath().toString();
				errors.setError(propertyName, messageId);
			}
			errors.setHasErrors(true);
		}
		return errors;
	}
//	public String getErrorDesc()
//	{
//		return errors.values().toString();
//	}
}
