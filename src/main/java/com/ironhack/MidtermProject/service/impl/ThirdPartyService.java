package com.ironhack.MidtermProject.service.impl;

import com.ironhack.MidtermProject.controller.DTO.ThirdPartyDTO;
import com.ironhack.MidtermProject.model.users.ThirdParty;
import com.ironhack.MidtermProject.repository.users.ThirdPartyRepository;
import com.ironhack.MidtermProject.service.interfaces.IThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ThirdPartyService implements IThirdPartyService {

    @Autowired
    private ThirdPartyRepository thirdPartyRepository;

    public ThirdParty getById(long id) {
        if(!thirdPartyRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Third Party user not found");
        }
        return thirdPartyRepository.findById(id).get();
    }

    public ThirdParty createThirdParty (ThirdPartyDTO thirdPartyDTO) {
        ThirdParty thirdParty = new ThirdParty();
        thirdParty.setName(thirdPartyDTO.getName());
        thirdParty.setHashedKey(thirdPartyDTO.getHashedKey());
        return thirdPartyRepository.save(thirdParty);
    }
}
