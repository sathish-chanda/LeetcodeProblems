class Solution {
    public int totalFruit(int[] tree) {
           int start = 0;
           int end = 0;
           int totalFruits = 0;
           int total;
           while(start < tree.length)
           {
               end = start;
               total = 0;
               Map<Integer,Integer> counter = new HashMap<Integer,Integer>();
               while(end < tree.length)
               {
                   if(counter.containsKey(tree[end]))
                   {
                       counter.put(tree[end],counter.get(tree[end])+1);
                   }
                   else
                   {
                       if(counter.size()==2)
                       break;
                       counter.put(tree[end],1);   
                   }
                   
                   end++;
               }
               for(Integer key : counter.keySet())
               {
                   total+=counter.get(key);
               }
               if(total > totalFruits)
                   totalFruits = total;
               if(total == tree.length)
                   break;
               start++;
           }
           return totalFruits;
    }
}