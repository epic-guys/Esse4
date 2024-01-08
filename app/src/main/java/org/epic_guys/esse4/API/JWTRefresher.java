package org.epic_guys.esse4.API;

import android.content.Context;
import android.util.Log;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import de.adorsys.android.securestoragelibrary.SecurePreferences;

public class JWTRefresher {

    private Context context;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public JWTRefresher(Context context){
        this.context = context;
    }

        public void start() {
            final Runnable refresher = () -> {
                Boolean res = renewJWT();
                Log.i("JWTRefresher", "JWTRefresher renewed: " + res.toString());
                /*
                TODO: go back to login
                if(!res){
                    Log.i("JWTRefresher", "JWTRefresher stopped");
                    scheduler.shutdown();
                }*/
            };
            scheduler.scheduleAtFixedRate(refresher, 10, 10, TimeUnit.MINUTES);
            Log.i("JWTRefresher", "JWTRefresher started");
        }

    private boolean renewJWT(){

        CompletableFuture<Boolean> res = new CompletableFuture<>();

        try{
            API.refreshJwt().thenAccept(jwt -> {
                        res.complete(true);
                    })
                    .exceptionally(
                            throwable -> {
                                res.complete(login());

                                return null;
                            }
                    ).isCompletedExceptionally();
        } catch (RuntimeException e) {
            return false;
        }

        return res.join();
    }

    private boolean login(){
        CompletableFuture<Boolean> res = new CompletableFuture<>();

        String matricola = SecurePreferences.getStringValue("matricola", context,  "");
        String password = SecurePreferences.getStringValue( "password", context, "");

        try{
            API.login(matricola,password).thenAccept(jwt -> {
                        res.complete(true);
                    })
                    .exceptionally(
                            throwable -> {
                                res.complete(false);
                                return null;
                            }
                    ).isCompletedExceptionally();
        } catch (RuntimeException e) {
            return false;
        }

        return res.join();
    }
}
