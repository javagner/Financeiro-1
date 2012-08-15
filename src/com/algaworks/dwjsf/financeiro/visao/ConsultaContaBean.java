package com.algaworks.dwjsf.financeiro.visao;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.algaworks.dwjsf.financeiro.dominio.Conta;
import com.algaworks.dwjsf.financeiro.negocio.ContaService;
import com.algaworks.dwjsf.financeiro.negocio.RegraNegocioException;

public class ConsultaContaBean {

	private Conta contaExclusao;
	private List<Conta> contas = new ArrayList<Conta>();

	public void consultar(ActionEvent event) {
		this.contas = new ContaService().listarTodas();
	}

	public String excluir() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			new ContaService().excluir(this.contaExclusao);
			this.contas.remove(this.contaExclusao);
			this.contaExclusao = null;
			FacesMessage msg = new FacesMessage("Conta excluída com sucesso!");
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			context.addMessage(null, msg);
		} catch (RegraNegocioException e) {
			context.addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage msg = new FacesMessage("Erro inesperado ao excluir conta!");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
		}
		return null;
	}

	public List<Conta> getContas() {
		return contas;
	}

	public Conta getContaExclusao() {
		return contaExclusao;
	}

	public void setContaExclusao(Conta contaExclusao) {
		this.contaExclusao = contaExclusao;
	}

}
