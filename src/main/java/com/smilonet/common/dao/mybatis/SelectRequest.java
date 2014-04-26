package com.smilonet.common.dao.mybatis;

public class SelectRequest extends ExecuteRequest<SelectParam> {

	public SelectRequest(String executedStatementName) {
		this(executedStatementName, null);
	}

	public SelectRequest(String executedStatementName, Object param) {
		super(executedStatementName, new SelectParam(param));
	}

}
