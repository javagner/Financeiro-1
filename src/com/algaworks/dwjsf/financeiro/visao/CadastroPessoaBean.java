package com.algaworks.dwjsf.financeiro.visao;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import com.algaworks.dwjsf.financeiro.dominio.Pessoa;
import com.algaworks.dwjsf.financeiro.negocio.ContaService;
import com.algaworks.dwjsf.financeiro.negocio.PessoaService;
import com.algaworks.dwjsf.financeiro.negocio.RegraNegocioException;

public class CadastroPessoaBean {
	private Pessoa pessoa;
	private List<Pessoa> pessoas = new ArrayList<Pessoa>();
		
		public String inicializar(){
			this.setPessoa(new Pessoa());
			this.pessoas = new PessoaService().listarTodas();
			return "cadastroPessoa";
		}
		
		public void salvar(ActionEvent event) {
			FacesContext context = FacesContext.getCurrentInstance();
			try {
				new PessoaService().salvar(this.getPessoa());
				this.setPessoa(new Pessoa());
				this.pessoas = new PessoaService().listarTodas();
				FacesMessage msg = new FacesMessage("Pessoa salva com sucesso!");
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				context.addMessage(null, msg);
			} catch (RegraNegocioException e) {
				context.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, e
								.getMessage(), e.getMessage()));
			} catch (Exception e) {
				e.printStackTrace();
				FacesMessage msg = new FacesMessage(
						"Erro inesperado ao salvar pessoa!");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				context.addMessage(null, msg);
			}
		}
		
		/**
		 * @return the pessoa
		 */
		public Pessoa getPessoa() {
			return pessoa;
		}

		/**
		 * @param pessoa the pessoa to set
		 */
		public void setPessoa(Pessoa pessoa) {
			this.pessoa = pessoa;
		}

		/**
		 * @return the pessoas
		 */
		public List<Pessoa> getPessoas() {
			return pessoas;
		}

		/**
		 * @param pessoas the pessoas to set
		 */
		public void setPessoas(List<Pessoa> pessoas) {
			this.pessoas = pessoas;
		}

		
}
