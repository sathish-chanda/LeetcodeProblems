class Node{
      public Character content;
      public Node[] children = new Node[52];
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
          System.out.println(word);
          Node current = root;
          int i,pos;
          for(i=0;i<word.length()-1;i++)
          {
              if('a' <= word.charAt(i))
                 pos = 26 + (int)word.charAt(i)-(int)'a';
              else
                 pos = (int)word.charAt(i) - (int)'A';
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
          if('a' <= word.charAt(i))
              pos = 26 + (int)word.charAt(i)-(int)'a';
          else
              pos = (int)word.charAt(i) - (int)'A';    
          if(current.children[pos]!=null)
          {
             current.children[pos].isWord = true;
           }
           else
           {
              current.children[pos] = new Node(word.charAt(i),true);
           }
      }
      public List<Boolean> getCamelMatch(String[] queries,String pattern)
      {
          List<Boolean> list = new ArrayList<>();
          int index,pindex,i;
          boolean match;
          for(String query : queries)
          {
              pindex = 0;
              i=0;
              Node current = root;
              match = true;
              while(i<query.length())
              {
                  if('a' <= query.charAt(i))
                     index = 26 + (int)query.charAt(i)-(int)'a';
                  else
                     index = (int)query.charAt(i) - (int)'A';    
                  //System.out.println(query.charAt(i)+ " "+pattern.charAt(pindex));
                  current = current.children[index];
                  if(current.content == pattern.charAt(pindex))
                  {
                    pindex++;
                  }
                  else
                  {
                     if( 'A' <= current.content && current.content <= 'Z')
                     {
                         match = false; 
                         i++;
                         break;
                     }
                  }
                  if(pindex==pattern.length())
                  {
                     i++; 
                     break;
                  }
                  i++;
              }
              if(pindex!=pattern.length())
              {
                  match = false;
                  i = query.length();
              }
              if(i<query.length()){
                if(pindex==pattern.length())
                while(i<query.length())
                { 
                  
                  if('a' <= query.charAt(i))
                     index = 26 + (int)query.charAt(i)-(int)'a';
                  else
                     index = (int)query.charAt(i) - (int)'A';
                  current = current.children[index];
                  if('A' <= current.content && current.content <= 'Z')
                  {
                      match = false;
                      break;
                  }
                  i++;
                }
              }
              list.add(match);
              //System.out.println();
          }
          return list;
      }
}
class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
           Trie trie = new Trie();
           for(int i=0;i<queries.length;i++)
               trie.addWord(queries[i]);
           return trie.getCamelMatch(queries,pattern);
    }
}