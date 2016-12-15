package mbd.org.roadtrip.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.HashMap;
@IgnoreExtraProperties
public class Chats implements Parcelable{

    public String id;
    public long end;
    public String lastMessage;
    public long timestamp;
    public String title;
    public String destination;
    public long start;
    public int members;
    public HashMap<String, String> people;

    public Chats(){

    }

    protected Chats(Parcel in) {
        id = in.readString();
        end = in.readLong();
        lastMessage = in.readString();
        timestamp = in.readLong();
        title = in.readString();
        destination = in.readString();
        start = in.readLong();
        members = in.readInt();
        people = (HashMap<String, String>) in.readSerializable();

    }

    public static final Creator<Chats> CREATOR = new Creator<Chats>() {
        @Override
        public Chats createFromParcel(Parcel in) {
            return new Chats(in);
        }

        @Override
        public Chats[] newArray(int size) {
            return new Chats[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeLong(end);
        dest.writeString(lastMessage);
        dest.writeLong(timestamp);
        dest.writeString(title);
        dest.writeString(destination);
        dest.writeLong(start);
        dest.writeInt(members);
        dest.writeSerializable(people);

    }
}

