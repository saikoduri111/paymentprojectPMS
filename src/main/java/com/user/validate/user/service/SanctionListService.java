package com.user.validate.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.yaml.snakeyaml.tokens.ScalarToken;

import java.io.*;
import java.util.Scanner;
//import java.lang.reflect.Field;



@Service
public class SanctionListService {
    private final Status status = new Status();

    boolean matchFound = false;

     public Status fetchUserInSanctionList(String name) throws IOException {

         String[] words;
         String inputFileName = "src/main/resources/sdnlist.txt";
         FileReader fr = new FileReader(inputFileName);  //Creation of File Reader object
         BufferedReader br = new BufferedReader(fr); //Creation of BufferedReader object
         String s;

         status.setMessage("Not Found");
         while((s = br.readLine()) != null)   //Reading Content from the file
         {
             words = s.split(" ");  //Split the word using space
             for (String word : words) {
                 if (word.equals(name)) {
                     status.setMessage("Found");
                     fr.close();
                     return status;
                 }
             }
         }

         return status;
     }

}
