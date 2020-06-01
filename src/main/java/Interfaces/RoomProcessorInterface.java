package Interfaces;

import Model.Room;

import java.util.ArrayList;
import java.util.HashMap;

public interface RoomProcessorInterface {

    void addRoom(int number, String type, int price, String description);

    Room selectOne(int id);

    void editRoom(int id, int number, boolean status, String type, int price, String description);

    void delete(int id);

    int findId(int number);

    void toBook(int id);

    void toFree(int id);

    HashMap<String, ArrayList<Room>> roomsMap(ArrayList<Room> rooms);
}
