import java.util.Scanner;
/**
 * @author Joshua Radjavitch
 *
 * This file provides the main app functions of the Job Board.
 *
 */

public class App {
    static String url = "jdbc:mariadb://localhost:3306/JobBoard?useSSL=false";
    static String user = "radjavij";
    static String pswd = "jo$hRad9";
    static SQLConnection connect = new SQLConnection(url, user, pswd);
    public static void main(String[] args) {
        while (true) { 
        System.out.println("\nWelcome to the Job Board v0.1. What would you like to do?\n");
        System.out.println("1. Print information for all jobs\n");
        System.out.println("2. Search jobs based on various filters\n");
        System.out.println("3. Show a specific job description\n");
        System.out.println("4. Print information for all companies\n");
        System.out.println("5. Show a specific company description\n");
        System.out.println("6. Find applications for a job\n");
        System.out.println("7. Post a new job\n");
        System.out.println("8. Modify a current job posting\n");
        System.out.println("9. Remove a current job posting\n");
        System.out.println("10. Apply to a job\n");
        System.out.println("11. Withdraw a job application\n");
        System.out.println("12. Exit the Program\n\n");
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
                    printJobDescription();
                    break;
                case 4:
                    connect.printCompanies();
                    break;
                case 5:
                    printCompanyDescription();
                    break;
                case 6:
                    printSpecificJobApps();
                    break;
                case 7:
                    connect.addJob();
                    break;
                case 8:
                    modifyJob();
                    break;
                case 9:
                    connect.removeJob();
                    break;
                case 10:
                    connect.addJobApp();
                    break;
                case 11:
                    connect.removeJobApp();
                case 12:
                    return;
            }
        }
        
    }

    public static void secondMenu() {
        System.out.println("\nSearch jobs based on various filters\n\n");
        System.out.println("Which attribute do you want to search by?\n");
        System.out.println("1. Job Title\n");
        System.out.println("2. Company Name\n");
        System.out.println("3. Required Level of Education\n");
        System.out.println("4. Department\n");
        System.out.println("5. Available Positions\n");
        System.out.println("6. Wage\n");
        System.out.println("7. Post Date\n\n");
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
                connect.printJobByAttribute(query); 
                break;
            case 2:
                System.out.println("\nWhat Company?\n");
                String company = in.nextLine();
                query = "companyName='" + company + "'";
                System.out.println("\nQuery Results:\n");
                connect.printJobByAttribute(query); 
                break;
            case 3:
                System.out.println("\nWhat Required Level of Education?\n");
                String education = in.nextLine();
                query = "education='" + education + "'";
                System.out.println("\nQuery Results:\n");
                connect.printJobByAttribute(query); 
                break;
            case 4:
                System.out.println("\nWhat Department?\n");
                String department = in.nextLine();
                query = "department='" + department + "'";
                System.out.println("\nQuery Results:\n");
                connect.printJobByAttribute(query); 
                break;
            case 5:
                System.out.println("\nHow many available positions?\n");
                int numAvailable = in.nextInt();
                in.nextLine();
                query = "numAvailable=" + numAvailable;
                System.out.println("\nQuery Results:\n");
                connect.printJobByAttribute(query); 
                break;
            case 6:
                System.out.println("\nWhat wage? (Format: x.xx)\n");
                double wage = in.nextDouble();
                in.nextLine();
                query = "wage=" + wage;
                System.out.println("\nQuery Results:\n");
                connect.printJobByAttribute(query); 
                break;
            case 7:
                System.out.println("\nWhat Post Date? (Format: YYYY-MM-DD)\n");
                String postDate = in.nextLine();
                query = "postDate='" + postDate + "'";
                System.out.println("\nQuery Results:\n");
                connect.printJobByAttribute(query); 
                break;
        }
    }

    public static void printJobDescription() {
        Scanner in = new Scanner(System.in);
        System.out.println("\nWhat job title?\n");
        String title = in.nextLine();
        System.out.println("\nWhat Company?\n");
        String company = in.nextLine();
        connect.printSpecificJobDescription(title, company);
    }

    public static void printCompanyDescription() {
        Scanner in = new Scanner(System.in);
        System.out.println("\nWhat Company?\n");
        String company = in.nextLine();
        connect.printSpecificCompanyDescription(company);
    }

    public static void printSpecificJobApps() {
        Scanner in = new Scanner(System.in);
        System.out.println("\nWhat Job Title?\n");
        String title = in.nextLine();
        System.out.println("\nWhat Company?\n");
        String company = in.nextLine();
        connect.printJobApplications(title, company);
    }
}