package com.example.ibcshlapplication;
import java.util.*;
import java.io.*;

public class School {

    /*
    plan of app:
    be able to dfs on the rooms
    ind is the index (1,2,3,4...)
    convert letter of name to hallway (1-5)
    convert room # to a hash using custom hash function
    storing of rooms:
    array1: has the rooms by index -> necessary because have to locate a room by index
    array2: has the rooms by hash -> necessary because have to locate room properties with name -> could be done in O(n) looping through index array checking for name,
    but def faster for O(1) with the use of extra space

    also, adj list
    between each room in a hallway, draw edge
    have to look at school plan to determine corners and such
    when weight updated, all rooms in the same hallway have their weights updated.
    can be done O(1)? -> no unless complex structure of hallways
    simply loop thru all rooms, see which have the same hallway, update their weights
    O(n) for updating those, O(v + e) for djikstra, O(1) for accessing -> overall good time complexity
     */
    private List<Room> rooms;
    Scanner scan;
    private Room[] roomHash;
    public School() {
        rooms = new ArrayList<Room>();
        roomHash = new Room[999];
        scan = new Scanner("rooms.txt");
        int i = 0;
        while (scan.hasNextLine()) {
            String name = scan.nextLine();
            rooms.add(new Room(i, name));
            char re = name.charAt(0);
            int first = (int) re - 64;
            String ret = Integer.toBinaryString(first);
            ret += Integer.toBinaryString(Integer.parseInt(String.valueOf(name.charAt(1)))-1);
            ret += name.charAt(2);
            ret += Integer.toBinaryString(Integer.parseInt(String.valueOf(name.charAt(3))));
            int ind = Integer.parseInt(ret, 2);
            roomHash[ind] = new Room(i, name);
        }
    }


}
