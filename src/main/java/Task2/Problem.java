package Task2;

import java.util.HashSet;
import java.util.Set;

//I created the Java classes Problem and ProblemList for parsing our JSON content into a Java object and vice versa


public class Problem {
    private String hash;
    private Set<String> data;
    //Though in the JSON schema data is an array of strings, I want to utilize HashSets, because the set of problem properties isn't ordered and the contains method of HashSets are way faster.
    public Problem(){
    }

    public Problem(String hash) {
        this.hash = hash;
        this.data = new HashSet<>();
    }

    public Problem(String hash, Set<String> list) {
        this.hash = hash;
        this.data = list;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Set<String> getData() {
        return data;
    }

    public void setData(Set<String> data) {
        this.data = data;
    }
}
