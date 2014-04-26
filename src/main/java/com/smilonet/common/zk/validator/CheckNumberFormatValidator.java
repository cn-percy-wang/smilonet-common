package com.smilonet.common.zk.validator;

import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;

import com.smilonet.common.utils.RegExp;
import com.smilonet.common.utils.StringUtils;

public class CheckNumberFormatValidator extends AbstractValidator {

    @Override
    public void validate(ValidationContext ctx) {

        Integer integerLength = (Integer) ctx.getBindContext().getValidatorArg("integerLength");

        Integer fractionLength = (Integer) ctx.getBindContext().getValidatorArg("fractionLength");

        Boolean canBeEmpty = (Boolean) ctx.getBindContext().getValidatorArg("canBeEmpty");
        if (canBeEmpty == null) {
            canBeEmpty = true;
        }

        String checkedPropertyValue = (String) ctx.getProperty().getValue();
        String checkedPropertyName = (String) ctx.getProperty().getProperty();
        boolean isValid = true;

        if (!StringUtils.matchReg(checkedPropertyValue, RegExp.NUMBER)) {
            isValid = false;
        }

        if (checkedPropertyValue.contains(".")) {
            int dotPosition = checkedPropertyValue.charAt('.');
            int factIntegerLength = dotPosition;
            // 判断小数部分长度是否合法
            if (fractionLength > 0) {
                int factFractionLength = checkedPropertyValue.length() - dotPosition;
                if (factFractionLength > fractionLength) {
                    isValid = false;
                }
            }

            // 判断整数部分长度是否合法
            if (integerLength != null) {
                if (factIntegerLength > integerLength) {
                    isValid = false;
                }
            }
        } else {
            // 判断小数部分是否合法
            if (fractionLength != null) {
                isValid = false;
            }
            // 判断整数部分是否合法
            if (integerLength != null) {
                if (checkedPropertyValue.length() > integerLength) {
                    isValid = false;
                }
            }
        }

        if (isValid == false) {

        }
    }

}
