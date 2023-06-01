package com.example;

import java.util.ListResourceBundle;

public class Authors extends ListResourceBundle {

    @Override
    public Object[][] getContents() {
        return contents;
    }

    private Object [][] contents = {
            {"autorzy", "autorzy"},
            {"autor1", "Patrycja Przybylska,"},
            {"autor2", "Krzysztof Macherzynski"}
    };
}
