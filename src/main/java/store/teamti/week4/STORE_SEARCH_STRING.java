package store.teamti.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;
/**
 * <p>Description:</p>
 * A database contains a sequence of key k1, k2, ..., kn which are strings (1<=n<=100000). Perform a sequence of actions of two kinds:
 * · find k: find and return 1 if k exists in the database, and return 0, otherwise
 * · insert k: insert a key k into the database and return 1 if the insertion is successful (k does not exist in the database) and return 0 if the insertion is failed (k exists in the database)
 * Note that the length of any key is greater than 0 and less than or equal to 50.
 * <p>Input:</p>
 * Two blocks of information. The first block contains a key of (k1,k2,...,kn) in each line. The first block is terminated with a line containing *. The second block is a sequence of actions of two finds described above: each line contains 2 string: cmd and k in which cmd = find or insert and k is the key (parameter of the action). The second block is terminated with a line containing ***. Note that the number of actions can be up to 100000.
 * <p>Output:</p>
 * Each line contains the result (0 or 1) of the corresponding action.
 * <p>Example:</p>
 * <pre>
 * Input
 * computer
 * university
 * school
 * technology
 * phone
 * *
 * find school
 * find book
 * insert book
 * find algorithm
 * find book
 * insert book
 * ***
 * Output
 * 1
 * 0
 * 1
 * 0
 * 1
 * 0
 * </pre>
 * */
public class STORE_SEARCH_STRING {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            HashSet<String> database = new HashSet<>();

            while (true) {
                String line = br.readLine().trim();
                if (line.equals("*")) break;
                database.add(line);
            }

            while (true) {
                String line = br.readLine().trim();
                if (line.equals("***")) break;

                StringTokenizer st = new StringTokenizer(line);
                String cmd = st.nextToken();
                String key = st.nextToken();

                if (cmd.equals("find")) {
                    System.out.println(database.contains(key) ? 1 : 0);
                } else if (cmd.equals("insert")) {
                    System.out.println(database.add(key) ? 1 : 0);
                }
            }

        } catch (IOException ignored) {
        }
    }
}

