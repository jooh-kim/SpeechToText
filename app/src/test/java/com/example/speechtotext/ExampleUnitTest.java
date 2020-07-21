package com.example.speechtotext;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void test_functions() {

        ArrayList<String> samples = new ArrayList<String>();

//        samples.add("Put Item1 in Location1");
//        samples.add("place item1 in location1");
//        samples.add("In Location2 put item2");
//        samples.add("find item3");
//        samples.add("where is item4");
//        samples.add("what is in location3");
//        samples.add("in location7, place item7");
        samples.add("in location6, place item1 and item4");

        resultParse rp = new resultParse();

        try {
            int i = 0;
            while(i < samples.size()) {
                rp.parseResult(samples.get(i));
                i++;
            }
        }catch(IOException e){
            System.out.println("IOEXception");
        }


//        assertEquals(4, 2 + 2);
    }
}