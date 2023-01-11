import com.google.gson.Gson;
import org.json.simple.JSONObject;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8989);) {
            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), true);) {

                    Gson gson = new Gson();
                    String infoForServer = in.readLine();
                    ClientInfo infoFromClient = gson.fromJson(infoForServer, ClientInfo.class);
                    System.out.println(infoFromClient);

                    File file = new File("./././categories.tsv");
                    ArrayList<String[]> data = tsvReader(file);

                    JSONObject obj = new JSONObject();

                    data.forEach(array -> {
                        if (array[0].equals(infoFromClient.getTitle())) {
                            System.out.println(array[0]);
                            System.out.println(array[1]);
                        }
                    });


                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }

    public static ArrayList<String[]> tsvReader(File file) {
        ArrayList<String[]> data = new ArrayList<>();
        try (BufferedReader TSVReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = TSVReader.readLine()) != null) {
                String[] lineItems = line.split("\t");
                data.add(lineItems);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }


}
