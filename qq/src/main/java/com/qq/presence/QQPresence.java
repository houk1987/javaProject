package com.qq.presence;

import com.imService.presence.PresenceType;
import org.jivesoftware.smack.packet.Presence;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by a on 2014/9/5.
 */
public class QQPresence {

    public enum YhPresenceType{
        ONLINE,
        BUSY,
        HIDE;
    }


    public static List<PresenceType> getPresenceTypeList() {
        List<PresenceType> presenceTypes = new ArrayList<>();
//        presenceTypes.add(onLine);
//        presenceTypes.add(busy);
//        presenceTypes.add(hide);
        return presenceTypes;
    }

    private static ImageIcon createImage(String name){
        return new ImageIcon("res/status/"+name);
    }

    public static PresenceType getPresenceType(YhPresenceType yhPresenceType){
        PresenceType presenceType = null;
//

        return presenceType;
    }

    public static ImageIcon getPresenceTypeIcon(Presence presence){
        if(presence.isAvailable()){ //ÎÒÓÐ¿Õ
            return getPresenceTypeIcon(YhPresenceType.ONLINE);
        }else if(presence.isAvailable() && presence.getMode().equals(Presence.Mode.dnd)){  //Ã¦Âµ
            return getPresenceTypeIcon(YhPresenceType.BUSY);
        }else { //ÒþÉí
            return getPresenceTypeIcon(YhPresenceType.HIDE);
        }
    }

    public static ImageIcon getPresenceTypeIcon(YhPresenceType yhPresenceType){
        PresenceType presenceType  = getPresenceType(yhPresenceType);
        return presenceType.getPresenceTypeIcon();
    }
}
