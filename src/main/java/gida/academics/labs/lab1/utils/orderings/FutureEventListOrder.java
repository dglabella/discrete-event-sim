package gida.academics.labs.lab1.utils.orderings;

import java.util.Comparator;
import gida.academics.labs.lab1.bootstrapping.Planificable;

public final class FutureEventListOrder implements Comparator<Planificable> {

    // private Comparator<Planificable> typeComparator;

    // public FutureEventListOrder(Comparator<Planificable> typeComparator) {
    // this.typeComparator = typeComparator;
    // }

    @Override
    public int compare(Planificable p1, Planificable p2) {

        int ret = 0;

        if (p1.getEvent().getClock() < p2.getEvent().getClock()) {
            ret = -1;
        } else if (p1.getEvent().getClock() > p2.getEvent().getClock()) {
            ret = 1;
        } else {
            if (p1.getEvent().getOrder().ordinal() < p2.getEvent().getOrder().ordinal())
                ret = -1;
            else
                ret = 1;
        }

        return ret;
    }
}
