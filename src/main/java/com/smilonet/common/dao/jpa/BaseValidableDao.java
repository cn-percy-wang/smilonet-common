package com.smilonet.common.dao.jpa;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.smilonet.common.entity.IValidable;

public interface BaseValidableDao<T extends IValidable<ID>, ID extends Serializable> extends BaseDao<T, ID> {

    T findOneValid(final Specification<T> spec);
    
	List<T> findAllValid();

    List<T> findAllInvalid();

    List<T> findAllValid(Sort sort);

    List<T> findAllInvalid(Sort sort);

    Page<T> findValid(Pageable pageable);

    Page<T> findInvalid(Pageable pageable);

    Page<T> findValid(final Specification<T> spec, Pageable pageable);

    List<T> findValid(final Specification<T> spec);

    List<T> findValid(final Specification<T> spec, Sort sort);

    Page<T> findInValid(final Specification<T> spec, Pageable pageable);

    List<T> findInValid(final Specification<T> spec);

    List<T> findInValid(final Specification<T> spec, Sort sort);

    long countAllValid();

    long countAllInvalid();

    long countValid(final Specification<T> spec);

    void setInvalid(T entity);

    void setInvalid(ID... ids);

    void setInvalid(Iterable<? extends T> entities);

    void setValid(T entity);
    
    void setValid(ID... ids);

    void setValid(Iterable<? extends T> entities);

}
