package com.example.ibcshlapplication;
import java.util.*;
import java.io.*;

public class School {

    /*
        plan of app:
        be able to dfs on the rooms
        ind is the index (1,2,3,4...)
        convert letter of name to hallway (1-5)
        convert room # to a hash using custom hash function -> convert back using ARRAY (NOT RHASH)
        storing of rooms:
        array1: has the rooms by ind -> necessary because have to locate rooms by ind
        array2: has the rooms by hash -> have to convert name -> hash -> ind

        also, adj list
        when weight updated, all rooms in the same hallway have their weights updated.
        can be done O(1)? -> no unless complex structure of hallways
        simply loop thru all rooms, see which have the same hallway, update their weights
        O(n) for updating those, O(v + e) for djikstra, O(1) for accessing -> overall good time complexity
         */
    private static List<Room> rooms;
    private static List<List<Room>> adjacent;
    Room[] roomhashed = new Room[1000];
    private static FastPath fp;
    public School() throws FileNotFoundException {
        rooms = new ArrayList<Room>();
        File fin = new File("C:\\Users\\varun\\StudioProjects\\ib_cs_hl_app\\app\\src\\main\\java\\com\\example\\ibcshlapplication\\classrooms.txt");
        Scanner scan = new Scanner(fin);
        String[][] arr = new String[6][12];
        for (int i = 0; i < 12; i++) {
            scan.nextLine();
            for (int j = 0; j < 6; j++) {
                arr[j][i] = scan.next();
            }
        }
        adjacent = new ArrayList<List<Room>>();


        int q = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 12; j++) {
                if (!arr[i][j].equals("#####")) {
                    Room y = new Room(q, arr[i][j]);
                    rooms.add(y);
                    roomhashed[hashh(arr[i][j])] = y;
                    q++;
                }
            }
        }


        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 12; j++) {
                if (!arr[i][j].equals("#####")) {
                    int y = roomhashed[hashh(arr[i][j])].getInd();
                    if (i-1 >= 0 && !arr[i-1][j].equals("#####")) adjacent.get(y).add(roomhashed[hashh(arr[i-1][j])]);
                    if (i+1 <= 5 && !arr[i+1][j].equals("#####")) adjacent.get(y).add(roomhashed[hashh(arr[i+1][j])]);
                    if (j-1 >= 0 && !arr[j-1][j].equals("#####")) adjacent.get(y).add(roomhashed[hashh(arr[j-1][j])]);
                    if (j+1 <= 11 && !arr[j+1][j].equals("#####")) adjacent.get(y).add(roomhashed[hashh(arr[j+1][j])]);
                }
            }
        }
        fp = new FastPath(rooms.size(), adjacent);



    }


    public List<String> pathfinder(String loc, String des) {
        fp.find_path(roomhashed[hashh(loc)].getInd(), roomhashed[hashh(des)].getInd());
        List<Integer> res = fp.getPath();
        List<String> fin = new ArrayList<String>();
        for (int i = 0; i<res.size(); i++) {
            fin.add(rooms.get(res.get(i)).getName());
        }
        return fin;
    }


    public static int hashh(String name) {
        char re = name.charAt(0);
        int first = (int) re - 64;
        String ret = Integer.toBinaryString(first);
        ret += Integer.toBinaryString(Integer.parseInt(String.valueOf(name.charAt(1)))-1);
        ret += name.charAt(2);
        ret += Integer.toBinaryString(Integer.parseInt(String.valueOf(name.charAt(3))));
        int index = Integer.parseInt(ret, 2);
        return index;
    }

    public void update_weights(String name, int w) {
        Room r = roomhashed[hashh(name)];
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getHallway() == r.getHallway()) {
                rooms.get(i).update_weight(w);
            }
        }
    }


}

