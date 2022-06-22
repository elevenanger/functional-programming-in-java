package cn.anger.functional.collections;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author : liuanglin
 * @date : 2022/6/22 18:38
 * @description : 从集合中选择一个元素
 */
public class PickingAnElement {

    public static void main(String[] args) {

        final List<String> vegetables =
            Arrays.asList("cabbage","potato","eggplant","tomato","pepper");
        pickVegetableByForLoop(vegetables,"a");
        pickVegetableByForLoop(vegetables,"p");
        pickElementByLambda(vegetables,"a");
        pickElementByLambda(vegetables,"p");
    }

    /**
     * 根据首字母匹配集合中的元素
     * 匹配第一个满足条件的元素
     * 匹配到元素则打印元素
     * 未匹配则输出未查到的信息
     * @param vegetables 集合
     * @param startingLetter 首字母
     */
    static void pickVegetableByForLoop(List<String> vegetables,String startingLetter) {
        String foundVeg = null;
        for (String vegetable : vegetables) {
            if (vegetable.startsWith(startingLetter)) {
                foundVeg = vegetable;
                break;
            }
        }
        System.out.printf("for 循环选择首字母为 %s 的蔬菜 : %n",startingLetter );
        if (foundVeg != null) {
            System.out.println(foundVeg);
        }else {
            System.out.println("未匹配到蔬菜");
        }
    }

    static void pickElementByLambda(List<String> vegetables, String startLetter){
        Optional<String> foundVeg =
            vegetables.stream()
                .filter(s -> s.startsWith(startLetter))
                .findFirst();
        System.out.printf("lambda 表达式选择首字母为 %s 的蔬菜 : %s%n",
            startLetter,foundVeg.orElse("未匹配到蔬菜"));
    }
}
