package com.yh.session.message;

import com.imService.message.FontStyle;
import com.imService.message.MessageContentHtml;
import com.imService.message.SessionMessage;

import java.text.SimpleDateFormat;

/**
 * Created by a on 2014/9/5.
 */
public class ChatMessageContentHtml implements MessageContentHtml {

    private SessionMessage sessionMessage;
    public ChatMessageContentHtml(SessionMessage sessionMessage) {
        this.sessionMessage = sessionMessage;
    }

    @Override
    public String getContentHtml() {
        String html = BasicHtml.getBasicHtml();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = "";//formatter.format(sessionMessage.getSendTime());

        html = html
                .replaceAll("#algin#", "left")
                .replaceAll("#msgtip#", sessionMessage.getFrom() + "  " + dateString)
                .replaceAll("#content#", setFontStyle());
        return html;
    }

    private String setFontStyle() {
      FontStyle fontStyle = sessionMessage.getFontStyle();
      return "<p style = \"font-family:ËÎÌו;font-style:italic;font-weight:bold; color:red; font-size:35pt; padding-bottom:2px\">"+sessionMessage.getContent()+"</p>";
    }

    private String getFontStyle(boolean italic){
        return italic?"italic":"normal";
    }

    private String getFontWeight(boolean bold){
        return bold?"bold":"normal";
    }

}
