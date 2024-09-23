package org.example;

import java.util.ArrayList;
import java.util.List;


public class HomeWork extends BinaryIntSearchTree {

    /**
     * <h1>Задание 1.</h1>
     * Дан класс BinaryTree, который реализует бинарное дерево поиска.
     * Реализовать метод findMaxDigits, который возвращает массив
     * наибольших элементов в дереве, не превосходящих upperBound.
     * <br/>
     * Пример :
     * коллекция в дереве 1, 2, 3, 4, 5
     * count = 3, upperBound 4
     * ответ [4, 3, 2]

     *
     * @param count максимальное количество элементов в ответе
     * @param upperBound верхняя граница для поиска элементов
     * @return массив найденных максимальных значений не более чем upperBound и длиной не более count, отсортировано от большего к меньшему
     * Сигнатуру метода не меняем
     */
    public List<Integer> findMaxDigits(int count, int upperBound) {
        return findMaxDigitsRec(root, count, upperBound);
    }

    private List<Integer> findMaxDigitsRec(Node node, int count, int upperBound) {
        //обработка частных случаев
        if (node == null || count <= 0) {
            return new ArrayList<>();
        }

        if (node.value > upperBound) {
            //все значения <= upperBound находятся в левом поддереве
            return findMaxDigitsRec(node.left, count, upperBound);
        } else {
            //ищем нужное количество элементов в правом поддереве
            List<Integer> resultRight = (node.value < upperBound) ?
                    findMaxDigitsRec(node.right, count, upperBound) :
                    new ArrayList<>();

            int resultRightSize = resultRight.size();
            //если нужное количество элементов в правом поддереве найдено, возвращаем
            if (resultRightSize == count) {
                return resultRight;
            }

            //возвращаем нужное число элементов из левого поддерева + значение из корня + элементы правого поддерева
            List<Integer> resultLeft = findMaxDigitsRec(node.left, count - 1 - resultRightSize, upperBound);
            resultLeft.add(node.value);
            resultLeft.addAll(resultRight);
            return resultLeft;
        }
    }
}
