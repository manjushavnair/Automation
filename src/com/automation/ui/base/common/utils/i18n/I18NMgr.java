package com.automation.ui.base.common.utils.i18n;

import com.automation.ui.base.common.exception.BusinessException;

import java.util.*;


public class I18NMgr {


    final public static String ENCODING_AUTO = "auto";
    private final static String sDefaultLanguageEncoding = "ISO-8859-1";
    private static I18NMgr sI18NMgr = null;
    private List fEncodings = null;
    private Map fEncodingTable = null;

    private I18NMgr() {
        fEncodings = null;
        getEncodings();
    }

    public static I18NMgr getI18NMgr() {
        if (sI18NMgr != null)
            return sI18NMgr;

        synchronized (I18NMgr.class) {
            if (sI18NMgr != null)
                return sI18NMgr;

            I18NMgr sI18NMgr = new I18NMgr();
            return sI18NMgr;
        }
    }

    public String getDefaultEncoding() {
        return sDefaultLanguageEncoding;
    }


    public String getContentType(String encoding) {
        if (encoding == null)
            encoding = getDefaultEncoding();
        String type = "text/html";
        String t = type + "; charset=" + encoding;
        return t;
    }


    public I18NEncoding getEncodingInfo(String encoding) {
        String dft_key = getDefaultEncoding().toLowerCase();
        if (encoding == null)
            encoding = dft_key;
        else
            encoding = encoding.toLowerCase();
        I18NEncoding o = (I18NEncoding) fEncodingTable.get(encoding);
        if (o == null)
            o = (I18NEncoding) fEncodingTable.get(dft_key);
        return o;
    }

    /**
     * Get Encodings
     */
    public Collection getEncodings() {
        if (fEncodings != null)
            return fEncodings;

        String code_we = getDefaultEncoding();
        String code_u8 = "UTF-8";
        String[][] ecs = {
                {"auto", "Auto", "Same as Current Customer"},
                {code_u8, "UTF8", "Unicode (UTF8)"},
                {code_we, "Western", "Western"},
                {"gb2312", "&#31616;&#20307;&#20013;&#25991;", "&#31616;&#20307;&#20013;&#25991; (Simplified Chinese)"},
                {"big5", "&#32321;&#39636;&#20013;&#25991;", "&#32321;&#39636;&#20013;&#25991; (Traditional Chinese)"},
                {"SJIS", "&#26085;&#26412;&#35486;", "&#26085;&#26412;&#35486; (Japanese Shift-JIS)"}
        };

        fEncodingTable = new HashMap();
        fEncodings = new LinkedList();
        for (int i = 0; i < ecs.length; i++) {
            I18NEncoding ec = new I18NEncoding(ecs[i][0], ecs[i][2], ecs[i][1]);
            fEncodings.add(ec);
            fEncodingTable.put(ec.getEncoding().toLowerCase(), ec);
        }

        return fEncodings;
    }


    public String stringToCode(String s) {
        if (s == null)
            return "null";
        String r = "|";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            //int n = Character.getNumericValue(c);
            int n = (int) c;
            r = r + n + "|";
        }
        return r;
    }

    public String asciiToUnicode(String s0, String encoding) throws BusinessException {
        String s1 = "";
        if (s0 == null)
            return null;
        try {
            byte[] bs = s0.getBytes("ISO-8859-1");
            s1 = new String(bs, encoding);

        } catch (java.io.UnsupportedEncodingException e) {
            throw new BusinessException("unsupported encoding: " + encoding);
        }
        return s1;
    }

    public String unicodeToAscii(String s0, String encoding) throws BusinessException {
        String s1 = "";
        if (s0 == null)
            return null;
        try {
            byte[] bs = s0.getBytes(encoding);
            s1 = new String(bs, "ISO-8859-1");

        } catch (java.io.UnsupportedEncodingException e) {
            throw new BusinessException("unsupported encoding: " + encoding);
        }
        return s1;
    }


}
