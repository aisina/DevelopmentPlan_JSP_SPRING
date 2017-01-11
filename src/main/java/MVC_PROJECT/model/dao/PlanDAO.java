package MVC_PROJECT.model.dao;

import MVC_PROJECT.controller.db.DatabaseConnection;
import MVC_PROJECT.model.Plan;
import MVC_PROJECT.model.exceptions.PlanDAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by innopolis on 27.12.2016.
 */
@Repository
public class PlanDAO extends AbstractPlanDAO<Plan> {

    private static  final Logger LOGGER = LoggerFactory.getLogger(PlanDAO.class);
    private  List<Plan> plansList = new ArrayList<>();

    @Override
    public List<Plan> getAll() throws PlanDAOException {
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
            return this.plansList;

        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
            throw new PlanDAOException();
        }

    }

    public List<String> getPlanYear() throws PlanDAOException{
        Connection connection = DatabaseConnection.getConnection();
        List<String> years = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT DISTINCT YEAR FROM PLAN ORDER BY YEAR DESC");
            while(rs.next()){
                years.add(rs.getString("year"));
            }

            return years;

        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
            throw new PlanDAOException();
        }

    }

    @Override
    public void deleteById(int id) throws PlanDAOException{

    }

    @Override
    public boolean add(Plan plan) throws UnsupportedEncodingException, PlanDAOException{
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
            throw new PlanDAOException();
        }

    }


    @Override
    public List<Plan> getPlanByYear(String year) throws PlanDAOException{
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
            return plans;

        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
            throw new PlanDAOException();
        }

    }

    @Override
    public List<Plan> getPlanByEmplId(String id) throws PlanDAOException{
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
            return plansList;

        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
            throw new PlanDAOException();
        }


    }
}
