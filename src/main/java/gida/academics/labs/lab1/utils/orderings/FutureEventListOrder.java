package gida.academics.labs.lab1.utils.orderings;

import java.util.Comparator;
import gida.academics.labs.lab1.model.Event;

public final class FutureEventListOrder implements Comparator<Event> {

    @Override
    public int compare(Event e1, Event e2) {

        int ret = 0;

        if (e1.getClock() < e2.getClock()) {
            ret = -1;
        } else if (e1.getClock() > e2.getClock()) {
            ret = 1;
        } else {
            if (e1.getPriority() < e2.getPriority())
                ret = -1;
            else
                ret = 1;
        }

        return ret;
    }
}
