import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import synthesizer.GuitarString;

public class GuitarHero {
    private static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    /** convert contents of keybord string to char list */
    private static List<Character> convertKeyboardToList(String keyboard) {
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < keyboard.length(); i++) {
            char c = keyboard.charAt(i);
            list.add(c);
        }
        return list;
    }

    /** create GuitarString map by keyboard list */
    private static Map<Character, GuitarString> createGuitarStringMap(List<Character> list) {
        Map<Character, GuitarString> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            double concert = 440.0 * Math.pow(2, (i - 24.0) / 12.0);
            map.put(list.get(i), new GuitarString(concert));
        }
        return map;
    }

    /** sum all peek sample in the map */
    private static double sumAllSample(Map<Character, GuitarString> map) {
        double sum = 0;
        for (GuitarString value : map.values()) {
            sum += value.sample();
        }
        return sum;
    }

    /** tic all GuitarString in the map */
    private static void ticAll(Map<Character, GuitarString> map) {
        map.forEach((Character key, GuitarString value) -> {
            value.tic();
        });
    }

    public static void main(String[] args) {
        List<Character> keyboardList = convertKeyboardToList(KEYBOARD);
        Map<Character, GuitarString> guitarStringMap = createGuitarStringMap(keyboardList);
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (guitarStringMap.containsKey(key)) {
                    guitarStringMap.get(key).pluck();
                }
            }
            double sample = sumAllSample(guitarStringMap);

            StdAudio.play(sample);

            ticAll(guitarStringMap);
        }
    }
}
