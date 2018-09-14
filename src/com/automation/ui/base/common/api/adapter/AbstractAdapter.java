package com.automation.ui.base.common.api.adapter;

import com.automation.ui.base.common.api.util.ContentType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class AbstractAdapter {

    private Object object;
    private Map parammap;
    private Map header;
    private String endPoint;
    private String method;
    private ContentType contentType;

    protected AbstractAdapter(AbstractBuilder<?, ?> builder) {
        this.object = builder.object;
        this.endPoint = builder.endPoint;
        this.method = builder.method;
        this.contentType = builder.contentType;
        this.parammap = builder.parammap;
        this.header = builder.header;
    }

    public static AbstractBuilder<?, ?> builder() {
        return new DefaultAbstractBuilder();
    }

    public Object getObject() {
        return object;
    }
    public Map getParams() {
        return parammap;
    }
    public Map getHeaders() {
        return header;
    }



    public String getEndPoint() {
        return endPoint;
    }

    public String getMethod() {
        return method;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public String prettyPrint(String body) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(body);
        return gson.toJson(je);
    }

    public String getResponseStringFromConn(HttpURLConnection conn, String payLoad) throws IOException {

        // Send the http message payload to the server.
        if (payLoad != null) {
            conn.setDoOutput(true);
            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
            osw.write(payLoad);
            osw.flush();
            osw.close();
        }

        // Get the message response from the server.
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line = "";
        StringBuffer stringBuffer = new StringBuffer();
        while ((line = br.readLine()) != null) {
            stringBuffer.append(line);
        }

        br.close();

        return stringBuffer.toString();
    }

    public byte[] getByteaArrayFromConn(HttpURLConnection conn, boolean isSuccess) throws IOException {

        InputStream is = conn.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        int bytesRead = 0;

        while ((bytesRead = is.read(buff, 0, buff.length)) != -1) {
            baos.write(buff, 0, bytesRead);
        }

        byte[] bytes = baos.toByteArray();
        baos.close();
        return bytes;
    }

    public String makeRawRequest(HttpURLConnection connection, boolean isSuccess) throws IOException {

        BufferedReader in = null;
        if (isSuccess) {
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } else {
            in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }

        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        getResponseHeader(connection);
        return response.toString();
    }

    /**
     * for bad response, whose responseCode is not 200 level
     *
     * @param responseCode Get the data for the responsecode
     * @param errorCode
     * @param errorMsg
     * @return JSONObject
     * @throws JSONException
     */
    public JSONObject processResponse(int responseCode, String errorCode, String errorMsg) throws JSONException {
        JSONObject response = new JSONObject();
        response.put("responseCode", responseCode);
        response.put("errorCode", errorCode);
        response.put("errorMsg", errorMsg);

        return response;
    }

    /**
     * for bad response, whose responseCode is not 200 level
     *
     * @param responseCode Get the data for the responsecode
     * @param goodRespStr

     * @return JSONObject
     * @throws JSONException
     */
    public JSONObject processGoodRespStr(int responseCode, String goodRespStr) throws JSONException {
        JSONObject response = new JSONObject();
        response.put("responseCode", responseCode);
        if (goodRespStr.equalsIgnoreCase("")) {
            response.put("responseMsg", "");
        } else {
            response.put("responseMsg", new JSONObject(goodRespStr));
        }

        return response;
    }

    /**
     * for good response
     *
     * @param responseCode Get the data for the responsecode
     * @param responseMsg
     * @return JSONObject
     * @throws JSONException
     */
    public JSONObject processBadRespStr(int responseCode, String responseMsg) throws JSONException {

        JSONObject response = new JSONObject();
        response.put("responseCode", responseCode);
        if (responseMsg.equalsIgnoreCase("")) { // good response is empty string
            response.put("responseMsg", "");
        } else { // bad response is json string
            JSONObject errorObject = new JSONObject(responseMsg).optJSONObject("odata.error");

            String errorCode = errorObject.optString("code");
            String errorMsg = errorObject.optJSONObject("message").optString("value");
            response.put("responseCode", responseCode);
            response.put("errorCode", errorCode);
            response.put("errorMsg", errorMsg);
        }

        return response;
    }


    public void getResponseHeader(HttpURLConnection con) throws IOException {
        StringBuilder builder = new StringBuilder();
        builder.append(con.getResponseCode())
                .append(" ")
                .append(con.getResponseMessage())
                .append("\n");

        Map<String, List<String>> map = con.getHeaderFields();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            if (entry.getKey() == null)
                continue;
            builder.append(entry.getKey())
                    .append(": ");

            List<String> headerValues = entry.getValue();
            Iterator<String> it = headerValues.iterator();
            if (it.hasNext()) {
                builder.append(it.next());

                while (it.hasNext()) {
                    builder.append(", ")
                            .append(it.next());
                }
            }
            builder.append("\n");
        }
        System.out.println(builder);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("object", object)
                .append("endPoint", endPoint)
                .append("method", method)
                .append("contentType", contentType)
                .toString();
    }

    public static abstract class AbstractBuilder<S extends AbstractAdapter, B extends AbstractBuilder<S, B>> {
        private Object object;
        private String endPoint;
        private String method;
        private ContentType contentType;
        private Map parammap;
        private Map header;

        @SuppressWarnings("unchecked")
        public B setRequestObject(Object object) {
            this.object = object;
            return (B) this;
        }

        @SuppressWarnings("unchecked")
        public B setParams(Map parammap) {
            this.parammap = parammap;
            return (B) this;
        }
        @SuppressWarnings("unchecked")
        public B setHeaders(Map header) {
            this.header = header;
            return (B) this;
        }

        @SuppressWarnings("unchecked")
        public B setEndPoint(String endPoint) {
            this.endPoint = endPoint;
            return (B) this;
        }

        @SuppressWarnings("unchecked")
        public B setMethodName(String method) {
            this.method = method;
            return (B) this;
        }

        @SuppressWarnings("unchecked")
        public B setContentType(ContentType contentType) {
            this.contentType = contentType;
            return (B) this;
        }

        public abstract S build();
    }

    private static class DefaultAbstractBuilder extends AbstractBuilder<AbstractAdapter, DefaultAbstractBuilder> {

        @Override
        public AbstractAdapter build() {
            return new AbstractAdapter(this);
        }
    }
}
