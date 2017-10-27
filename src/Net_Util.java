import java.io.*;
import java.net.*;
import java.util.*;

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


  /**
    *
    * In order to prevent memory leak, the user of this method is responsible
    * for closing the Socket it gets.
    *
    * NOTE: this does not actually create a thread for it.  It simply listnes
    * One time and then returns.
    *
  **/
  public static Socket welcomeClient(int portNumber){
    ServerSocket welcome;
    Socket connectionSocket;
    try{
		    welcome = new ServerSocket(portNumber);

		    connectionSocket = welcome.accept();

        System.out.println("Connection Established With "+connectionSocket.getInetAddress().toString());

        welcome.close();

    }
    catch(Exception e){
      e.printStackTrace();
      return null;
    }
    return connectionSocket;
  }

  //TODO: Edit this to actually list commands.
  public static String promptInput() {//return a string asking the user to enter commands and list the commands
    String out="Please Enter a Command:\n> ";
    return out;
  }

  public static BufferedReader getReader(Socket readSocket){
    BufferedReader b;
    try{
      b=new BufferedReader(new InputStreamReader(readSocket.getInputStream()));
    }catch(Exception e){
      e.printStackTrace();
      return null;
    }
    return b;
  }


  /**
  * This is the start of the Send Functions.  This is an overloaded method
  * that takes every type we may need and sends it to the server without worry.
  **/
  public static void send(Socket s, int i){
    sendToServer("INT",i+"",s);
  }
  /*
   *  This is adaptable to any type.
   *  I just didnt feel like doing all types of arrays.
   */
  public static void send(Socket s, int[] i){
    String out="";
    for(int c:i)
      out+=c+"|";
    out=out.substring(0,out.length()-1);
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
    out=out.substring(0,out.length()-1);
    sendToServer("STRINGARR",out,s);
  }
  /**
  * This is the end of the Send block.
  * TODO: expand upon this overloaded method to add functionality.
  **/




  public static int recInt(Socket inSocket)throws IOException{
    String type="INT";
    String[] tokens;

      //This does the thing below.
    BufferedReader in=getReader(inSocket);
      // BufferedReader in=new BufferedReader(new InputStreamReader(inSocket.getInputStream()));

      tokens=in.readLine().split("::");
      if(!type.equals(tokens[0])){

        //return false;
      }
      String message=tokens[1];


      return 0;
  }








  /**
  *
  * Type and Message are separated by ::
  * Arrays are separated by |
  * type is the same as sendToServer
  *
  **/
  private static boolean recieveFromClient(String type,Socket inSocket)throws IOException{

    String inString="";
    String[] tokens;
    String message;
    BufferedReader in= getReader(inSocket);
    inString=in.readLine();
    tokens=inString.split("::");
    if(!type.equals(tokens[0])){
      return false;
    }
    message=tokens[1];

    switch(tokens[0]){
      case "INT":
        Integer.parseInt(message);
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
    return false;
  }




  /**
    *
    *Type Inputs are as follows:
    *INT  INTARR  BOOL  OBJ DOUBLE  STRING  STRINGARR
    *
    **/

  private static void sendToServer(String type,String message,Socket s){
    try{
      DataOutputStream out = new DataOutputStream(s.getOutputStream());
      out.writeBytes(type+"::"+message);
    }catch(Exception e){
      e.printStackTrace();
    }



    // switch(type){
    //   case "INT":
    //     out.writeBytes(message);
    //     break;
    //   case "INTARR":
    //     break;
    //   case "BOOL":
    //     break;
    //   case "OBJ":
    //     break;
    //   case "DOUBLE":
    //     break;
    //   case "STRING":
    //     break;
    //   case "STRINGARR":
    //     break;



    }

  }
