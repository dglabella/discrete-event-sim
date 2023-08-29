package gida.academics.labs.lab1.bootstrapping;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import gida.academics.labs.lab1.utils.orderings.FutureEventListOrder;

public class FutureEventList {

    private List<Planificable> fel;
    private Comparator<Planificable> order;

    public FutureEventList() {
        this.fel = new ArrayList<>();
        this.order = new FutureEventListOrder();
    }

    protected Planificable getImminent() {
        throw new RuntimeException("get imminent from the FEL not implemented yet...");
    }

    public void insert(Planificable event) {
        throw new RuntimeException("fel insertion not implemented yet...");
    }

    // @Override
    // public String toString() {
    // String ret = "[";

    // for (int i = 0; i < this.fel.size() - 1; i++)
    // ret += "{" + this.fel.get(i).getEvent().toString() + "},";

    // ret += "{" + this.fel.get(this.fel.size() - 1).toString() + "}]";

    // return ret;
    // }
}
