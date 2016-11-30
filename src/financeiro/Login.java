package financeiro;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.persistence.Dao;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static List<Login> listaLogin = new ArrayList<Login>();
	
	private int idusuario;
	private String nome;
	private String email;
	private String usuario;
	private String senha;
	private int ativo;

	public int getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}

	
	
	public Login(int idusuario, String nome, String email, String usuario, String senha, int ativo) {
		super();
		this.idusuario = idusuario;
		this.nome = nome;
		this.email = email;
		this.usuario = usuario;
		this.senha = senha;
		this.ativo = ativo;
		
		Login.listaLogin.add(this);
	}

	/*
	 * */
	public String cadastraUsuario(List<Login> lista) {
		Dao objDao = new Dao();
		String sql = "INSERT INTO usuario (nome,email,usuario,senha,ativo) VALUES ('"+getNome()+"', '"+getEmail()+"', '"+getUsuario()+"', '"+getSenha()+"', '"+getAtivo()+"')";
		return objDao.gravar(sql);
	}

	/*
	 * */
	public String listaUsuarios(int limit) {
		Dao objDao = new Dao();
		String sql = "select * from usuario LIMIT" + limit;
		return objDao.listar(sql);
	}	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
