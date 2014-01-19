package ua.kiev.javacourses.parser;

import java.io.Serializable;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Oleg
 * Date: 22.12.13
 * Time: 11:56
 * To change this template use File | Settings | File Templates.
 */
public class Vacancy implements Serializable {
    private static long id=0;
    String title;
    String link;
    String description;
    String pubDate;
    long cntDate;

    public Vacancy() {
        cntDate=System.nanoTime();
    }

    public Vacancy(String title, String link, String description, String pubDate) {
        this.title=title;
        this.link=link;
        this.description=description;
        this.pubDate=pubDate;
        cntDate=System.nanoTime();
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() { return description; }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() { return link; }
    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() { return pubDate; }
    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public long getCntDate() {
        return cntDate;
    }

    public static List<Vacancy> parseVacancies(String feed) {
        List<Vacancy> set=new ArrayList<Vacancy>();
        String[] vacancies=feed.split("<item>");

        String[] temp;

        for(int i=1;i<vacancies.length;i++) {
            Vacancy v = new Vacancy();
            //System.out.println(i);
            temp=vacancies[i].split("</title>");
            temp[0]=temp[0].replace("<title>","");
            v.setTitle(temp[0]);
            //System.out.println(i+" TITLE "+temp[0]);
            temp=temp[1].split("</link>");
            temp[0]=temp[0].replace("<link>","");
            v.setLink(temp[0]);
            //System.out.println("LINK "+temp[0]);
            temp=temp[1].split("</description>");
            temp[0]=temp[0].replace("<description>","");
            if (temp[0].length()>4096)
                temp[0]=temp[0].substring(0,4095);
            v.setDescription(temp[0]);
            //System.out.println("DESC "+temp[0]);
            temp=temp[1].split("</pubDate>");
            temp[0]=temp[0].replace("<pubDate>","");
            v.setPubDate(temp[0]);
            //System.out.println("PDATE "+temp[0]);
            //System.out.println(v.getCntDate());
            //System.out.println();
            v.setId(v.generateId());
            set.add(v);
            //System.out.println(v.toString());
        }
        return set;
    }

    public static void storeVacancies(Path path, Set<Vacancy> set) {
        //ObjectOutputStream out = new ObjectOutputStream(new FileWriter(path));
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append("TITLE "+title+"\r\n");
        sb.append("LINK " + link + "\r\n");
        sb.append("DESC " + description + "\r\n");
        sb.append("PDATE " + pubDate + "\r\n");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vacancy v = (Vacancy)o;

        if (!this.title.equals(v.title)) return false;
        if (!this.link.equals(v.link)) return false;
        if (!this.description.equals(v.description)) return false;
        if (!this.pubDate.equals(v.pubDate)) return false;

        return true;
    }

    private long generateId() {
        return id++;
    }
}
