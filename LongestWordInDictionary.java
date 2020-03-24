class Node{
      public Character content;
      public Node[] children = new Node[26];
      public boolean isWord;
      public Node(Character ch,boolean isWord)
      {
          this.content = ch;
          this.isWord = isWord;
      }
      public String toString()
      {
          StringBuilder childs = new StringBuilder("{");
          for(Node node : children)
              if(node!=null)
              childs.append(node.toString()).append(" , ");
          childs.append("}");
          return content + " " + isWord + " " + childs;
      }
}
class Trie{
      public Node root;
      public Trie()
      {
          root = new Node('$',false);
      }
      public void addWord(String word)
      {
          Node current = root;
          int i;
          for(i=0;i<word.length()-1;i++)
          {
              int pos = (int)word.charAt(i) - (int)'a';
              if(current.children[pos]!=null)
              {
                  current = current.children[pos];
              }
              else
              {
                  current.children[pos] = new Node(word.charAt(i),false);
                  current = current.children[pos];
              }
          }
          int pos = (int)word.charAt(i) - (int)'a';
          if(current.children[pos]!=null)
          {
             current.children[pos].isWord = true;
           }
           else
           {
              current.children[pos] = new Node(word.charAt(i),true);
           }
      }
      public String getLongestWord(String[] words)
      {
          String longest = "";
          int index,max = Integer.MIN_VALUE;
          for(String word : words)
          {
              Node current = root;
              int count = 0;
              for(int i=0;i<word.length();i++)
              {
                  index = (int)word.charAt(i)-(int)'a';
                  current = current.children[index];
                  if(current.isWord)
                     count++;
              }
              if(count!=word.length())
                 continue;
              //System.out.println(word+ " " + count+ " | "+longest + " " + max);
              if(max < count)
              {
                  longest = word;
                  max = count;
              }
              if(max == count)
              {
                 //System.out.println(longest.equals(word));
                 int i=0;
                 while(i < longest.length())
                 {
                     if(word.charAt(i) == longest.charAt(i))
                         i++;
                     else
                         break;
                 }
                 if(i<word.length() && word.charAt(i) < longest.charAt(i))
                    longest = word;
              }
          }
          return longest;
      }
      public String toString()
      {
          return root.toString();
      }
}

class Solution {
    public String longestWord(String[] words) {
        Trie trie = new Trie();
        for(String word : words)
            trie.addWord(word);
        System.out.println(trie);
        String ans = trie.getLongestWord(words);
        System.out.println(ans);
        return ans;
    }
}