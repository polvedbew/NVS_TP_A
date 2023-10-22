package com.norden.nvs_tp_a;


import android.os.Handler;
import android.os.Looper;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class UdpCon {

    abstract void newData(String data);

    private Handler hnd;
    private volatile boolean flag;
    public String ip="";
    UdpCon(String ip){
        this.ip=ip;
        mLog.print("new udp, ...");
        if(flag){
            mLog.print("Job exists, returning...");
            return;

        }
        hnd=new Handler(Looper.getMainLooper());
        flag=true;

    }

    public void stop(){
        flag=false;
    }

    public void startSocket(){

        try {
            String messageStr = "START";
            int server_port = 1818;
            InetAddress local = InetAddress.getByName(ip);
            int msg_length = messageStr.length();
            byte[] message = messageStr.getBytes();


            DatagramSocket s = new DatagramSocket();
            s.setSoTimeout(20000);

            DatagramPacket p = new DatagramPacket(message, msg_length, local, server_port);
            s.send(p);

            message = new byte[5000];
            p = new DatagramPacket(message,message.length );
            try {
                s.receive(p);
            }catch (Exception e){
                flag=false;
                mLog.print(e.toString());
                return;
            }
            final byte[] data =  p.getData();
            mLog.print("raw data: "+ new String(data));

            String str=null;
            try{
                str= new String(data);
                if(str.length()>2){
                    str=str.substring(1,str.length()-1);
                }
                mLog.print("Received string is:"+str);
            }catch(Exception e){
                flag=false;
                return;
            }
            if(str==null){
                flag=false;
                return;
            }
            final String tmp=str;
            hnd.post(()-> {
                    newData(tmp);
            });
            flag=false;
            s.close();

        }
        catch(Exception ex)
        {
//
        }
    }


    public void sendValues(final boolean isRing,final int tolerance,String ip,int[] template){
        try {
            StringBuilder messageStr = new StringBuilder("SET ");
            for(int a: template){
                String tmp=String.valueOf(a);
                while(tmp.length()<3){
                    tmp="0"+tmp;
                }
                messageStr.append(tmp);
            }
            int server_port = 1818;
            InetAddress local = InetAddress.getByName(ip);
            int msg_length = messageStr.length();
            byte[] message = messageStr.toString().getBytes();


            DatagramSocket s = new DatagramSocket();
            s.setSoTimeout(20000);

            DatagramPacket p = new DatagramPacket(message, msg_length, local, server_port);
            s.send(p);

            message = new byte[5000];
            p = new DatagramPacket(message,message.length );
            try {
                s.receive(p);
            }catch (Exception e){
                flag=false;
                mLog.print(e.toString());
                return;
            }
            final byte[] data =  p.getData();
            mLog.print("raw data: "+ new String(data));

            String str=null;
            try{
                str= new String(data);
                if(str.length()>2){
                    str=str.substring(1,str.length()-1);
                }
                mLog.print("Received string is:"+str);
            }catch(Exception e){
                flag=false;
                return;
            }
            if(str==null){
                flag=false;
                return;
            }
            final String tmp=str;
            hnd.post(()-> {
                newData(tmp);
            });
            flag=false;
            s.close();

        }

        catch(Exception ex)
        {
//
        }
    }

    public void readData(){

        try {
            String messageStr = "READ";
            int server_port = 1818;
            InetAddress local = InetAddress.getByName(ip);
            int msg_length = messageStr.length();
            byte[] message = messageStr.getBytes();


            DatagramSocket s = new DatagramSocket();
            s.setSoTimeout(20000);

            DatagramPacket p = new DatagramPacket(message, msg_length, local, server_port);
            s.send(p);

            message = new byte[5000];
            p = new DatagramPacket(message,message.length );
            try {
                s.receive(p);
            }catch (Exception e){
                flag=false;
                mLog.print(e.toString());
                return;
            }
            final byte[] data =  p.getData();
            mLog.print("raw data: "+ new String(data));

            String str=null;
            try{
                str= new String(data);
                if(str.length()>2){
                    str=str.substring(1,str.length()-1);
                }
                mLog.print("Received string is:"+str);
            }catch(Exception e){
                flag=false;
                return;
            }
            if(str==null){
                flag=false;
                return;
            }
            final String tmp=str;
            hnd.post(()-> {
                newData(tmp);
            });
            flag=false;
            s.close();

        }
        catch(Exception ex)
        {
//
        }
    }

}
