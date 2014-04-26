package com.smilonet.common.dao.mybatis;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Data
@EqualsAndHashCode(callSuper = true)
public class SelectPageRequest extends ExecuteRequest<SelectPageParam> {

	protected String dataStatementName;
	protected String countStatementName;

	public SelectPageRequest(String countStatementName, String dataStatementName, PageRequest pager) {
		this(countStatementName, dataStatementName, null, null, pager);
	}

	public SelectPageRequest(String countStatementName, String dataStatementName, PageRequest pager, Sort sort) {
		this(countStatementName, dataStatementName, null, sort, pager);
	}

	public SelectPageRequest(String countStatementName, String dataStatementName, Object condition, PageRequest pager) {
		this(countStatementName, dataStatementName, condition, null, pager);
	}

	public SelectPageRequest(String countStatementName, String dataStatementName, Object condition, Sort sort, PageRequest pager) {
		super(null, new SelectPageParam(condition, sort, pager));
		this.dataStatementName = dataStatementName;
		this.countStatementName = countStatementName;
	}
	
	public void setAsCountMode() {
		this.executedStatementName = this.countStatementName;
	}
	
	public void setAsResultMode() {
		this.executedStatementName = this.dataStatementName;
	}

}
