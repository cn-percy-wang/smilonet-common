package com.smilonet.common.dao.mybatis;


public class UpdateRequest extends ExecuteRequest<UpdateParam> {

	public UpdateRequest(String executedStatementName, Object target) {
		this(executedStatementName, null, target);
	}

	public UpdateRequest(String executedStatementName, Object condition, Object target) {
		super(executedStatementName, new UpdateParam(condition, target));
	}

}
