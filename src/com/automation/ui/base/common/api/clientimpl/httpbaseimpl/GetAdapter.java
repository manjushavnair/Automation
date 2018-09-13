package com.automation.ui.base.common.api.clientimpl.httpbaseimpl;

import com.automation.ui.base.common.api.util.ContentType;
import com.automation.ui.base.common.api.util.MethodType;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import com.automation.ui.base.common.api.adapter.*;
import io.restassured.path.json.JsonPath;

public class GetAdapter extends  AbstractAdapter implements RestAdapter {
    private String name;

    //   HttpGet request = new HttpGet("http://lifecharger.org/3-tips-for-a-better-life/");

    protected GetAdapter(GetBuilder<?, ?> builder) {
        super(builder);
        this.name = builder.name;

    }
    @Override
    public JsonPath execute() {

        return null;
    }
    public static GetBuilder<?, ?> builder() {
        return new DefaultGetBuilder();
    }

    public String getName() {
        return name;
    }

    @Override
    public <T> T execute(Class<T> responseClass) {
        Gson jsonParser = new Gson();
        final String endpoint = getEndPoint() + getMethod();
        String body = jsonParser.toJson(getObject());
        HttpURLConnection request = null;
        try {
            request = getRequest(endpoint);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String response = null;
        try {
            response = makeRawRequest(request, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Request \n" + prettyPrint(body) + "\n");
        System.out.println("Response \n" + prettyPrint(response) + "\n");
        return jsonParser.fromJson(response, responseClass);
    }

    private HttpURLConnection getRequest(String command) throws IOException {
        URL obj = new URL(command);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod(MethodType.GET.getMethodType());
        con.setRequestProperty("Content-Type", ContentType.JSON.getContentType());
        return con;
    }

    public static abstract class GetBuilder<S extends GetAdapter, B extends GetBuilder<S, B>> extends AbstractBuilder<S, B> {
        private String name;

        @SuppressWarnings("unchecked")
        public B name(String name) {
            this.name = name;
            return (B) this;
        }

    }

    private static class DefaultGetBuilder extends GetBuilder<GetAdapter, DefaultGetBuilder> {
        @Override
        public GetAdapter build() {
            return new GetAdapter(this);
        }
    }
}
