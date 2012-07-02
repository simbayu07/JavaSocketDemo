
package com.cloudsoft.demos.SocketDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketDemo
{
    private void work() throws IOException
    {
        ServerSocket server = new ServerSocket(9777);
        while (true)
        {
            Socket client = server.accept();
            System.out.println("client accept: " + client.getInetAddress().toString() + ":" + client.getPort());
            Server s = new Server(client);
            s.start();
        }
    }

    public static void main(String[] args) throws IOException
    {
        new SocketDemo().work();
    }

    private class Server extends Thread
    {
        private Socket client;

        Server(Socket client)
        {
            this.client = client;
        }

        @Override
        public void run()
        {
            super.run();
            try
            {
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter out = new PrintWriter(client.getOutputStream());
                while (true)
                {
                    String str = in.readLine();
                    System.out.println(str);
                    // out.println("has receive....");
                    out.flush();
                    if (str.equals("plz@^_^@end"))
                    {
                        break;
                    }
                }
                client.close();
            } catch (Exception e)
            {
                // TODO: handle exception
            }

        }
    }

}
