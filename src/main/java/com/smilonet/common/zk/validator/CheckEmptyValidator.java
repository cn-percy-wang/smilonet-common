package com.smilonet.common.zk.validator;

import org.apache.commons.lang3.StringUtils;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;

public class CheckEmptyValidator extends AbstractValidator {

    @Override
    public void validate(ValidationContext ctx) {

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

        if (isValid == false) {
            this.addInvalidMessage(ctx, checkedPropertyName, "不能为空");
        }
    }

}
