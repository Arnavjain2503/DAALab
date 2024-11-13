package DSA.DAA;

import java.util.*;

class DisjointSet {
    List<Integer> rank = new ArrayList<>();
    List<Integer> parent = new ArrayList<>();
    List<Integer> size = new ArrayList<>();
    public DisjointSet(int n) {
        for (int i = 0; i <= n; i++) {
            rank.add(0);
            parent.add(i);
            size.add(1);
        }
    }
    public int findUPar(int node) {
        if (node == parent.get(node)) {
            return node;
        }
        int ulp = findUPar(parent.get(node));
        parent.set(node, ulp);
        return parent.get(node);
    }
    public void unionByRank(int u, int v) {
        int ulp_u = findUPar(u);
        int ulp_v = findUPar(v);

        if (ulp_u == ulp_v) {  // They belong to the same component
            return;
        }
        if (rank.get(ulp_u) < rank.get(ulp_v)) {
            parent.set(ulp_u, ulp_v);
        } else {
            parent.set(ulp_v, ulp_u);
            rank.set(ulp_u, rank.get(ulp_u) + 1);
        }
    }

    public void unionBySize(int u, int v) {
        int ulp_u = findUPar(u);
        int ulp_v = findUPar(v);

        if (ulp_u == ulp_v) {
            return;
        }
        if (size.get(ulp_u) < size.get(ulp_v)) {
            parent.set(ulp_u, ulp_v);
            size.set(ulp_v, size.get(ulp_v) + size.get(ulp_u));
        } else {
            parent.set(ulp_v, ulp_u);
            size.set(ulp_u, size.get(ulp_u) + size.get(ulp_v));
        }
    }
}

class Edge implements Comparable<Edge> {
    int src, dest, weight;

    Edge(int _src, int _dest, int _wt) {
        this.src = _src;
        this.dest = _dest;
        this.weight = _wt;
    }

    public int compareTo(Edge compareEdge) {
        return this.weight - compareEdge.weight;
    }
}

class KruskalSoln {
    static int spanningTree(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, HashMap<String, Integer> vertexMap, HashMap<Integer, String> reverseVertexMap) {
        List<Edge> edges = new ArrayList<>();
        List<Edge> mstEdges = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < adj.get(i).size(); j++) {
                int adjNode = adj.get(i).get(j).get(0);
                int wt = adj.get(i).get(j).get(1);
                int node = i;

                Edge temp = new Edge(i, adjNode, wt);
                edges.add(temp);
            }
        }
        DisjointSet ds = new DisjointSet(V);
        Collections.sort(edges);
        int mstWt = 0;

        for (Edge edge : edges) {
            int wt = edge.weight;
            int u = edge.src;
            int v = edge.dest;

            if (ds.findUPar(u) != ds.findUPar(v)) {
                mstWt += wt;
                mstEdges.add(edge);
                ds.unionByRank(u, v);
            }
        }

        System.out.println("Edges in the MST:");
        for (Edge edge : mstEdges) {
            String srcVertex = reverseVertexMap.get(edge.src);
            String destVertex = reverseVertexMap.get(edge.dest);
            System.out.println(srcVertex + " - " + destVertex + " with weight " + edge.weight);
        }

        return mstWt;
    }
}

public class J_05_Kruskal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of vertices (V): ");
        int V = sc.nextInt();
        System.out.print("Enter the number of edges (E): ");
        int E = sc.nextInt();

        sc.nextLine();

        HashMap<String, Integer> vertexMap = new HashMap<>();
        HashMap<Integer, String> reverseVertexMap = new HashMap<>();

        System.out.println("Enter the vertex names:");
        for (int i = 0; i < V; i++) {
            String vertexName = sc.nextLine();
            vertexMap.put(vertexName, i);
            reverseVertexMap.put(i, vertexName);
        }

        ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        System.out.println("Enter the edges in the format: source destination weight");
        for (int i = 0; i < E; i++) {
            String src = sc.next();
            String dest = sc.next();
            int w = sc.nextInt();

            int u = vertexMap.get(src);
            int v = vertexMap.get(dest);

            adj.get(u).add(new ArrayList<>(Arrays.asList(v, w)));
            adj.get(v).add(new ArrayList<>(Arrays.asList(u, w))); // For undirected graph
        }

        KruskalSoln obj = new KruskalSoln();
        int mstWt = obj.spanningTree(V, adj, vertexMap, reverseVertexMap);
        System.out.println("The sum of all the edge weights: " + mstWt);

        sc.close();
    }
}
