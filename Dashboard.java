import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Dashboard {

    public void Dashboard() throws IOException, SQLException {
    }


    public void askForID_number() throws IOException, SQLException {

        Repository r = new Repository();
        Scanner sc = new Scanner(System.in);
        System.out.println("Skriv medlems personnummer: ");
        String input = sc.nextLine();

        int memberID = r.getMemberID_number(input);
        List<WorkOutSession> workOutSessions = r.getSessionIdsByMemberID(memberID);

        if (workOutSessions.isEmpty()) {
            System.out.println("You are not enrolled to any sessions.");
        } else {
            System.out.println("Välj workout session: ");

            for (int i = 0; i < workOutSessions.size(); i++) {
                System.out.println(i + 1 + ". " + workOutSessions.get(i));
            }


            int userChoice = sc.nextInt();
            WorkOutSession workOutSession = workOutSessions.get(userChoice - 1);
            System.out.println(workOutSession.getActivity());
            System.out.println(workOutSession.getStart());
            System.out.println(workOutSession.getEnd());
            int workOutSessionId = workOutSession.getId();

            try {
                r.callDB(memberID, workOutSessionId);
                System.out.println("Nu har du checkat in på passet");
            } catch (MySQLIntegrityConstraintViolationException e) {
                System.out.println("Medlem har redan checkat in.");
            } catch (SQLException e) {
                if (e.getMessage().contains("Not enrolled")) {
                    System.out.println("Not enrolled");
                } else {
                    e.printStackTrace();
                    System.out.println("Error!");
                }
            }
        }
    }

    public static void main(String[] arg) throws IOException, SQLException {

        Dashboard d = new Dashboard();
        d.askForID_number();
        //d.print();


    }


}
