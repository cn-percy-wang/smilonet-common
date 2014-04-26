package com.smilonet.common.dao.mybatis;

import org.springframework.data.domain.Sort;

public class SortableSelectRequest extends ExecuteRequest<SortableSelectParam> {

	public SortableSelectRequest(String executedStatementName) {
		this(executedStatementName, null, null);
	}

	public SortableSelectRequest(String executedStatementName, Sort sort) {
		this(executedStatementName, null, sort);
	}

	public SortableSelectRequest(String executedStatementName, Object param) {
		this(executedStatementName, param, null);
	}

	public SortableSelectRequest(String executedStatementName, Object param, Sort sort) {
		super(executedStatementName, new SortableSelectParam(param, sort));
	}

}
