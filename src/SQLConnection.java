/**
 * @author Richard Stansbury, modified by Joshua Radjavitch
 *
 * This file provides some simple demonstration of SQL Connections and Integrations with Java.
 *
 * Operations to be performed include:
 *   SELECT
 */


 import java.sql.*;

 public class SQLConnection {
 
     //  DECLARE CLASS VARIABLES
 
     Connection sql;
 
     /**
      * Default Constructor - creates an instance of the database connection
      *
      * @param url - URL for the MySQL database connection
      * @param user - user name for the MySQL database
      * @param pswd - password for MySQL Database.
      */
     public SQLConnection(String url, String user, String pswd) {
 
         /** Creates an instance of the database connection **/
         try {
            this.sql = DriverManager.getConnection(url, user, pswd);
         }
         catch (SQLException e){
             System.out.println(e.getMessage()); //Handle exceptions
         System.exit(0);
         }
     }
 
     //  Option 1: Print All Jobs

     public void printJobs()
     {
         try {
 
             //Define the query as a string
             String query = "SELECT * FROM Jobs";
 
             //Request a Statement object from SQL class
             Statement stmt = sql.createStatement();
 
             //Execute the query
             ResultSet results = stmt.executeQuery(query);
             System.out.println("Title, Company Name, Education, Department, Open Positions, Wage, Description, Post Date\n");
             //Print each record
             while (results.next()) {
                 System.out.printf("%s, %s, %s, %s, %d, %.2f, %s, %s\n",
                         results.getString("title"),
                         results.getString("companyName"),
                         results.getString("education"),
                         results.getString("department"),
                         results.getInt("numAvailable"),
                         results.getFloat("wage"),
                         results.getString("description"),
                         results.getDate("postDate").toString());
             }
         }
         catch(SQLException e) {
             System.out.println(e.getMessage()); //Handle exceptions
         }
     }

     public void printByAttribute(String attribute) {
        try {
            String query = "SELECT * FROM Jobs WHERE " + attribute;

            Statement stmt = sql.createStatement();

            ResultSet results = stmt.executeQuery(query);

            while (results.next()) {
                System.out.printf("%s, %s, %s, %s, %d, %.2f, %s, %s\n",
                        results.getString("title"),
                        results.getString("companyName"),
                        results.getString("education"),
                        results.getString("department"),
                        results.getInt("numAvailable"),
                        results.getFloat("wage"),
                        results.getString("description"),
                        results.getDate("postDate").toString());
            }
        } 
        catch (SQLException e) {
            System.out.println(e.getMessage()); //Handle exceptions
        }
     }

     public void printSpecificDescription(String jobTitle, String company) {
        try {
 
            //Define the query as a string
            String query = "SELECT Description FROM Jobs WHERE title='" + jobTitle + "' AND companyName='" + company + "'";

            Statement stmt = sql.createStatement();
            ResultSet results = stmt.executeQuery(query);
            if (results.next()) {
                System.out.println("\nDescription: " + results.getString("description"));
            } else {
                System.out.println("\nJob (" + jobTitle + ", " + company + ") not found.");
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage()); //Handle exceptions
        }
     }

     public void printCompanies()
     {
         try {
 
             //Define the query as a string
             String query = "SELECT * FROM Companies";
 
             //Request a Statement object from SQL class
             Statement stmt = sql.createStatement();
 
             //Execute the query
             ResultSet results = stmt.executeQuery(query);
             System.out.println("Company Name, Manager, Point of Contact\n");
             //Print each record
             while (results.next()) {
                 System.out.printf("%s, %s, %s\n",
                         results.getString("companyName"),
                         results.getString("manager"),
                         results.getString("pointOfContact"));
             }
         }
         catch(SQLException e) {
             System.out.println(e.getMessage()); //Handle exceptions
         }
     }
 
    }