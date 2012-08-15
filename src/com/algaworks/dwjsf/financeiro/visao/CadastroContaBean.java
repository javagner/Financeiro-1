package com.algaworks.dwjsf.financeiro.visao;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import com.algaworks.dwjsf.financeiro.dominio.Conta;
import com.algaworks.dwjsf.financeiro.dominio.Pessoa;
import com.algaworks.dwjsf.financeiro.dominio.TipoConta;
import com.algaworks.dwjsf.financeiro.negocio.ContaService;
import com.algaworks.dwjsf.financeiro.negocio.PessoaService;
import com.algaworks.dwjsf.financeiro.negocio.RegraNegocioException;

public class CadastroContaBean {
	private Conta contaEdicao;
	private List<SelectItem> tiposContas;
	private List<SelectItem> pessoas;

	public String inicializar() {
		this.contaEdicao = new Conta();
		this.tiposContas = null;
		this.pessoas = null;
		return "cadastroConta";
	}

	public List<String> sugerirDescricao(Object event) {
		return new ContaService().pesquisarDescricoes(event.toString());
	}

	public void salvar(ActionEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			new ContaService().salvar(this.contaEdicao);
			this.contaEdicao = new Conta();
			FacesMessage msg = new FacesMessage("Conta salva com sucesso!");
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
					"Erro inesperado ao salvar conta!");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
		}
	}

	public List<SelectItem> getPessoas() {
		if (this.pessoas == null) {
			this.pessoas = new ArrayList<SelectItem>();
			List<Pessoa> pessoas = new PessoaService().listarTodas();
			this.pessoas.add(new SelectItem(null, "Selecione"));
			for (Pessoa pessoa : pessoas) {
				this.pessoas.add(new SelectItem(pessoa, pessoa.getNome()));
			}
		}
		return this.pessoas;
	}

	public List<SelectItem> getTiposLancamentos() {
		if (this.tiposContas == null) {
			this.tiposContas = new ArrayList<SelectItem>();
			for (TipoConta tipo : TipoConta.values()) {
				this.tiposContas.add(new SelectItem(tipo, tipo.toString()));
			}
		}
		return tiposContas;
	}

	public Conta getContaEdicao() {
		return contaEdicao;
	}

	public void setContaEdicao(Conta contaEdicao) {
		this.contaEdicao = contaEdicao;
	}
}
