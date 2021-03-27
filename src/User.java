import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;
public class User {
    int id;
    String user_name;
    String password;
    String role;
    List<Integer> subform_list;
    User(int id,String user_name,String password, String role,List list) {
        this.id=id;
        this.user_name=user_name;
        this.password=password;
        this.role=role;
        this.subform_list=list;
    }
    User() {
        subform_list=new ArrayList<Integer>();
    }
    public int getId() {
        return id;
    }

    public List getSubform_list() {
        return subform_list;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSubform_list(List subform_list) {
        this.subform_list = subform_list;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    public void addSubform(int id) {
        this.subform_list.add(id);
    }
}

