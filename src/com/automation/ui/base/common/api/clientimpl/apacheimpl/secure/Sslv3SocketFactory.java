package com.automation.ui.base.common.api.clientimpl.apacheimpl.secure;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.params.HttpParams;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.net.Socket;

public class Sslv3SocketFactory extends SSLSocketFactory {

    private final Log log = LogFactory.getLog(getClass());

    /**
     * Instantaites a new {@link Sslv3SocketFactory}.
     *
     * @param sslContext:       The SSLContext.
     * @param hostnameVerifier: The X509HostnameVerifier.
     */
    public Sslv3SocketFactory(SSLContext sslContext, X509HostnameVerifier hostnameVerifier) {
        super(sslContext, hostnameVerifier);
    }

    @Override
    public Socket createSocket(HttpParams params) throws IOException {

        if (true) {
            log.debug("createSocket: " + params);
        }

        SSLSocket socket = (SSLSocket) super.createSocket(params);
        socket.setEnabledProtocols(new String[]{"SSLv3", "TLSv1.2"});
        //socket.setEnabledProtocols(new String[]{"TLSv1.2"});


        return socket;
    }
}