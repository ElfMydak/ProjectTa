package Service;

import Interfaces.ModelLayerRoom;
import Interfaces.RoomProcessorInterface;
import Model.Room;
import Repository.RoomModel;

import java.util.ArrayList;
import java.util.HashMap;

public class RoomProcessor implements RoomProcessorInterface {

    ModelLayerRoom modelRoom = new RoomModel();

    @Override
    public void addRoom(int number, String type, int price, String description){
        Room room = new Room(number, false, type, price, description);
        modelRoom.insert(room);
    }

    @Override
    public Room selectOne(int id){
        return modelRoom.selectOne(id);
    }

    @Override
    public void editRoom(int id, int number, boolean status, String type, int price, String description){
        Room room = new Room(id, number, status, type, price, description);
        modelRoom.update(room);
    }

    @Override
    public void delete(int id){
        modelRoom.delete(id);
    }

    @Override
    public int findId(int roomNumber){
        Room room = modelRoom.selectAllNumbers().find(roomNumber);
        return room.getRoomId();
    }

    @Override
    public void toBook(int id){
        modelRoom.toBook(id);
    }

    @Override
    public void toFree(int id){
        modelRoom.toFree(id);
    }

    @Override
    public HashMap<String, ArrayList<Room>> roomsMap(ArrayList<Room> rooms){
        HashMap<String, ArrayList<Room>> roomMap = new HashMap<>();
        for(Room room : rooms){
            if(!roomMap.containsKey(room.getRoomType())) {
                roomMap.put(room.getRoomType(), getArray(rooms, room.getRoomType()));
            }
        }
        return roomMap;
    }

    public ArrayList<Room> getArray(ArrayList<Room> list, String key){
        ArrayList<Room> roomList = new ArrayList<>();
        for(Room room : list){
            if (room.getRoomType().equals(key) && !room.getRoomStatus()){
                roomList.add(room);
            }
        }
        return  roomList;
    }
}
