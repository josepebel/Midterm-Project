package com.ironhack.MidtermProject.service.interfaces;

import com.ironhack.MidtermProject.controller.DTO.ThirdPartyDTO;
import com.ironhack.MidtermProject.model.users.ThirdParty;

public interface IThirdPartyService {

    ThirdParty getById(long id);
    ThirdParty createThirdParty (ThirdPartyDTO thirdPartyDTO);
}
