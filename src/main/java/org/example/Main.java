package org.example;
import oauth.signpost.OAuth;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.basic.DefaultOAuthProvider;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.http.HttpParameters;
import oauth.signpost.http.HttpRequest;
import oauth.signpost.signature.OAuthMessageSigner;
import oauth.signpost.signature.SigningStrategy;
import java.util.*;
import java.util.function.Consumer;

public class Main{
    public static void main(String[] args) throws Exception{
        Scanner scan = new Scanner(System.in);
        System.out.println("Hello world!");


        //test program for the Oauth Process

        // consumer key
        String apiKey = "cf24a0145d52fd92f2b8681aad69f420";
        //secret key
        String apiSecret = "d3f1b99041cd432982625ee44aeb2b8ae097f8364b28a7b85ce5b06152cf118a";

        //setting etrade api endpoints


        String requestTokenEndpoint = "https://api.etrade.com/oauth/request_token";
        String accessTokenEndpoint = "https://api.etrade.com/oauth/access_token";
        String authorizeEndpoint = "https://us.etrade.com/e/t/etws/authorize";

        //consumer - this application
        //user - user who is using application
        //service provider - etrade
        //consumer key - value used by consumer to identify itself to the service provider
        //consumer secret - hash used by consumer to certify that they own consumer key
        //request token - a value used by the consumer to obtain authorization from the user - exchanged for an access token
        //access token - a value used by the consumer to gain access to user's protected data
        //token secret - secret used by the consumer to certify ownership of a token

        //create a OauthConsumer object with constructors of both key and secret declared earlier
        OAuthConsumer consumer = new DefaultOAuthConsumer(apiKey, apiSecret);
        //create simple OauthProviderObject with constructors of all three endpoints
        OAuthProvider provider = new DefaultOAuthProvider(requestTokenEndpoint, accessTokenEndpoint, authorizeEndpoint);
        //getting request code and handling api responses
        String authUrl = provider.retrieveRequestToken(consumer, OAuth.OUT_OF_BAND);


        System.out.println("Website visit " + authUrl);
        System.out.println("Enter verification code");
        System.out.println("Waiting for user authorization...");
        String code = scan.nextLine();

        provider.retrieveAccessToken(consumer, code);


        String apiUrl = "https://api.etrade.com/v1/accounts/list";
        consumer.setTokenWithSecret(consumer.getToken(), consumer.getConsumerSecret());

        String response = consumer.sign(apiUrl);

        System.out.println("Api Response " + response);


    }
}