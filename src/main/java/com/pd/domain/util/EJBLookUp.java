package com.pd.domain.util;

import com.pd.domain.bean.PlayerService;
import com.pd.domain.bean.ShotService;

import javax.naming.NamingException;

/**
 * Created with IntelliJ IDEA.
 * User: gy6258
 * Date: 2/13/13
 * Time: 2:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class EJBLookUp {


    public PlayerService lookUpPlayerService(){
        PlayerService ps=null;
        try {
            javax.naming.InitialContext ic = new javax.naming.InitialContext();
            ps = (PlayerService)ic.lookup("java:global/player-ear/PlayerBean");

        }
        catch (NamingException ne){
            ne.printStackTrace();
        }
        return ps;
    }

    public ShotService lookUpShotService(){
        ShotService shotService=null;
        try {
            javax.naming.InitialContext ic = new javax.naming.InitialContext();
            shotService = (ShotService)ic.lookup("java:global/player-ear/ShotBean");

        }
        catch (NamingException ne){
            ne.printStackTrace();
        }
        return shotService;
    }
}
