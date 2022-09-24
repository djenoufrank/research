//package g55301g48982.chatserver.common;
//
///**
// *
// * @author User
// */
//public class Process {
//  public static final String DIACRITICS = " àâäéèêëîïôùûç";
//    public static final String DIACRITICS_REPLACE = "+aaaeeeeiiouuc";
//    public static int indexOf(char c, String str) {
//        for (int i = 0; i < str.length(); i++)
//            if (str.charAt(i) == c)
//                return i;
//        return -1;
//    }
//
//    public static String processLine(String line) {
//        line = line.toLowerCase();
//        StringBuilder out = new StringBuilder();
//        for (int i = 0; i < line.length(); i++) {
//            char c = line.charAt(i);
//
//            int index = indexOf(c, DIACRITICS);
//            if (index != -1) //c is DIACRITICS
//                out.append(DIACRITICS_REPLACE.charAt(index));
//            else
//                out.append(c);
//        }
//        return out.toString();
//    }
//}
