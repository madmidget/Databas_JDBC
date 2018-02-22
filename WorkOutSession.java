import java.sql.Timestamp;
import java.util.List;

public class WorkOutSession {

    private final int id;
    private final String activity;
    private final Timestamp start;
    private final Timestamp end;

    public WorkOutSession(int id, String activity, Timestamp start, Timestamp end) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.activity = activity;
    }

    public int getId() {
        return id;
    }

    public Timestamp getStart() {
        return start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public String getActivity() {
        return activity;
    }

    @Override
    public String toString() {
        return "WorkOutSession{" +
                "id=" + id +
                ", activity='" + activity + '\'' +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
