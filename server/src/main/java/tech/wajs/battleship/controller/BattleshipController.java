/*
 * Copyright (c) 2022 Konrad Wajs, All rights reserved.
 */

package tech.wajs.battleship.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.wajs.battleship.dto.ResponseDTO;
import tech.wajs.battleship.service.BattleshipService;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class BattleshipController {
    private BattleshipService battleshipService;
    @PostMapping(value = "/start")
    public void shot() {
        battleshipService.start();
    }
    @PutMapping(value = "/shot")
    public ResponseDTO shot(@RequestParam String coordinates) {
        return battleshipService.shot(coordinates);
    }
}
