package com.example.ibcshlapplication;
import java.util.*;
import java.io.*;

public class School {

    private static List<Room> rooms;
    private static List<List<Room>> adjacent;
    private HashMap<String, Room> roomhashed;
    private static FastPath fp;
    public School(InputStream fin){
        rooms = new ArrayList<Room>();
        roomhashed = new HashMap<String, Room>();

            Scanner scan = new Scanner(fin);
            String[][] arr = new String[6][12];
            for (int i = 0; i < 12; i++) {
                scan.nextLine();
                for (int j = 0; j < 6; j++) {
                    arr[j][i] = scan.next();
                }
            }
            adjacent = new ArrayList<List<Room>>();
            for (int i = 0; i < 2048; i++) {
                adjacent.add(new ArrayList<Room>());
            }
            int q = 0;
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 12; j++) {
                    if (!arr[i][j].equals("#####")) {
                        Room y = new Room(q, arr[i][j]);
                        rooms.add(y);
                        roomhashed.put(arr[i][j], y);
                        q++;
                    }
                }
            }


            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 12; j++) {
                    if (!arr[i][j].equals("#####")) {
                        int y = roomhashed.get(arr[i][j]).getInd();
                        if (i - 1 >= 0 && !arr[i - 1][j].equals("#####"))
                            adjacent.get(y).add(roomhashed.get(arr[i - 1][j]));
                        if (i + 1 <= 5 && !arr[i + 1][j].equals("#####"))
                            adjacent.get(y).add(roomhashed.get(arr[i + 1][j]));
                        if (j - 1 >= 0 && !arr[i][j-1].equals("#####"))
                            adjacent.get(y).add(roomhashed.get(arr[i][j-1]));
                        if (j + 1 <= 11 && !arr[i][j+1].equals("#####"))
                            adjacent.get(y).add(roomhashed.get(arr[i][j+1]));
                    }
                }
            }


    }


    public String pathfinder(String loc, String des) {
        fp = new FastPath(rooms.size(), adjacent);
        fp.find_path(roomhashed.get(loc).getInd());
        List<Integer> res = fp.getPath(roomhashed.get(des).getInd());
        List<String> fin = new ArrayList<String>();
        for (int i = 0; i<res.size(); i++) {
            fin.add(rooms.get(res.get(i)).getName());
        }
        StringBuilder x = new StringBuilder();
        for (int i = 0; i < fin.size(); i++) {
            x.append(fin.get(i)).append(" ");
        }
        return x.toString();
    }

    public void update_weights(String name, int w) {
        Room r = roomhashed.get(name);
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getHallway() == r.getHallway()) {
                rooms.get(i).update_weight(w);
            }
        }
    }


}

