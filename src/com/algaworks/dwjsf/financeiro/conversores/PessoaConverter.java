package com.algaworks.dwjsf.financeiro.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import com.algaworks.dwjsf.financeiro.dominio.Pessoa;
import com.algaworks.dwjsf.financeiro.negocio.PessoaService;

public class PessoaConverter implements Converter {

	public Object getAsObject(FacesContext context, UIComponent component,
			String value) throws ConverterException {
		if (value == null) {
			return null;
		}
		return new PessoaService().pesquisarPorId(Long.parseLong(value));
	}

	public String getAsString(FacesContext context, UIComponent component,
			Object object) throws ConverterException {
		if (object == null) {
			return null;
		}
		Pessoa pessoa = (Pessoa) object;
		return pessoa.getId().toString();
	}

}
