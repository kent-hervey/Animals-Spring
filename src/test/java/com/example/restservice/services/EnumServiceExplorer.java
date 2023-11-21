package com.example.restservice.services;

import com.example.restservice.enums.Kind;
import java.util.List;
import org.junit.jupiter.api.Test;

class EnumServiceExplorer {
//Note the below is not literally a unit test.  It is used for exploration or dry runs. One reason is that we are using the actual Kinds enum, not a mock.
    @Test
    void exploreKinds() {
        EnumService enumService = new EnumService();
        List<Kind> result = enumService.fetchKinds();
        result.stream().forEach(kind -> System.out.print("Here is the result:  " + kind));
    }

    //Note the below is not literally a unit test.  It is used for exploration or dry runs. One reason is that we are using the actual Kinds enum, not a mock.
    @Test
    void exploreKindsStrings() {
        EnumService enumService = new EnumService();
        String result = enumService.fetchKindsStrings();
        System.out.println("Here is the result:  " + result);
    }
}