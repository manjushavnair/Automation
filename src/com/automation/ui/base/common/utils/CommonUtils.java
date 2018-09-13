package com.automation.ui.base.common.utils;

import com.automation.ui.base.common.core.exceptions.TestEnvInitFailedException;
import com.automation.ui.base.common.logging.Log;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class CommonUtils {

    public static final int MAX_PASSWORD = 12;
    public static final int MIN_PASSWORD = 8;
    final static String APORSTROPHESQL = "''";
    private static Logger logger = Logger
            .getLogger(CommonUtils.class);
    final String charList = "& ' < > \" ";
    final String AMPERSAND = "&amp;";
    final String GREATERTHAN = "&gt;";
    final String LESSTHAN = "&lt;";
    final String QUOTE = "&quot;";


    /******************************************************************************/
    final String APORSTROPHE = "&apos;";
    final int MAX_WORD_SIZE = 20;


    private CommonUtils() {

    }

    public static void appendTextToFile(String filePath, String textToWrite) {
        try {
            FileWriter newFile = new FileWriter(filePath, true);
            BufferedWriter out = new BufferedWriter(newFile);


            out.write(textToWrite);
            out.newLine();
            out.flush();
            out.close();
        } catch (IOException e) {
            throw new TestEnvInitFailedException();
        }
    }

    /**
     * delete directory by path
     */
    public static void deleteDirectory(String dirName) {
        try {
            FileUtils.deleteDirectory(new File(dirName));
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("Unable to delete directory: " + dirName);
        }
    }

    /**
     * creates directory based on given path
     */
    public static void createDirectory(String fileName) {
        try {


            File makeDir = new File(fileName);
            if (!makeDir.exists()) ;
            {
                makeDir.mkdir();

                // set application user permissions to 455
                makeDir.setExecutable(true);
                makeDir.setReadable(true);
                makeDir.setWritable(true);
            }


            // System.out.println("directory " + fileName + " created");
        } catch (SecurityException e) {
            e.printStackTrace();
            throw new TestEnvInitFailedException();
        }
    }

    public static String getAbsolutePathForFile(String relativePath) {
        File fileCheck = new File(relativePath);
        if (!fileCheck.isFile()) {
            throw new TestEnvInitFailedException("file " + relativePath + " doesn't exists");
        }
        return fileCheck.getAbsolutePath();
    }



    /**
     * * Escapes all of the special characters out of a String.<BR>
     * * This was designed to overcome the CDATA ]]> tag in mail message or subject
     * * confusing output stream.  It escapes the characters using &#xXX; where XX is
     * * the hex representation of the character.  In this implemetation a special
     * * character is defined as not ( a-z, A-Z, 0-9, or space ).
     * *
     * *
     * * @param         pString   the input string
     * *
     * * @return        the escaped string
     * *
     * // used in AddressBook,Login and Mail
     *******************************************************************************/
    public static String escape(String pString) {
        StringBuffer result = new StringBuffer();
           /* for (int i = 0; pString != null && i < pString.length(); i++)
	        {
	            char ch;
	            ch = pString.charAt(i);
	            if (('a' <= ch && ch <= 'z')
	                    || ('A' <= ch && ch <= 'Z')
	                    || ('0' <= ch && ch <= '9')
	                    || ('�' == ch)
	                    || ('�' == ch)
	                    || ('�' == ch)
	                    || ('\u00C4' == ch)
	                    || // �A
	                    ('\u00D6' == ch)
	                    || // �O
	                    ('\u00DC' == ch)
	                    || // �U
	                    ('�' == ch)
	                    || ('�' == ch)
	                    || ('�' == ch)
	                    || ('\u00C9' == ch)
	                    || //�E
	                    ('\u00C8' == ch)
	                    || //`E
	                    ('\u00C0' == ch)
	                    || //`A
	                    (' ' == ch)
	                    || ('.' == ch)
	                    || ('-' == ch)
	                    || ('_' == ch)
	                    || ('@' == ch))
	            {
	                result.append(ch);
	            }
	            else
	            {
	                result.append("&#x");
	                result.append(Integer.toHexString((int) ch));
	                result.append(";");
	            }
	        }
	        */
        return result.toString();
    }

    /*
     * This method checks null or empty for the input String.
     *
     * @param String strValue is the input String.
     * @reurn true if the input String is null.
     *        false if the input is not null or empty
     *
     */
    public static boolean isNull(String strValue) {
        if (strValue == null || strValue.trim().length() == 0) {
            return true;
        }
        return false;
    }

    /**
     * This method is used to remove special character from the
     * input string and replace them with their corresponding XML
     * excape sequences
     * <p>
     * This method is implemented as a static method as this method is
     * used very frequently and this will reduce the object instantiation
     * overheads.
     * <p>
     * If you find any special character missing, please add the same.
     * <p>
     * The special characters that are handled and their equivalents are
     * "&" -> "&amp;"
     * """ -> "&quot;" -> This is the character for double quotes
     * "<" -> "&lt;"
     * ">" -> "&gr;"
     * "'" -> "&apos"
     *
     * @param inString The xml data that needs xml special characters treatment
     * @return String. The xml data with special characters
     */
    public static String removeSpChars(String inString) {

        int pos1 = -1;
        int i = 0;
        StringBuffer sb;
        String[] replaceStr = {"&", "\"", "<", ">", "'"};
        String[] replaceWithStr =
                {"&amp;", "&quot;", "&lt;", "&gt;", "&apos;"};
        if (inString == null || inString.equals(""))
            return "";

        for (i = 0; i < replaceStr.length; i++) {
            pos1 = inString.indexOf(replaceStr[i]);

            if (pos1 != -1) {
                sb = new StringBuffer(inString);
                int pos2 = 0;

                outer:
                do {
                    if ((i == 0)) {
                        for (int j = 0; j < replaceWithStr.length; j++) {
                            if (inString
                                    .regionMatches(
                                            pos1,
                                            replaceWithStr[j],
                                            0,
                                            replaceWithStr[j].length())) {
                                pos1 =
                                        inString.indexOf(replaceStr[i], pos1 + 1);
                                continue outer;
                            }
                        }
                    }
                    sb =
                            sb.replace(
                                    pos1 + pos2,
                                    pos1 + pos2 + 1,
                                    replaceWithStr[i]);
                    pos2 = pos2 + replaceWithStr[i].length() - 1;
                    pos1 = inString.indexOf(replaceStr[i], pos1 + 1);
                } while (pos1 != -1);
                inString = new String(sb);
            }
        }
        return inString;
    } //end of removeSpecialChars method

    /**
     * This method removes any "'" in the sql string about to be
     * executed.
     *
     * @param inString String of the input sql
     * @return String which will execute proper SQL
     */
    public static String removenonSQLChars(String inputString) {
        String outPutString = "";
        StringTokenizer st = null;
        //StringTokenizer stForCharList = new StringTokenizer(charList);

        if (inputString == null)
            return "";

        String substituteString = APORSTROPHESQL;
        st = new StringTokenizer(inputString, "'");
        String strippedString = "";

        while (st.hasMoreTokens()) {
            if (strippedString.equals("")) {
                strippedString += st.nextToken();
            } else {
                String tempString = st.nextToken();
                strippedString += substituteString + tempString;
            }
        }

        outPutString = strippedString;
        return outPutString;
    }

    /**
     * Method to replace all the occurances of the supplied String[] array <code>replaceStr</code> with the
     * corrosponding values in the <code>replaceWithStr</code> String[] array in the specified
     * input string <code>inString</code>
     *
     * @param inString       input string to modify
     * @param replaceStr     the array of strings to replace
     * @param replaceWithStr the array of strings to replace with
     * @return String modifed string
     */
    public static String replaceAll(String inString, String[] replaceStr, String[] replaceWithStr) {
        int pos1 = -1;
        int i = 0;
        StringBuffer sb;

        if (inString == null || inString.equals(""))
            return "";

        for (i = 0; i < replaceStr.length; i++) {
            pos1 = inString.indexOf(replaceStr[i]);

            if (pos1 != -1) {
                sb = new StringBuffer(inString);
                int pos2 = 0;

                outer:
                do {
                    if ((i == 0)) {
                        for (int j = 0; j < replaceWithStr.length; j++) {
                            if (inString
                                    .regionMatches(
                                            pos1,
                                            replaceWithStr[j],
                                            0,
                                            replaceWithStr[j].length())) {
                                pos1 =
                                        inString.indexOf(replaceStr[i], pos1 + 1);
                                continue outer;
                            }
                        }
                    }
                    sb =
                            sb.replace(
                                    pos1 + pos2,
                                    pos1 + pos2 + 1,
                                    replaceWithStr[i]);
                    pos2 = pos2 + replaceWithStr[i].length() - 1;
                    pos1 = inString.indexOf(replaceStr[i], pos1 + 1);
                } while (pos1 != -1);
                inString = new String(sb);
            }
        }
        return inString;
    }


    public static boolean checkPasswordLength(String password) {
        return (password.length() >= MIN_PASSWORD)
                && (password.length() <= MAX_PASSWORD);
    }


    private static boolean isValidCharacterSet(String pCheckField) {
	        /*
	         ** Loop through each char checking if it is valid
	         */
        char[] characterSet = pCheckField.toCharArray();
        boolean validChar = false;
        for (int i = 0; i < characterSet.length; i++) {
            int value = (int) characterSet[i];
            validChar = false;
            if (Character.isLetterOrDigit(characterSet[i]))
                validChar = true;
	            /*
	             ** Check for dash
	             */
            if (value == 45) {
                validChar = true;
            }
	            /*
	             ** Check for dot
	             */
            if (value == 46) {
                validChar = true;
            }
            if (validChar == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method replaces all occurrence of a substring with the given
     * substring in a string
     *
     * @param strString     String The parent string
     * @param strSrchString String The substring which needs to be replaced
     * @param strRplString  String The new substring which will replace all
     *                      occurences of strsrchString
     * @return returns String Parent string with all occurences of strsrchString
     * replaced by strRplString
     */
    public static String replaceString(String strString,
                                       String strSrchString,
                                       String strRplString) {
        String strOutString = null;
        int intIndex = 0;
        int intPrevIndex = 0;
        int intSrcStrLength = strSrchString.length();

        if (strString == null) {
            return " ";
        }

        do {
            intIndex = strString.indexOf(strSrchString, intPrevIndex);

            if (intIndex == -1) {
                strOutString += strString.substring(intPrevIndex);
                break;
            }

            strOutString += strString.
                    substring(intPrevIndex, intIndex) + strRplString;
            intPrevIndex = intIndex + intSrcStrLength;
        } while (true);

        return strOutString;
    }

    /**
     * This method checks for the escape characters
     *
     * @param strName      String that contains escape character comes
     * @param strCharacter String escape character
     * @return returns String
     */
    public static String checkCharacter(String strName, String strCharacter) {
        String strCh = null;
        String strNewname = null;
        int intSize = strName.length();

        for (int intI = 0; intI < intSize; intI++) {
            strCh = String.valueOf(strName.charAt(intI));
            if (strCh.equals(strCharacter)) {
                strNewname = (strNewname + "\\" + strCh);
            } else {
                strNewname = strNewname + strCh;
            }
        } //for

        return strNewname;
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
     * This  method determines if the specified char is a Invalid ,
     * special character or not
     *
     * @param chrVar char
     * @return boolean
     */
    private static boolean CheckInvalidSpChar(char chrVar) {
        if (
                (chrVar == '=') || (chrVar == '<') ||
                        (chrVar == '>') || (chrVar == '?') ||
                        (chrVar == '/') || (chrVar == '\\') ||
                        (chrVar == '"') || (chrVar == '|') ||
                        (chrVar == '*') || (chrVar == ':')) {
            return false;
        }
        return true;
    }

    /**
     * This  method checks whether they are Repeated
     * ALPHA Password should be a series of non sequential number ,If the number is in sequence
     * 12345 ,56789 , The password is invalid.
     *
     * @param String This variable hold the ALPHA Password
     * @return boolean
     */
    public static boolean checkRepetition(String strPasswordId) {
        char chrNext = ' ';
        char chrFirst = strPasswordId.charAt(0);
        char chrCurrent = strPasswordId.charAt(1);
        if (Integer.parseInt(chrFirst + "") > Integer.parseInt(chrCurrent + "")) {
            strPasswordId = StringUtils.reverseString(strPasswordId);
        } else {
            strPasswordId = strPasswordId;
        }
        chrFirst = strPasswordId.charAt(0);
        chrCurrent = strPasswordId.charAt(1);
        int intLength = strPasswordId.length();
        for (int intPassword = 0; intPassword < intLength; intPassword++) {
            if (intPassword == intLength - 1) {
                chrNext = strPasswordId.charAt(intPassword);
                if ((Integer.parseInt(chrNext + "") != Integer.parseInt(chrFirst + ""))) {
                    return true;
                }
            } else {

                chrNext = strPasswordId.charAt(intPassword + 1);
                if (Integer.parseInt(chrNext + "") != Integer.parseInt(chrFirst + "") + 1) {
                    return true;
                }
            }
            chrFirst = chrNext;
        }
        return false;
    }

    /**
     * Get the contents of the given file as byte array
     *
     * @return byte[] file contents as byte array
     * @throws FileNotFoundException thrown if the specified file
     *                               does not exists
     * @throws IOException           thrown in case there occurs any i/o problems
     */
    public static byte[] getFileBytes(String filePath)
            throws FileNotFoundException, IOException {

        String path = filePath;

        try {
            URL url = new URL(path);
            path = url.getFile();

        } catch (MalformedURLException murle) {
            // ignore. It just means the specified path is not in the URL form
        }

        FileInputStream fis = new FileInputStream(path);

        byte[] fileContents = new byte[fis.available()];

        fis.read(fileContents);

        return fileContents;
    }

    /**
     * Get the passed in object's class name.
     * This method removes package name is any from the
     * object's class name and returns the same.
     *
     * @param obj object
     * @return String object's class name
     * @see java.lang.Object#getClass()
     * @see java.lang.Class#getName()
     */
    public static final String getClassName(Object obj) {
        String className = obj.getClass().getName();

        int index = className.lastIndexOf(".");
        if (index != -1) {
            className = className.substring(index + 1);
        }

        return className;
    }

    public static String getCurrentDateInFooterFormat() {
        SimpleDateFormat curDateFormater = new SimpleDateFormat("EEEEEEEEE, MMMMMMMMM dd, yyyy 'at' hh:mm:ss a");
        String curDate = curDateFormater.format(new Date());
        return curDate;
    }

    /**
     * Check if a list only have one item
     */
    static public boolean isOne(List s) {
        if (s != null && s.size() == 1)
            return true;
        else
            return false;
    }

    /**
     * Check if a list is empty or null
     */
    static public boolean isListEmpty(Collection s) {
        if (s == null || s.size() == 0)
            return true;
        else
            return false;
    }

    /**
     * Check if a map is empty or null
     */
    static public boolean isMapEmpty(Map s) {
        if (s == null || s.size() == 0)
            return true;
        else
            return false;
    }

    /**
     * Split an string by separator
     */
    static public ArrayList split(String s, String sep) {

        if (StringUtils.isEmptyString(s))
            return new ArrayList();

        if (StringUtils.isEmptyString(sep)) {
            ArrayList list1 = new ArrayList();
            list1.add(s);
            return list1;
        }

        ArrayList list = new ArrayList();
        int len = sep.length();
        int i = 0;
        int n = 0;
        while (true) {
            int j = s.indexOf(sep, i);
            if (j < 0) {
                String t = s.substring(i);
                if (t != null)
                    t = t.trim();
                if (t != null && t.length() > 0)
                    list.add(t);
                break;
            } else {
                String t = s.substring(i, j);
                if (t != null)
                    t = t.trim();
                if (t != null && t.length() > 0)
                    list.add(t);
                i = j + len;
                n++;
            }
        }

        return list;
    }

    /**
     * Split an string by separator
     */
    static public ArrayList split4Tab(String s, String sep) {

        if (StringUtils.isEmptyString(s))
            return new ArrayList();

        if (StringUtils.isEmpty4Tab(sep)) {
            ArrayList list1 = new ArrayList();
            list1.add(s);
            return list1;
        }

        ArrayList list = new ArrayList();
        int len = sep.length();
        int i = 0;
        int n = 0;
        while (true) {
            int j = s.indexOf(sep, i);
            if (j < 0) {
                String t = s.substring(i);
                if (t == null)
                    t = " ";
                list.add(t);
	                   /*if (t != null)
	                       t = t.trim();
	                   if (t != null && t.length() > 0)
	                       list.add(t);*/
                break;
            } else {
                String t = s.substring(i, j);
	                   /*if (t != null)
	                       t = t.trim();
	                   if (t != null && t.length() > 0)*/
                if (t == null)
                    t = " ";
                list.add(t);
                i = j + len;
                n++;
            }
        }

        return list;
    }

    /**
     * Split an string by separator
     */
    static public ArrayList splitWithEmptyInList(String s, String sep) {
        if (StringUtils.isEmptyString(s))
            return new ArrayList();

        if (StringUtils.isEmptyString(sep)) {
            ArrayList list1 = new ArrayList();
            list1.add(s);
            return list1;
        }

        ArrayList list = new ArrayList();
        int len = sep.length();
        int i = 0;
        int n = 0;
        while (true) {
            int j = s.indexOf(sep, i);
            if (j < 0) {
                String t = s.substring(i);
                if (t != null)
                    t = t.trim();
                if (t != null && t.length() > 0)
                    list.add(t);
                break;
            } else {
                String t = s.substring(i, j);
                if (t != null)
                    t = t.trim();

                list.add(t);
	                /*
	                if (t != null && t.length() > 0)
	                    list.add(t);
	                */
                i = j + len;
                n++;
            }
        }

        return list;
    }

    /**
     * This  method determines if the specified text contains only letters
     *
     * @param strText String
     * @return returns boolean
     */

    private boolean alphaCheck(String strText) {
        int intSize = strText.length();
        boolean blnTemp = false;

        for (int intCount = 0; intCount < intSize; intCount++) {
            blnTemp = Character.isLetter(strText.charAt(intCount));
            if (!blnTemp) {
                return false;
            }
        }
        return true;
    }

    /**
     * This  method determines if the specified text contains only letters and
     * digits.
     *
     * @param strText String
     * @return returns boolean
     */
    private boolean alphaNumericCheck(String strText) {
        int intSize = strText.length();
        boolean blnTemp = false;

        for (int intCount = 0; intCount < intSize; intCount++) {
            blnTemp = Character.isLetterOrDigit(strText.charAt(intCount));
            if (!blnTemp) {
                return false;
            }
        }
        return true;
    }

    /**
     * This  method checks whether the numerics are in same in the series
     * The VP Password should not be 1111 or 3333
     * It should be a series of non sequential number
     *
     * @param String This variable hold the VP Password
     * @return boolean
     */
    private boolean checkOrder(String strPasswordId) {
        char chrFirst = strPasswordId.charAt(0);
        char chrNext = ' ';
        char chrCurrent = ' ';
        int intPasswordSize = strPasswordId.length();
        for (int intPassword = 0; intPassword < intPasswordSize; intPassword++) {
            chrCurrent = strPasswordId.charAt(intPassword);
            if (chrFirst == chrCurrent) {
                continue;
            } else {
                return true;
            }
        }
        return false;
    }


}
