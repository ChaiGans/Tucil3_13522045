package algorithm;

import java.util.ArrayList;
import java.util.List;

public class WordLadder {
    private List<String> path;
    private long executionTime;
    protected int nodesVisited;

    public WordLadder(List<String> path, long executionTime, int nodesVisited) {
        this.path = path;
        this.executionTime = executionTime;
        this.nodesVisited = nodesVisited;
    }

     public List<String> getPath() {
        return path;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public int getNodesVisited() {
        return nodesVisited;
    }

    public void setValue(List<String> path, long executionTime, int nodesVisited) {
        this.path = path;
        this.executionTime = executionTime;
        this.nodesVisited = nodesVisited;
    }

    public void reset() {
        this.path = new ArrayList<>();
        this.nodesVisited = 0;
        this.executionTime = 0;
    }
    
}
