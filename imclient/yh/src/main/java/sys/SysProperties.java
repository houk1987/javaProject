package sys;

import com.pubTools.properties.PropertiesTools;

/**
 * Created by HK on 2014/9/15.
 */
public class SysProperties {

    private static final String FILE_PATH = "user/sys.properties";

    public static String getHost(){
        return PropertiesTools.readValue(FILE_PATH, "host");
    }

    public static int getPort(){
        String value = PropertiesTools.readValue(FILE_PATH,"port");
        if(value!=null)return Integer.valueOf(value);
        return -1;
    };

    public static String domain(){
        return PropertiesTools.readValue(FILE_PATH,"domain");
    }

}
