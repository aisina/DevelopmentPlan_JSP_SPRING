package MVC_PROJECT.model.dao;

import MVC_PROJECT.controller.db.DatabaseConnection;
import MVC_PROJECT.model.Plan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by innopolis on 27.12.2016.
 */
public class PlanDAO implements myDAO<Plan> {

    private static  final Logger LOGGER = LoggerFactory.getLogger(PlanDAO.class);
    private  List<Plan> plansList = new ArrayList<>();

    @Override
    public List<Plan> getAll() {
        Connection connection = DatabaseConnection.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PLAN");
            while(rs.next()){
                Plan plan = new Plan();
                plan.setYear(rs.getString("year"));
                plan.setEmployeeName(rs.getString("empl_name"));
                plan.setEmployeePosition(rs.getString("position"));
                plan.setPlanType(rs.getString("plan_type"));
                plansList.add(plan);
            }

        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        return this.plansList;
    }

    public List<String> getPlanYear() {
        Connection connection = DatabaseConnection.getConnection();
        List<String> years = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT DISTINCT YEAR FROM PLAN ORDER BY YEAR DESC");
            while(rs.next()){
                years.add(rs.getString("year"));
            }

        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        return years;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public boolean add(Plan plan) throws UnsupportedEncodingException {
        String year = plan.getYear();
        String name = plan.getEmployeeName();
        String position = plan.getEmployeePosition();
        String planType = plan.getPlanType();

        Connection connection = DatabaseConnection.getConnection();
        try {
            Statement stmt = connection.createStatement();
            System.out.println(year + " " + name + " " + position + " " + planType);
            ResultSet rs = stmt.executeQuery("select id from employee where name='" + name + "' and position='" + position + "'");
            String id="";
            while(rs.next()){
                id = (String) rs.getString("id");
            }
            stmt.executeUpdate("INSERT INTO PLAN VALUES('" + year + "', '" + name + "', '" + position + "', '" + planType + "', '" + id + "')");

            return true;

        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
            return false;
        }

    }


    public List<Plan> getPlanByYear(String year) {
        Connection connection = DatabaseConnection.getConnection();
        List<Plan> plans = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PLAN WHERE YEAR = '" + year + "'");
            while(rs.next()){
                Plan plan = new Plan();
                //plan.setYear(year);
                plan.setYear(rs.getString("year"));
                plan.setEmployeeName(rs.getString("empl_name"));
                plan.setEmployeePosition(rs.getString("position"));
                plan.setPlanType(rs.getString("plan_type"));
                plans.add(plan);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plans;
    }

    public List<Plan> getPlanByEmplId(String id) {
        List<Plan> plansList = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PLAN WHERE EMPLOYEE_ID = '" + id + "'");
            while(rs.next()){
                Plan plan = new Plan();
                plan.setYear(rs.getString("year"));
                plan.setEmployeeName(rs.getString("empl_name"));
                plan.setEmployeePosition(rs.getString("position"));
                plan.setPlanType(rs.getString("plan_type"));
                plansList.add(plan);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return plansList;
    }
}
