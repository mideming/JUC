package com.mideming.juc.T09_container.T03_container;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class T03_SynchronizedList {
    List<String> strings = new ArrayList<>();
    List<String> stringsSync = Collections.synchronizedList(strings);

}
