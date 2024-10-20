package store.teamti.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TreeNode {
    int id;
    List<TreeNode> children;

    TreeNode(int id) {
        this.id = id;
        this.children = new ArrayList<>();
    }
}

class Tree {
    private TreeNode root;
    private final Map<Integer, TreeNode> nodeMap;

    Tree() {
        nodeMap = new HashMap<>();
    }

    void makeRoot(int id) {
        if (root == null) {
            root = new TreeNode(id);
            nodeMap.put(id, root);
        }
    }

    void insert(int u, int v) {
        if (nodeMap.containsKey(u) || !nodeMap.containsKey(v)) return;
        TreeNode parent = nodeMap.get(v);
        TreeNode newTreeNode = new TreeNode(u);
        parent.children.add(newTreeNode);
        nodeMap.put(u, newTreeNode);
    }

    void preOrder() {
        List<Integer> result = new ArrayList<>();
        preOrderTraversal(root, result);
        System.out.println(String.join(" ", result.stream().map(String::valueOf).toArray(String[]::new)));
    }

    private void preOrderTraversal(TreeNode TreeNode, List<Integer> result) {
        if (TreeNode == null) return;
        result.add(TreeNode.id);
        for (TreeNode child : TreeNode.children) {
            preOrderTraversal(child, result);
        }
    }

    void inOrder() {
        List<Integer> result = new ArrayList<>();
        inOrderTraversal(root, result);
        System.out.println(String.join(" ", result.stream().map(String::valueOf).toArray(String[]::new)));
    }

    private void inOrderTraversal(TreeNode TreeNode, List<Integer> result) {
        if (TreeNode == null) return;
        if (!TreeNode.children.isEmpty()) {
            inOrderTraversal(TreeNode.children.get(0), result);
        }
        result.add(TreeNode.id);
        for (int i = 1; i < TreeNode.children.size(); i++) {
            inOrderTraversal(TreeNode.children.get(i), result);
        }
    }

    void postOrder() {
        List<Integer> result = new ArrayList<>();
        postOrderTraversal(root, result);
        System.out.println(String.join(" ", result.stream().map(String::valueOf).toArray(String[]::new)));
    }

    private void postOrderTraversal(TreeNode TreeNode, List<Integer> result) {
        if (TreeNode == null) return;
        for (TreeNode child : TreeNode.children) {
            postOrderTraversal(child, result);
        }
        result.add(TreeNode.id);
    }
}

/**
 * <p>Description:</p>
 * Mỗi nút trên cây có trường id (identifier) là một số nguyên (id của các nút trên cây đôi một khác nhau)
 * Thực hiện 1 chuỗi các hành động sau đây bao gồm các thao tác liên quan đến xây dựng cây và duyệt cây
 * · MakeRoot u: Tạo ra nút gốc u của cây
 * · Insert u v: tạo mới 1 nút u và chèn vào cuối danh sách nút con của nút v (nếu nút có id bằng v không tồn tại hoặc nút có id bằng u đã tồn tại thì không chèn thêm mới)
 * · PreOrder: in ra thứ tự các nút trong phép duyệt cây theo thứ tự trước
 * · InOrder: in ra thứ tự các nút trong phép duyệt cây theo thứ tự giữa
 * · PostOrder: in ra thứ tự các nút trong phép duyệt cây theo thứ tự sau
 * <p>Input:</p>
 * bao gồm các dòng, mỗi dòng là 1 trong số các hành động được mô tả ở trên, dòng cuối dùng là * (đánh dấu sự kết thúc của dữ liệu).
 * <p>Output:</p>
 * ghi ra trên mỗi dòng, thứ tự các nút được thăm trong phép duyệt theo thứ tự trước, giữa, sau của các hành động PreOrder, InOrder, PostOrder tương ứng đọc được từ dữ liệu đầu vào
 * <p>Example:</p>
 * <pre>
 * Input:
 * MakeRoot 10
 * Insert 11 10
 * Insert 1 10
 * Insert 3 10
 * InOrder
 * Insert 5 11
 * Insert 4 11
 * Insert 8 3
 * PreOrder
 * Insert 2 3
 * Insert 7 3
 * Insert 6 4
 * Insert 9 4
 * InOrder
 * PostOrder
 * *
 * Output
 * 11 10 1 3
 * 10 11 5 4 1 3 8
 * 5 11 6 4 9 10 1 8 3 2 7
 * 5 6 9 4 11 1 8 2 7 3 10
 * </pre>
 */
public class TREE_MANIPULATION_TRAVERSAL {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Tree tree = new Tree();
        String line;

        while (!(line = br.readLine()).equals("*")) {
            String[] parts = line.split(" ");
            String operation = parts[0];

            switch (operation) {
                case "MakeRoot":
                    tree.makeRoot(Integer.parseInt(parts[1]));
                    break;
                case "Insert":
                    tree.insert(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                    break;
                case "PreOrder":
                    tree.preOrder();
                    break;
                case "InOrder":
                    tree.inOrder();
                    break;
                case "PostOrder":
                    tree.postOrder();
                    break;
            }
        }
        br.close();
    }
}
