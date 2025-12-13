package org.example.form;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;

public class HelloForm extends ActionForm {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {

        ActionErrors ae = new ActionErrors();
        if(name.isEmpty()){
            ae.add("name", new ActionMessage("msg"));
        }
        return ae;
    }
}