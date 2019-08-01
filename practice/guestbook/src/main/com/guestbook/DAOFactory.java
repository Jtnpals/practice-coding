package com.guestbook;

public class DAOFactory {
    //Test를 위해  static
    public static GuestBookDAO getGuestBookDAO(){
        return new GuestBookDAO(getConnectionMaker());
    }

    public static ConnectionMaker getConnectionMaker() {
        return new OracleConnectionMaker();
    }
}
