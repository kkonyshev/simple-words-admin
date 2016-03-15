package app;

/**
 *
 * Created by konishev on 10/03/2016.
 */
public class ObjectPrinter {

    public static final String DELIMITER_SEMICOLON = ";";

    public String merge(Object... list) {
        return merge(DELIMITER_SEMICOLON, list);
    }
    public String merge(String delimiter, Object... list) {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<list.length; i++) {
            Object item = list[i];
            sb.append(item==null ? null : item.toString());
            if (i<list.length-1) {
                sb.append(delimiter);
            }
        }
        return sb.toString();
    }
}
