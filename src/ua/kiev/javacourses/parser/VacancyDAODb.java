package ua.kiev.javacourses.parser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleg on 19.01.14.
 */
public class VacancyDAODb implements VacancyDAO {
    private Connection con;
    private PreparedStatement stmt;

    public VacancyDAODb(Connection con) throws SQLException {
        this.con = con;
    }

    @Override
    public Vacancy getVacancy(int id) {
        Vacancy v = new Vacancy();
        try {
            stmt=con.prepareStatement("SELECT title,link,descr,pdate FROM vacancy WHERE id=?");
            stmt.setLong(1,id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                v.setTitle(rs.getString("title"));
                v.setLink(rs.getString("link"));
                v.setDescription(rs.getString("descr"));
                v.setPubDate(rs.getString("pdate"));
            } else {
                v=null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return v;
    }

    @Override
    public List<Vacancy> searchVacanciesTitleKey(String key) {
        List<Vacancy> v = new ArrayList<Vacancy>();
        try {
            stmt=con.prepareStatement("SELECT title,link,descr,pdate FROM vacancy WHERE title LIKE '%" + key + "%'");
            //stmt.setString(1,key);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Vacancy tempV=new Vacancy();
                tempV.setTitle(rs.getString("title"));
                tempV.setLink(rs.getString("link"));
                tempV.setDescription(rs.getString("descr"));
                tempV.setPubDate(rs.getString("pdate"));
                v.add(tempV);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return v;
    }

    @Override
    public List<Vacancy> getVacancies() {
        List<Vacancy> v = new ArrayList<Vacancy>();
        try {
            stmt=con.prepareStatement("SELECT title,link,descr,pdate FROM vacancy");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Vacancy tempV=new Vacancy();
                tempV.setTitle(rs.getString("title"));
                tempV.setLink(rs.getString("link"));
                tempV.setDescription(rs.getString("descr"));
                tempV.setPubDate(rs.getString("pdate"));
                v.add(tempV);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addVacancy(Vacancy v) {
        try {
            stmt=con.prepareStatement("INSERT INTO vacancy (title,link,descr,pdate)" +
                    "VALUES (?,?,?,?)");
            stmt.setString(1,v.getTitle());
            stmt.setString(2,v.getLink());
            stmt.setString(3,v.getDescription());
            stmt.setString(4,v.getPubDate());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateVacancy(int id, Vacancy newVac) {
        try {
            stmt=con.prepareStatement("UPDATE vacancy SET title=? AND link=? AND decr=? AND " +
                    "pdate=? WHERE id=?");
            stmt.setString(1,newVac.getTitle());
            stmt.setString(2,newVac.getLink());
            stmt.setString(3,newVac.getDescription());
            stmt.setString(4,newVac.getPubDate());
            stmt.setInt(5,id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateVacancy(Vacancy oldVac, Vacancy newVac) {
        try {
            stmt=con.prepareStatement("UPDATE vacancy SET title=? AND link=? AND decr=? AND " +
                    "pdate=? WHERE title=? AND link=? AND decr=? AND pdate=?");
            stmt.setString(1,newVac.getTitle());
            stmt.setString(2,newVac.getLink());
            stmt.setString(3,newVac.getDescription());
            stmt.setString(4,newVac.getPubDate());
            stmt.setString(5,oldVac.getTitle());
            stmt.setString(6,oldVac.getLink());
            stmt.setString(7,oldVac.getDescription());
            stmt.setString(8,oldVac.getPubDate());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addAllVacancies(List<Vacancy> vList) {
        try {
            stmt=con.prepareStatement("INSERT INTO vacancy (title,link,descr,pdate) " +
                    "VALUES (?,?,?,?)");
            for(Vacancy v:vList) {
                stmt.setString(1,v.getTitle());
                stmt.setString(2,v.getLink());
                stmt.setString(3,v.getDescription());
                stmt.setString(4,v.getPubDate());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeVacancy(int id) {
        try {
            stmt=con.prepareStatement("DELETE FROM vacancy WHERE id=?");
            stmt.setInt(1,id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeVacancy(Vacancy v) {
        try {
            stmt=con.prepareStatement("DELETE FROM vacancy WHERE title=? AND " +
                    "link=? AND descr=? AND pdate=?");
            stmt.setString(1, v.getTitle());
            stmt.setString(2,v.getLink());
            stmt.setString(3,v.getDescription());
            stmt.setString(4,v.getPubDate());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
