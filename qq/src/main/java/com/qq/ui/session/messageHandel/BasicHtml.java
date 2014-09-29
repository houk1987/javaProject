package com.qq.ui.session.messageHandel;

/**
 * Created by a on 2014/9/5.
 */
public class BasicHtml {

    public static String getBasicHtml() {

        StringBuffer sb = new StringBuffer("");
        sb.append(
                "<tr id=\"#messageid#\"> <td style=\"padding-top:2pt\" align=\"#algin#\"><table  width=\"68%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"float:right\" >")

                .append("<tr> <td width=\"16\" height=\"21\" background=\"#top1url#\"  ></td>");
        sb.append("<td bubbleid=\"#messageid#\"  msgtype=\"#msgtype#\"  background=\"#top2url#\"   style=\"line-height:21pt; color:#333; font-size:12pt\">");
        sb.append("#msgtip#").append("</td>");

        sb.append(
                "<td width=\"22\" background=\"#top3url#\"></td>  </tr> ")
                .append("<tr> <td valign=\"top\" width=\"16\"  background=\"#center1url#\" style=\"background-repeat:repeat-y\"><div background=\"#center2url#\"   style=\"width:16pt; height:13pt; background-repeat:no-repeat;\"></div></td>");

        sb.append("<td bubbleid=\"#messageid#\" msgtype=\"#msgtype#\"  style = \"line-height:21pt; color:#333; font-size:13pt;padding-bottom:2px\">#content#</td>");
        sb.append(
                " <td valign=\"top\" width=\"22\"  background=\"#center4url#\"  style=\"background-repeat:repeat-y\"><div background=\"#center5url#\"  style=\"width:22pt; height:13pt; background-repeat:no-repeat; background-position:top\"></div></td>")
                .append("</tr>")
                .append("<tr>  <td width=\"16\" height=\"13\" background=\"#bottom1url#\"  style=\"background-repeat:no-repeat \"></td>");
        sb.append("<td background=\"#bottom2url#\" style=\"background-repeat:repeat-x\"></td>");
        sb.append(
                "<td width=\"22\" height=\"13\" background=\"#bottom3url#\" style=\"background-repeat:no-repeat \"></td>")
                .append(" </tr></table></td></tr>");
        return sb.toString();
    }
}
