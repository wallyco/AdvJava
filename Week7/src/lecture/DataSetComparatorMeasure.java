package lecture;

import java.util.Comparator;

public class DataSetComparatorMeasure implements Comparator<Measurable> {

    public int compare(Measurable o1, Measurable o2) {
        if (o1.getMeasure() < o2.getMeasure()) {
            return -1;
        }
        else if (o1.getMeasure() == o2.getMeasure()) {
            return 0;
        }
        else {
            return 1;
        }
    }
}
