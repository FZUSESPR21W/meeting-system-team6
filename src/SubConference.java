import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class SubConference {
    private int chairman_id;
    private int secretary_id;
    private Date date;
    private List<User> participants;

    public SubConference(int chairman_id, int secretary_id, Date date) {
        this.chairman_id = chairman_id;
        this.secretary_id = secretary_id;
        this.date = date;
        this.participants = new ArrayList<>();
    }

    public int getChairman_id() {
        return chairman_id;
    }

    public void setChairman_id(int chairman_id) {
        this.chairman_id = chairman_id;
    }

    public int getSecretary_id() {
        return secretary_id;
    }

    public void setSecretary_id(int secretary_id) {
        this.secretary_id = secretary_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public void addParticipant(User user) {
        participants.add(user);
    }
}
