package store.teamti.week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * <p>Description:</p>
 * Given a DataBase about citizen, perform queries over this DataBase.
 * <p>Input:</p>
 * The input consists of two blocks: the first block is the DataBase and the second block is the list of queries.
 * Two blocks are separated by a line containing a character *.
 * 1. The first block (DataBase about citizen) consists of lines (number of lines can be upto 100000),
 * each line is the information about a person and is under the format:
 * <code>  <dat_of_birth>  <fathher_code>   <mother_code>  <is_alive>  <region_code>
 * <code>: the code of the person which is a string of length 7
 * <date_of_birth>: the date of birth of the person and has the format YYYY-MM-DD (for example 1980-02-23), <date_of_birth> is before 3000-12-31
 * <fathher_code> and <mother_code> is the code of father and mother: they are also strings of length 7.
 * If the code is 0000000, then the current person does not has information about his father or mother
 * <is_alive>: a character with two values: ‘Y’ means that the person is still alive, and ‘N’ means tat the current person is died.
 * <region_code>: the code of the region where the person lives
 * 2. The second block is the list of queries (number of queries can be upto 100000) over the DataBase which consists of following commands:
 * NUMBER_PEOPLE: return the number of people (number of lines of the DataBase)
 * NUMBER_PEOPLE_BORN_AT <date>: return the number of people having date-of-birth is equal to <date>
 * MOST_ALIVE_ANCESTOR <code>: find the most ancestor (farthest in term of generation distance) of the given person <code>.
 * Return the generation distance between the ancestor found and the given person
 * NUMBER_PEOPLE_BORN_BETWEEN <from_date> <to_date>: compute the number of people having date-of-birth between <from_date> and <to_date> (<from_date> and <to_date> are under the form YYYY-MM-DD, <to_date> is before 3000-12-31)
 * MAX_UNRELATED_PEOPLE: find a subset of people in which two any people of the subset do not have father/mother-children and the size of the subset is maximal.
 * Return the size of the subset found.
 * The second block is terminated by a line containing ***.
 * <p>Output:</p>
 * Each line presents the result of the corresponding query (described above).
 * <p>Example:</p>
 * <pre>
 * Input
 * 0000001 1920-08-10 0000000 0000000 Y 00002
 * 0000002 1920-11-03 0000000 0000000 Y 00003
 * 0000003 1948-02-13 0000001 0000002 Y 00005
 * 0000004 1946-01-16 0000001 0000002 Y 00005
 * 0000005 1920-11-27 0000000 0000000 Y 00005
 * 0000006 1920-02-29 0000000 0000000 Y 00004
 * 0000007 1948-07-18 0000005 0000006 Y 00005
 * 0000008 1948-07-18 0000005 0000006 Y 00002
 * 0000009 1920-03-09 0000000 0000000 Y 00005
 * 0000010 1920-10-16 0000000 0000000 Y 00005
 * NUMBER_PEOPLE
 * NUMBER_PEOPLE_BORN_AT 1919-12-10
 * NUMBER_PEOPLE_BORN_AT 1948-07-18
 * MAX_UNRELATED_PEOPLE
 * MOST_ALIVE_ANCESTOR 0000008
 * MOST_ALIVE_ANCESTOR 0000001
 * NUMBER_PEOPLE_BORN_BETWEEN 1900-12-19 1928-11-16
 * NUMBER_PEOPLE_BORN_BETWEEN 1944-08-13 1977-12-15
 * NUMBER_PEOPLE_BORN_BETWEEN 1987-01-24 1988-06-03
 * Output
 * 10
 * 0
 * 2
 * 6
 * 1
 * 0
 * 6
 * 4
 * 0
 * </pre>
 */
public class CITIZEN_DATA_ANALYZE {
    static class Person {
        String code;
        LocalDate dateOfBirth;
        String fatherCode;
        String motherCode;
        boolean isAlive;
        String regionCode;
        List<String> children = new ArrayList<>();

        Person(String code, String dateOfBirth, String fatherCode, String motherCode,
               String isAlive, String regionCode) {
            this.code = code;
            this.dateOfBirth = LocalDate.parse(dateOfBirth, dateFormatter);
            this.fatherCode = fatherCode;
            this.motherCode = motherCode;
            this.isAlive = isAlive.equals("Y");
            this.regionCode = regionCode;
        }
    }

    static Map<String, Person> citizens = new HashMap<>();
    static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while (!(line = br.readLine()).equals("*")) {
            processPersonRecord(line);
        }

        buildRelationships();

        while (!(line = br.readLine()).equals("***")) {
            processQuery(line);
        }

        br.close();
    }

    private static void processPersonRecord(String line) {
        String[] parts = line.split(" ");
        Person person = new Person(
                parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]
        );
        citizens.put(person.code, person);
    }

    private static void buildRelationships() {
        for (Person person : citizens.values()) {
            if (!person.fatherCode.equals("0000000")) {
                Person father = citizens.get(person.fatherCode);
                if (father != null) {
                    father.children.add(person.code);
                }
            }
            if (!person.motherCode.equals("0000000")) {
                Person mother = citizens.get(person.motherCode);
                if (mother != null) {
                    mother.children.add(person.code);
                }
            }
        }
    }

    private static void processQuery(String line) {
        String[] parts = line.split(" ");
        String queryType = parts[0];

        switch (queryType) {
            case "NUMBER_PEOPLE":
                System.out.println(citizens.size());
                break;

            case "NUMBER_PEOPLE_BORN_AT":
                LocalDate date = LocalDate.parse(parts[1]);
                long count = citizens.values().stream()
                        .filter(p -> p.dateOfBirth.equals(date))
                        .count();
                System.out.println(count);
                break;

            case "MOST_ALIVE_ANCESTOR":
                System.out.println(findMostAliveAncestorDistance(parts[1]));
                break;

            case "NUMBER_PEOPLE_BORN_BETWEEN":
                LocalDate fromDate = LocalDate.parse(parts[1]);
                LocalDate toDate = LocalDate.parse(parts[2]);
                long countBetween = citizens.values().stream()
                        .filter(p -> !p.dateOfBirth.isBefore(fromDate) && !p.dateOfBirth.isAfter(toDate))
                        .count();
                System.out.println(countBetween);
                break;

            case "MAX_UNRELATED_PEOPLE":
                System.out.println(findMaxUnrelatedPeople());
                break;
        }
    }

    private static int findMostAliveAncestorDistance(String personCode) {
        if (!citizens.containsKey(personCode)) return 0;

        int maxDistance = 0;
        Queue<String> queue = new LinkedList<>();
        Map<String, Integer> distance = new HashMap<>();

        queue.offer(personCode);
        distance.put(personCode, 0);

        while (!queue.isEmpty()) {
            String currentCode = queue.poll();
            int currentDistance = distance.get(currentCode);
            Person current = citizens.get(currentCode);

            // Check father
            if (!current.fatherCode.equals("0000000") && citizens.containsKey(current.fatherCode)) {
                if (!distance.containsKey(current.fatherCode)) {
                    queue.offer(current.fatherCode);
                    distance.put(current.fatherCode, currentDistance + 1);
                    maxDistance = Math.max(maxDistance, currentDistance + 1);
                }
            }

            // Check mother
            if (!current.motherCode.equals("0000000") && citizens.containsKey(current.motherCode)) {
                if (!distance.containsKey(current.motherCode)) {
                    queue.offer(current.motherCode);
                    distance.put(current.motherCode, currentDistance + 1);
                    maxDistance = Math.max(maxDistance, currentDistance + 1);
                }
            }
        }

        return maxDistance;
    }

    private static int findMaxUnrelatedPeople() {
        Set<String> remainingPeople = new HashSet<>(citizens.keySet());
        List<Set<String>> groups = new ArrayList<>();

        while (!remainingPeople.isEmpty()) {
            String startPerson = remainingPeople.iterator().next();
            Set<String> relatedGroup = findRelatedPeople(startPerson);
            groups.add(relatedGroup);
            remainingPeople.removeAll(relatedGroup);
        }

        int totalMax = 0;
        for (Set<String> group : groups) {
            Set<String> independent = findIndependentSet(new ArrayList<>(group));
            totalMax += independent.size();
        }

        return totalMax;
    }

    private static Set<String> findRelatedPeople(String startPerson) {
        Set<String> related = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(startPerson);
        related.add(startPerson);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            Person person = citizens.get(current);

            // Add parents
            if (!person.fatherCode.equals("0000000")) {
                if (related.add(person.fatherCode)) {
                    queue.offer(person.fatherCode);
                }
            }
            if (!person.motherCode.equals("0000000")) {
                if (related.add(person.motherCode)) {
                    queue.offer(person.motherCode);
                }
            }

            // Add children
            for (String child : person.children) {
                if (related.add(child)) {
                    queue.offer(child);
                }
            }
        }

        return related;
    }

    private static Set<String> findIndependentSet(List<String> group) {
        Set<String> result = new HashSet<>();
        Set<String> used = new HashSet<>();

        group.sort(Comparator.comparing(a -> citizens.get(a).dateOfBirth));

        for (String personCode : group) {
            if (!used.contains(personCode)) {
                result.add(personCode);
                used.add(personCode);

                Person person = citizens.get(personCode);
                used.add(person.fatherCode);
                used.add(person.motherCode);
                used.addAll(person.children);
            }
        }

        return result;
    }
}