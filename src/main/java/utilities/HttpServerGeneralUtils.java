package utilities;

public class HttpServerGeneralUtils {

    public String getMime( String extension ) {
        String mime = "";
        switch ( extension ) {
            case ".js":
                mime = "text/javascript";
                break;
            case ".html":
                mime = "text/html";
                break;
            case ".css":
                mime = "text/css";
                break;
            case ".ico":
                mime = "image/x-icon";
                break;
            case ".json":
                mime = "application/json";
            default:
                mime = "application/json";
                break;
        }
        return mime;
    }

    public static Boolean isNumeric( String str ) {
        try {
            Integer d = Integer.parseInt( str );
        } catch ( NumberFormatException nfe ) {
            return false;
        }
        return true;
    }

}
