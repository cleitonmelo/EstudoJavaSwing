package br.com.estudo.java.swing.people.dao;

import java.sql.SQLException;
import java.util.List;

public interface IAbstractDAO<T> {

	List<T> all() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException;

	T findById(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException;

	void insert(T entity) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException;

	void update(T entity) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException;

	void delete(T entity) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException;

}
