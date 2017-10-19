package com.parth.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.parth.model.TransLation;
import com.parth.model.ReverseSearchCriteria;
import com.parth.services.SearchService;

@RestController
public class ReverseSearchController {
	
	@Autowired
	private SearchService reverseLabelSearchService;

    @PostMapping("/api/searchReverse")
    public ResponseEntity<?> getReverseSearchResultViaAjax(@Valid @RequestBody ReverseSearchCriteria searchReverse, Errors errors){
        TransLation translation = new TransLation();
        if(errors.hasErrors()){
            return ResponseEntity.badRequest().body(translation);
        }
        String label = reverseLabelSearchService.findReverseTranslation(searchReverse.getLabel());
        translation.setTranslation(label);
        if(label == null) {
            return null;
        }
        return ResponseEntity.ok(translation);
    }

}
