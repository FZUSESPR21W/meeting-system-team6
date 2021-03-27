class Message {
    int user_id;
    int sub_id;
    String users;
    Message(int user_id,int sub_id,String users) {
        this.sub_id=sub_id;
        this.user_id=user_id;
        this.users=users;
    }

    public int getSub_id() {
        return sub_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setSub_id(int sub_id) {
        this.sub_id = sub_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public void handleMsg(int id) {
        String temp="";
        String[]ss = users.split(",");
        for(int i=0;i<ss.length;i++)
        {
            if(!Integer.valueOf(id).toString().equals(ss[i])) {
                temp += ss[i];
                temp+=",";
            }
        }
        temp=temp.substring(0,temp.length()-1);
        users=temp;
    }
}
