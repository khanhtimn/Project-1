package store.teamti.week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * <p>Description:</p>
 * Data about submission of a programming contest consists a sequence of lines, each line has the following information:
 * <UserID> <ProblemID> <TimePoint> <Status> <Point>
 * in which the user <UserID> submits his/her code to solve the problem <ProblemID> at time-point <TimePoint>, and gets status <Status> and point <Point>
 * <UserID>: string of length from 3 to 10
 * <ProblemID>: string under the format Pxy where x, y are digits 0,1,...,9 (for example P03, P10)
 * <TimePoint>: string representing time-point with the format HH:MM:SS (for example, 09:45:20 means the time-point 9 hour 45 minutes 20 seconds)
 * <Status>: string with two cases (ERR, OK)
 * <Point>: integer from {0, 1, 2, ..., 10}
 * <p>
 * A user can submit the code for solving each problem several time. The point that the user gets for a problem is the maximal point among the submissions for that problem.
 * <p>
 * Perform a sequence of queries of following types:
 * ?total_number_submissions: return the number of submissions of the contest
 * ?number_error_submision: return the number of submissions having status ERR
 * ?number_error_submision_of_user <UserID>: return the number of submission having status ERR of user <UserID>
 * ?total_point_of_user <UserID>: return the total point of user <UserID>
 * ?number_submission_period <from_time_point> <to_time_point>: return the number of submissions in the period from <from_time_point> to <to_time_point> (inclusive)
 * <p>
 * <p>Input:</p>
 * The input consists of two blocks of data:
 * The first block is the operational data, which is a sequence of lines (number of lines can be up to 100000), each line contains the information of a submission with above format .The first block is terminated with a line containing the character #
 * The second block is the query block, which is a sequence of lines (number of lines can be up to 100000), each line is a query described above. The second block is terminated with a line containing the character #
 * <p>
 * <p>Output:</p>
 * Write in each line, the result of the corresponding query
 * <p>
 * <p>Example:</p>
 * <pre>
 * Input
 * U001 P01 10:30:20 ERR 0
 * U001 P01 10:35:20 OK 10
 * U001 P02 10:40:20 ERR 0
 * U001 P02 10:55:20 OK 7
 * U002 P01 10:40:20 ERR 0
 * U001 P01 11:35:20 OK 8
 * U002 P02 10:40:20 OK 10
 * #
 * ?total_number_submissions
 * ?number_error_submision
 * ?number_error_submision_of_user U002
 * ?total_point_of_user U001
 * ?number_submission_period 10:00:00 11:30:45
 * #
 * <p>
 * <p>
 * Output
 * 7
 * 3
 * 1
 * 17
 * 6
 * </pre>
 */
public class ANALYZE_CODE_SUBMISSION {
    static class Submission {
        String userId;
        String problemId;
        String timePoint;
        String status;
        int point;

        Submission(String line) {
            String[] parts = line.split(" ");
            this.userId = parts[0];
            this.problemId = parts[1];
            this.timePoint = parts[2];
            this.status = parts[3];
            this.point = Integer.parseInt(parts[4]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Submission> submissions = new ArrayList<>();

        Map<String, Map<String, Integer>> userProblemPoints = new HashMap<>();

        Map<String, Integer> userErrorCounts = new HashMap<>();

        int totalErrors = 0;
        String line;
        while (!(line = br.readLine()).equals("#")) {
            Submission sub = new Submission(line);
            submissions.add(sub);

            if (sub.status.equals("ERR")) {
                totalErrors++;
                userErrorCounts.merge(sub.userId, 1, Integer::sum);
            }

            userProblemPoints
                    .computeIfAbsent(sub.userId, k -> new HashMap<>())
                    .merge(sub.problemId, sub.point, Integer::max);
        }

        while (!(line = br.readLine()).equals("#")) {
            String[] query = line.split(" ");

            switch (query[0]) {
                case "?total_number_submissions":
                    System.out.println(submissions.size());
                    break;

                case "?number_error_submision":
                    System.out.println(totalErrors);
                    break;

                case "?number_error_submision_of_user":
                    System.out.println(userErrorCounts.getOrDefault(query[1], 0));
                    break;

                case "?total_point_of_user":
                    int totalPoints = userProblemPoints
                            .getOrDefault(query[1], Collections.emptyMap())
                            .values()
                            .stream()
                            .mapToInt(Integer::intValue)
                            .sum();
                    System.out.println(totalPoints);
                    break;

                case "?number_submission_period":
                    String fromTime = query[1];
                    String toTime = query[2];
                    long count = submissions.stream()
                            .filter(sub -> sub.timePoint.compareTo(fromTime) >= 0 &&
                                    sub.timePoint.compareTo(toTime) <= 0)
                            .count();
                    System.out.println(count);
                    break;
            }
        }
    }
}
