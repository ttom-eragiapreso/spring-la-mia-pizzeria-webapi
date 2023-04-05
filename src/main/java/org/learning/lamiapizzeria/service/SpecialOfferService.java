package org.learning.lamiapizzeria.service;

import org.learning.lamiapizzeria.model.SpecialOffer;
import org.learning.lamiapizzeria.repository.SpecialOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecialOfferService {
    @Autowired
    private SpecialOfferRepository specialOfferRepository;

    public SpecialOffer store(SpecialOffer formSpecialOffer) {
        return specialOfferRepository.save(formSpecialOffer);
    }

    public SpecialOffer getById(Integer id) {
        return specialOfferRepository.findById(id).orElseThrow();
    }
}
