package com.dauntless.project;

import android.os.Parcel;
import android.os.Parcelable;

public class FamilyAdmin implements Parcelable{
	String name;
	String surname;
	String position;
	
	public FamilyAdmin()
	{
		this.name = "default";
		this.surname = "default";
		this.position = "default";
	}
	
	public FamilyAdmin(String name, String surname, String position){
        this.name = name;
        this.surname = surname;
        this.position = position;
   }
	
	public void setName(String newName)
	{
		name = newName;
	}
	
	public void setSurname(String newSurname)
	{
		surname = newSurname;
	}
	
	public void setPosition(String newPosition)
	{
		position = newPosition;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getSurname()
	{
		return surname;
	}
	
	public String getPosition()
	{
		return position;
	}
	
	public FamilyAdmin(Parcel in){
        String[] data = new String[3];

        in.readStringArray(data);
        this.name = data[0];
        this.surname = data[1];
        this.position = data[2];
    }

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {this.name,
                                            this.surname,
                                            this.position});
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public FamilyAdmin createFromParcel(Parcel in) {
            return new FamilyAdmin(in); 
        }

        public FamilyAdmin[] newArray(int size) {
            return new FamilyAdmin[size];
        }
    };
}
