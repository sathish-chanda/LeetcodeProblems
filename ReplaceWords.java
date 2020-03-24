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
      public String getRootWord(String successor)
      {
          Node current = root;
          if(current.children[(int)successor.charAt(0)-(int)'a']==null)
             return successor;
          int i=0,index;
          StringBuilder sb = new StringBuilder();
          while(i<successor.length())
          {   
              index = (int)successor.charAt(i)-(int)'a';
              if(current.children[index]!=null)
              {
                  current = current.children[index];
                  sb.append(Character.toString(current.content));
              }
              else
                break;
              if(current.isWord)
                 break;
              i++;
          }
          if(current.isWord)
             return sb.toString();
          return successor;
       }
}
class Solution {
    public String replaceWords(List<String> dict, String sentence) {
           Trie trie = new Trie();
           for(int i=0;i<dict.size();i++)
           {
               trie.addWord(dict.get(i));
           }
           sentence = sentence.trim();
           String[] list = sentence.split(" ");
           StringBuilder ans = new StringBuilder();
           for(String word : list)
           {
               ans.append(trie.getRootWord(word.trim())).append(" ");
           }
           return ans.toString().trim();
    }
}