import java.util.HashMap;
import java.util.Map;


/* Запускать через сначала компиляцию  javac FrequencyAnalyzer.java, потом java FrequencyAnalyzer*/

public class FrequencyAnalyzer {
    /**
     * Считает, сколько раз каждый элемент встречается в массиве.
     *
     * Если array == null: TODO (задача 2, решить и написать здесь).
     * null-элементы: TODO (считаются как обычный ключ или пропускаются).
     */
    
    
    public static <T> Map<T, Integer> countFrequency(T[] array) {
        Map<T, Integer> result = new HashMap<>();

        // TODO (задача 2): обработать array == null
        // Бросает IllegalArgumentException если array == null
        if(array == null) {
            throw new IllegalArgumentException();
        }
        
        // TODO (задача 3): пройти по массиву и подсчитать элементы
        for (T element : array) {
            result.put(element, result.getOrDefault(element, 0) + 1);
        }
        return result;
    }


    public static void main(String[] args) {
        // TODO (задача 6): проверки на String[], Integer[], Character[]
        String[] words = {null, "b", "a", "c", "a", "b"};
        System.out.println(countFrequency(words));   // ожидаем {a=3, b=2, c=1}
    }
}
