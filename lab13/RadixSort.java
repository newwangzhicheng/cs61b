/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    private static final int BOX_LENGTH = 129;

    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1
     * length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        String[] result = asciis;
        int maxWitdh = 0;
        for (int i = 0; i < asciis.length; i++) {
            maxWitdh = maxWitdh < asciis[i].length() ? asciis[i].length() : maxWitdh;
            result[i] = asciis[i];
        }
        for (int i = 0; i < maxWitdh; i++) {
            sortHelperLSD(result, i);
        }
        return result;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * 
     * @param asciis Input array of Strings
     * @param index  The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        int[] counts = new int[BOX_LENGTH];
        for (int i = 0; i < asciis.length; i++) {
            int c = stringAtIndexToInt(asciis[i], index);
            counts[c]++;
        }

        int[] starts = new int[BOX_LENGTH];
        int pos = 0;
        for (int i = 0; i < BOX_LENGTH; i++) {
            starts[i] = pos;
            pos += counts[i];
        }

        String[] result = new String[asciis.length];
        for (int i = 0; i < asciis.length; i++) {
            String item = asciis[i];
            int c = stringAtIndexToInt(asciis[i], index);
            int place = starts[c];
            result[place] = item;
            starts[c] += 1;
        }
        System.arraycopy(result, 0, asciis, 0, result.length);

        return;
    }

    private static int stringAtIndexToInt(String item, int index) {
        if (item.length() <= index) {
            return 0;
        }
        return (int) item.charAt(index) + 1;
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the
     * sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start  int for where to start sorting in this method (includes String
     *               at start)
     * @param end    int for where to end sorting in this method (does not include
     *               String at end)
     * @param index  the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }

    public static void main(String[] args) {
        String[] strings = {
                "42a", "2a", "42b", ":", "4c"
        };
        String[] sorted = sort(strings);
        for (String s : sorted) {
            System.out.println(s);
        }
    }
}
