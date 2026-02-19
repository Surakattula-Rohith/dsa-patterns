package io.github.surakattula_rohith.dsa.pascal_triangle;

import java.util.*;

public class GenerateTriangle {
    public List<List<Integer>> generate(int numRows) {

        List<List<Integer>> ans = new ArrayList<>();

        List<Integer> topLevel = new ArrayList<>();

        topLevel.add(1);
        ans.add(topLevel);

        numRows--;

        while (numRows > 0) {

            List<Integer> secondLevel = new ArrayList<>();

            secondLevel.add(1);

            for (int i = 0; i < topLevel.size() - 1; i++) {
                secondLevel.add(topLevel.get(i) + topLevel.get(i + 1));
            }

            secondLevel.add(1);

            ans.add(secondLevel);
            topLevel = secondLevel;

            numRows--;
        }

        return ans;
    }
}
