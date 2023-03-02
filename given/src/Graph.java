/* This program contains 2 parts: (1) and (2)
   YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)============================================
import java.io.*;
import java.util.*;
//-------------------------------------------------------------------------------
public class Graph {
  int [][] a; int n;
  char v[];
  int deg[];
  Graph() {
    v = "ABCDEFGHIJKLMNOP".toCharArray();
    deg = new int[20];
    a = new int[20][20];
    n = 0;
   }

  void loadData(int k) {  //do not edit this function
    RandomAccessFile f;int i,j,x;
    String s;StringTokenizer t;
    a = new int[20][20];
    try {
     f = new RandomAccessFile("data.txt","r");
     for(i=0;i<k;i++) f.readLine();
     s = f.readLine();s = s.trim();
     n = Integer.parseInt(s);
     for(i=0;i<n;i++) {
       s = f.readLine();s = s.trim();
       t = new StringTokenizer(s);
       for(j=0;j<n;j++) { 
         x = Integer.parseInt(t.nextToken().trim());
         a[i][j] = x;
        }
       }
     f.close();
     }
    catch(Exception e) {}

   }

  void dispAdj() {
    int i,j;
    for(i=0;i<n;i++) {
      System.out.println();
      for(j=0;j<n;j++)
        System.out.printf("%4d",a[i][j]);
     }
   }

  void fvisit(int i, RandomAccessFile f) throws Exception {
    f.writeBytes("  "+v[i]);
   }

 void fdispAdj(RandomAccessFile f) throws Exception { 
    int i,j;
    f.writeBytes("n = "+n+"\r\n");
    for(i=0;i<n;i++) {
      f.writeBytes("\r\n");
      for(j=0;j<n;j++)  f.writeBytes("  " + a[i][j]);
     }
    f.writeBytes("\r\n");
   }

  void breadth(boolean [] en, int i, RandomAccessFile f) throws Exception {
    Queue q = new Queue();
    int r,j;
    q.enqueue(i); en[i]=true;
    while(!q.isEmpty()) {
      r = q.dequeue();
      fvisit(r,f);
      for(j=0;j<n;j++) {
        if(!en[j] && a[r][j]>0) {
         q.enqueue(j);en[j]=true;
        }
       }
     }
   }

  void breadth(int  k, RandomAccessFile f) throws Exception {
    boolean [] en = new boolean[20];
    int i;
    for(i=0;i<n;i++) en[i]=false;
    breadth(en,k,f);
    for(i=0;i<n;i++) 
      if(!en[i]) breadth(en,i,f);
   }

 void depth(boolean [] visited,int k, RandomAccessFile f) throws Exception {
    fvisit(k,f);
    visited[k]=true;    
    for(int i=0;i<n;i++) 
      if(!visited[i] && a[k][i]>0) 
          depth(visited,i,f);
     
   }
  void depth(int k, RandomAccessFile f) throws Exception {
    boolean [] visited = new boolean[20];
    int i;
    for(i=0;i<n;i++) visited[i]=false;
    //depth(visited,k,f); nen bo dong nay bi thua
    for(i=0;i<n;i++)
       if(!visited[i]) 
           depth(visited,i,f);
   }

//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================
  
  
  void f1() throws Exception {
    loadData(1);
    String fname = "f1.txt";
    File g123 = new File(fname);
    if(g123.exists()) g123.delete();
    RandomAccessFile  f = new RandomAccessFile(fname, "rw"); 
    depth(0,f);
    f.writeBytes("\r\n");
    //-------------------------------------------------------------------------------------
     /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
    

      depth2(0, f);

    //-------------------------------------------------------------------------------------
    f.writeBytes("\r\n");
    f.close();
   }
   int count = 0;
   void depth2(boolean [] visited,int k, RandomAccessFile f) throws Exception {
    if(count >= 3 && count < 8) {
        fvisit(k,f);
    }
    count++;
    visited[k]=true;    
    for(int i=0;i<n;i++) 
      if(!visited[i] && a[k][i]>0) 
          depth2(visited,i,f);
     
   }
  void depth2(int k, RandomAccessFile f) throws Exception {
    boolean [] visited = new boolean[20];
    int i;
    for(i=0;i<n;i++) visited[i]=false;
    //depth(visited,k,f); nen bo dong nay bi thua
    for(i=0;i<n;i++)
       if(!visited[i]) 
           depth2(visited,i,f);
   }
  
//=================================================================

  void f2() throws Exception {
    loadData(13);
    String fname = "f2.txt";
    File g123 = new File(fname);
    if(g123.exists()) g123.delete();
    RandomAccessFile  f = new RandomAccessFile(fname, "rw"); 
    f.writeBytes("\r\n");
    //-------------------------------------------------------------------------------------
     /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
      // You can use the statement fvisit(i,f); i = 0, 1, 2,...,n-1 to display the vertex i to file f2.txt 
      //  and statement f.writeBytes(" " + k); to write  variable k to the file f2.txt  
      int from = 0, to = 0;
      from = search(from, 'C');
      to = search(to, 'H');
      dijkstra(from, to, f);
      dijkstra(4, 6, f);
    //-------------------------------------------------------------------------------------
    f.writeBytes("\r\n");
    f.close();
   }
  
  int search(int from, char var) {
      for (int i = 0; i < v.length; i++) {
          if(v[i] == var) {
              from = i;
              return from;
          }
      }
      return 0;
  }
  
  void dijkstra(int fro, int to, RandomAccessFile f) throws Exception {
    // Initialize variables
    int i, j, k, t, INF;
    INF = 99; // A large value to represent infinity
    boolean[] S = new boolean[n]; // Whether each vertex is in the set S
    int[] d = new int[n]; // Distance from the starting vertex to each vertex
    int[] p = new int[n]; // Predecessor of each vertex on the shortest path
    for (i = 0; i < n; i++) {
        S[i] = false; // Initially, no vertices are in the set S
        d[i] = a[fro][i]; // Initialize the distance to each vertex from the starting vertex
        p[i] = fro; // Set the predecessor of each vertex to the starting vertex
    }

    // Initialize variables for keeping track of the order in which vertices are added to the set S
    int[] ss = new int[n];
    int[] pp = new int[n];
    int m, r;
    j = 0;
    S[fro] = true; // Add the starting vertex to the set S
    ss[0] = fro;

    // Main loop of the algorithm
    while (true) {
        k = -1;
        t = INF;
        // Find the vertex with the smallest d value that is not already in the set S
        for (i = 0; i < n; i++) {
            if (S[i] == true) {
                continue; // Skip vertices that are already in the set S
            }
            if (d[i] < t) {
                k = i;
                t = d[i];
            }
        }
        if (k == -1) {
            return; // No solution
        }
        // Add k to the set S
        S[k] = true;
        j++;
        ss[j] = k;
        if (k == to) {
            break; // Found the shortest path to the destination vertex
        }
        // Update the d values of the neighbors of k
        for (i = 0; i < n; i++) {
            if (S[i] == true) {
                continue; // Skip vertices that are already in the set S
            }
            if (d[i] > d[k] + a[k][i]) {
                d[i] = d[k] + a[k][i];
                p[i] = k;
            }
        }
    }
    m = j;

    // Backtrack from the destination vertex to the starting vertex to find the shortest path
    Stack s = new Stack();
    i = to;
    while (true) {
        s.push(i);
        if (i == fro) {
            break; // Reached the starting vertex
        }
        i = p[i]; // Move to the predecessor of i on the shortest path
    }
    j = 0;
    while (!s.isEmpty()) {
        i = s.pop();
        pp[j++] = i; // Add each vertex to the final shortest path in reverse order
    }
    r = j;

    // Write the shortest path to the file
    f.writeBytes("" + v[pp[0]]);
    for (i = 1; i < r; i++) {
//        f.writeBytes(" " + v[pp[i]]);
        fvisit(pp[i], f);
    }
    f.writeBytes("\r\n");
    f.writeBytes("" + d[to]);
    f.writeBytes("\r\n");
}
  
  
//        f.writeBytes("\r\n");
//      for (i = 0; i < r; i++) {
//        f.writeBytes("" + "(" + d[pp[i]] + ")");
//      }
}
