package com.apicula.brokenbricks;

import com.apicula.brokenbricks.AdditionalFiles.Const;
import com.apicula.brokenbricks.Models.UserDefaults;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpParametersUtils;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Server {
    private static Const CONST;
    private static String RoomID;
    private static String EnemyNickname;
    private static String queue;
    private static String[] splited;
    private static UserDefaults user;

    public static boolean isGame = false;
    public static int bulletPosX = 0;
    public static int bulletPosY = 0;
    public static int mainPosX = 0;

    private static boolean isStartRequestSending = false;

    public static void startRequest() {
        if (!isStartRequestSending) {
            isStartRequestSending = true;
            Net.HttpRequest req = new Net.HttpRequest(Net.HttpMethods.GET);
            req.setUrl(CONST.URLSTART);
            req.setContent("name=" + user.nickname);

            Gdx.net.sendHttpRequest(req, new Net.HttpResponseListener() {
                public void handleHttpResponse(Net.HttpResponse httpResponse) {
                    String status = httpResponse.getResultAsString();
                    //do stuff here based on response
                    System.out.println(status);
                    splited = status.split(" ");

                    try {
                        RoomID = splited[0];
                        EnemyNickname = splited[1];
                        queue = splited[2];
                        isGame = true;
                    } catch (Throwable t) {
                        System.out.println(t);
                    }
                }

                public void failed(Throwable t) {
                    String status = "failed";
                    //do stuff here based on the failed attempt
                }

                @Override
                public void cancelled() {

                }
            });
        }
    }

    public static void gameRequest(int BulletPosX, int BulletPosY, int MainPosX) {
        Net.HttpRequest req = new Net.HttpRequest(Net.HttpMethods.GET);
        req.setUrl(CONST.URLGAME);
        req.setContent("room_id=" + RoomID + "&MainPosX=" + MainPosX + "&BulletPosX=" + BulletPosX + "&BulletPosY=" + BulletPosY + "&Q=" + queue);

        Gdx.net.sendHttpRequest(req, new Net.HttpResponseListener() {
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                String status = httpResponse.getResultAsString();
                splited = status.split(" ");

                bulletPosX = Integer.parseInt(splited[0]);
                bulletPosY = Integer.parseInt(splited[1]);
                mainPosX = Integer.parseInt(splited[2]);

                System.out.println(bulletPosX);
                System.out.println((bulletPosY));
            }

            public void failed(Throwable t) {
                String status = "failed";
                //do stuff here based on the failed attempt
            }

            @Override
            public void cancelled() {

            }
        });
    }
}
