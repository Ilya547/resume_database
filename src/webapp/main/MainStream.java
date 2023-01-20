package webapp.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.toList;

public class MainStream {
    public static void main(String[] args) {
        int[] values = {1, 2, 3, 3, 2, 3};
        List<Integer> listNumbers = new ArrayList<>();
        listNumbers.add(1);
        listNumbers.add(2);
        listNumbers.add(3);

        System.out.println("method minValue execution : " + minValue(values));
        System.out.println("method oddOrEven execution : " + oddOrEven(listNumbers));

    }

    private static int minValue(int[] values) {
            return Arrays.stream(values)
                    .distinct()
                    .sorted()
                    .reduce(0, (a, b) -> 10 * a + b);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        Map<Boolean, List<Integer>> map = integers.stream()
                .collect(partitioningBy(x -> x % 2 == 0, toList()));
        return map.get(true).size() > map.get(false).size() ? map.get(true) : map.get(false);
    }

    public static int concatNums(int[] arr) {
        int result = 0;
        int multiplier = 1;
        for (int i = arr.length; i > 0; --i) {
            int num = arr[i - 1];
            while (num != 0) {
                result += multiplier * (num % 10);
                num /= 10;
                multiplier *= 10;
            }
        }
        return result;
    }
}
