package br.com.estudo.java.swing.people.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.estudo.java.swing.people.model.Pessoa;
import br.com.estudo.java.swing.people.utils.DbUtils;

public class PessoaDAO implements IAbstractDAO<Pessoa> {

	@Override
	public List<Pessoa> all()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			conn = DbUtils.getConnection();
			ResultSet rs = DbUtils.getResultSet(conn, "SELECT * FROM peoples");
			List<Pessoa> listaPessoas = new ArrayList<Pessoa>();
			while (rs.next()) {
				Pessoa pessoa = new Pessoa(rs.getInt("id"), rs.getString("name"), rs.getInt("age"));
				listaPessoas.add(pessoa);
			}
			return listaPessoas;
		} finally {
			DbUtils.closeConnection(conn);
		}
	}

	@Override
	public Pessoa findById(int id)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			conn = DbUtils.getConnection();
			PreparedStatement preparedStatement = DbUtils.getPreparedStatement(conn,
					"SELECT * FROM peoples WHERE id = ?");
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				Pessoa pessoa = new Pessoa(rs.getInt("id"), rs.getString("nome"), rs.getInt("age"));
				return pessoa;
			}
			return null;
		} finally {
			DbUtils.closeConnection(conn);
		}
	}

	@Override
	public void insert(Pessoa entity)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			conn = DbUtils.getConnection();
			PreparedStatement statement = DbUtils.getPreparedStatement(conn,
					"INSERT INTO peoples ( name, age ) VALUE ( ?, ? )");
			statement.setString(1, entity.getNome());
			statement.setInt(2, entity.getIdade());
			statement.executeUpdate();
		} finally {
			DbUtils.closeConnection(conn);
		}
	}

	@Override
	public void update(Pessoa entity)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			conn = DbUtils.getConnection();
			PreparedStatement statement = DbUtils.getPreparedStatement(conn,
					"UPDATE peoples SET name = ? , age = ? WHERE id = ?");
			statement.setString(1, entity.getNome());
			statement.setInt(2, entity.getIdade());
			statement.setInt(3, entity.getId());
			statement.executeUpdate();
		} finally {
			DbUtils.closeConnection(conn);
		}

	}

	@Override
	public void delete(Pessoa entity)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			conn = DbUtils.getConnection();
			PreparedStatement statement = DbUtils.getPreparedStatement(conn, "DELETE FROM peoples WHERE id = ?");
			statement.setInt(1, entity.getId());
			statement.executeUpdate();
		} finally {
			DbUtils.closeConnection(conn);
		}
	}

}
