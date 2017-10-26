import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/****************************************************
*
* This Class is intended to serve as a general utility class which should not
* be used in a non-static way.
*
****************************************************/
class Net_Util{
  public static final BufferedReader USER_INPUT=new BufferedReader(new InputStreamReader(System.in));


  public static Socket connectToServer(String serverIP,int portNumber){
    try {
      return new Socket(serverIP,portNumber);
    } catch (UnknownHostException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  //TODO: Edit this to actually list commands.
  public static String promptInput() {//return a string asking the user to enter commands and list the commands
    String out="Please Enter a Command:\n> ";
    return out;
  }



  /**
  * This is the start of the Send Functions.  This is an overloaded method
  * that takes every type we may need and sends it to the server without worry.
  **/
  public static void send(Socket s, int i){
    sendToServer("INT",i+"",s);
  }
  /*
   *  This is adaptable to any type.  I just didnt feel like doing all types of arrays.
   */
  public static void send(Socket s, int[] i){
    String out="";
    for(int c:i)
      out+=c+"|";
    out=out.substring(0,out.length-1);
    sendToServer("INTARR",out,s);
  }

  public static void send(Socket s, boolean b){
    String bool=(b) ? "True":"False";
    sendToServer("BOOL",bool,s);
  }
  public static void send(Socket s, Object o){
    sendToServer("OBJ",o.toString(),s);
  }
  public static void send(Socket s, double d){
    sendToServer("DOUBLE",d+"",s);
  }
  public static void send(Socket s, String str){
    sendToServer("STRING",str,s);
  }
  public static void send(Socket s, String[] starr){
    String out="";
    for(String str:starr)
      out+=str+"|";
    out=out.substring(0,out.length-1);
    sendToServer("STRINGARR",out,s);
  }
  /**
  * This is the end of the Send block.
  * TODO: expand upon this overloaded method to add functionality.
  **/







  private static void sendToServer(String type,String message,Socket s){
    
    switch(type){
      case "INT":
        break;
      case "INTARR":
        break;
      case "BOOL":
        break;
      case "OBJ":
        break;
      case "DOUBLE":
        break;
      case "STRING":
        break;
      case "STRINGARR":
        break;



    }

  }




}
