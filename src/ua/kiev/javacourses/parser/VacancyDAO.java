package ua.kiev.javacourses.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleg on 19.01.14.
 */
public interface VacancyDAO {
    public Vacancy getVacancy(int id);
    public List<Vacancy> searchVacanciesTitleKey(String key);
    public List<Vacancy> getVacancies();
    public void addVacancy(Vacancy v);
    public void updateVacancy(int id, Vacancy newVac);
    public void updateVacancy(Vacancy oldVac, Vacancy newVac);
    public void addAllVacancies(List<Vacancy> vList);
    public void removeVacancy(int id);
    public void removeVacancy(Vacancy v);
    //public void storeVacancies(File f);
}
