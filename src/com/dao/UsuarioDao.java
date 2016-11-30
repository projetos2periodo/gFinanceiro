package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bd.DBUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import financeiro.Usuario;

public class UsuarioDao {
	
	private Connection connection;

	public UsuarioDao() {
		connection = DBUtil.getConnection();
	}

	public void addUsuario(Usuario usuario) {
		try {
			java.sql.PreparedStatement prepare = connection.prepareStatement("INSERT INTO usuario (nome,email,usuario,senha,ativo) VALUES (?,?,?,?,?)");
			prepare.setInt(1, usuario.getIdusuario());
			prepare.setString(2, usuario.getNome());
			prepare.setString(3, usuario.getEmail());
			prepare.setString(4, usuario.getUsuario());
			prepare.setString(5, usuario.getSenha());
			prepare.setInt(6, usuario.getAtivo());
			prepare.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public void deleteUsuario(int Idusuario) {
		try {
			java.sql.PreparedStatement prepare = connection.prepareStatement("DELETE FROM USUARIO WHERE idusuario = ?");
			prepare.setInt(1, Idusuario);
			prepare.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public void updateUsuario(Usuario usuario) {
		try {
			java.sql.PreparedStatement prepare = connection.prepareStatement("UPDATE USUARIO SET nome = ?,email = ?, usuario = ?, senha = ?, ativo = ? WHERE idusuario = ?");
			prepare.setInt(1, usuario.getIdusuario());
			prepare.setString(2, usuario.getNome());
			prepare.setString(3, usuario.getEmail());
			prepare.setString(4, usuario.getUsuario());
			prepare.setString(5, usuario.getSenha());
			prepare.setInt(6, usuario.getAtivo());
			prepare.executeUpdate();

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public List<Usuario> getAllUsuario() {
		List<Usuario> usuario = new ArrayList<Usuario>();
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM usuario ORDER BY idusuario");
			while (rs.next()) {
				Usuario usuario0 = new Usuario();

				usuario0.setIdusuario(rs.getInt("idusuario"));
				usuario0.setNome(rs.getString("nome"));
				usuario0.setEmail(rs.getString("email"));
				usuario0.setUsuario(rs.getString("usuario"));
				usuario0.setSenha(rs.getString("senha"));
				usuario0.setAtivo(rs.getInt("ativo"));
				usuario.add(usuario0);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return usuario;
	}
	
	public Usuario getUserById(int idusuario) {
		Usuario usuario1 = new Usuario();
		try {
			java.sql.PreparedStatement prepare = connection.prepareStatement("select * from tblUser where userid=?");
			prepare.setInt(1, idusuario);
			ResultSet rs = prepare.executeQuery();
			
			if (rs.next()) {
				usuario1.setIdusuario(rs.getInt("idusuario"));
				usuario1.setNome(rs.getString("nome"));
				usuario1.setEmail(rs.getString("email"));
				usuario1.setUsuario(rs.getString("usuario"));
				usuario1.setSenha(rs.getString("senha"));
				usuario1.setAtivo(rs.getInt("ativo"));;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario1;
	}	

}
