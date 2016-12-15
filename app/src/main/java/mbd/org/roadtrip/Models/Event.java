package mbd.org.roadtrip.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by derekyu on 12/12/16.
 */

public class Event implements Parcelable{

    public Event(){

    }
    public String name;
    public String location;
    public String description;
    public float duration;
    public Long date;

    public Event(Parcel in){
        name = in.readString();
        location = in.readString();
        description = in.readString();
        duration = in.readFloat();
        date = in.readLong();
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(location);
        dest.writeString(description);
        dest.writeFloat(duration);
        dest.writeLong(date);
    }
}
