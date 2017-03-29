package de.auc.services.interceptor;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;

@MyTransactionInterceptor
@Interceptor
@Dependent
public class TransactionInterceptor {

	 @Inject
	 private UserTransaction userTransaction;
	
	 
	 @AroundInvoke
	 public Object myTransactionInterceptor(InvocationContext context) throws Exception{
		 userTransaction.begin();
		 Object res = context.proceed();
		 userTransaction.commit();
		 return res;
	 }
	
}