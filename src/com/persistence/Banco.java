package com.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Banco {

	static String sql_alteraTabela1 = "ALTER TABLE contatos ADD PRIMARY KEY (chave);";
	static String sql_alteraTabela2 = "ALTER TABLE contatos MODIFY chave int(11) NOT NULL AUTO_INCREMENT;";
	static String sql_criaBanco = "create database agenda";
	static String sql_criaBase = "CREATE TABLE contatos(" + "chave int(11) NOT NULL," + "nome varchar(50) NOT NULL," + "telefone varchar(10) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8;";

	public static void alteraDados(Dados contato) {
		String url = "jdbc:mysql://localhost:3306/agenda";
		String usuario = "root";
		String senha = "";
		String sql_novoContato = "update contatos set nome='" + contato.getNome() + "', telefone='" + contato.getTelefone() + "' where chave='" + contato.getChavePrimaria() + "';";
		Connection con;
		Statement stmt;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			con = DriverManager.getConnection(url, usuario, senha);
			stmt = con.createStatement();
			stmt.executeUpdate(sql_novoContato);
			stmt.close();
			con.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

	}

	public static void alteraTabela() {
		String url = "jdbc:mysql://localhost:3306/agenda";
		String usuario = "root";
		String senha = "";
		Connection con;
		Statement stmt;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			con = DriverManager.getConnection(url, usuario, senha);
			stmt = con.createStatement();
			stmt.execute(sql_alteraTabela1);
			stmt.execute(sql_alteraTabela2);
			stmt.close();
			con.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public static Dados carregaRegistro(int chave) {
		String url = "jdbc:mysql://localhost:3306/agenda";
		String usuario = "root";
		String senha = "";
		String sql_novoContato = "select * from contatos where chave=" + chave + ";";
		Connection con;
		Statement stmt;
		ResultSet resultados;
		String nome = null, telefone = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			con = DriverManager.getConnection(url, usuario, senha);
			stmt = con.createStatement();
			resultados = stmt.executeQuery(sql_novoContato);
			if (resultados.next()) {
				nome = resultados.getString("nome");
				telefone = resultados.getString("telefone");
			}
			stmt.close();
			con.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return new Dados(chave, nome, telefone);
	}

	public static void criaBase() {
		String url = "jdbc:mysql://localhost:3306/";
		String usuario = "root";
		String senha = "";
		Connection con;
		Statement stmt;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			con = DriverManager.getConnection(url, usuario, senha);
			stmt = con.createStatement();
			stmt.execute(sql_criaBanco);
			stmt.close();
			con.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public static void criaTabela() {
		String url = "jdbc:mysql://localhost:3306/agenda";
		String usuario = "root";
		String senha = "";
		Connection con;
		Statement stmt;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			con = DriverManager.getConnection(url, usuario, senha);
			stmt = con.createStatement();
			stmt.execute(sql_criaBase);
			stmt.close();
			con.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public static void excluir(int i) {
		String url = "jdbc:mysql://localhost:3306/agenda";
		String usuario = "root";
		String senha = "";
		String sql = "delete from contatos where chave=" + i;
		Connection con;
		Statement stmt;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			con = DriverManager.getConnection(url, usuario, senha);
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
			con.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

	}

	public static void gravaDados(Dados contato) {
		String url = "jdbc:mysql://localhost:3306/agenda";
		String usuario = "root";
		String senha = "";
		String sql_novoContato = "insert into contatos(nome,telefone) values('" + contato.getNome() + "','" + contato.getTelefone() + "');";
		Connection con;
		Statement stmt;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			con = DriverManager.getConnection(url, usuario, senha);
			stmt = con.createStatement();
			stmt.executeUpdate(sql_novoContato);
			stmt.close();
			con.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public static List<Dados> listaTodos() {
		String url = "jdbc:mysql://localhost:3306/agenda";
		String usuario = "root";
		String senha = "";
		String sql_novoContato = "select * from contatos;";
		Connection con;
		Statement stmt;
		ResultSet resultados;
		int chave;
		String nome, telefone;
		List<Dados> lista = new ArrayList<Dados>();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			con = DriverManager.getConnection(url, usuario, senha);
			stmt = con.createStatement();
			resultados = stmt.executeQuery(sql_novoContato);
			while (resultados.next()) {
				chave = resultados.getInt("chave");
				nome = resultados.getString("nome");
				telefone = resultados.getString("telefone");
				lista.add(new Dados(chave, nome, telefone));
			}
			stmt.close();
			con.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return lista;
	}

	public static void main(String[] args) {
		// Banco.criaBase();
		// Banco.criaTabela();
		// Banco.alteraTabela();
		for (Dados d : Banco.listaTodos()) {
			System.out.println(d.getChavePrimaria() + "," + d.getNome() + "," + d.getTelefone());
		}

	}
}
