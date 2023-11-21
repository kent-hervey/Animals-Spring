package com.example.restservice.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.example.restservice.enums.Kind;

@Slf4j
@Service
public class EnumService {

    public List<Kind> fetchKinds() {
        List<Kind> kinds = new ArrayList<>(Arrays.asList(Kind.values()));
        log.info(">>>>>>\n>>>>>kinds: " + kinds + "\n>>>>>>");
        return kinds;
    }

    public String fetchKindsStrings() {
        List<String> kindsString = new ArrayList<>();
        for(Kind enumKind : Kind.values()){
            kindsString.add(enumKind.toString());
        }
        log.info(">>>>>>\n>>>>>kindsString: " + kindsString + "\n>>>>>>");
        return String.join(", ", kindsString);
    }


}
