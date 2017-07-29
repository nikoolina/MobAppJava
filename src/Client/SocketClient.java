
package Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author tin 
 */
public class SocketClient {
       
    public void run() throws Exception {
        String a;
        Socket SOCK = new Socket("127.0.1.1", 8896);
        do {
            PrintStream PS = new PrintStream(SOCK.getOutputStream());
            
            InputStreamReader IR = new InputStreamReader(SOCK.getInputStream());
            BufferedReader BR = new BufferedReader(IR);
            String m = BR.readLine();

            System.out.println(m);
            Scanner sc = new Scanner(System.in);
            a = sc.nextLine();
            if (a.equals("kraj")) {
                break;
            }
            PS.println(a);
        } while (true);

    }
}
