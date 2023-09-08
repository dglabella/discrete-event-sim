package gida.academics.labs.lab1.bootstrapping;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import gida.academics.labs.lab1.model.Event;
import gida.academics.labs.lab1.utils.orderings.FutureEventListOrder;

public class FutureEventList {

    private List<Event> fel;
    private Comparator<Event> ordering;

    public FutureEventList() {
        this.fel = new ArrayList<>();
        this.ordering = new FutureEventListOrder();
    }

    public FutureEventList(List<Event> list, Comparator<Event> ordering) {
        this.fel = list;
        this.ordering = ordering;
    }

    public Event getImminent() {
        return this.fel.remove(0);
    }

    public void insert(Event event) {
        this.fel.add(event);
        this.fel.sort(ordering);
    }

    @Override
    public String toString() {
        String ret = "[";

        for (int i = 0; i < this.fel.size() - 1; i++)
            ret += this.fel.get(i).toString() + ",\n";

        ret += this.fel.get(this.fel.size() - 1).toString() + "]";

        return ret;
    }
}
