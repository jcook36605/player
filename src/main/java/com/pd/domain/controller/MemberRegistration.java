package com.pd.domain.controller;

import com.pd.domain.bean.PlayerService;
import com.pd.domain.bean.ShotService;
import com.pd.domain.model.Player;
import com.pd.domain.model.PlayerShot;
import com.pd.domain.model.ShotType;
import com.pd.domain.util.EJBLookUp;
import com.pd.domain.web.PlayerProfile;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;


/**
 * Created with IntelliJ IDEA.
 * User: gy6258
 * Date: 2/22/13
 * Time: 12:36 PM
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@SessionScoped
public class MemberRegistration implements Serializable {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;






/*
    private Member newMember;*/
/*

    @Produces
    @Named
    public Member getNewMember() {
        return newMember;
    }
*/


    public String signIn() throws Exception {
        log.info("signIn.nnnnnnnnnnn............................. ");
        return "main";
    }




    @PostConstruct
    public void initNewMember() {
        System.out.println("MemberRegistration.initNewMember");
    }

}
