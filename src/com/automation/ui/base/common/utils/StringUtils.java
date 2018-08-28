package com.automation.ui.base.common.utils;

/* java specific imports */

import org.apache.log4j.Logger;

import java.awt.*;
import java.lang.reflect.Array;
import java.text.Format;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;


public class StringUtils {


    /**
     * This is the static constant that stores the data base escape character, used in
     * data base query string like clause.
     */
    public static final String dbSearchStringEscapeChar = "\\";
    /**
     * This is the static constant that stores the Delimeter for converting the
     * string containing these delimeters to string array and vice-versa.
     */
    private static final String DELIMITER = "~";


    /**
     * replaces built-in HTML/XML charecters(&,<,>,',") with escape charecters.
     *
     * @param a_content        The string to be encoded
     * @return String        The encoded string
     *
     */
    private static Logger logger = Logger
            .getLogger(StringUtils.class);

    /**
     * Java String treats " (for termination) and \ (for escape) as special
     * characters which are to be escaped for considering them as literals.
     *
     * @param a_content The string which is to be escaped.
     * @return String        The escaped string.
     */
    public static final String escapeString(String a_content) {
        if (null == a_content || 0 == a_content.length()) {
            return a_content;
        }//end if (null == a_content || 0 == a_content.length())

        StringBuffer encodedContent = new StringBuffer(a_content);
        encodedContent.replace(0, encodedContent.length(),
                replace(encodedContent.toString(), "\\",
                        "\\\\"));
        encodedContent.replace(0, encodedContent.length(),
                replace(encodedContent.toString(), "\"",
                        "\\\""));
        return encodedContent.toString();
    }

    /**
     * This method is used for escaping %,_" charecters.
     *
     * @param a_queryStr The string to be encoded.
     * @return String        The encoded string
     */
    public static final String escapeDBSearchString(String a_queryStr) {
        if (null == a_queryStr || 0 == a_queryStr.length()) {
            return a_queryStr;
        }//end if (null == a_queryStr || 0 == a_queryStr.length())

        StringBuffer encodedContent = new StringBuffer(a_queryStr);
        encodedContent.replace(0, encodedContent.length(),
                replace(encodedContent.toString(), "\\",
                        "\\\\"));
        encodedContent.replace(0, encodedContent.length(),
                replace(encodedContent.toString(), "%",
                        "\\%"));
        encodedContent.replace(0, encodedContent.length(),
                replace(encodedContent.toString(), "_",
                        "\\_"));

        return encodedContent.toString();
    }

    /**
     * This method is used for escaping ' charecter in db parameters.
     *
     * @param a_paramStr The string to be encoded.
     * @return String        The encoded string
     */
    public static final String escapeDBParameter(String a_paramStr) {
        if (null == a_paramStr || 0 == a_paramStr.length()) {
            return a_paramStr;
        }

        String encodedContent = replace(a_paramStr, "'", "''");

        return encodedContent;
    }

    /**
     * This method is used for escaping  keywords and operators supported
     * by the contains operator used in search queries.
     *
     * @param a_paramStr The string to be encoded.
     * @return String        The encoded string
     */
    public static final String escapeIndexSearchParameter(String a_paramStr) {

        StringBuffer initialContent = new StringBuffer(a_paramStr);
        int length = initialContent.length();
        initialContent.replace(0, length,
                replace(initialContent.toString(), "}",
                        "}}"));
        initialContent.replace(0, length,
                replace(initialContent.toString(), "\\",
                        "\\\\"));
        StringBuffer encodedContent = new StringBuffer();
        encodedContent.append("{" + initialContent.toString() + "}");
        return encodedContent.toString();
    }


    public static final String encodeHTML(String a_content) {
        if (null == a_content) {
            return null;
        }//end if (null == a_content)
        if (0 == a_content.length()) {
            return a_content;
        }//end if (0 == a_content.length())

        StringBuffer encodedContent = new StringBuffer(a_content);

        encodedContent.replace(0, encodedContent.length(),
                replace(encodedContent.toString(), "&",
                        "&amp;"));
        encodedContent.replace(0, encodedContent.length(),
                replace(encodedContent.toString(), "\"",
                        "&quot;"));
        encodedContent.replace(0, encodedContent.length(),
                replace(encodedContent.toString(), "<",
                        "&lt;"));
        encodedContent.replace(0, encodedContent.length(),
                replace(encodedContent.toString(), ">",
                        "&gt;"));
        encodedContent.replace(0, encodedContent.length(),
                replace(encodedContent.toString(), "'",
                        "&#39;"));

        return encodedContent.toString();
    }


    /**
     * This method is used for converting the string array into
     * delimeted string.
     *
     * @param a_inputValues The string array to be converted into delimeted
     *                      string using the given delimeter.
     * @param a_delimiter   Given delimeter used for converting the string
     *                      array into delimeted string.
     * @return String            The delimeted string.
     */
    public static final String convertIntArrayToDelimitedString(int[] a_inputValues,
                                                                String a_delimiter) {
        if (null == a_inputValues) {
            return null;
        }//end if (null == a_inputValues)

        StringBuffer temp = new StringBuffer();

        if (0 != a_inputValues.length) {
            temp = temp.append(a_inputValues[0]);
            for (int count = 1; count < a_inputValues.length; count++) {
                temp = temp.append(a_delimiter).append(a_inputValues[count]);
            }//end of count/count < a_inputValues.length
        }//end if (0 != a_inputValues.length)

        return temp.toString();
    }


    /**
     * This method is used for converting the string array into
     * delimeted string.
     *
     * @param a_inputValues The string array to be converted into
     *                      delimeted string using the default delimeter(~).
     * @return String            The delimeted string.
     */
    public static final String convertStringArrayToDelimitedString(String[] a_inputValues) {
        int arrayLength = 0;
        if (null == a_inputValues) {
            return null;
        }//end if (null == inputValues)

        StringBuffer temp = new StringBuffer();
        arrayLength = a_inputValues.length;
        if (0 != arrayLength) {
            temp = temp.append(a_inputValues[0]);
            for (int count = 1; count < arrayLength; count++) {
                temp = temp.append(DELIMITER).append(a_inputValues[count]);
            }//end of count/count < arrayLength
        }//end if (0 != arrayLength)

        return temp.toString();
    }

    /**
     * This method is used for converting the string array into
     * delimeted string.
     *
     * @param a_inputValues The string array to be converted into delimeted
     *                      string using the given delimeter.
     * @param a_delimiter   Given delimeter used for converting the string
     *                      array into delimeted string.
     * @return String            The delimeted string.
     */
    public static final String convertStringArrayToDelimitedString(String[] a_inputValues,
                                                                   String a_delimiter) {
        if (null == a_inputValues) {
            return null;
        }//end if (null == a_inputValues)

        StringBuffer temp = new StringBuffer();

        if (0 != a_inputValues.length) {
            temp = temp.append(a_inputValues[0]);
            for (int count = 1; count < a_inputValues.length; count++) {
                temp = temp.append(a_delimiter).append(a_inputValues[count]);
            }//end of count/count < a_inputValues.length
        }//end if (0 != a_inputValues.length)

        return temp.toString();
    }

    /**
     * This method is used for converting a given delimeted string into
     * string array.
     *
     * @param a_stringToBeConverted The delimeted string to be converted
     *                              into string array using the default delimeter(~).
     * @return String[]                The string array converted from the
     * delimeted string.
     */
    public static final String[] convertDelimitedStringToStringArray(String a_stringToBeConverted) {
        int toknum = 0;
        if (null == a_stringToBeConverted) {
            return new String[0];
        }//end if (null == stringToBeConverted)

        StringTokenizer strTok = new StringTokenizer(a_stringToBeConverted,
                DELIMITER);
        toknum = strTok.countTokens();
        String[] strArr = new String[toknum];
        for (int count = 0; count < toknum; count++) {
            strArr[count] = strTok.nextToken();
        }//end of count/count < toknum

        return strArr;
    }


    /**
     * This method is used for converting a given delimeted string into
     * collection object.
     *
     * @param a_stringToBeConverted - The delimeted string to be converted
     *                              into string array using the default delimeter(~).
     * @return Collection - The collectiob converted from the
     * delimeted string.
     */
    public static final Collection convertDelimitedStringToList(String a_stringToBeConverted) {
        int toknum = 0;
        if (null == a_stringToBeConverted) {
            return new ArrayList();
        }//end if (null == stringToBeConverted)

        StringTokenizer strTok = new StringTokenizer(a_stringToBeConverted,
                DELIMITER);
        toknum = strTok.countTokens();
        Collection coll = new ArrayList(toknum);
        for (int count = 0; count < toknum; count++) {
            coll.add(strTok.nextToken());
        }//end of count/count < toknum

        return coll;
    }

    /**
     * This method is used for converting a given delimeted string into
     * collection object.
     *
     * @param a_stringToBeConverted - The delimeted string to be converted
     *                              into string array using the default delimeter(~).
     * @param a_delimeter           - The delimeted string to be converted
     *                              into string array using the default delimeter(~).
     * @return Collection - The collectiob converted from the
     * delimeted string.
     */
    public static final Collection convertDelimitedStringToList(String a_stringToBeConverted, String a_delimeter) {
        int toknum = 0;
        if (null == a_stringToBeConverted) {
            return new ArrayList();
        }//end if (null == stringToBeConverted)

        StringTokenizer strTok = new StringTokenizer(a_stringToBeConverted,
                a_delimeter);
        toknum = strTok.countTokens();
        Collection coll = new ArrayList(toknum);
        for (int count = 0; count < toknum; count++) {
            coll.add(strTok.nextToken());
        }//end of count/count < toknum

        return coll;
    }


    /**
     * This method is used for converting a given delimeted string into
     * string array.
     *
     * @param a_stringToBeConverted The delimeted string to be converted
     *                              into string array
     * @param a_delimeter           Given delimeter used for converting the delimeted
     *                              string into  string array.
     * @return String[] The string array converted from the delimeted string.
     */
    public static final String[] convertDelimitedStringToStringArray(String a_stringToBeConverted,
                                                                     String a_delimeter) {
        int toknum = 0;
        if (null == a_stringToBeConverted) {
            return new String[0];
        }//end if (null == stringToBeConverted)

        StringTokenizer strTok = new StringTokenizer(a_stringToBeConverted,
                a_delimeter);
        toknum = strTok.countTokens();
        String[] strArr = new String[toknum];
        for (int count = 0; count < toknum; count++) {
            strArr[count] = strTok.nextToken();
        }//end of count/count < toknum

        return strArr;
    }


    /**
     * This method is used for converting a given delimeted string into
     * int array.
     *
     * @param a_stringToBeConverted The delimeted string to be converted
     *                              into int array using the default delimeter(~).
     * @return The int array converted from the delimeted string.
     */
    public static final int[] convertDelimitedStringToIntArray(String a_stringToBeConverted) {
        int toknum = 0;
        if (null == a_stringToBeConverted) {
            return new int[0];
        } //end if (null == stringToBeConverted)

        StringTokenizer strTok = new StringTokenizer(a_stringToBeConverted, DELIMITER);
        toknum = strTok.countTokens();

        int[] intArr = new int[toknum];
        for (int count = 0; count < toknum; count++) {
            intArr[count] = Integer.parseInt(strTok.nextToken());
        } //end of count/count < toknum

        return intArr;
    }

    /**
     * This method is used for converting a given delimeted string into
     * int array.
     *
     * @param a_stringToBeConverted The delimeted string to be converted
     *                              into int array using the a_delimeter.
     * @param a_delimeter           The delimeted string to be converted
     *                              into int array using the a_delimeter.
     * @return The int array converted from the delimeted string.
     */
    public static final int[] convertDelimitedStringToIntArray(String a_stringToBeConverted, String a_delimeter) {
        int toknum = 0;
        if (null == a_stringToBeConverted) {
            return new int[0];
        } //end if (null == stringToBeConverted)

        StringTokenizer strTok = new StringTokenizer(a_stringToBeConverted, a_delimeter);
        toknum = strTok.countTokens();

        int[] intArr = new int[toknum];
        for (int count = 0; count < toknum; count++) {
            intArr[count] = Integer.parseInt(strTok.nextToken());
        } //end of count/count < toknum

        return intArr;
    }


    /**
     * This method formats the number to a decimal format
     *
     * @param argMessage       String
     * @param argMessageParams String[]
     * @return returns String
     */
    public static String formatErrorMessage(String argMessage,
                                            String[] argMessageParams) {
        MessageFormat objFormatter = new MessageFormat("");
        String strMessage = null;
        objFormatter.applyPattern(argMessage);
        strMessage = objFormatter.format(argMessageParams);
        return strMessage;
    }


    /**
     * Method
     *
     * @param str        Parameter
     * @param subString  Parameter
     * @param strReplace Parameter
     * @return Return Value
     */
    public static String replaceSubString(String str, String subString, String strReplace) {
        if (str == null || subString == null) {
            return str;
        }
        if (strReplace == null) {
            strReplace = "";
        }
        int replaceLength = subString.length();
        if (replaceLength < 1) {
            return str;
        }

        StringBuffer sb = new StringBuffer(str);
        int replacedLength = strReplace.length();
        int startIndex = 0;
        try {
            for (; ; ) {
                str = sb.toString();
                startIndex = str.indexOf(subString, startIndex);

                if (startIndex == -1) {
                    break;
                }
                sb.replace(startIndex, startIndex + replaceLength, strReplace);
                startIndex = startIndex + replacedLength;
                //log.debug(sb.toString());
            }
        } catch (Exception e) {
            return str;
        }
        str = sb.toString();
        return str;
    }


    /**
     * This  method is used to reverse a string .The password enter in the descending order will be reversed
     * and cross checked .Used for easy computation purpose.
     *
     * @param strPassword
     * @return String
     */
    public static String reverseString(String strPassword) {
        StringBuffer sbf = new StringBuffer(strPassword);
        return sbf.reverse().toString();
    }


    /**
     * This method trims the given string if it is not null
     *
     * @param strValue
     * @return String
     */
    public static String trimString(String strValue) {
        if (strValue != null) {
            return strValue.trim();
        } else {
            return strValue;
        }
    }

    /**
     * This method returns a blank string if the input passed is null.
     * This is used generally where values from bean elements are used
     * to paint the contents of screen elements and where the string
     * 'null' shouldn't be displayed
     *
     * @param strInput String   The input string
     * @return returns String   The default string value
     */
    public static String getNonNullString(String strInput) {
        return ((strInput == null) ? " " : strInput.trim());
    }

    public static boolean isNull(String str) {
        if (str.equals(""))
            return true;
        else
            return false;
    }

    /**
     * Method isEmptyString.
     *
     * @param str
     * @return boolean
     */
    public static boolean isEmptyString(String str) {
        return str == null || str.length() == 0 || isWhitespace(str);
    }

    /**
     * Method isWhitespace.
     *
     * @param str
     * @return boolean
     */
    public static boolean isWhitespace(String str) {
        int len = str.length();

        for (int i = 0; i < len; i++)
            if (!Character.isWhitespace(str.charAt(i)))
                return false;

        return true;
    }

    /**
     * Method toString.
     *
     * @param o
     * @return String
     */
    public static String toString(Object o) {
        if (o != null && o.getClass().isArray()) {
            int len = Array.getLength(o);
            StringBuffer sb = new StringBuffer(len * 3);
            sb.append("{ ");
            for (int i = 0; i < len; i++) {
                if (i != 0)
                    sb.append(", ");
                sb.append(toString(Array.get(o, i)));
            }

            sb.append(" }");
            return sb.toString();
        } else {
            return String.valueOf(o);
        }
    }

    /**
     * Method replaceGlobal.
     *
     * @param theString
     * @param theSubString
     * @param theNewSubString
     * @return String
     */
    public static String replaceGlobal(
            String theString,
            String theSubString,
            String theNewSubString) {
        StringBuffer sb = new StringBuffer();
        int subStrLen = theSubString.length();
        int prevIndx = 0;

        do {
            int indx = theString.indexOf(theSubString, prevIndx);
            if (indx >= 0) {
                sb.append(theString.substring(prevIndx, indx));
                sb.append(theNewSubString);
                prevIndx = indx + subStrLen;
            } else {
                sb.append(theString.substring(prevIndx));
                return sb.toString();
            }
        } while (true);
    }


    /**
     * This method pads out String out to padToLen with character ch.
     * Depending upon postfix decides whether it puts padding before string
     * or after.
     * <p>
     *
     * @param str      String to be padded
     * @param ch       character to use for padding
     * @param padToLen final length of string after padding
     * @param postfix  if true put padding after string, else before string
     * @return String containing str with padding added
     */
    public static String padString(String str, char ch, int padToLen, boolean postfix) {
        StringBuffer sb = new StringBuffer();
        // if null string supplied just return string containing ch's upto
        // length padToLen
        if (null == str) {
            for (int i = 0; i < padToLen; i++) {
                sb.append(ch);
            }
        } else {
            // check if padding chars to be added at end of string or before
            if (postfix) {
                for (int i = 0; i < padToLen; i++) {
                    if (i < str.length()) {
                        sb.append(str.charAt(i));
                    } else {
                        sb.append(ch);
                    }
                }
            } else {
                for (int i = 0; i < padToLen - str.length(); i++) {
                    sb.append(ch);
                }
                sb.append(str);
            }
        }
        return sb.toString();
    }


    /**
     * This method pads the given string with the
     * given charecter with alingment left or right
     *
     * @param string  string
     * @param length     length
     * @param charecter    charecter
     * @param paddingLeft paddingLeft
     * @return string padded string
     */
    public static String paddingString(String string, int length, char charecter, boolean paddingLeft) {
        String stringToPad = string;
        if (string == null) {
            stringToPad = " ";
        }
        StringBuffer buff = new StringBuffer(stringToPad);
        int strLength = buff.length();
        if (length > 0 && length > strLength) {
            for (int i = 0; i <= length; i++) {
                if (paddingLeft) {
                    if (i < length - strLength)
                        buff.insert(0, charecter);
                } else {
                    if (i > strLength)
                        buff.append(charecter);
                }
            }
        }
        return buff.toString();
    }

    /**
     * if a prod# contains a ', change it to ''; used for sql statement
     *
     * @param prod
     * @return
     */
    static public String escapeProd(String prod) {
        String thisProd = prod;
        int i, from;
        StringBuffer sb;
        from = 0;
        while ((i = thisProd.indexOf("'", from)) > -1) {
            sb = new StringBuffer(thisProd);
            sb.replace(i, i + 1, "''");
            thisProd = sb.toString();
            from = i + 2;
        }

        return thisProd;
    }

    /**
     * Split an string by separator
     */
    static public String join(String[] ss, String join_by) {
        if (ss != null && ss.length > 0) {
            StringBuffer sb = new StringBuffer();

            for (int i = 0; i < ss.length; i++) {
                sb.append(ss[i]);
                if (i < ss.length)
                    sb.append(join_by);
            }

            return sb.toString();
        }
        return null;
    }

    /*No seperator at the end. Such as join a,b and c by | will be a|b|c */
    static public String joinEndFree(String[] ss, String join_by) {
        if (ss != null && ss.length > 0) {
            StringBuffer sb = new StringBuffer();

            for (int i = 0; i < ss.length; i++) {
                sb.append(ss[i]);
                if (i < ss.length - 1)
                    sb.append(join_by);
            }
            return sb.toString();
        }
        return null;
    }

    /*No seperator at the end. Such as join a,b and c by | will be a|b|c */
    static public String joinListEndFree(List ss, String join_by) {
        if (ss != null && ss.size() > 0) {
            StringBuffer sb = new StringBuffer();

            for (int i = 0; i < ss.size(); i++) {
                sb.append((String) ss.get(i));
                if (i < ss.size() - 1)
                    sb.append(join_by);
            }
            return sb.toString();
        }
        return null;
    }

    static public String objectToString(Format format, Object object) {
        if (object == null)
            return null;

        if (format == null)
            return object.toString();
        else {
            return format.format(object);
        }
    }

    static public boolean containsChar(String string, char c) {
        int length = string.length();
        for (int i = 0; i < length; i++) {
            if ((string.charAt(i)) == c)
                return true;
        }
        return false;
    }

    static public String stripWhitespace(String string) {
        if (containsWhitespace(string)) {
            char c;
            string = string.trim();
            int length = string.length();
            StringBuffer outString = new StringBuffer("");
            for (int i = 0; i < length; i++) {
                c = string.charAt(i);
                if (!Character.isWhitespace(c))
                    outString.append(c);
            }
            return outString.toString();
        } else
            return string;
    }


    static public boolean containsWhitespace(String string) {
        int length = string.length();
        for (int i = 0; i < length; i++) {
            if (Character.isWhitespace(string.charAt(i)))
                return true;
        }
        return false;
    }

    public static String unescape(String s) {

        if (s != null && !isEmptyString(s)) {
            String out = replace(s, "&amp;", "&");
            out = replace(out, "&gt;", ">");
            out = replace(out, "&lt;", "<");
            return out;
        } else
            return null;

    }

    static public String replace(String string,
                                 String oldSubstring,
                                 String newSubstring) {
        if (oldSubstring == null || newSubstring == null)
            return string;
        if (string == null)
            return string;
        if (string.indexOf(oldSubstring) < 0)
            return string;
        StringBuffer value = new StringBuffer("");
        int length = string.length();
        for (int i = 0; i < length; i++) {
            if (string.startsWith(oldSubstring, i)) {
                value.append(newSubstring);
                i += oldSubstring.length() - 1;
            } else
                value.append(string.charAt(i));
        }
        return value.toString();
    }


    static public boolean isEmpty4Tab(String s) {
        if (s == null || s.length() == 0)
            return true;
        else
            return false;
    }


    /**
     * Check if char is plain ASCII lower case.
     *
     * @param c char to check
     * @return true if char is in range a..z
     * @see Character#isLowerCase(char)
     */
    public static boolean isUnaccentedLowerCase(char c) {
        return 'a' <= c && c <= 'z';
    } // isUnaccentedLowerCase

    /**
     * Check if char is plain ASCII upper case.
     *
     * @param c char to check.
     * @return true if char is in range A..Z.
     * @see Character#isUpperCase(char)
     */
    public static boolean isUnaccentedUpperCase(char c) {
        return 'A' <= c && c <= 'Z';
    } // end isUnaccentedUpperCase


    /**
     * Produce a String of a given repeating character.
     *
     * @param c     the character to repeat
     * @param count the number of times to repeat
     * @return String, e.g. rep('*',4) returns "****"
     */
    public static String rep(char c, int count) {
        char[] s = new char[count];
        for (int i = 0; i < count; i++) {
            s[i] = c;
        }
        return new String(s).intern();
    } // end rep

    /**
     * Ensure the string contains only legal characters
     *
     * @param candidate  string to test.
     * @param legalChars characters than are legal for candidate.
     * @return true if candidate is formed only of chars from the legal set.
     */
    public static boolean isLegal(String candidate, String legalChars) {
        for (int i = 0; i < candidate.length(); i++) {
            if (legalChars.indexOf(candidate.charAt(i)) < 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * Convert int to hex with lead zeroes
     *
     * @param h number you want to convert to hex
     * @return 0x followed by unsigned hex 8-digit representation
     * @see #toString(Color)
     */
    public static String toHexString(int h) {
        String s = Integer.toHexString(h);
        if (s.length() < 8) { // pad on left with zeros
            s = "00000000".substring(0, 8 - s.length()) + s;
        }
        return "0x" + s;
    }


    /**
     * Method removeDecimalPoint.
     *
     * @param str
     * @return String
     */
    private String removeDecimalPoint(String str) {
        StringBuffer buff = new StringBuffer(0);
        if (str != null) {
            buff.append(str);
            int index = buff.indexOf(".");
            if (index >= 0)
                buff = buff.deleteCharAt(index);
        }
        return buff.toString();
    }

} // end of class StringUtils
