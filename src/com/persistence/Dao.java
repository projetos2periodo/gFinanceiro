package com.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

import financeiro.Categoria;

public class Dao {
	private String url = "jdbc:mysql://localhost:3306/gfinanceiro";
	private String usuario = "root"; // "gfinanceiro";
	private String senha = ""; // "f1n4nc4$";

	// Getters e setters
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean alterar(String queryString) {

		Connection con;
		Statement stmt;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		try {
			con = DriverManager.getConnection(this.getUrl(), this.getUsuario(), this.getSenha());
			stmt = con.createStatement();

			if (stmt.executeUpdate(queryString) > 0) {
				stmt.close();
				con.close();
				return true;
			} else {
				stmt.close();
				con.close();
				return false;
			}

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}

	/*
	 * 
	 * */
	public String gravar(String queryString) {

		Connection con;
		Statement stmt;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "{\"success\":\"false\"}";
		}

		try {
			con = DriverManager.getConnection(this.getUrl(), this.getUsuario(), this.getSenha());
			stmt = con.createStatement();

			stmt.execute(queryString);
			stmt.close();
			con.close();
			return "{\"success\":\"true\"}";

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			return "{\"success\":\"false\"}";
		}
	}

	/*
	 * 
	 * */
	public String deletar(String queryString) {

		Connection con;
		Statement stmt;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "{\"success\":\"false\"}";
		}
		try {
			con = DriverManager.getConnection(this.getUrl(), this.getUsuario(), this.getSenha());
			stmt = con.createStatement();

			if (stmt.execute(queryString)) {
				stmt.close();
				con.close();
				return "{\"success\":\"true\"}";
			} else {
				stmt.close();
				con.close();
				return "{\"success\":\"true\"}";
			}

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			return "{\"success\":\"true\"}";
		}
	}

	public String listar(String queryString) {
		Connection con;
		Statement stmt;
		ResultSet resultados;
		ArrayList<Categoria> arrayFinal = new ArrayList<Categoria>();
		String json = "{}";

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			con = DriverManager.getConnection(this.getUrl(), this.getUsuario(), this.getSenha());
			stmt = con.createStatement();

			resultados = stmt.executeQuery(queryString);
			while(resultados.next()){
				String idcategoria  = resultados.getString("idcategoria");
				 String nome  = resultados.getString("nome");
				 String tipoCategoria  = resultados.getString("tipoCategoria");
				 String dtaCadastro  = resultados.getString("dtaCadastro");

				  Categoria myClass = new Categoria();
				  myClass.setIdcategoria(Integer.parseInt(idcategoria));
				  myClass.setNome(nome);
				  myClass.setTipoCategoria(Integer.parseInt(tipoCategoria));
				  myClass.setDtaCadastro(dtaCadastro);
				  arrayFinal.add(myClass);
				}

			stmt.close();
			con.close();
			json = new Gson().toJson(arrayFinal);
			return json;

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		return json;

	}
}
