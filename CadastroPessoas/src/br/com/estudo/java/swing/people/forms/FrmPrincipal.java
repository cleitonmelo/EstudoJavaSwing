package br.com.estudo.java.swing.people.forms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.estudo.java.swing.people.components.PersonJTable;
import br.com.estudo.java.swing.people.dao.PersonDAO;
import br.com.estudo.java.swing.people.model.Person;

public class FrmPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FrmPrincipal() {
		this.setSize(600, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(300, 200);
		this.setTitle("Estudo - Cadastro de Pessoas");

		JPanel panel = new JPanel();
		panel.setLayout(null);

		JLabel labelId = new JLabel("Código: ");
		labelId.setBounds(10, 10, 50, 20);
		panel.add(labelId);

		JTextField textId = new JTextField();
		textId.setBounds(60, 10, 150, 20);
		textId.setEnabled(false);
		panel.add(textId);

		JLabel labelName = new JLabel("Nome: ");
		labelName.setBounds(10, 40, 50, 20);
		panel.add(labelName);

		JTextField textName = new JTextField();
		textName.setBounds(60, 40, 500, 20);
		panel.add(textName);

		JLabel labelAge = new JLabel("Idade: ");
		labelAge.setBounds(10, 70, 50, 20);
		panel.add(labelAge);

		JTextField textAge = new JTextField();
		textAge.setBounds(60, 70, 100, 20);
		textAge.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				char key = e.getKeyChar();
				if (Character.isAlphabetic(key)) {
					e.consume();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
		panel.add(textAge);

		PersonJTable pessoaJTable = new PersonJTable();
		pessoaJTable.setBounds(10, 130, 565, 250);
		loadTable(pessoaJTable);
		pessoaJTable.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					Person personSelected = pessoaJTable.getSelectedPerson();
					textId.setText(String.valueOf(personSelected.getId()));
					textName.setText(personSelected.getNome());
					textAge.setText(String.valueOf(personSelected.getIdade()));
				}
			}
		});
		panel.add(pessoaJTable);

		JButton buttonNew = new JButton("Novo");
		buttonNew.setBounds(460, 10, 100, 20);
		buttonNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textId.setText("");
				textName.setText("");
				textAge.setText("");
			}
		});
		panel.add(buttonNew);

		JButton buttonSave = new JButton("Salvar");
		buttonSave.setBounds(55, 100, 100, 20);
		buttonSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Person person = new Person();
				person.setNome(textName.getText());
				person.setIdade(Integer.parseInt(textAge.getText()));
				try {
					if (textId.getText().isEmpty()) {
						new PersonDAO().insert(person);
						JOptionPane.showMessageDialog(null, "Cadastro Inserido com Sucesso!!");
					} else {
						person.setId(Integer.parseInt(textId.getText()));
						new PersonDAO().update(person);
						JOptionPane.showMessageDialog(null, "Cadastro Atualizado com Sucesso!!");
					}
					loadTable(pessoaJTable);
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Erro ao Inserir / Atualizar o Cadastro");
				}
			}
		});
		panel.add(buttonSave);

		JButton buttonDelete = new JButton("Excluir");
		buttonDelete.setBounds(265, 100, 100, 20);
		buttonDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Person person = new Person();
				if (textId.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Selecionar o Cadastro para ser removido !!!");
				} else {
					person.setId(Integer.parseInt(textId.getText()));
					try {
						new PersonDAO().delete(person);
						JOptionPane.showMessageDialog(null, "Cadastro removido com Sucesso!!");
						textId.setText("");
						textName.setText("");
						textAge.setText("");
						loadTable(pessoaJTable);
					} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
							| SQLException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Erro ao Deletar o Cadastro");
					}
				}

			}
		});
		panel.add(buttonDelete);

		this.add(panel);
		this.setVisible(true);
	}

	private void loadTable(PersonJTable table) {
		try {
			table.load(new PersonDAO().all());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Houve um erro ao carregar as pessoas !!");
		}
	}
}
