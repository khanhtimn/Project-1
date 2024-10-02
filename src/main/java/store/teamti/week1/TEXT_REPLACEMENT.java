package store.teamti.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Problem: Week 1 - Text Replacement
 *
 * <p>Description:</p>
 * Cho văn bản T và 2 mẫu P1, P2 đều là các xâu ký tự (không chứa ký tự xuống dòng, độ dài không vượt quá 1000)
 * Hãy thay thế các xâu P1 trong T bằng xâu P2.
 *
 * <p>Dữ liệu:</p>
 * <ul>
 *   <li>Dòng 1: xâu P1</li>
 *   <li>Dòng 2: xâu P2</li>
 *   <li>Dòng 3: văn bản T</li>
 * </ul>
 *
 * <p>Kết quả:</p>
 * Ghi văn bản T sau khi thay thế
 *
 * <p>Ví dụ:</p>
 * <pre>
 * Dữ liệu:
 * AI
 * Artificial Intelligence
 * Recently, AI is a key technology. AI enable efficient operations in many fields.
 *
 * Kết quả:
 * Recently, Artificial Intelligence is a key technology. Artificial Intelligence enable efficient operations in many fields.
 * </pre>
 */
public class TEXT_REPLACEMENT {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line1 = br.readLine();
        if (line1 == null) {
            return;
        }

        String line2 = br.readLine();
        if (line2 == null) {
            return;
        }

        String line3 = br.readLine();
        if (line3 == null) {
            return;
        }

        String P1 = line1.trim();
        String P2 = line2.trim();
        String T = line3.trim();

        String result = T.replace(P1, P2);

        System.out.println(result);
    }
}
