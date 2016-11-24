package mbd.org.roadtrip.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.firebase.database.ServerValue;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by derekyu on 11/20/16.
 */

public class Message {
    public String name;
    public String message;
    public String email;
    public Long timestamp;

    public Message(){

    }

    public Message(String name, String message, String email){
        this.name  = name;
        this.message = message;
        this.email = email;

    }

    public Map<String, String> getTimestamp() {
        return ServerValue.TIMESTAMP;
    }
}
