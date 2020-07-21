package com.example.speechtotext;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;


public class resultParse {

    public ArrayList<String> parseResult(String sentence) throws IOException {

        InputStream tokenModelIn = null;
        InputStream posModelIn = null;
        ArrayList<String> result = new ArrayList<String>();

        try {
            // tokenize the sentence
            tokenModelIn = new FileInputStream("./en-token.bin");
            TokenizerModel tokenModel = new TokenizerModel(tokenModelIn);
            Tokenizer tokenizer = new TokenizerME(tokenModel);
            String tokens[] = tokenizer.tokenize(sentence);

            // Parts-Of-Speech Tagging
            // reading parts-of-speech model to a stream
            posModelIn = new FileInputStream("./en-pos-maxent.bin");
            // loading the parts-of-speech model from stream
            POSModel posModel = new POSModel(posModelIn);
            // initializing the parts-of-speech tagger with model
            POSTaggerME posTagger = new POSTaggerME(posModel);
            // Tagger tagging the tokens
            String tags[] = posTagger.tag(tokens);
            // Getting the probabilities of the tags given to the tokens
//                double probs[] = posTagger.probs();

//            for (int i = 0; i < tags.length; i++) {
//                System.out.println(tokens[i] + ":" + tags[i]);
//            }

            result = parseTags(tags, tokens);
            for (String word : result){
                System.out.print(word + " ");
            }
            System.out.print("\n");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (tokenModelIn != null) {
                try {
                    tokenModelIn.close();
                } catch (IOException e) {
                }
            }
            if (posModelIn != null) {
                try {
                    posModelIn.close();
                } catch (IOException e) {
                }
            }

        }
        return result;
    }

    public ArrayList<String> parseTags(String[] tags, String[] tokens){
        ArrayList<String> result = new ArrayList<String>();
        String action = null;
        String item = null;
        String location = null;

        int i = 0;
        while (i < tags.length){
            String word = tokens[i].toLowerCase();
            String tag = tags[i];

            if (tag.equals("VBD") || tag.equals("VB")){
                if (word.equals("find"))
                    action = "get";
                else if(word.equals("put") || word.equals("place"))
                    action = "post";
            }
            else if (tag.equals("PRP") || tag.equals("PRP$") || tag.equals("CC"))
                item = word;
            else if (tag.equals("NN"))
                action = "post";
            else if (tag.equals("IN")){
                i++;
                location = tokens[i].toLowerCase();
            }
            else if (tag.equals("WP") || tag.equals("WRB"))
                action = "get";
            i++;
        }

        if (action == "get"){
            if (item == null && location != null){
                // Requesting Item(s)
                result.add(action);
                result.add("0");
                result.add(location);
                return result;
            }
            else
                return null;
        }
        else if (action == "post"){
            if (item == null || location == null)
                return null;
            result.add(action);
            result.add(item);
            result.add(location);
        }else
            return null;
        return result;
    }

}
