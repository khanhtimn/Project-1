package store.teamti.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

class LinkedListNode {
    int key;
    LinkedListNode next;

    LinkedListNode(int key) {
        this.key = key;
        this.next = null;
    }
}

class LinkedList {
    private LinkedListNode head;
    private LinkedListNode tail;
    private final Set<Integer> keys;

    LinkedList() {
        head = null;
        tail = null;
        keys = new HashSet<>();
    }

    void addLast(int k) {
        if (keys.contains(k)) return;
        LinkedListNode newLinkedListNode = new LinkedListNode(k);
        keys.add(k);
        if (head == null) {
            head = tail = newLinkedListNode;
        } else {
            tail.next = newLinkedListNode;
            tail = newLinkedListNode;
        }
    }

    void addFirst(int k) {
        if (keys.contains(k)) return;
        LinkedListNode newLinkedListNode = new LinkedListNode(k);
        keys.add(k);
        if (head == null) {
            head = tail = newLinkedListNode;
        } else {
            newLinkedListNode.next = head;
            head = newLinkedListNode;
        }
    }

    void addAfter(int u, int v) {
        if (keys.contains(u) || !keys.contains(v)) return;
        LinkedListNode current = head;
        while (current != null && current.key != v) {
            current = current.next;
        }
        if (current != null) {
            LinkedListNode newLinkedListNode = new LinkedListNode(u);
            keys.add(u);
            newLinkedListNode.next = current.next;
            current.next = newLinkedListNode;
            if (current == tail) {
                tail = newLinkedListNode;
            }
        }
    }

    void addBefore(int u, int v) {
        if (keys.contains(u) || !keys.contains(v)) return;
        if (head.key == v) {
            addFirst(u);
            return;
        }
        LinkedListNode prev = null;
        LinkedListNode current = head;
        while (current != null && current.key != v) {
            prev = current;
            current = current.next;
        }
        if (current != null) {
            LinkedListNode newLinkedListNode = new LinkedListNode(u);
            keys.add(u);
            newLinkedListNode.next = current;
            prev.next = newLinkedListNode;
        }
    }

    void remove(int k) {
        if (!keys.contains(k)) return;
        if (head.key == k) {
            head = head.next;
            if (head == null) tail = null;
            keys.remove(k);
            return;
        }
        LinkedListNode prev = null;
        LinkedListNode current = head;
        while (current != null && current.key != k) {
            prev = current;
            current = current.next;
        }
        if (current != null) {
            prev.next = current.next;
            if (current == tail) {
                tail = prev;
            }
            keys.remove(k);
        }
    }

    void reverse() {
        LinkedListNode prev = null;
        LinkedListNode current = head;
        LinkedListNode next;
        tail = head;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        LinkedListNode current = head;
        while (current != null) {
            sb.append(current.key).append(" ");
            current = current.next;
        }
        return sb.toString().trim();
    }
}

/**
 * <p>Description:</p>
 * Viết chương trình thực hiện công việc sau:
 * Xây dựng danh sách liên kết với các khóa được cung cấp ban đầu là dãy a
 * 1
 * , a
 * 2
 * , …, a
 * n
 * , sau đó thực hiện các thao tác trên danh sách bao gồm: thêm 1 phần tử vào đầu, vào cuối danh sách, hoặc vào trước, vào sau 1 phần tử nào đó trong danh sách, hoặc loại bỏ 1 phần tử nào đó trong danh sách
 *
 * <p>Input:</p>
 * Dòng 1: ghi số nguyên dương n (1 <= n <= 1000)
 * Dòng 2: ghi các số nguyên dương a
 * 1
 * , a
 * 2
 * , …, a
 * n
 * .
 * Các dòng tiếp theo lần lượt là các lệnh để thao tác (kết thúc bởi ký hiệu #) với các loại sau:
 * addlast  k: thêm phần tử có key bằng k vào cuối danh sách (nếu k chưa tồn tại)
 * addfirst  k: thêm phần tử có key bằng k vào đầu danh sách (nếu k chưa tồn tại)
 * addafter  u  v: thêm phần tử có key bằng u vào sau phần tử có key bằng v trên danh sách (nếu v đã tồn tại trên danh sách và u chưa tồn tại)
 * addbefore  u  v: thêm phần tử có key bằng  u vào trước phần tử có key bằng v trên danh sách (nếu v đã tồn tại trên danh sách và u của tồn tại)
 * remove  k: loại bỏ phần tử có key bằng k khỏi danh sách
 * reverse: đảo ngược thứ tự các phần tử của danh sách (không được cấp phát mới các phần tử, chỉ được thay đổi mối nối liên kết)
 * <p>Output:</p>
 * Ghi ra dãy khóa của danh sách thu được sau 1 chuỗi các lệnh thao tác đã cho
 *
 * <p>Example:</p>
 * <pre>
 * Input
 * 5
 * 5 4 3 2 1
 * addlast 3
 * addlast 10
 * addfirst 1
 * addafter 10 4
 * remove 1
 * #
 *
 * Output
 * 5 4 3 2 10
 * </pre>
 */
public class LINKED_LIST_MANIPULATION {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        String[] initialKeys = br.readLine().split(" ");

        LinkedList list = new LinkedList();
        for (String key : initialKeys) {
            list.addLast(Integer.parseInt(key));
        }

        String line;
        while (!(line = br.readLine()).equals("#")) {
            String[] parts = line.split(" ");
            String operation = parts[0];

            switch (operation) {
                case "addlast":
                    list.addLast(Integer.parseInt(parts[1]));
                    break;
                case "addfirst":
                    list.addFirst(Integer.parseInt(parts[1]));
                    break;
                case "addafter":
                    list.addAfter(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                    break;
                case "addbefore":
                    list.addBefore(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                    break;
                case "remove":
                    list.remove(Integer.parseInt(parts[1]));
                    break;
                case "reverse":
                    list.reverse();
                    break;
            }
        }

        System.out.println(list);
        br.close();
    }
}
