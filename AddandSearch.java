class Node
{
     public Character content;
     public Node[] hm = new Node[26];
     public boolean isWord;
     public Node(Character content)
     {
         this.content = content;
         this.isWord = false;
         //this.hm = new HashMap<Character,Node>();
         //System.out.println(this);
     }
     public String toString()
     {
         return content + " " + isWord + hm.toString();
     }
}
class WordDictionary {
    Node root;
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new Node('$');
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
           Node current = root;
           int i;
           int index;
           for(i=0;i<word.length();i++)
           {
              // System.out.println(current);
               index = (int)word.charAt(i)-(int)'a';
               if(current.hm[index]!=null)
               {
                   current = current.hm[index];
               }
               else
               {
                   current.hm[index] = new Node(word.charAt(i));
                   current = current.hm[index];
               }
               //System.out.println(current);
           }
           current.isWord = true;
           //System.out.println(root);
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
           Node current  = root;
           int i=0;
           int index;
           while(i<word.length()-1)
           {
               if(word.charAt(i)!='.')
               {
                 index = (int)word.charAt(i) - (int)'a';
                 if(current.hm[index]==null)
                    return false;
                 current = current.hm[index];
                 i++;
               }
               else
               {
                 return deepSearch(word,current,i);
               }
           }
           if(word.charAt(i)!='.')
           {
              index = (int)word.charAt(i) - (int)'a';
              if(current.hm[index]==null)
                 return false;
              else
                 return current.hm[index].isWord;
           }
           else
           { 
                    for(Node node : current.hm)
                        if(node!=null && node.isWord)
                           return true;
                   return deepSearch(word,current,i);
           }
    }
    
    public boolean deepSearch(String word,Node current,int pos)
    {
           //System.out.println(current + "   " + pos);
           pos++;
           if(word.length()==pos)
           {
               for(Node node : current.hm)
                   if(node!=null && node.isWord)
                       return true;
               //return false;
           }
           Queue<Node> queue = new LinkedList<>();
           for(Node node : current.hm)
           { 
               if(node!=null)
               queue.add(node);
           }
           int index;
           while(queue.size() > 0)
           {
              int i = pos;
              Node cur = queue.remove();
              while(i<word.length() && word.charAt(i)!='.' && cur.hm[(int)word.charAt(i)-(int)'a']!=null)
              {
                   cur = cur.hm[(int)word.charAt(i)-(int)'a'];
                   i++;
                   if(i==word.length())
                      if(cur.isWord)
                          return true;
              }
              if(i<word.length() && word.charAt(i)=='.')
                 if(deepSearch(word,cur,i))
                     return true;
           }
           return false;
    }
}
