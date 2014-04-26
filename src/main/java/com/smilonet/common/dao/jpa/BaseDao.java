package com.smilonet.common.dao.jpa;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.smilonet.common.entity.IPersistable;

public interface BaseDao<T extends IPersistable<ID>, ID extends Serializable> {

    public T getReference(ID id);

	T find(ID id);

    List<T> find(final Specification<T> spec);

	List<T> find(Iterable<ID> ids);

	boolean exists(ID id);

	List<T> findAll();
	
	List<T> findAll(Sort sort);

    T findOne(final Specification<T> spec);

    List<T> find(final Specification<T> spec, Sort sort);

	Page<T> findAll(Pageable pageable);

    Page<T> find(final Specification<T> spec, Pageable pageable);
    
    void refresh(final T entity);

	long countAll();

	long count(final Specification<T> spec);

	void delete(ID id);

	void delete(T entity);

	void deleteInBatch(Iterable<? extends ID> entityIds);

	void deleteAll();

	void deleteAllInBatch();
	
	void flush();
	
	<S extends T> S save(S entity);

	<S extends T> List<S> save(Iterable<S> entities);

	T saveAndFlush(T entity);

	<S extends T> List<S> saveAndFlush(Iterable<S> entities);
	
	void flushAndClear();
}
