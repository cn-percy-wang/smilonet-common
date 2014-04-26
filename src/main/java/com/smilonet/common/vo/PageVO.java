package com.smilonet.common.vo;

import org.springframework.data.domain.Page;

public class PageVO<E> extends BaseVO {

    private static final long serialVersionUID = -8747888807491615354L;

    private Page<E> page;

    public PageVO() {
        super(SUCCESS);
    }

    public PageVO(Page<E> page) {
        super(SUCCESS);
        this.page = page;
    }

    public Page<E> getPage() {
        return page;
    }

    public void setPage(Page<E> page) {
        this.page = page;
    }

}
