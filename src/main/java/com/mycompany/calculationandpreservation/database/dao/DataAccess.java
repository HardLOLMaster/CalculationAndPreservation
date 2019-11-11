/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.calculationandpreservation.database.dao;

/**
 * Интерфейс предоставляющий базовые методы
 * для работы с БД
 * @author HardLOLMaster
 */
public interface DataAccess<T> {
    public T getById(long id);
    public void update(T t);
    public void save(T t);
    public void delete(T t);
}
