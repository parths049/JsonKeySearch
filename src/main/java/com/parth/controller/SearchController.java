package com.parth.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.parth.model.TransLation;
import com.parth.model.SearchCriteria;
import com.parth.services.SearchService;

@RestController
public class SearchController {
	
	@Autowired
    private SearchService labelSearchService;

    @PostMapping("/api/search")
    public ResponseEntity<?> getSearchResultViaAjax(@Valid @RequestBody SearchCriteria search, Errors errors){
        TransLation translation = new TransLation();
        if(errors.hasErrors()){
            return ResponseEntity.badRequest().body(translation);
        }
        String label = labelSearchService.findTranslation(search.getLabel());
        translation.setTranslation(label);
        if(label == null) {
            return null;
        }
        return ResponseEntity.ok(translation);
    }

}
