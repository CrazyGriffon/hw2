package org.example;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HomeWorkTest {

    @Test
    public void test() {
        //empty tree
        HomeWork emptyTree = new HomeWork();
        assertEquals(0, emptyTree.findMaxDigits(1, 1).size());

        List<Integer> treeValues = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16);
        //случайным образом генерируем nTrees деревьев
        int nTrees = 500;
        for (int i = 0; i < nTrees; ++i) {
            //перестановка значений, добавляемых в дерево
            Collections.shuffle(treeValues);

            //заполняем дерево для данной перестановки, каждый раз сбалансированность будет отличаться
            HomeWork tree = new HomeWork();
            for (Integer treeValue : treeValues) {
                tree.add(treeValue);
            }

            //получаем не более 5 элементов <= 10
            List<Integer> res1 = tree.findMaxDigits(5, 10);
            assertTrue(
                    res1.containsAll(Arrays.asList(6, 7, 8, 9, 10)) &&
                            res1.size() == 5
            );

            //получаем не более 1 элемента <= 13
            List<Integer> res2 = tree.findMaxDigits(1, 13);
            assertTrue(
                    res2.contains(13) &&
                            res2.size() == 1
            );

            //получаем все элементы дерева: upperBound > max(tree values)
            List<Integer> res3 = tree.findMaxDigits(treeValues.size(), 17);
            assertEquals(res3.size(), treeValues.size());

            //ожидаем пустой список: upperBound < min(tree values)
            List<Integer> res4 = tree.findMaxDigits(treeValues.size(), -1);
            assertEquals(0, res4.size());

            //получаем элементы дерева <= upperBound, upperBound не принадлежит дереву
            List<Integer> res5 = tree.findMaxDigits(treeValues.size(), 15);
            assertEquals(15, res5.size());
        }
    }
}