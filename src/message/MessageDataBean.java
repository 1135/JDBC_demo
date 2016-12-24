package message;

/**
 * Created by Administrator on 2016/12/24.
 */
public class MessageDataBean {
    private String s1,s2,s3,s4;
    //setter方法
    public void setid(String s1){ this.s1=s1;}
    public void setname(String s2){ this.s2=s2;}
    public void setarea(String s3){ this.s3=s3;}
    public void setmessage(String s4){ this.s4=s4;}

    //getter方法
    public String getS1(){return this.s1;}
    public String getS2(){return this.s2;}
    public String getS3(){return this.s3;}
    public String getS4(){return this.s4;}
}
