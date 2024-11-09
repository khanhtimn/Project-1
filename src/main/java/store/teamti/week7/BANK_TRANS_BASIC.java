package store.teamti.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * <p>Description:</p>
 * The data about bank transactions consists of a sequence of transactions: the information of each transaction has the following format:
 * <from_account>   <to_account>   <money>   <time_point>   <atm>
 * In which:
 * •	<from_account>: the account from which money is transferred (which is a string of length from 6 to 20 )
 * •	<to_account>: the account which receives money in the transaction (which is a string of length from 6 to 20)
 * •	<money>: amount of money transferred in the transaction (which is an integer from 1 to 10000)
 * •	<time_point>: the time point at which the transaction is performed, it is a string under the format HH:MM:SS  (hour: minute: second)
 * •	<atm>: the code of the ATM where the transaction is taken (a string of length from 3 to 10)
 * Example: T00112233445 T001234002 2000 08:36:25 BIDV (at the ATM BIDV, account T00112233445 transfers 2000$ to account T001234002 at time point 08:36:25 (08 hour, 36 minutes, 25 seconds)
 * A transaction cycle of length k starting from account a1 is defined to be a sequence of distinct account a1, a2, …, ak  in which there are transactions from account a1 to a2, from a2 to a3, …, from ak to a1.
 * Write a program that process the following queries:
 * ?number_transactions: compute the total number of transactions of the data
 * ?total_money_transaction: compute the total amount of money of transactions
 * ?list_sorted_accounts: compute the sequence of bank accounts (including sending and receiving accounts) appearing in the transaction (sorted in an increasing (alphabetical) order)
 * ?total_money_transaction_from <account>: compute the total amount of money transferred from the account <account>
 * ?inspect_cycle <account> k : return 1 if there is a transaction cycle of length k, starting from <account>, and return 0, otherwise
 * <p>Input:</p>
 * The input consists of 2 blocks of information: the data block and the query block
 * •	The data block consists of lines:
 * o	Each line contains the information about a transaction described above
 * o	The data is terminated by a line containing #
 * •	The query block consists of lines:
 * o	Each line is a query described above
 * o	The query block is terminated by a line containing #
 * <p>Output:</p>
 * •	Print to stdout (in each line) the result of each query described above
 * <p>Example:</p>
 * <pre>
 * Input
 * T000010010 T000010020 1000 10:20:30 ATM1
 * T000010010 T000010030 2000 10:02:30 ATM2
 * T000010010 T000010040 1500 09:23:30 ATM1
 * T000010020 T000010030 3000 08:20:31 ATM1
 * T000010030 T000010010 4000 12:40:00 ATM2
 * T000010040 T000010010 2000 10:30:00 ATM1
 * T000010020 T000010040 3000 08:20:31 ATM1
 * T000010040 T000010030 2000 11:30:00 ATM1
 * T000010040 T000010030 1000 18:30:00 ATM1
 * #
 * ?number_transactions
 * ?total_money_transaction
 * ?list_sorted_accounts
 * ?total_money_transaction_from T000010010
 * ?inspect_cycle T000010010 3
 * #
 * Output
 * 9
 * 19500
 * T000010010 T000010020 T000010030 T000010040
 * 4500
 * 1
 * </pre>
 */
public class BANK_TRANS_BASIC {
    static class Transaction {
        String fromAccount;
        String toAccount;
        int money;
        String timePoint;
        String atm;

        Transaction(String fromAccount, String toAccount, int money, String timePoint, String atm) {
            this.fromAccount = fromAccount;
            this.toAccount = toAccount;
            this.money = money;
            this.timePoint = timePoint;
            this.atm = atm;
        }
    }

    static List<Transaction> transactions = new ArrayList<>();
    static Set<String> accounts = new TreeSet<>();
    static Map<String, List<String>> transactionGraph = new HashMap<>();
    static Map<String, Integer> totalTransferFromAccount = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        // Process transactions
        while (!(line = br.readLine()).equals("#")) {
            processTransaction(line);
        }

        // Process queries
        while (!(line = br.readLine()).equals("#")) {
            processQuery(line);
        }

        br.close();
    }

    private static void processTransaction(String line) {
        String[] parts = line.split(" ");
        String fromAccount = parts[0];
        String toAccount = parts[1];
        int money = Integer.parseInt(parts[2]);
        String timePoint = parts[3];
        String atm = parts[4];

        Transaction transaction = new Transaction(fromAccount, toAccount, money, timePoint, atm);
        transactions.add(transaction);

        accounts.add(fromAccount);
        accounts.add(toAccount);

        transactionGraph.computeIfAbsent(fromAccount, k -> new ArrayList<>()).add(toAccount);

        totalTransferFromAccount.merge(fromAccount, money, Integer::sum);
    }

    private static void processQuery(String line) {
        String[] parts = line.split(" ");
        String queryType = parts[0];

        switch (queryType) {
            case "?number_transactions":
                System.out.println(transactions.size());
                break;

            case "?total_money_transaction":
                int totalMoney = transactions.stream()
                        .mapToInt(t -> t.money)
                        .sum();
                System.out.println(totalMoney);
                break;

            case "?list_sorted_accounts":
                System.out.println(String.join(" ", accounts));
                break;

            case "?total_money_transaction_from":
                String account = parts[1];
                System.out.println(totalTransferFromAccount.getOrDefault(account, 0));
                break;

            case "?inspect_cycle":
                String startAccount = parts[1];
                int cycleLength = Integer.parseInt(parts[2]);
                System.out.println(hasCycle(startAccount, cycleLength) ? 1 : 0);
                break;
        }
    }

    private static boolean hasCycle(String startAccount, int k) {
        Set<String> visited = new HashSet<>();
        visited.add(startAccount);
        return findCycle(startAccount, startAccount, k - 1, visited);
    }

    private static boolean findCycle(String currentAccount, String startAccount, int remainingLength, Set<String> visited) {
        if (remainingLength == 0) {
            List<String> neighbors = transactionGraph.getOrDefault(currentAccount, Collections.emptyList());
            return neighbors.contains(startAccount);
        }

        List<String> neighbors = transactionGraph.getOrDefault(currentAccount, Collections.emptyList());
        for (String nextAccount : neighbors) {
            if (!visited.contains(nextAccount)) {
                visited.add(nextAccount);
                if (findCycle(nextAccount, startAccount, remainingLength - 1, visited)) {
                    return true;
                }
                visited.remove(nextAccount);
            }
        }
        return false;
    }
}
