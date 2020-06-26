import jdk.internal.jline.internal.Nullable;

import java.util.Arrays;
import java.util.Comparator;

//https://www.careercup.com/question?id=24532662
/*
 * You are given two array, first array contain integer which represent heights of persons and second array contain how many persons in front of him are standing who are greater than him in term of height and forming a queue. Ex
 * A: 3 2 1
 * B: 0 1 1
 * It means in front of person of height 3 there is no person standing, person of height 2 there is one person in front of him who has greater height then he, similar to person of height 1. Your task to arrange them
 * Ouput should be.
 * 3 1 2
 * Here - 3 is at front, 1 has 3 in front ,2 has 1 and 3 in front.
 * */
public class ReorderPeople {
    private static RopeNode root;

    private static void insertNodeUtil(int key, int value, RopeNode root) {
        if (root.value > value) {
            if (root.left != null) {
                insertNodeUtil(key, value, root.left);
            } else {
                root.left = new RopeNode(key, 1);
            }
            root.value++;
        } else {
            int leftValue = 1;
            if (root.left != null) {
                leftValue = root.left.value + 1;
            }
            if (root.right != null) {
                insertNodeUtil(key, value - leftValue, root.right);
            } else {
                root.right = new RopeNode(key, 1);
            }
        }
    }

    private static void inOrderTraverse(RopeNode root) {
        if (root == null) {
            return;
        }
        inOrderTraverse(root.left);
        System.out.print(root.key + " " + root.value + " | ");
        inOrderTraverse(root.right);
    }

    private static void insertNode(int key, int value) {
        if (root == null) {
            root = new RopeNode(key, 1);
        } else {
            insertNodeUtil(key, value, root);
        }
    }

    public static void main(String[] args) {
        PersonInfo[] arr = {
                new PersonInfo(6, 0),
                new PersonInfo(5, 0),
                new PersonInfo(3, 2),
                new PersonInfo(2, 2),
                new PersonInfo(1, 4),
                new PersonInfo(4, 0)
        };
        Arrays.sort(arr, new Comparator<PersonInfo>() {
            @Override
            public int compare(PersonInfo a, PersonInfo b) {
                return a.inFront - b.inFront;
            }
        });
        for (int i = 0; i < arr.length; i++) {
            insertNode(arr[i].height, arr[i].inFront);
            inOrderTraverse(root);
            System.out.println();
        }
        inOrderTraverse(root);
    }

    private static class PersonInfo {
        final int height;
        final int inFront;

        public PersonInfo(int height, int inFront) {
            this.height = height;
            this.inFront = inFront;
        }
    }

    private static class RopeNode {
        final int key;
        int value;

        @Nullable
        RopeNode left;

        @Nullable
        RopeNode right;

        public RopeNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
