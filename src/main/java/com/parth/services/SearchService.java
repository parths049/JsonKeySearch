package com.parth.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.parth.model.Label;

@Service
public class SearchService {

    private Label label;

    public String findTranslation(String key){
        if(key == null || key.length() == 0){
            return null;
        }
        return label.getKeyToLabelMap().get(key);
    }

    public String findReverseTranslation(String key){
        if(key == null || key.length() == 0){
            return null;
        }
        if(label.getLabelToKeyMap().get(key) != null){
            StringBuilder result = new StringBuilder();
            List<String> lst = label.getLabelToKeyMap().get(key);
            for(String str : lst){
                result.append(str);
                result.append(",");
            }
            result.deleteCharAt(result.length() - 1);
            return result.toString();
        }
        return null;
    }

    @PostConstruct
    private void initData(){

        ObjectMapper jsonMapper = new ObjectMapper();
        Map<String, Object> translationMap = null;
        try {
            translationMap = jsonMapper.readValue(new File("translation.json"), HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        label = new Label();
        List<String> keyList = new ArrayList<>();
        organizeInitData(translationMap, keyList);

    }

    private void organizeInitData(Map<String, Object> tMap, List<String> keyList) {
        Iterator it = tMap.keySet().iterator();

        while (it.hasNext()) {
            String k = (String) it.next();
            Object obj = tMap.get(k);

            if (obj instanceof String) {
                if (keyList == null || keyList.size() == 0) {
                    putDataInMap(k, (String) obj);
                } else {
                    StringBuilder key = new StringBuilder();
                    for (int i = 0; i < keyList.size(); i++) {
                        key.append(keyList.get(i));
                        key.append(".");
                    }
                    key.append(k);
                    keyList.add(key.toString());
                    putDataInMap(key.toString(), (String) obj);
                }
            } else if (obj instanceof Map) {
                keyList.add(k);
                organizeInitData((Map) obj, keyList);
            }

            if (!keyList.isEmpty()) {
                keyList.remove(keyList.size() - 1);
            }
        }
    }

    private void putDataInMap(String key, String value){
        if(key.length() == 0 || value.length() == 0){
            return;
        }

        List<String> lst;

        label.getKeyToLabelMap().put(key, value);
        if (label.getLabelToKeyMap().get(value) != null) {
            lst = label.getLabelToKeyMap().get(value);
        } else {
            lst = new ArrayList<>();
        }
        lst.add(key);
        label.getLabelToKeyMap().put(value, lst);
    }

}
