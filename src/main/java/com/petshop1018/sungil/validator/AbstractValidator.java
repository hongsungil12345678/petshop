package com.petshop1018.sungil.validator;

import com.petshop1018.sungil.dto.MemberDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * 중복 검사 유효성 검증, Validator 구현한 추상 클래스
 * */
@Slf4j
public abstract class AbstractValidator<T> implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return getSupportedClass().isAssignableFrom(clazz);
    }
    @SuppressWarnings("unchecked")
    @Override
    public void validate(Object target, Errors errors) {
        try {
            doValidate((T) target, errors);
        } catch (IllegalStateException e) {
            log.error("중복 검증 에러", e);
            errors.reject("duplicate.validation.error", "검증 중 오류가 발생했습니다.");
        }
    }

    // 수정 -> 해당 타입만 valid 처리 하기 위해서
    protected abstract Class<T> getSupportedClass();
    //protected abstract Class<MemberDto.Request>getSupportedClass(); // 모든 모든 타입 <MemberDto.Request>로 받아서 에러남

    /* 유효성 검증 로직 */
    protected abstract void doValidate(final T dto, final Errors errors);
}
