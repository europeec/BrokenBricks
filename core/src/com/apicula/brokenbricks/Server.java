//package com.apicula.brokenbricks;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Net;
//import com.badlogic.gdx.net.HttpParametersUtils;
//
//import java.net.URL;
//import java.util.HashMap;
//import java.util.Map;
//
//
//public class Server {
//    private static Const CONST;
//
//    public static void main(String[] args) {
//        Map parameters = new HashMap();
//        Net.HttpRequest req = new Net.HttpRequest(Net.HttpMethods.POST);
//        req.setUrl(CONST.URLGAME);
//        req.setContent(HttpParametersUtils.convertHttpParameters(parameters));
//        Gdx.net.sendHttpRequest (req, new Net.HttpResponseListener() {
//            public void handleHttpResponse(Net.HttpResponse httpResponse) {
//                status = httpResponse.getResultAsString();
//                //do stuff here based on response
//            }
//
//            public void failed(Throwable t) {
//                status = "failed";
//                //do stuff here based on the failed attempt
//            }
//        });
//    }
//
//
//}
