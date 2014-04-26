package com.smilonet.common.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface IPageSearchService<T> {

    /**
     * Returns a {@link Page} of entities matching the given {@link Specification}.
     * 
     * @param spec
     * @param pageable
     * @return
     */
    Page<T> findPage(Object condition, Pageable pageable);

    /**
     * Returns a {@link Page} of entities matching the given {@link Specification}.
     * 
     * @param spec
     * @param pageable
     * @return
     */
    Page<T> findPage(Pageable pageable);
    
}
