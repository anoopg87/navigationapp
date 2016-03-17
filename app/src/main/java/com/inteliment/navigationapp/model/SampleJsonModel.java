package com.inteliment.navigationapp.model;


import android.os.Parcel;
import android.os.Parcelable;

public class SampleJsonModel implements Parcelable {
    int id;
    String name;
    Fromcentral fromcentral;
    Location location;

    public SampleJsonModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    protected SampleJsonModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        fromcentral = in.readParcelable(Fromcentral.class.getClassLoader());
        location = in.readParcelable(Location.class.getClassLoader());
    }

    public static final Creator<SampleJsonModel> CREATOR = new Creator<SampleJsonModel>() {
        @Override
        public SampleJsonModel createFromParcel(Parcel in) {
            return new SampleJsonModel(in);
        }

        @Override
        public SampleJsonModel[] newArray(int size) {
            return new SampleJsonModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Fromcentral getFromcentral() {
        return fromcentral;
    }

    public void setFromcentral(Fromcentral fromcentral) {
        this.fromcentral = fromcentral;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location loaction) {
        this.location = loaction;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeParcelable(fromcentral, flags);
        dest.writeParcelable(location, flags);
    }
}


