package com.pd.domain.util;

import com.pd.domain.model.Player;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: gy6258
 * Date: 2/19/13
 * Time: 11:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class PlayerHttpWrapper {

    public Player getPlayer(HttpServletRequest r){
        String firstName = r.getParameter("firstName");
        String lastName = r.getParameter("lastName");

        Player p = new Player();
        if(firstName != null){
            p.setFirstName(firstName);
        }
        if(lastName != null){
            p.setLastName(lastName);
        }

        String playerId = r.getParameter("playerId");
        if(playerId != null) {
            p.setId(Long.parseLong(playerId));
        }
        //p.se(r.getParameter("maleOrFemale"));
        //p.s(r.getParameter("mi"));
       // p.setFirstName(r.getParameter("sex"));
        return p;

    }
}
