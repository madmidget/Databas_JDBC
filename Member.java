import java.util.ArrayList;
import java.util.List;

public class Member {

    private int id;
    private static String forename;
    private static String surname;
    private List<WorkOutSession> enrolled = new ArrayList<>();
    private List<WorkOutSession>attendance = new ArrayList<>();

    public Member(int id, String forename, String surname, List<WorkOutSession> enrolled, List<WorkOutSession> attendance) {
        this.id = id;
        this.forename = forename;
        this.surname = surname;
        this.enrolled = enrolled;
        this.attendance = attendance;
    }

    public Member(String forename) {
        this.forename = forename;

    }

    public static String getForename() {
        return forename;
    }

    public static void setForename(String forename) {
        Member.forename = forename;
    }

    public static String getSurname() {
        return surname;
    }

    public static void setSurname(String surname) {
        Member.surname = surname;
    }

    public List<WorkOutSession> getEnrolled() {
        return enrolled;
    }

    public void setEnrolled(List<WorkOutSession> enrolled) {
        this.enrolled = enrolled;
    }

    public List<WorkOutSession> getAttendance() {
        return attendance;
    }

    public void setAttendance(List<WorkOutSession> attendance) {
        this.attendance = attendance;
    }
}
