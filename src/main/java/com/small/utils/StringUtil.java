package com.small.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 * 字符转换类
 *
 * @author csx
 */
public class StringUtil {
    /**
     * 判断某个字符串是否为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 简单的字符串格式化，性能较好。支持不多于10个占位符，从%1开始计算，数目可变。参数类型可以是字符串、Integer、Object，
     * 甚至int等基本类型
     * 、以及null，但只是简单的调用toString()，较复杂的情况用String.format()。每个参数可以在表达式出现多次。
     *
     * @param msgWithFormat
     * @param autoQuote
     * @param args
     * @return
     */
    public static StringBuilder formatMsg(CharSequence msgWithFormat, boolean autoQuote, Object... args) {
        int argsLen = args.length;
        boolean markFound = false;

        StringBuilder sb = new StringBuilder(msgWithFormat);

        if (argsLen > 0) {
            for (int i = 0; i < argsLen; i++) {
                String flag = "%" + (i + 1);
                int idx = sb.indexOf(flag);
                // 支持多次出现、替换的代码
                while (idx >= 0) {
                    markFound = true;
                    sb.replace(idx, idx + 2, toString(args[i], autoQuote));
                    idx = sb.indexOf(flag);
                }
            }

            if (args[argsLen - 1] instanceof Throwable) {
                StringWriter sw = new StringWriter();
                ((Throwable) args[argsLen - 1])
                        .printStackTrace(new PrintWriter(sw));
                sb.append("\n").append(sw.toString());
            } else if (argsLen == 1 && !markFound) {
                sb.append(args[argsLen - 1].toString());
            }
        }
        return sb;
    }

    public static StringBuilder formatMsg(String msgWithFormat, Object... args) {
        return formatMsg(new StringBuilder(msgWithFormat), true, args);
    }

    public static String toString(Object obj, boolean autoQuote) {
        StringBuilder sb = new StringBuilder();
        if (obj == null) {
            sb.append("NULL");
        } else {
            if (obj instanceof Object[]) {
                for (int i = 0; i < ((Object[]) obj).length; i++) {
                    sb.append(((Object[]) obj)[i]).append(", ");
                }
                if (sb.length() > 0) {
                    sb.delete(sb.length() - 2, sb.length());
                }
            } else {
                sb.append(obj.toString());
            }
        }
        if (autoQuote
                && sb.length() > 0
                && !((sb.charAt(0) == '[' && sb.charAt(sb.length() - 1) == ']') || (sb
                .charAt(0) == '{' && sb.charAt(sb.length() - 1) == '}'))) {
            sb.insert(0, "[").append("]");
        }
        return sb.toString();
    }

    /**
     * 把字符串中的带‘与"转成\'与\"
     *
     * @param orgStr
     * @return
     */
    public static String convertQuot(String orgStr) {
        return orgStr.replace("'", "\\'").replace("\"", "\\\"");
    }

    public static synchronized String encryptSha256(String inputStr) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] digest = md.digest(inputStr.getBytes("UTF-8"));

            return new String(Base64.encodeBase64(digest));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * HTML实体编码转成普通的编码
     *
     * @param dataStr
     * @return
     */
    public static String htmlEntityToString(final String dataStr) {
        int start = 0;
        int end = 0;
        final StringBuffer buffer = new StringBuffer();
        while (start > -1) {
            int system = 10;// 进制
            if (start == 0) {
                int t = dataStr.indexOf("&#");
                if (start != t) {
                    start = t;
                }
            }
            end = dataStr.indexOf(";", start + 2);
            String charStr = "";
            if (end != -1) {
                charStr = dataStr.substring(start + 2, end);
                // 判断进制
                char s = charStr.charAt(0);
                if (s == 'x' || s == 'X') {
                    system = 16;
                    charStr = charStr.substring(1);
                }
            }
            // 转换
            try {
                char letter = (char) Integer.parseInt(charStr, system);
                buffer.append(new Character(letter).toString());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

            // 处理当前unicode字符到下一个unicode字符之间的非unicode字符
            start = dataStr.indexOf("&#", end);
            if (start - end > 1) {
                buffer.append(dataStr.substring(end + 1, start));
            }

            // 处理最后面的非unicode字符
            if (start == -1) {
                int length = dataStr.length();
                if (end + 1 != length) {
                    buffer.append(dataStr.substring(end + 1, length));
                }
            }
        }
        return buffer.toString();
    }

    /**
     * 把String转成html实体字符
     *
     * @param str
     * @return
     */
    public static String stringToHtmlEntity(String str) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            switch (c) {
                case 0x0A:
                    sb.append(c);
                    break;

                case '<':
                    sb.append("&lt;");
                    break;

                case '>':
                    sb.append("&gt;");
                    break;

                case '&':
                    sb.append("&amp;");
                    break;

                case '\'':
                    sb.append("&apos;");
                    break;

                case '"':
                    sb.append("&quot;");
                    break;

                default:
                    if ((c < ' ') || (c > 0x7E)) {
                        sb.append("&#x");
                        sb.append(Integer.toString(c, 16));
                        sb.append(';');
                    } else {
                        sb.append(c);
                    }
            }
        }
        return sb.toString();
    }

    // 转unicode
    public static String stringToUnicode(String s) {
        String unicode = "";
        char[] charAry = new char[s.length()];
        for (int i = 0; i < charAry.length; i++) {
            charAry[i] = (char) s.charAt(i);
            unicode += "\\u" + Integer.toString(charAry[i], 16);
        }
        return unicode;
    }

    public static String unicodeToString(String unicodeStr) {
        StringBuffer sb = new StringBuffer();
        String[] str = unicodeStr.toUpperCase().split("\\\\U");
        for (int i = 0; i < str.length; i++) {
            if ("".equals(str[i])) {
                continue;
            }
            char c = (char) Integer.parseInt(str[i].trim(), 16);
            sb.append(c);
        }
        return sb.toString();
    }


    public static String html2Text(String inputString) {
        String htmlStr = inputString; // 含html标签的字符串
        String textStr = "";
        java.util.regex.Pattern p_script;
        java.util.regex.Matcher m_script;
        java.util.regex.Pattern p_style;
        java.util.regex.Matcher m_style;
        java.util.regex.Pattern p_html;
        java.util.regex.Matcher m_html;

        try {
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script>]*?>[\s\S]*?<\/script>
            // }
            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style>]*?>[\s\S]*?<\/style>
            // }
            String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

            p_script = java.util.regex.Pattern.compile(regEx_script,
                    java.util.regex.Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(""); // 过滤script标签

            p_style = java.util.regex.Pattern.compile(regEx_style,
                    java.util.regex.Pattern.CASE_INSENSITIVE);
            m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll(""); // 过滤style标签

            p_html = java.util.regex.Pattern.compile(regEx_html,
                    java.util.regex.Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll(""); // 过滤html标签

            textStr = htmlStr;

        } catch (Exception e) {
            System.err.println("Html2Text: " + e.getMessage());
        }

        return textStr;// 返回文本字符串
    }

    /**
     * 字符串追加
     *
     * @param num 生成数量
     * @param chr 生成的字符串
     * @return
     */
    public static String splitStr(int num, String chr) {
        String str = "";
        for (int i = 0; i <= num; i++) {
            str += chr;
        }
        return str;
    }

    /**
     * 检测字符串是否重复 完全重复的情况
     *
     * @param s     检测的字符串
     * @param sChar 分隔符
     * @return
     */
    public static String[] checkIdIsRepeat(String s, String sChar) {
        String[] str = new String[2];
        String[] strArr = s.split(sChar);
        String[] strArrCopy = strArr;
        int count;
        for (int i = 0; i < strArr.length; i++) {
            count = 0;
            for (int j = 0; j < strArrCopy.length; j++) {
                if (strArr[i].equals(strArrCopy[j])) {
                    count++;
                }
            }

            if (count > 1) {
                str[0] = "N";
                str[1] = strArr[i] + "有重复出现，请检查！";
                break;
            } else {
                str[0] = "Y";
                str[1] = "经检查无重复项！";
            }

        }

        return str;
    }

    /**
     * 根据指定长度 分隔字符串
     *
     * @param str    需要处理的字符串
     * @param length 分隔长度
     * @return 字符串集合
     */
    public static List<String> splitString(String str, int length) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < str.length(); i += length) {
            int endIndex = i + length;
            if (endIndex <= str.length()) {
                list.add(str.substring(i, i + length));
            } else {
                list.add(str.substring(i, str.length() - 1));
            }
        }
        return list;
    }

    /**
     * 字符串加密 utf8
     *
     * @param s
     * @return
     */
    public static String stringURLEncoderByUTF8(String s) {
        try {
            s = URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * 字符串解密 utf8
     *
     * @param s
     * @return
     */
    public static String stringURLDecoderByUTF8(String s) {
        try {
            s = URLDecoder.decode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * 字符串加密 GBK
     *
     * @param s
     * @return
     */
    public static String stringURLEncoderByGBK(String s) {
        try {
            s = URLEncoder.encode(s, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * 字符串解密 GBK
     *
     * @param s
     * @return
     */
    public static String stringURLDecoderByGBK(String s) {
        try {
            s = URLDecoder.decode(s, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s;
    }
}
