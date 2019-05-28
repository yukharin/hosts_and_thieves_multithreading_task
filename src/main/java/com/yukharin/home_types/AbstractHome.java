package com.yukharin.home_types;

import java.util.List;

public abstract class AbstractHome<T> implements Home<T> {
    protected List<T> elements;
}
