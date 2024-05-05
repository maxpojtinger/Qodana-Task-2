package Task2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Task2 {
    private final ObjectMapper OBJECT_MAPPER;
    public Task2(){
        OBJECT_MAPPER = new ObjectMapper();
    }

    /**
     * This very simple method quickly determines, if the input file has a valid JSON format or not.
     *
     * @param file The file that needs to be validated
     * @return
     */
    public boolean isValidJson(File file) {
        try {
            OBJECT_MAPPER.readTree(file);
            return true;
        } catch (JsonProcessingException e) {
            System.out.println("There has been a parsing problem, your JSON-format probably wasn't valid");
            return false;
        } catch (IOException e) {
            System.out.println("There probably has been a general reading your file, your JSON-format wasn't necessarily invalid");
            return false;
        }
    }
    //This is a pretty dumb method, but I still use it, because otherwise in task2 you wouldn't know, which file is responsible for the problem

    /**
     * This method is responsible for solving the actual problem (that's why I creatively named it task2)
     * Given two Bugban analysis output files, this method will generate three JSON files (according to the schema above):
     * One for the problems only detected in the first Bugban analysis, one for the problems only detected in the second analysis, and one for the problems identified in both analyses.
     * Two Bugban problems are considered equal if they have an equal "hash" field and an equal set of strings in a "data" field (equality does not depend on the values' order). The "hash" field is the hash of the "data" strings computed by the Bugban.
     *
     * @param files An array with the program files needed for solving this task.
     */
    public void task2(File[] files){
        try{
            ProblemList input1 = OBJECT_MAPPER.readValue(files[0], ProblemList.class);
            ProblemList input2 = OBJECT_MAPPER.readValue(files[1], ProblemList.class);

            ProblemList onlyInput1 = new ProblemList(new ArrayList<>());
            ProblemList onlyInput2 = new ProblemList(new ArrayList<>());
            ProblemList both = new ProblemList(new ArrayList<>());

            Map<String, Problem> map1 = new HashMap<>();
            Map<String, Problem> map2 = new HashMap<>();
            for(Problem p : input1.getProblems()){
                map1.put(p.getHash(), p);
            }
            for(Problem p : input2.getProblems()){
                map2.put(p.getHash(), p);
            }
            for(String s : map1.keySet()){
                if(map2.containsKey(s)){
                    if(map1.get(s).getData().containsAll(map2.get(s).getData()) && map2.get(s).getData().containsAll(map1.get(s).getData())){
                        both.getProblems().add(map1.get(s));
                    } else {
                        onlyInput1.getProblems().add(map1.get(s));
                    }
                } else {
                    onlyInput1.getProblems().add(map1.get(s));
                }
            }

            for(String s : map2.keySet()){
                if(map1.containsKey(s)){
                    if(!(map1.get(s).getData().containsAll(map2.get(s).getData()) && map2.get(s).getData().containsAll(map1.get(s).getData()))){
                        onlyInput2.getProblems().add(map2.get(s));
                    }
                } else {
                    onlyInput2.getProblems().add(map2.get(s));
                }
            }

            OBJECT_MAPPER.writeValue(files[2], onlyInput1);
            OBJECT_MAPPER.writeValue(files[3], onlyInput2);
            OBJECT_MAPPER.writeValue(files[4], both);

        } catch (JsonProcessingException e){
            System.out.println("There has been a parsing problem, your JSON-format probably wasn't valid");
        } catch (IOException e){
            System.out.println("There probably has been a general reading your file, your JSON-format wasn't necessarily invalid");
        }
    }
    /*
    Note: I'm using hash maps, because they're simply faster than checking every file for equality in a nested for loop.
     */
}
