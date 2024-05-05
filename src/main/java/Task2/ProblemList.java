package Task2;

import java.util.List;

public class ProblemList {

    //I created the Java classes Problem and ProblemList for parsing our JSON content into a Java object and vice versa
    private List<Problem> problems;
    public ProblemList(){
    }

    public ProblemList(List<Problem> problems) {
        this.problems = problems;
    }
    public List<Problem> getProblems() {
        return problems;
    }

    public void setProblems(List<Problem> problems) {
        this.problems = problems;
    }
}
