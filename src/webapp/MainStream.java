package webapp;

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

        minValue(values);
        System.out.println("\nmethod oddOrEven execution : " + oddOrEven(listNumbers));

    }

    private static void minValue(int[] values) {
        if (values.length < 10) {
            Arrays.stream(values).
                    sorted().distinct().forEach(System.out::print);
        }
        System.err.println("The number of digits cannot exceed 9.");
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        Map<Boolean, List<Integer>> map = integers.stream()
                .collect(partitioningBy(x -> x % 2 == 0, toList()));
        if (map.get(true).size() > map.get(false).size()) {
            return map.get(true);
        } else {
            return map.get(false);
        }
    }
}
