/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smilonet.common.dao.jpa;

import static org.springframework.data.jpa.repository.query.QueryUtils.toOrders;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.data.jpa.repository.support.LockMetadataProvider;
import org.springframework.util.Assert;

import com.smilonet.common.entity.IActivable;
import com.smilonet.common.entity.IDefaultable;
import com.smilonet.common.entity.IHasCode;
import com.smilonet.common.entity.IInheritable;
import com.smilonet.common.entity.IPersistable;
import com.smilonet.common.entity.IValidable;

/**
 *
 * @author wanglong
 */
public class BaseDaoImpl<T extends IPersistable<ID>, ID extends Serializable> implements BaseDao<T, ID> {
  	
    private EntityManager entityManager;
    private Class<T> clazz;

	private LockMetadataProvider lockMetadataProvider;
    
    private boolean isDefaultable = false;
    private boolean isActivable = false;
    private boolean isValidable = false;
    private boolean isInheritable = false;
    private boolean isHasCode = false;
    
    public BaseDaoImpl() {
    	Type[] types = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments();

        if (types[0] instanceof ParameterizedType) {
            ParameterizedType type = (ParameterizedType) types[0];
            clazz = (Class<T>) type.getRawType();
        } else {
            clazz = (Class<T>) types[0];
        }
        
        checkGenericClass();
    }

	/**
	 * Configures a custom {@link LockMetadataProvider} to be used to detect {@link LockModeType}s to be applied to
	 * queries.
	 * 
	 * @param lockMetadataProvider
	 */
	public void setLockMetadataProvider(LockMetadataProvider lockMetadataProvider) {
		this.lockMetadataProvider = lockMetadataProvider;
	}

    private void checkGenericClass() {
        for (@SuppressWarnings("rawtypes") Class i : clazz.getInterfaces()) {
            if (i == IDefaultable.class) {
                isDefaultable = true;
            } else if (i == IActivable.class) {
                isActivable = true;
            } else if (i == IValidable.class) {
            	isValidable = true;
            } else if (i == IInheritable.class) {
                isInheritable = true;
            } else if (i == IHasCode.class) {
                isHasCode = true;
            }
        }
    }

    /**
     * Set entity manager.
     * 
     * @param entityManager entity manager
     */
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public T getReference(ID id) {
        return (T) entityManager.getReference(clazz, id);
    }

    public <S extends T> S save(S entity) {
        if (entity.getId() != null) {
        	preUpdate(entity);
            return entityManager.merge(entity);
        } else {
            entityManager.persist(entity);
            return entity;
        }
    }

    public void save(final T... entitys) {
        for (T entity : entitys) {
            save(entity);
        }
    }

    public void delete(final ID id) throws UnsupportedOperationException {
        delete(getReference(id));
    }

    public void delete(final ID... ids) throws UnsupportedOperationException {
    	for (ID id : ids) {
    		delete(id);
    	}
    }

    public void delete(final T object) throws UnsupportedOperationException {
        if (isDefaultable) {
            checkIfDefault(object);
        }
        if (isValidable) {
            ((IValidable) object).setIsValid(false);
            entityManager.merge(object);
        } else {
            entityManager.remove(object);
        }
    }

    public void delete(final T... objects) throws UnsupportedOperationException {
//        delete(Arrays.asList(objects));
    }

    public void deleteAll() throws UnsupportedOperationException {
//        deleteAll(getAll());
    }

    private void checkIfDefault(T entity) {
        if (((IDefaultable) entity).isDefault()) {
            throw new UnsupportedOperationException("can't delete default entity " + clazz + "#" + entity.getId());
        }
    }

    public void refresh(final T entity) {
        entityManager.refresh(entity);
    }

    public void flushAndClear() {
        entityManager.flush();
        entityManager.clear();
    }

    /**
     * Get entity manager.
     * 
     * @return entity manager
     */
    protected EntityManager getEntityManager() {
        return entityManager;
    }
    
	protected void preUpdate(final Object entity) {
    }

	@Override
	public T find(ID id) {
		return entityManager.find(clazz, id);
	}

	@Override
	public List<T> find(Specification<T> spec) {
		return null;
	}

	@Override
	public T findOne(Specification<T> spec) {
		return null;
	}

	@Override
	public List<T> find(Iterable<ID> ids) {
		return null;
	}

	@Override
	public boolean exists(ID id) {
		return false;
	}

	@Override
	public List<T> findAll() {
		return null;
	}

	@Override
	public List<T> findAll(Sort sort) {
		return null;
	}

	@Override
	public List<T> find(Specification<T> spec, Sort sort) {
		return null;
	}

	@Override
	public Page<T> findAll(Pageable pageable) {
		return null;
	}

	@Override
	public Page<T> find(Specification<T> spec, Pageable pageable) {
		return null;
	}

	@Override
	public long countAll() {
		return 0;
	}

	@Override
	public long count(Specification<T> spec) {
		return 0;
	}

	@Override
	public void deleteInBatch(Iterable<? extends ID> entityIds) {
		
	}

	@Override
	public void deleteAllInBatch() {
		
	}

	@Override
	public void flush() {
		
	}

	@Override
	public <S extends T> List<S> save(Iterable<S> entities) {
		return null;
	}

	@Override
	public T saveAndFlush(T entity) {
		return null;
	}

	@Override
	public <S extends T> List<S> saveAndFlush(Iterable<S> entities) {
		return null;
	}
	
	/**
	 * Reads the given {@link TypedQuery} into a {@link Page} applying the given {@link Pageable} and
	 * {@link Specification}.
	 * 
	 * @param query must not be {@literal null}.
	 * @param spec can be {@literal null}.
	 * @param pageable can be {@literal null}.
	 * @return
	 */
	private Page<T> readPage(TypedQuery<T> query, Pageable pageable, Specification<T> spec) {

		query.setFirstResult(pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());

		Long total = QueryUtils.executeCountQuery(getCountQuery(spec));
		List<T> content = total > pageable.getOffset() ? query.getResultList() : Collections.<T> emptyList();

		return new PageImpl<T>(content, pageable, total);
	}

	/**
	 * Creates a new {@link TypedQuery} from the given {@link Specification}.
	 * 
	 * @param spec can be {@literal null}.
	 * @param pageable can be {@literal null}.
	 * @return
	 */
	private TypedQuery<T> getQuery(Specification<T> spec, Pageable pageable) {

		Sort sort = pageable == null ? null : pageable.getSort();
		return getQuery(spec, sort);
	}

	/**
	 * Creates a {@link TypedQuery} for the given {@link Specification} and {@link Sort}.
	 * 
	 * @param spec can be {@literal null}.
	 * @param sort can be {@literal null}.
	 * @return
	 */
	private TypedQuery<T> getQuery(Specification<T> spec, Sort sort) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(clazz);

		Root<T> root = applySpecificationToCriteria(spec, query);
		query.select(root);

		if (sort != null) {
			query.orderBy(toOrders(sort, root, builder));
		}

		return applyLockMode(entityManager.createQuery(query));
	}

	/**
	 * Creates a new count query for the given {@link Specification}.
	 * 
	 * @param spec can be {@literal null}.
	 * @return
	 */
	private TypedQuery<Long> getCountQuery(Specification<T> spec) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);

		Root<T> root = applySpecificationToCriteria(spec, query);

		if (query.isDistinct()) {
			query.select(builder.countDistinct(root));
		} else {
			query.select(builder.count(root));
		}

		return entityManager.createQuery(query);
	}

	/**
	 * Applies the given {@link Specification} to the given {@link CriteriaQuery}.
	 * 
	 * @param spec can be {@literal null}.
	 * @param query must not be {@literal null}.
	 * @return
	 */
	private <S> Root<T> applySpecificationToCriteria(Specification<T> spec, CriteriaQuery<S> query) {

		Assert.notNull(query);
		Root<T> root = query.from(clazz);

		if (spec == null) {
			return root;
		}

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		Predicate predicate = spec.toPredicate(root, query, builder);

		if (predicate != null) {
			query.where(predicate);
		}

		return root;
	}

	private TypedQuery<T> applyLockMode(TypedQuery<T> query) {

		LockModeType type = lockMetadataProvider == null ? null : lockMetadataProvider.getLockModeType();
		return type == null ? query : query.setLockMode(type);
	}
}
