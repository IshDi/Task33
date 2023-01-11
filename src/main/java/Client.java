import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket client = new Socket("127.0.0.1", 8989);
             BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
             PrintWriter out = new PrintWriter(client.getOutputStream(), true);) {

            Gson gson = new Gson();
            String json = gson.toJson(convertClientInfo());
            out.println(json);

        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ClientInfo convertClientInfo() {
        ClientInfo clientInfo = new ClientInfo();
        Scanner scanner = new Scanner(System.in);

        System.out.println("¬ведите название покупки:");
        String product = scanner.next();
        clientInfo.setTitle(product);

        System.out.println("¬ведите сумму покупки:");
        int sum = scanner.nextInt();
        clientInfo.setSum(sum);

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        clientInfo.setDate(formatter.format(date));

        return clientInfo;
    }
}
