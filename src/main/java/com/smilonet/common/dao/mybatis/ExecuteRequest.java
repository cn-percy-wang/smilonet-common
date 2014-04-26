package com.smilonet.common.dao.mybatis;

import lombok.Data;

@Data
public class ExecuteRequest<T> {

	protected String executedStatementName;
	protected T param;

	public ExecuteRequest(String executedStatementName, T param) {
		this.executedStatementName = executedStatementName;
		this.param = param;
	}

}
