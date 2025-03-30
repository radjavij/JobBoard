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
             System.out.println("Title\t Company Name \t Education \t Department \t Open Positions \t Wage \t Description \t Post Date \t\n");
             //Print each record
             while (results.next()) {
                 System.out.printf("%s\t %s\t %s\t %s\t %d\t %.2f\t %s\t %s\t\n",
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
 
 
    }