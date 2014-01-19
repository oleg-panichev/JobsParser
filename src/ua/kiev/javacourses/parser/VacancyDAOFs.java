package ua.kiev.javacourses.parser;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by oleg on 19.01.14.
 */
public class VacancyDAOFs implements VacancyDAO {
    File file;
    List<Vacancy> v = new ArrayList<Vacancy>();

    public VacancyDAOFs(File file) {
        this.file=file;
    }

    public void loadVacancies(File file) {
        try {
            ObjectInputStream in=new ObjectInputStream(new FileInputStream(file));
            v=(ArrayList<Vacancy>)in.readObject();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Vacancy getVacancy(int id) {
        return null;
    }

    @Override
    public List<Vacancy> searchVacanciesTitleKey(String key) {
        return null;
    }

    @Override
    public List<Vacancy> getVacancies() {

        return null;
    }

    @Override
    public void addVacancy(Vacancy v) {

    }

    @Override
    public void updateVacancy(int id, Vacancy newVac) {

    }

    @Override
    public void updateVacancy(Vacancy oldVac, Vacancy newVac) {

    }

    @Override
    public void addAllVacancies(List<Vacancy> vList) {

    }

    @Override
    public void removeVacancy(int id) {

    }

    @Override
    public void removeVacancy(Vacancy v) {

    }

    //@Override
    public void storeVacancies(File f) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
            out.writeObject(v);
            out.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
