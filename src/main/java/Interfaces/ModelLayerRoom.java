package Interfaces;

import BTree.BPlusTree;
import Model.Room;

import java.util.ArrayList;

public interface ModelLayerRoom {

    BPlusTree<Room, Integer> selectAllNumbers();

    ArrayList<Room> selectAll();

    Room selectOne(int id);

    void toBook(int id);

    void toFree(int id);

    int insert(Room room);

    int update(Room room);

    int delete(int id);
}
