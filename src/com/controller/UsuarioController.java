package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UsuarioDao;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import financeiro.Usuario;

@WebServlet("/usuario")
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuarioDao dao;

	public UsuarioController() {
		dao = new UsuarioDao();
	}


		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {		
		if (request.getParameter("metodo") != null) {
			List<Usuario> lstUsuario = new ArrayList<Usuario>();
			String acao = (String) request.getParameter("metodo");
			Gson gson = new Gson();
			response.setContentType("application/json");
			
			if (acao.equals("listar")) {
				try {
					lstUsuario = dao.getAllUsuario();
					// Convert Java Object to Json
					JsonElement elemento = gson.toJsonTree(lstUsuario, new TypeToken<List<Usuario>>() {
					}.getType());
					JsonArray jsonArray = elemento.getAsJsonArray();
					String listData = jsonArray.toString();

					listData = "{\"Result\":\"OK\",\"Records\":" + listData + "}";
					response.getWriter().print(listData);
				} catch (Exception ex) {
					String error = "{\"Result\":\"ERROR\",\"Message\":" + ex.getMessage() + "}";
					response.getWriter().print(error);
					ex.printStackTrace();
				}
			} else if (acao.equals("create") || acao.equals("update")) {
				Usuario usuario = new Usuario();
				if (request.getParameter("Usuarioid") != null) {
					int idusuario = Integer.parseInt(request.getParameter("idusuario"));
					usuario.setIdusuario(idusuario);
				} // nome,email,usuario,senha,ativo
				if (request.getParameter("nome") != null) {
					String nome = (String) request.getParameter("nome");
					usuario.setNome(nome);
				}
				if (request.getParameter("email") != null) {
					String email = (String) request.getParameter("email");
					usuario.setEmail(email);
				}
				if (request.getParameter("usuario") != null) {
					String email = (String) request.getParameter("usuario");
					usuario.setEmail(email);
				}
				if (request.getParameter("senha") != null) {
					String senha = (String) request.getParameter("senha");
					usuario.setSenha(senha);
				}
				if (request.getParameter("ativo") != null) {
					String ativo = request.getParameter("ativo");
					usuario.setAtivo(Integer.parseInt(ativo));
				}				
				try {
					if (acao.equals("create")) {
						dao.addUsuario(usuario);
						lstUsuario.add(usuario);
						// Convert Java Object to Json
						String json = gson.toJson(usuario);
						// Return Json in the format required by jTable plugin
						String listData = "{\"Result\":\"OK\",\"Record\":" + json + "}";
						response.getWriter().print(listData);
					} else if (acao.equals("update")) {// Update existing
															// record
						dao.updateUsuario(usuario);
						String listData = "{\"Result\":\"OK\"}";
						response.getWriter().print(listData);
					}
				} catch (Exception ex) {
					String error = "{\"Result\":\"ERROR\",\"Message\":" + ex.getStackTrace().toString() + "}";
					response.getWriter().print(error);
				}
			} else if (acao.equals("delete")) {// Delete record
				try {
					if (request.getParameter("idusuario") != null) {
						String idusuario = (String) request.getParameter("idusuario");
						dao.deleteUsuario(Integer.parseInt(idusuario));
						String listData = "{\"Result\":\"OK\"}";
						response.getWriter().print(listData);
					}
				} catch (Exception ex) {
					String error = "{\"Result\":\"ERROR\",\"Message\":" + ex.getStackTrace().toString() + "}";
					response.getWriter().print(error);
				}
			}
		}
	}
}
