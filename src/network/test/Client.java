package network.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import static network.test.Server.*;
import static util.MyLogger.log;

public class Client {

    public static void main(String[] args) {
        log("클라이언트 시작");


        try(Socket socket =new Socket("localhost", PORT);
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());) {

            Scanner scanner = new Scanner(System.in);

            while (true) {

            }

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
