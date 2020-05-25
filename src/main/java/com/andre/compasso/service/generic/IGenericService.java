package com.andre.compasso.service.generic;

import com.andre.compasso.exception.RuleException;

public interface IGenericService<T> {

    void save(T entity) throws RuleException;
    T findById(Integer id) throws RuleException;
    void delete(Integer id) throws RuleException;
    boolean existsById(Integer integer);

}
