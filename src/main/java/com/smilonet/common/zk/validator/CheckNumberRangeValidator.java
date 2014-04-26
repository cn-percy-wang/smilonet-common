package com.smilonet.common.zk.validator;

import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;

import com.smilonet.common.utils.RegExp;
import com.smilonet.common.utils.StringUtils;

public class CheckNumberRangeValidator extends AbstractValidator {

    @Override
    public void validate(ValidationContext ctx) {

        Double max = (Double) ctx.getBindContext().getValidatorArg("max");

        Double min = (Double) ctx.getBindContext().getValidatorArg("min");

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

        Double doubleValue = Double.valueOf(checkedPropertyValue);

        if (min != null && doubleValue < min) {
            isValid = false;
        }

        if (max != null && doubleValue > max) {
            isValid = false;
        }

        if (isValid == false) {

        }
    }

}