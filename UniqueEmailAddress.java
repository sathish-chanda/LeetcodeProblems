class Solution {
    public int numUniqueEmails(String[] emails) {
           Set<String> set = new HashSet<String>();
           for(String email : emails)
           {
               int atPos = email.indexOf("@");
               String local = email.substring(0,atPos);
               String domain = email.substring(atPos);
               int plusPos = local.indexOf("+");
               //System.out.println(local+domain);
               if(plusPos > 0 && plusPos < local.length())
                  local = local.substring(0,plusPos);
               local = local.replace(".","");
               set.add(local+domain);
           }
        return set.size();
    }
}