import java.util.*;

public class Q1 {

    public static int[] get_start(String[][][] jail) {
        int levels= jail.length;
        int rows= jail[0].length;
        int cols= jail[0][0].length;
        int[] start_end= new int[5];
        int place= 0;
        for (int i = 0; i < levels; i++) {
            for (int j = 0; j < rows; j++) {
                for (int k = 0; k < cols; k++) {
                    if(jail[i][j][k].equals("S")){
                        start_end[0]=i;
                        start_end[1]=j;
                        start_end[2]=k;
                        start_end[4]=place;
                        return start_end;
                    }
                    place++;
                }
            }
        }
        return null;
    }

    public static ArrayList<int[]> get_adj_list(String[][][] jail, int[] node) {
        int levels= jail.length;
        int rows= jail[0].length;
        int cols= jail[0][0].length;
        int lev= node[0];
        int row= node[1];
        int col= node[2];
        int dist= node[3];
        int place= node[4];
        ArrayList<int[]> neighbors = new ArrayList<int[]>();
        if(lev+1<levels && !(jail[lev+1][row][col].equals("#"))){
            int[] neighbor= {lev+1, row, col, dist+1, place+(rows*cols)};
            neighbors.add(neighbor);
        }
        if(row+1<rows && !(jail[lev][row+1][col].equals("#"))){
            int[] neighbor= {lev, row+1, col, dist+1, place+cols};
            neighbors.add(neighbor);
        }
        if(col+1<cols && !(jail[lev][row][col+1].equals("#"))){
            int[] neighbor= {lev, row, col+1, dist+1, place+1};
            neighbors.add(neighbor);
        }
        if(lev-1>=0 && !(jail[lev-1][row][col].equals("#"))){
            int[] neighbor= {lev-1, row, col, dist+1, place-(rows*cols)};
            neighbors.add(neighbor);
        }
        if(row-1>=0 && !(jail[lev][row-1][col].equals("#"))){
            int[] neighbor= {lev, row-1, col, dist+1, place-cols};
            neighbors.add(neighbor);
        }
        if(col-1>=0 && !(jail[lev][row][col-1].equals("#"))){
            int[] neighbor= {lev, row, col-1, dist+1, place-1};
            neighbors.add(neighbor);
        }
        return neighbors;
    }

    public static int find_exit(String[][][] jail) {
        int levels= jail.length;
        int rows= jail[0].length;
        int cols= jail[0][0].length;
        HashSet<Integer> passed= new HashSet<Integer>();
        int[] Start = get_start(jail);
        LinkedList<int[]>queue = new LinkedList<int[]>();
        passed.add(Start[4]);
        queue.addLast(Start);
        while (queue.size() != 0) {
            int[] curr= queue.removeFirst();
            int lev= curr[0];
            int row= curr[1];
            int col= curr[2];
            int dist= curr[3];
            if(jail[lev][row][col].equals("E")){
                return dist;
            }
            for (int i=0; i<get_adj_list(jail, curr).size(); i++){
                if(!(passed.contains(get_adj_list(jail, curr).get(i)[4]))){
                    passed.add(get_adj_list(jail, curr).get(i)[4]);
                    queue.addLast(get_adj_list(jail, curr).get(i));
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
    }
}
