package com.smilonet.common.dao.mybatis;

import lombok.Data;

@Data
public class UpdateParam {

	protected Object condition;
	protected Object target;

	public UpdateParam(Object condition, Object target) {
		this.condition = condition;
		this.target = target;
	}

}
