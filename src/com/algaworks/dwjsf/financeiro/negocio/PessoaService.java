package com.algaworks.dwjsf.financeiro.negocio;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

import com.algaworks.dwjsf.financeiro.dominio.Pessoa;
import com.algaworks.dwjsf.financeiro.util.HibernateUtil;

public class PessoaService {
	Session session = null;
	Transaction tx = null;
	
	public void salvar(Pessoa pessoa) throws RegraNegocioException{
		session = HibernateUtil.getSession();
		tx = session.beginTransaction();
		session.saveOrUpdate(pessoa);
		
		tx.commit();
		session.close();
	}
	
	public void excluir(Pessoa pessoa) throws RegraNegocioException{
		session = HibernateUtil.getSession();
		tx = session.beginTransaction();
		session.delete(pessoa);
		tx.commit();
		session.close();
	}
	
	public Pessoa pesquisarPorId(Long id){
		session = HibernateUtil.getSession();
		try {
			return (Pessoa) session.get(Pessoa.class, id);
		} finally {
			session.close();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Pessoa> listarTodas(){
		session = HibernateUtil.getSession();
		try {
			return session.createCriteria(Pessoa.class).addOrder(Order.asc("nome")).list();
		} finally{
			session.close();
		}
	}
}
