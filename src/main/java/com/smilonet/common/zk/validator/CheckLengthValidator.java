package com.smilonet.common.zk.validator;

import org.apache.commons.lang3.StringUtils;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;

public class CheckLengthValidator extends AbstractValidator {

    @Override
    public void validate(ValidationContext ctx) {

        Integer length = (Integer) ctx.getBindContext().getValidatorArg("length");

        Boolean canBeEmpty = (Boolean) ctx.getBindContext().getValidatorArg("canBeEmpty");
        if (canBeEmpty == null) {
            canBeEmpty = true;
        }

        String checkedPropertyValue = (String) ctx.getProperty().getValue();
        String checkedPropertyName = (String) ctx.getProperty().getProperty();
        boolean isValid = true;

        if (!canBeEmpty && StringUtils.isEmpty(checkedPropertyValue)) {
            isValid = false;
        }

        if (isValid == true && length != null && checkedPropertyValue.length() != length) {
            isValid = false;
        }

        if (isValid == false) {

        }
    }

}
