package br.com.estudo.java.swing.people.components;

import java.util.List;

import javax.swing.JTable;

import br.com.estudo.java.swing.people.model.Person;

public class PersonJTable extends JTable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9032860718060767926L;
	
	private PersonTableModel tableModel;
	
	public PersonJTable() {
		this.tableModel = new PersonTableModel();
		setModel(this.tableModel);
	}

	public void load(List<Person> pessoas) {
		this.tableModel.load(pessoas);
	}
	
	public Person getSelectedPerson() {
		int index = getSelectedRow();
		return this.tableModel.getPessoaAt(index);
	}
	
	

}
