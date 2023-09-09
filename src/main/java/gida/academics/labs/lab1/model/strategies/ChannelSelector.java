package gida.academics.labs.lab1.model.strategies;

import java.util.List;
import gida.academics.labs.lab1.model.Channel;

public interface ChannelSelector {

    Channel selectChannel(List<Channel> channels);
}
