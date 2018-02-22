import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Repository {


    private Properties p = new Properties();

    public Repository() throws IOException {

        try {
            p.load(new FileInputStream("/Users/joacim/IdeaProjects/TestGym/src/properties"));

            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int getMemberID_number(String id_number) throws SQLException {

        String query = "SELECT member.id from member where idNumber like ?";
        int id = 0;
        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("username"),
                p.getProperty("password"));

             PreparedStatement stm = con.prepareStatement(query);) {
            stm.setString(1, id_number);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                id = rs.getInt(1);
            }
            return id;
        }

    }


    public List<WorkOutSession> getSessionIdsByMemberID(int member) throws SQLException {

        String query = "SELECT workOutSession.id, activity.activity, workOutSession.startTime,workOutSession.endTime from member " +
                "inner join enrolled on member.id = enrolled.memberId " +
                "inner join workOutSession on enrolled.workOutSessionId = workOutSession.id " +
                "inner join activity on workOutSession.activityId = activity.id "  +
                "WHERE member.id = ?";
        //" and workOutSession.startTime > NOW() AND workOutSession.startTime <= NOW() +interval  1 day;";

        List<WorkOutSession> sessions = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("username"),
                p.getProperty("password"));
             PreparedStatement stm = con.prepareStatement(query)) {

            stm.setInt(1, member);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                sessions.add(new WorkOutSession(
                        rs.getInt("id"),
                        rs.getString("activity"),
                        rs.getTimestamp("startTime"),
                        rs.getTimestamp("endTime")
                ));
            }
        }
        return sessions;
    }

    public void callDB(int memberID, int workOutSessionID) throws SQLException {
        try (Connection myConn = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("username"),
                p.getProperty("password"));

             PreparedStatement callStatement = myConn.prepareCall("{call member_attendance(?, ?)}")) {

            // Set the parameters
            callStatement.setInt(1, memberID);
            callStatement.setInt(2, workOutSessionID);
            callStatement.execute(); // Retunerar  ifall en rad har lagts till = attendat redan
        }
    }
}
