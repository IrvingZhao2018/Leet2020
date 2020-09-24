package unionFind.leet261GraphValidTree;

class Solution {
    public boolean validTree(int n, int[][] edges) {
        if(edges.length != n - 1) return false;
        int[] parents = new int[n];
        int[] size = new int[n];
        for(int i = 0; i < n; i++){
            parents[i] = i;
            size[i] = 1;
        }
        for(int[] edge : edges) {
            if(!union(edge[0], edge[1], parents, size)) return false;
        }
        if(size[find(0, parents)] != n) return false;
        return true;
    }

    private int find(int child, int[] parents){
        int parent = parents[child];
        if(parent != child){
            parent = find(parent, parents);
        }
        parents[child] = parent;
        return parent;
    }

    private boolean union(int x, int y, int[] parents, int[] size){
        int parentX = find(x, parents);
        int parentY = find(y, parents);
        if(parentX == parentY) return false;
        if(size[parentX] > size[parentY]){
            parents[parentY] = parentX;
            size[parentX] += size[parentY];
        } else {
            parents[parentX] = parentY;
            size[parentY] += size[parentX];
        }
        return true;
    }
}
