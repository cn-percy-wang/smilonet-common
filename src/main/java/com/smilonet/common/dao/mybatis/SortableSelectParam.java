package com.smilonet.common.dao.mybatis;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.data.domain.Sort;

@Data
@EqualsAndHashCode(callSuper = true)
public class SortableSelectParam extends SelectParam {

	protected Sort sort;

	public SortableSelectParam(Object condition, Sort sort) {
		super(condition);
		this.sort = sort;
	}

}
