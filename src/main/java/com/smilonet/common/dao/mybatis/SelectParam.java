package com.smilonet.common.dao.mybatis;

import lombok.Data;

@Data
public class SelectParam {

	protected Object condition;

	public SelectParam(Object condition) {
		this.condition = condition;
	}

}
