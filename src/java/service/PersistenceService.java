/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;

/**
 * Utility class for dealing with persistence.
 *
 * @author wpavan
 */
public class PersistenceService {

    private static String DEFAULT_PU = "AgMIPWS2PU";
    private static ThreadLocal<PersistenceService> instance = new ThreadLocal<PersistenceService>() {

        @Override
        protected PersistenceService initialValue() {
            return new PersistenceService();
        }
    };
    private EntityManager em;
    private UserTransaction utx;
    
    private PersistenceService() {        
        try {
            this.em = (EntityManager) new InitialContext().lookup("java:comp/env/persistence/" + DEFAULT_PU);
            this.utx = (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
        } catch (NamingException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Returns an instance of PersistenceService.
     * 
     * @return an instance of PersistenceService
     */
    public static PersistenceService getInstance() {
        return instance.get();
    }
    
    private static void removeInstance() {
        instance.remove();
    }

    /**
     * Returns an instance of EntityManager.
     *
     * @return an instance of EntityManager
     */
    public EntityManager getEntityManager() {
        return em;
    }

    /**
     * Begins a resource transaction.
     */
    public void beginTx() {
        try {
            utx.begin();
            em.joinTransaction();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Commits a resource transaction.
     */
    public void commitTx() {
        try {
            utx.commit();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Rolls back a resource transaction.
     */
    public void rollbackTx() {
        try {
            utx.rollback();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Closes this instance.
     */
    public void close() {
        removeInstance();
    }
}
