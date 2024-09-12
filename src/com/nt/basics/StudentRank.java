package com.nt.basics;

public class StudentRank {
    private String[] students;
    private int[] ranks;

    public StudentRank(String[] students, int[] ranks) {
        this.students = students;
        this.ranks = ranks;
    }

    public String lowestRank() {
        int lowestRank = Integer.MAX_VALUE;
        String lowestRankStudent = null;

        for (int i = 0; i < ranks.length; i++) {
            if (ranks[i] < lowestRank) {
                lowestRank = ranks[i];
                lowestRankStudent = students[i];
            }
        }

        return lowestRankStudent;
    }

    public String highestRank() {
        int highestRank = Integer.MIN_VALUE; 
        String highestRankStudent = null;

        for (int i = 0; i < ranks.length; i++) {
            if (ranks[i] > highestRank) {
                highestRank = ranks[i];
                highestRankStudent = students[i];
            }
        }
        return highestRankStudent;
    }

    public static void main(String[] args) {
        String[] students = {"Taylor", "Wesley", "Jordan"};
        int[] ranks = {1, 5, 3};
        StudentRank sr = new StudentRank(students, ranks);
        System.out.println("Highest Rank Student: " + sr.highestRank());
        System.out.println("Lowest Rank Student: " + sr.lowestRank());
    }
}
