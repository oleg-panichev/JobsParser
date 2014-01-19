package ua.kiev.javacourses.parser;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Oleg
 * Date: 22.12.13
 * Time: 12:11
 * To change this template use File | Settings | File Templates.
 */
public class Starter {
    public static void main(String[] args) throws IOException {
        //URL url=new URL("http://192.168.7.224/feeds.txt");
        URL url=new URL("http://jobs.dou.ua/vacancies/feeds/?search=Java");
        //System.out.println(NetworkController.getDocument(url));
        String page=NetworkController.getDocument(url);
        List<Vacancy> v = new ArrayList<Vacancy>();
        v = Vacancy.parseVacancies(page);
        //for(Vacancy vav:v)
        //    System.out.println(vav.toString());


        //Connection con = null;
        try {
            Connection con = DriverManager.getConnection("jdbc:derby:/home/oleg/jobsdb");
            //con.setAutoCommit(false);
            VacancyDAO vDao=new VacancyDAODb(con);
            //vDao.addAllVacancies(v);

            int id=126;
            Vacancy tempV=vDao.getVacancy(id);
            if (tempV!=null)
                System.out.println(tempV.toString());
            else
                System.out.println("No data with id = "+id);

            List<Vacancy> tList=new ArrayList<Vacancy>();
            tList=vDao.searchVacanciesTitleKey("Ужгород");
            for(Vacancy vac:tList)
                System.out.println(vac.toString());

            vDao.removeVacancy(135);

            vDao.removeVacancy(vDao.getVacancy(146));

            //con.commit();
            //con.rollback();
            System.out.println("Hello!");
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
