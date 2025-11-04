package cruz.zoo.com;

import java.util.ArrayList;

public class AnimalNameListsWrapper {
    private ArrayList<String> hyenaNameList;
    private ArrayList<String> lionNameList;
    private ArrayList<String> tigerNameList;
    private ArrayList<String> bearNameList;

    public AnimalNameListsWrapper(ArrayList<String> hyenaNameList,
                                  ArrayList<String> lionNameList,
                                  ArrayList<String> tigerNameList,
                                  ArrayList<String> bearNameList) {
        this.hyenaNameList = hyenaNameList;
        this.lionNameList = lionNameList;
        this.tigerNameList = tigerNameList;
        this.bearNameList = bearNameList;
    }

    public ArrayList<String> getHyenaNameList() {
        return hyenaNameList;
    }

    public ArrayList<String> getLionNameList() {
        return lionNameList;
    }

    public ArrayList<String> getTigerNameList() {
        return tigerNameList;
    }

    public ArrayList<String> getBearNameList() {
        return bearNameList;
    }
}
