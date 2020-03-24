class Solution {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
           ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer> >();
           int i=0,j=0;
           while(i<A.length && j<B.length)
           {
               if(A[i][1] < B[j][0])
                   i++;
               else if(B[j][1] < A[i][0])
                   j++;
               else
               {
                   ArrayList<Integer> intersect = new ArrayList<Integer>();
                   intersect.add(Math.max(A[i][0],B[j][0]));
                   intersect.add(Math.min(A[i][1],B[j][1]));
                   list.add(intersect);
                   if(A[i][1] < B[j][1])
                      i++;
                   else
                       j++;
               }
           }
           //System.out.println(list);
           int[][] ans = new int[list.size()][2];
           for(i=0;i<list.size();i++)
           {
               ans[i][0] = list.get(i).get(0);
               ans[i][1] = list.get(i).get(1);
           }
           return ans;
    }
}