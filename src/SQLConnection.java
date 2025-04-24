/**
 * @author Richard Stansbury, modified by Joshua Radjavitch
 *
 * This file provides some simple demonstration of SQL Connections and Integrations with Java.
 *
 * Operations to be performed include:
 *   SELECT
 */


import java.sql.*;
 import java.util.Scanner;

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

     public void printJobByAttribute(String attribute) {
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

     public void printSpecificJobDescription(String jobTitle, String company) {
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

     public void printSpecificCompanyDescription(String company) {
        try {
 
            //Define the query as a string
            String query = "SELECT Description FROM Jobs WHERE companyName='" + company + "'";

            Statement stmt = sql.createStatement();
            ResultSet results = stmt.executeQuery(query);
            if (results.next()) {
                System.out.println("\nDescription: " + results.getString("description"));
            } else {
                System.out.println("\nCompany (" + company + ") not found.");
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage()); //Handle exceptions
        }
     }

     public void printJobApplications(String title, String company)
     {
         try {
 
             //Define the query as a string
             String query = "SELECT * FROM JobApplications WHERE jobTitle='" + title + "' AND company='" + company + "'";
 
             //Request a Statement object from SQL class
             Statement stmt = sql.createStatement();
 
             //Execute the query
             ResultSet results = stmt.executeQuery(query);
             System.out.println("Applicant Username, Application Date\n");
             //Print each record
             while (results.next()) {
                 System.out.printf("%s, %s\n",
                         results.getString("applicantUsername"),
                         results.getString("appDate"));
             }

         }
         catch(SQLException e) {
             System.out.println(e.getMessage()); //Handle exceptions
         }
     }

     public void addJob() {
         try {
             Scanner in = new Scanner(System.in);
             System.out.println("\nWhat is the title of the job?\n");
             String title = in.nextLine();

             System.out.println("\nWhat is the company name?\n");
             String company = in.nextLine();

             System.out.println("\nWhat is the required level of education?\n");
             String education = in.nextLine();

             System.out.println("\nWhat is the department?\n");
             String department = in.nextLine();

             System.out.println("\nHow many positions are available?\n");
             int numPositions = in.nextInt();
             in.nextLine();

             System.out.println("\nWhat is the wage? (Format: X.XX)\n");
             float wage = in.nextFloat();
             in.nextLine();

             System.out.println("\nWhat is the job description?\n");
             String description = in.nextLine();

             System.out.println("\nWhat is today's date? (Format: YYYY-MM-DD)\n");  
             String date = in.nextLine();


             String insertString = "INSERT INTO Jobs VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

             //Building prepared statement
             PreparedStatement insertStatement = sql.prepareStatement(insertString);
             insertStatement.setString(1, title);
             insertStatement.setString(2, company);
             insertStatement.setString(3, education);
             insertStatement.setString(4, department);
             insertStatement.setInt(5, numPositions);
             insertStatement.setFloat(6, wage);
             insertStatement.setString(7, description);
             insertStatement.setDate(8, Date.valueOf(date));
 
             //Execute the query and confirm that the new record was added.
             int rows = insertStatement.executeUpdate();

         }
         catch(SQLException e) {
             System.out.println(e.getMessage());
         }
     }

     public void updateJob(String title, String company, String attribute, String newAttribute) {
        try {
        //Query String
        String query = "UPDATE Jobs SET " + attribute + "=? WHERE title=? and companyName=?";

        //Prepared Statement
        PreparedStatement pstmt = sql.prepareStatement(query);
        pstmt.setString(1, newAttribute);
        pstmt.setString(2, title);
        pstmt.setString(3, company);
        

        //Execute the update
        pstmt.executeUpdate();

        } catch(SQLException e) {
            System.out.println(e.getMessage()); //Handles exception
        }
    }

    public void removeJob() {

        Scanner in = new Scanner(System.in);
        System.out.println("\nWhat is the title of the job you would like to remove?\n");
        String title = in.nextLine();
        System.out.println("What is the company of the job you would like to remove?");
        String companyName = in.nextLine();

    
        String deleteJobString = "DELETE FROM Jobs WHERE title = ? AND companyName = ?";

        //Declare prepared statement variables.
        PreparedStatement deleteJobStmt;

        try {

            //Start Transaction by Setting Auto Commit to False
            //  Note: we must re-enable auto-commit when we are done to restore the system to the default
            //  state.
            sql.setAutoCommit(false);

            //Perform the first delete by preparing and executing the statement
            deleteJobStmt = sql.prepareStatement(deleteJobString);
            deleteJobStmt.setString(1, title);
            deleteJobStmt.setString(2, companyName);
            deleteJobStmt.executeUpdate();

            //Commit the change to make the change live to the database.
            sql.commit();

        } catch(SQLException e) {

            //If an exception occurs, we will roll back the transaction to avoid having an error state.

            try {
                System.out.println("Rolling Back Transaction.");

                //Performs the roll back.
                sql.rollback();
            } catch(SQLException e2) {
                System.out.println(e2.getMessage()); //Handle exception
            }

        } finally {

            //Finally is called after the try ends by reaching the end of its code or from a return statement.
            //  We include it here so that we can reenable autocommit and take it out of transaction mode.
            try {
                //Re-enable autocommit
                sql.setAutoCommit(true);
            } catch(SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void addJobApp() {
        try {
            Scanner in = new Scanner(System.in);
            System.out.println("\nWhat is the title of the job you would like to apply to?\n");
            String title = in.nextLine();

            System.out.println("\nWhat is the name of the company you would like to apply to?\n");
            String company = in.nextLine();

            System.out.println("\nWhat is your username?\n");
            String username = in.nextLine();

            System.out.println("\nWhat is today's date? (Format: YYYY-MM-DD)\n");  
            String date = in.nextLine();


            String insertString = "INSERT INTO JobApplications VALUES (?, ?, ?, ?)";

            //Building prepared statement
            PreparedStatement insertStatement = sql.prepareStatement(insertString);
            insertStatement.setString(1, title);
            insertStatement.setString(2, company);
            insertStatement.setString(3, username);
            insertStatement.setDate(4, Date.valueOf(date));

            //Execute the query and confirm that the new record was added.
            int rows = insertStatement.executeUpdate();
            if (rows != 1) {
                System.out.println("ALERT: Insertion failed.");
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeJobApp() {

        Scanner in = new Scanner(System.in);
        System.out.println("\nWhat is the title of the job you would like to withdraw your application from?\n");
        String title = in.nextLine();
        System.out.println("What is the name of the company you would like to withdraw your applicaton from?");
        String companyName = in.nextLine();
        System.out.println("\nWhat is your username?\n");
        String username = in.nextLine();
    
        String deleteJobString = "DELETE FROM JobApplications WHERE jobTitle = ? AND company = ? AND applicantUsername = ?";

        //Declare prepared statement variables.
        PreparedStatement deleteJobAppStmt;

        try {

            //Start Transaction by Setting Auto Commit to False
            //  Note: we must re-enable auto-commit when we are done to restore the system to the default
            //  state.
            sql.setAutoCommit(false);

            //Perform the first delete by preparing and executing the statement
            deleteJobAppStmt = sql.prepareStatement(deleteJobString);
            deleteJobAppStmt.setString(1, title);
            deleteJobAppStmt.setString(2, companyName);
            deleteJobAppStmt.setString(3, username);
            deleteJobAppStmt.executeUpdate();

            //Commit the change to make the change live to the database.
            sql.commit();

        } catch(SQLException e) {

            //If an exception occurs, we will roll back the transaction to avoid having an error state.

            try {
                System.out.println("Rolling Back Transaction.");

                //Performs the roll back.
                sql.rollback();
            } catch(SQLException e2) {
                System.out.println(e2.getMessage()); //Handle exception
            }

        } finally {

            //Finally is called after the try ends by reaching the end of its code or from a return statement.
            //  We include it here so that we can reenable autocommit and take it out of transaction mode.
            try {
                //Re-enable autocommit
                sql.setAutoCommit(true);
            } catch(SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}