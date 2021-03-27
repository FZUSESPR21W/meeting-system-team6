import java.util.List;

public class MsgHandlerUtil {

    public static String handleMsg(String msg,int id){
        String idStr = Integer.valueOf(id).toString();
        String [] messages = msg.split(",");
        int index = -1;
        //拆分，记录下消息的id
        for (int i=0;i<messages.length;i++){
            if (idStr.equals(messages[i])){
                index = i;
            }
        }
        //合并消息
        StringBuilder builder = new StringBuilder();
        for (int i=0;i<messages.length;i++){
            if (i!=index){
                builder.append(messages[i]);
                builder.append(',');
            }
        }
        builder.deleteCharAt(builder.length()-1);
        return builder.toString();
    }


    public static void main(String[] args) {
        String s="100,2,3,4,5,6,7,8,9,10,11,12,13,14,15";
        System.out.println(MsgHandlerUtil.handleMsg(s,5));
        System.out.println(MsgHandlerUtil.handleMsg(s,6));
        System.out.println(MsgHandlerUtil.handleMsg(s,4));
        System.out.println(MsgHandlerUtil.handleMsg(s,15));
        System.out.println(MsgHandlerUtil.handleMsg(s,1));
        System.out.println(MsgHandlerUtil.handleMsg(s,100));
        System.out.println(MsgHandlerUtil.handleMsg(s,-3));
    }
}
