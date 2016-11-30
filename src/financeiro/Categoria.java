package financeiro;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.persistence.*;

@WebServlet("/categoria")
public class Categoria extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private int idcategoria;
	private String nome;
	private int tipoCategoria;
	private String dtaCadastro;

	public String getDtaCadastro() {
		return dtaCadastro;
	}

	public void setDtaCadastro(String dtaCadastro) {
		this.dtaCadastro = dtaCadastro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getTipoCategoria() {
		return tipoCategoria;
	}

	public void setTipoCategoria(int tipoCategoria) {
		this.tipoCategoria = tipoCategoria;
	}

	// getters e setters
	public int getIdcategoria() {
		return idcategoria;
	}

	public void setIdcategoria(int idcategoria) {
		this.idcategoria = idcategoria;
	}


	/*
	 * */
	public String cadastraCategoria(String nome, String tipoCategoria) {
		Dao objDao = new Dao();
		String sql = "insert into categoria (nome,tipoCategoria) values ('" + nome + "','"+ tipoCategoria +"')";
		return objDao.gravar(sql);
	}

	/*
	 * */
	public String getCategorias(int limit) {
		Dao objDao = new Dao();
		String sql = "select * from categoria LIMIT" + limit;
		return objDao.listar(sql);
	}

	public boolean editarCategoria() {
		return true;
	}

	public boolean getCategoriaById(int idcategoria) {
		return true;
	}

	public String excluirCategoria(int idcategoria) {
		Dao objDao = new Dao();
		String sql = "delete from categoria WHERE idcategoria=" + idcategoria;
		return objDao.deletar(sql);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		switch (request.getParameter("metodo")) {
		case "listar":
			response.getWriter().append(this.getCategorias(10));
			break;
		case "excluir":
			response.getWriter().append(this.excluirCategoria(Integer.valueOf(request.getParameter("idcategoria"))));
			break;
		case "alterar":
			response.getWriter().append(this.getCategorias(10));
			break;
		case "cadastrar":
			response.getWriter().append(this.cadastraCategoria(request.getParameter("nome"), request.getParameter("tipoCategoria")));
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public Categoria() {
		super();
	}
}
