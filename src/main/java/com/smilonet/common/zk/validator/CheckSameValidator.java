package com.smilonet.common.zk.validator;

import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;

import com.smilonet.common.utils.StringUtils;

public class CheckSameValidator extends AbstractValidator {

    @Override
    public void validate(ValidationContext ctx) {

        String targetPropertyName = (String) ctx.getBindContext().getValidatorArg("targetPropertyName");
        String targetPropertyShowName = (String) ctx.getBindContext().getValidatorArg("targetPropertyShowName");
        String checkedPropertyName = (String) ctx.getProperty().getProperty();

        String targetPropertyValue = (String) ctx.getProperties(targetPropertyName)[0].getValue();
        String value = (String) ctx.getProperty().getValue();

        if (StringUtils.isEmpty(targetPropertyName) || StringUtils.isEmpty(targetPropertyShowName) || StringUtils.isEmpty(targetPropertyName)) {
            return;
        }

        if (value != null && !value.equals(targetPropertyValue)) {
            this.addInvalidMessage(ctx, checkedPropertyName, "与" + targetPropertyShowName + "不一致");
        }

    }

}
