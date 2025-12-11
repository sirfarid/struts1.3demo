package org.example.action;

import org.apache.struts.action.*;
import org.example.form.HelloForm;
import org.example.model.Artist;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloAction extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HelloForm helloForm = (HelloForm) form;
        ActionForward fw = mapping.getInputForward();
        if(helloForm.getName() == null)
            return fw;
        if(!helloForm.getName().trim().isEmpty()){
            save(helloForm.getName());
            fw = mapping.findForward("success");
        } else {
            ActionErrors errs = new ActionErrors();
            errs.add("", new ActionMessage("hello.msg.err"));
            saveErrors(request, errs);
        }
        return fw;
    }

    void save(String name){
        try{
            EntityManagerFactory sessionFactory= Persistence.createEntityManagerFactory("org.example");
            EntityManager entityManager=sessionFactory.createEntityManager();
            EntityTransaction transaction= entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(new Artist(name));
            transaction.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}