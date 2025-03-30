import java.util.Scanner;
/**
 * @author Joshua Radjavitch
 *
 * This file provides the main app functions of the Job Board.
 *
 */

public class App {
    static String url = "jdbc:mariadb://localhost:3306/jobboard?useSSL=false";
    static String user = "root";
    static String pswd = "jo$hRad9";
    static SQLConnection connect = new SQLConnection(url, user, pswd);
    public static void main(String[] args) {
        while (true) { 
        System.out.println("\nWelcome to the Job Board v0.1. What would you like to do?\n");
        System.out.println("1. Print information for all jobs\n");
        System.out.println("2. Search jobs based on various filters\n");
        System.out.println("3. Exit the Program\n\n");
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        

            switch (choice) {
                case 1:
                    System.out.println("\nPrint job information\n\n");
                    connect.printJobs();
                    break;
                case 2:
                    secondMenu();
                    break;
                case 3:
                    return;
            }
        }
        
    }

    public static void secondMenu() {
        System.out.println("\nSearch jobs based on various filters\n\n");
        System.out.println("Which attribute do you want to search by?\n");
        System.out.println("1. Job Title\n");
        System.out.println("2. Company Name\n\n");
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        in.nextLine();
        String query;
        switch (choice) {
            case 1:
                System.out.println("\nWhat Title?\n");
                String title = in.nextLine();
                query = "title='" + title + "'";
                System.out.println("\nQuery Results:\n");
                connect.printByAttribute(query); 
                break;
            case 2:
                System.out.println("\nWhat Company?\n");
                String company = in.nextLine();
                query = "companyName='" + company + "'";
                System.out.println("\nQuery Results:\n");
                connect.printByAttribute(query); 
                break;
                
        }
    }
}