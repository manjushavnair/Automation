package com.automation.ui.base.common.api.clientimpl.httpbaseimpl;


import com.automation.ui.base.common.api.util.MethodType;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import com.automation.ui.base.common.api.adapter.*;

import static io.restassured.RestAssured.given;

public class DeleteAdapter extends AbstractAdapter implements RestAdapter {
    private String name;

    protected DeleteAdapter(DeleteBuilder<?, ?> builder) {
        super(builder);
        this.name = builder.name;

    }
    @Override
    public JsonPath execute() {

        return null;
    }

    public static DeleteBuilder<?, ?> builder() {
        return new DefaultDeleteBuilder();
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
            request = deleteRequest(endpoint);
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

    public HttpURLConnection deleteRequest(String command) throws IOException {
        URL obj = new URL(command);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod(MethodType.DELETE.getMethodType());
        con.setRequestProperty("Content-Type", ContentType.JSON.toString() );
        return con;
    }

    public static abstract class DeleteBuilder<S extends DeleteAdapter, B extends DeleteBuilder<S, B>> extends AbstractBuilder<S, B> {
        private String name;

        @SuppressWarnings("unchecked")
        public B name(String name) {
            this.name = name;
            return (B) this;
        }

    }

    private static class DefaultDeleteBuilder extends DeleteBuilder<DeleteAdapter, DefaultDeleteBuilder> {
        @Override
        public DeleteAdapter build() {
            return new DeleteAdapter(this);
        }
    }
}