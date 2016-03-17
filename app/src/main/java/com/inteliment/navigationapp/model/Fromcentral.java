package com.inteliment.navigationapp.model;


import android.os.Parcel;
import android.os.Parcelable;

public class Fromcentral implements Parcelable {
    String car;
    String train;


    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getTrain() {
        return train;
    }

    public void setTrain(String train) {
        this.train = train;
    }

    protected Fromcentral(Parcel in) {
        car = in.readString();
        train = in.readString();
    }

    public static final Creator<Fromcentral> CREATOR = new Creator<Fromcentral>() {
        @Override
        public Fromcentral createFromParcel(Parcel in) {
            return new Fromcentral(in);
        }

        @Override
        public Fromcentral[] newArray(int size) {
            return new Fromcentral[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(car);
        dest.writeString(train);
    }
}