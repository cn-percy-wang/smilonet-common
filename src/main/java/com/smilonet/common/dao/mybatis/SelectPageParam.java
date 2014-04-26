package com.smilonet.common.dao.mybatis;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Data
@EqualsAndHashCode(callSuper = true)
public class SelectPageParam extends SelectParam {

	protected PageRequest pager;

	public SelectPageParam(Object condition, Sort sort, PageRequest pager) {
		super(condition);
		this.pager = pager;
	}

}
