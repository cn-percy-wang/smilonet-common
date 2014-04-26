package com.smilonet.common.dao.mybatis.code.impl;

import java.util.Date;

import com.smilonet.common.dao.mybatis.code.CodeGenerator;
import com.smilonet.common.utils.DateTimeUtils;
import com.smilonet.common.utils.StringUtils;

public class TimestampCodeGenerator implements CodeGenerator {

    private String prefix;
    private String middlefixPattern = "yyyyMMdd";
    private int postfixSize = -2;

    @Override
    public String generate(Long serial) {
        StringBuffer code = new StringBuffer();

        if (StringUtils.isNotEmpty(prefix)) {
            code.append(prefix);
        }

        if (StringUtils.isNotEmpty(middlefixPattern)) {
            try {
                code.append(DateTimeUtils.convertDateToString(new Date(), middlefixPattern));
            } catch (Exception e) {
                code.append(DateTimeUtils.getDate("yyyyMMdd"));
            }
        }
        code.append(StringUtils.formatStringLength(serial.toString(), '0', postfixSize));
        return code.toString();
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getMiddlefixPattern() {
        return middlefixPattern;
    }

    public void setMiddlefixPattern(String middlefixPattern) {
        this.middlefixPattern = middlefixPattern;
    }

    public int getPostfixSize() {
        return postfixSize;
    }

    public void setPostfixSize(int postfixSize) {
        this.postfixSize = postfixSize;
    }

}
