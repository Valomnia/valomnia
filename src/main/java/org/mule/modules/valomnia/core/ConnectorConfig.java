package org.mule.modules.valomnia.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.mule.api.ConnectionException;
import org.mule.api.ConnectionExceptionCode;
import org.mule.api.annotations.Configurable;
import org.mule.api.annotations.Connect;
import org.mule.api.annotations.ConnectionIdentifier;
import org.mule.api.annotations.Disconnect;
import org.mule.api.annotations.TestConnectivity;
import org.mule.api.annotations.ValidateConnection;
import org.mule.api.annotations.components.Configuration;
import org.mule.api.annotations.components.ConnectionManagement;
import org.mule.api.annotations.display.Password;
import org.mule.api.annotations.param.ConnectionKey;
import org.mule.modules.valomnia.client.ValomniaClientProvider;
import org.mule.modules.valomnia.client.impl.ValomniaClientProviderImpl;

import com.google.gson.Gson;

@Configuration(configElementName = "config", friendlyName = " Configuration")
public class ConnectorConfig {
    
    
    
    public String getUser() {
        return user;
    }

    
    public void setUser(String user) {
        this.user = user;
    }

    
    public String getPassword() {
        return password;
    }

    
    public void setPassword(String password) {
        this.password = password;
    }

    
    public String getBaseUrl() {
        return baseUrl;
    }

    
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    private String token;
    @Configurable
    private String user;
    @Configurable
    private String password;
    @Configurable
    private String baseUrl;
    private ValomniaClientProvider clientProvider;

    public ValomniaClientProvider getClientProvider() {
        return clientProvider;
    }

    public void setClientProvider(ValomniaClientProvider clientProvider) {
        this.clientProvider = clientProvider;
    }

    /**
     * Connect
    
     * @throws ConnectionException
     *             if the username or password incorrect
     */
    
    @TestConnectivity
    public void connect() throws ConnectionException {

        token=authentication(user, password);
        if (token!=null)
        {
            
            
            setClientProvider(new ValomniaClientProviderImpl(baseUrl, password,
                    token));
        }
        else 
            throw new ConnectionException(
                    ConnectionExceptionCode.INCORRECT_CREDENTIALS,
                    "Invalid credentials"    , "Invalid credentials");
        
    }

   
    

    public String getAuthToken() {

        return authentication(this.user, this.password);

    }

    public static String encodage(final List<CustomNameValuePair> parameters) {
        return URLEncodedUtils.format(parameters, "ISO-8859-1");
    }

    public String authentication(final String login, final String password) {

        String test = null;
        try {
            final DefaultHttpClient client = new DefaultHttpClient();

            final List<CustomNameValuePair> parameters = new ArrayList<CustomNameValuePair>();
            parameters.add(new CustomNameValuePair("j_username", login));
            parameters.add(new CustomNameValuePair("j_password", password));
            parameters.add(new CustomNameValuePair("ajax", "true"));

            final String url = encodage(parameters);

            final HttpPost post = new HttpPost(
                    this.baseUrl + "j_spring_security_check?"
                            + url);

            post.setHeader("Content-type", "application/json");
            try {
                final HttpResponse response = client.execute(post);
                HttpResponse response2 = null;

                if (response.getStatusLine()
                        .getStatusCode() == 302) {

                    final String loc = response.getFirstHeader("Location")
                            .getValue();

                    final String cookie = response.getFirstHeader("Set-Cookie")
                            .getValue();
                    final String token = cookie.substring(11,
                            cookie.indexOf(";"));

                    if (loc != null) {

                        final DefaultHttpClient client2 = new DefaultHttpClient();

                        final HttpPost post2 = new HttpPost(loc);
                        post2.setHeader("Content-type",
                                "application/json;charset=UTF-8");
                        post2.setHeader("Cookie", "JSESSIONID=" + token);
                        response2 = client2.execute(post2);

                    }

                }

                if (response2.getStatusLine()
                        .getStatusCode() != 200) {
                    System.out.println("Failed : HTTP error code : "
                            + response2.getStatusLine()
                                    .getStatusCode());
                } else {

                    final BufferedReader reader = new BufferedReader(
                            new InputStreamReader(response2.getEntity()
                                    .getContent()));

                    final String line = reader.readLine();
                    if (line != null) {

                        @SuppressWarnings("unchecked")
                        final Map<String, Object> javaRootMapObject = new Gson()
                                .fromJson(line, Map.class);
                        if (javaRootMapObject != null
                                && javaRootMapObject.get("token") != null) {

                            test = javaRootMapObject.get("token")
                                    .toString();

                        }
                    }

                }
            } catch (HttpHostConnectException e) {
                test = "HttpHostConnectException";
                System.out.println(e.toString());
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return test;

    }

}