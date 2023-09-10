package com.driver;
import java.lang.Exception;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class CurrentAccount extends BankAccount {
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        super(name, balance, 5000);
       tradeLicenseId=tradeLicenseId.toUpperCase();

        if (balance < 5000) {
            throw new Exception("Insufficient Balance");
        }
        this.tradeLicenseId = tradeLicenseId;
    }

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public void setTradeLicenseId(String tradeLicenseId) {
        this.tradeLicenseId = tradeLicenseId;
    }

    public void validateLicenseId() throws Exception {
        if (IsValid(tradeLicenseId)) return;
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        HashMap<Character, Integer> hm = new HashMap<>();
        //first count the frequency of characters present in id
        for (char ch : tradeLicenseId.toCharArray()) {
            int count = hm.getOrDefault(ch, 0) + 1;
            hm.put(ch, count);
        }

        // Traverse through hashmap and find the failure condition where valid license id is not possible
        for (Map.Entry<Character, Integer> mapElement : hm.entrySet()) {
            int FreqOfChar = mapElement.getValue();
            int FreqOfRemainingChar = tradeLicenseId.length() - FreqOfChar;
            if (FreqOfRemainingChar < FreqOfChar - 1)
                throw new Exception("Valid License can not be generated");
        }
        PriorityQueue<pair> pq = new PriorityQueue<>((a, b) -> {
            return b.freq - a.freq;
        });


        // traverse through hashmap and add key value as pair in priority queue
        for (Map.Entry<Character, Integer> mapElement : hm.entrySet()) {
            pq.add(new pair(mapElement.getKey(), mapElement.getValue()));
        }

        StringBuilder identity = new StringBuilder();
        while (pq.size() > 0) {
            pair first = pq.remove();
            // add the most frequent ocuring char to new idString and decrement frequency
            identity.append(first.letter);
            --first.freq;
              boolean flag=false;
            if (pq.size() > 0) {
                pair second = pq.remove();
                // add the char to identity string  nd decrement the frequency
                 identity.append(second.letter);
                --second.freq;
                  flag=true;
                if (first.freq > 0) pq.add(first);
                if (second.freq > 0) pq.add(second);
            }
            if (flag=false&first.freq > 0) pq.add(first);

        }
//        if(IsValid(identity.toString())){}
        tradeLicenseId = identity.toString();
//        System.out.println(tradeLicenseId);
    }


    private boolean IsValid(String id) {
       for(int i=0;i<tradeLicenseId.length()-1;i++){
           if(tradeLicenseId.charAt(i)==tradeLicenseId.charAt(i+1)){
               return false;
           }
       }
        return true;

    }
    public static class pair{
        char letter;
        int freq;

        public pair(char letter, int freq) {
            this.letter = letter;
            this.freq = freq;
        }
    }
}