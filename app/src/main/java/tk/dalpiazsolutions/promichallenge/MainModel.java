package tk.dalpiazsolutions.promichallenge;

import java.util.LinkedList;

/**
 * Created by Christoph on 30.03.2018.
 */

public class MainModel {
    private MainActivity mainActivity;
    private String completeSite;
    private String linkContainer;
    private LinkedList<String> sources = new LinkedList<>();
    private LinkedList<String> links = new LinkedList<>();
    private LinkedList<String> names = new LinkedList<>();
    private int elementNumber;
    private int correctAnswer;

    public MainModel(MainActivity mainActivity)
    {
        this.mainActivity = mainActivity;
    }

    public String getCompleteSite() {
        return completeSite;
    }

    public void setCompleteSite(String completeSite) {
        this.completeSite = completeSite;
    }

    public LinkedList<String> getLinks() {
        return links;
    }

    public void setLinks(LinkedList<String> links) {
        this.links = links;
    }

    public void addLinksElement(String element) {
        this.links.add(element);
    }

    public LinkedList<String> getNames() {
        return names;
    }

    public void setNames(LinkedList<String> names) {
        this.names = names;
    }

    public void addNamesElement(String element) {
        this.names.add(element);
    }

    public String getLinkContainer() {
        return linkContainer;
    }

    public void setLinkContainer(String linkContainer) {
        this.linkContainer = linkContainer;
    }

    public LinkedList<String> getSources() {
        return sources;
    }

    public void addSourcesElement(String element) {
        this.sources.add(element);
    }

    public int getElementNumber() {
        return elementNumber;
    }

    public void setElementNumber(int elementNumber) {
        this.elementNumber = elementNumber;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
