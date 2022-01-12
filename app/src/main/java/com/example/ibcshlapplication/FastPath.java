package com.example.ibcshlapplication;
import java.util.*;

public class FastPath {

    private double[] dist;
    private Set<Integer> settled;
    private PriorityQueue<Room> pq;
    private int v;
    private List<List<Room>> adj;
    private List<Integer> path;
    private int[] parent;

    public FastPath(int v, List<List<Room>> adj){
        this.v = v;
        dist = new double[v];
        settled = new HashSet<Integer>();
        pq = new PriorityQueue<Room>(v, new Room());
        parent = new int[v];
        this.adj = adj;
        path = new ArrayList<Integer>();
    }

    public void find_path(int src, int dest) {
        parent[src] = -1;
        for (int i = 0; i < v; i++) dist[i] = Double.MAX_VALUE;
        pq.add(new Room(src, 0));
        dist[src] = 0;
        while (settled.size() != v){
            if (pq.isEmpty()) return;
            int u = pq.remove().getInd();
            if (settled.contains(u)) continue;
            settled.add(u);

            nbrs(u);
        }

        buildPath(dest);
    }

    public void nbrs(int u) {
        double ed= -1;
        double nd = -1;
        for (int i = 0; i < adj.get(u).size(); i++) {
            Room r = adj.get(u).get(i);

            if (!settled.contains(r.getInd())) {
                ed = r.getWeight();
                nd = dist[u] + ed;
                if (nd < dist[r.getInd()]) {
                    dist[r.getInd()] = nd;
                    parent[r.getInd()] = u;
                }

                pq.add(new Room(r.getInd(), dist[r.getInd()]));
            }
        }

    }

    public void buildPath(int j) {
        if (parent[j]==-1)
            return;

        buildPath(parent[j]);

        path.add(j);
    }

    public List<Integer> getPath() {
        return path;
    }



}
