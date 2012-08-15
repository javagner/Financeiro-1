package com.algaworks.dwjsf.financeiro.negocio;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.algaworks.dwjsf.financeiro.dominio.Conta;
import com.algaworks.dwjsf.financeiro.util.HibernateUtil;

public class ContaService {

	Session session = null;
	Transaction tx = null;

	@SuppressWarnings("unchecked")
	public List<String> pesquisarDescricoes(String descricao) {
		session = HibernateUtil.getSession();
		return this.session.createCriteria(Conta.class).setProjection(Projections.distinct(Projections.property("descricao"))).add(Restrictions.ilike("descricao", descricao,MatchMode.ANYWHERE)).addOrder(Order.asc("descricao")).list();
	}

	public void salvar(Conta conta) throws RegraNegocioException {
		if (conta.getValor().compareTo(BigDecimal.ZERO) <= 0) {
			throw new RegraNegocioException(
					"Valor da conta deve ser maior que zero");
		}

		session = HibernateUtil.getSession();
		tx = session.beginTransaction();

		session.saveOrUpdate(conta);
		tx.commit();
		session.close();

	}

	public Conta pesquisarPorId(Long id) {
		session = HibernateUtil.getSession();
		try {
			return (Conta) session.get(Conta.class, id);
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Conta> listarTodas() {
		session = HibernateUtil.getSession();
		try {
			return session.createCriteria(Conta.class)
					.addOrder(Order.desc("dataVencimento")).list();
		} finally {
			session.close();
		}
	}

	public void excluir(Conta conta) throws RegraNegocioException {
		if (conta.getDataBaixa() != null) {
			throw new RegraNegocioException(
					"Esta conta não pode ser excluída, pois ja foi baixada!");
		}

		session = HibernateUtil.getSession();
		tx = session.beginTransaction();
		session.delete(conta);
		tx.commit();
		session.close();
	}

}
