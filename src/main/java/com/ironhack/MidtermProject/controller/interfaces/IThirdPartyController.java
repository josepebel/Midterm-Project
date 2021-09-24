package com.ironhack.MidtermProject.controller.interfaces;

import com.ironhack.MidtermProject.controller.DTO.OperationThirdPartyDTO;
import com.ironhack.MidtermProject.model.users.ThirdParty;

import java.util.List;

public interface IThirdPartyController {
    List<ThirdParty> getAll();
    ThirdParty getById(long id);
    void thirdPartyOperation (Integer hashedKey, OperationThirdPartyDTO operationThirdPartyDTO);
}
