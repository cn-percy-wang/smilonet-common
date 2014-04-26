package com.smilonet.common.dao.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.smilonet.common.entity.IValidable;

public class BaseValidableDaoImpl<T extends IValidable<ID>, ID extends Serializable> extends BaseDaoImpl<T, ID> implements BaseValidableDao<T, ID> {

    @Override
    public List<T> findAllValid() {
        return super.find(new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get(IValidable.P_IS_VALID), true);
            }
        });
    }

    @Override
    public List<T> findAllInvalid() {
        return super.find(new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get(IValidable.P_IS_VALID), false);
            }
        });
    }

    @Override
    public List<T> findAllValid(Sort sort) {
        return super.find(new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get(IValidable.P_IS_VALID), true);
            }
        }, sort);
    }

    @Override
    public List<T> findAllInvalid(Sort sort) {
        return super.find(new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get(IValidable.P_IS_VALID), false);
            }
        }, sort);
    }

    @Override
    public Page<T> findValid(Pageable pageable) {
        return super.find(new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get(IValidable.P_IS_VALID), true);
            }
        }, pageable);
    }

    @Override
    public Page<T> findInvalid(Pageable pageable) {
        return super.find(new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get(IValidable.P_IS_VALID), false);
            }
        }, pageable);
    }

    @Override
    public long countAllValid() {
        return super.count(new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get(IValidable.P_IS_VALID), true);
            }
        });
    }

    @Override
    public long countAllInvalid() {
        return super.count(new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get(IValidable.P_IS_VALID), false);
            }
        });
    }

    @Override
    public void setInvalid(T entity) {
    	if (entity == null) {
    		return;
    	}
        entity.setIsValid(false);
        super.save(entity);
    }

    @Override
    public void setInvalid(Iterable<? extends T> entities) {
        for (T entity : entities) {
            entity.setIsValid(false);
        }
        super.save(entities);
    }

    @Override
    public void setValid(T entity) {
    	if (entity == null) {
    		return;
    	}
        entity.setIsValid(true);
        super.save(entity);
    }

    @Override
    public void setValid(Iterable<? extends T> entities) {
        for (T entity : entities) {
            entity.setIsValid(true);
        }
        super.save(entities);
    }

    @Override
    public T findOneValid(final Specification<T> spec) {
        return super.findOne(new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.and(cb.equal(root.get(IValidable.P_IS_VALID), true), spec.toPredicate(root, query, cb));
            }
        });
    }

    @Override
    public List<T> findValid(final Specification<T> spec) {
        return super.find(new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.and(cb.equal(root.get(IValidable.P_IS_VALID), true), spec.toPredicate(root, query, cb));
            }
        });
    }

    @Override
    public Page<T> findValid(final Specification<T> spec, Pageable pageable) {
        return super.find(new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.and(cb.equal(root.get(IValidable.P_IS_VALID), true), spec.toPredicate(root, query, cb));
            }
        }, pageable);
    }

    @Override
    public List<T> findValid(final Specification<T> spec, Sort sort) {
        return super.find(new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.and(cb.equal(root.get(IValidable.P_IS_VALID), true), spec.toPredicate(root, query, cb));
            }
        }, sort);
    }

    @Override
    public long countValid(final Specification<T> spec) {
        return super.count(new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.and(cb.equal(root.get(IValidable.P_IS_VALID), true), spec.toPredicate(root, query, cb));
            }
        });
    }

	@Override
	public Page<T> findInValid(final Specification<T> spec, Pageable pageable) {
		return super.find(new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.and(cb.equal(root.get(IValidable.P_IS_VALID), false), spec.toPredicate(root, query, cb));
            }
        }, pageable);
	}

	@Override
	public List<T> findInValid(final Specification<T> spec) {
		return super.find(new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.and(cb.equal(root.get(IValidable.P_IS_VALID), false), spec.toPredicate(root, query, cb));
            }
        });
	}

	@Override
	public List<T> findInValid(final Specification<T> spec, Sort sort) {
		return super.find(new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.and(cb.equal(root.get(IValidable.P_IS_VALID), false), spec.toPredicate(root, query, cb));
            }
        }, sort);
	}

	@Override
	public void setInvalid(ID... ids) {
		for (ID id : ids) {
			this.setInvalid(this.find(id));
		}
	}

	@Override
	public void setValid(ID... ids) {
		for (ID id : ids) {
			this.setValid(this.find(id));
		}
	}
}
