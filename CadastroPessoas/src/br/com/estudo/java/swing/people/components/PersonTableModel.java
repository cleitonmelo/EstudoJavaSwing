package br.com.estudo.java.swing.people.components;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.estudo.java.swing.people.model.Person;

public class PersonTableModel extends AbstractTableModel {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6046713262515783699L;
	
	private List<Person> listaPessoa = new ArrayList<Person>();
	private String[] collumnNames = { "Id", "Name", "Age" };
	private Class<?>[] collumnTypes = { Integer.class, String.class, Integer.class };

	public void load(List<Person> pessoas) {
		this.listaPessoa = pessoas;
		fireTableDataChanged();
	}

	public Person getPessoaAt(int index) {
		if (this.listaPessoa.size() <= 0) {
			return null;
		}
		return this.listaPessoa.get(index);
	}

	@Override
	public int getColumnCount() {
		return collumnNames.length;
	}

	@Override
	public int getRowCount() {
		return this.listaPessoa.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		if (this.listaPessoa.size() <= 0) {
			return null;
		}
		Person p = this.listaPessoa.get(arg0);
		switch (arg1) {
		case 0:
			return p.getId();
		case 1:
			return p.getNome();
		case 2:
			return p.getIdade();
		}
		return null;
	}

}
