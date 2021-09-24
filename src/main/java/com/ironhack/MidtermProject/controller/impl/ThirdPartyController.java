package com.ironhack.MidtermProject.controller.impl;

import com.ironhack.MidtermProject.controller.DTO.OperationThirdPartyDTO;
import com.ironhack.MidtermProject.controller.interfaces.IThirdPartyController;
import com.ironhack.MidtermProject.model.users.ThirdParty;
import com.ironhack.MidtermProject.repository.users.ThirdPartyRepository;
import com.ironhack.MidtermProject.service.impl.AccountService;
import com.ironhack.MidtermProject.service.impl.ThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ThirdPartyController implements IThirdPartyController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ThirdPartyService thirdPartyService;

    @Autowired
    private ThirdPartyRepository thirdPartyRepository;

    @GetMapping("/check/thirdParties")
    @ResponseStatus(HttpStatus.OK)
    public List<ThirdParty> getAll() {
        return thirdPartyRepository.findAll();
    }

    @GetMapping("/check/thirdParty/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ThirdParty getById(@PathVariable long id) {
        return thirdPartyService.getById(id);
    }

    @PatchMapping("/thirdPartyOperation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void thirdPartyOperation(@RequestParam Integer hashedKey, @RequestBody OperationThirdPartyDTO operationThirdPartyDTO) {
        accountService.operationThirdParty(hashedKey, operationThirdPartyDTO);
    }

}