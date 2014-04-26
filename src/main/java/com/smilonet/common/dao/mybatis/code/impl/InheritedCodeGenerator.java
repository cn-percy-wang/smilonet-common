package com.smilonet.common.dao.mybatis.code.impl;

import com.smilonet.common.dao.mybatis.code.CodeGenerator;
import com.smilonet.common.utils.StringUtils;

public class InheritedCodeGenerator implements CodeGenerator {

    /**
     * 每个级次流水号长度
     */
    private int unitSize = -2;

    /**
     * 父记录编码
     */
    private String parentCode;

    @Override
    public String generate(Long serial) {
        if (serial == null) {
            serial = 1L;
        }
        if (StringUtils.isNotEmpty(parentCode)) {
            return parentCode + StringUtils.formatStringLength(serial.toString(), '0', unitSize);
        } else {
            return StringUtils.formatStringLength(serial.toString(), '0', unitSize);
        }
    }

    public int getUnitSize() {
        return unitSize;
    }

    public void setUnitSize(int unitSize) {
        this.unitSize = unitSize;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

}
