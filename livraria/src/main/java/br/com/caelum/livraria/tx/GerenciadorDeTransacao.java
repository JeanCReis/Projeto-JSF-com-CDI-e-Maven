package br.com.caelum.livraria.tx;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@SuppressWarnings("serial")
@Transacional
@Interceptor
public class GerenciadorDeTransacao implements Serializable{
	
	@Inject
	EntityManager manager;

	@AroundInvoke
	public Object executaTx(InvocationContext contexto) throws Exception {
		// abre transacao
		manager.getTransaction().begin();
		
		System.out.println("Abrindo TX");
		
		//chamae os daos que precisam de um TX
		Object resultado = contexto.proceed();
		
		System.out.println("Fechando TX");
		
		// commita a transacao
		manager.getTransaction().commit();
		
		return resultado;
	}
}
