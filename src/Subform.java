import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Subform {
    int id;
    String title;
    Date date;
    List<Integer> user_list;
    Subform(int id,String title,Date date,List user_list) {
        this.id=id;
        this.title=title;
        this.date=date;
        this.user_list=user_list;
    }
    Subform() {
        this.user_list=new ArrayList<Integer>();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public List<Integer> getUser_list() {
        return user_list;
    }

    public Date getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUser_list(List<Integer> user_list) {
        this.user_list = user_list;
    }
}
