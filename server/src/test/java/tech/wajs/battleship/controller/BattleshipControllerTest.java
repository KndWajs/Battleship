/*
 * Copyright (c) 2022 Konrad Wajs, All rights reserved.
 */

package tech.wajs.battleship.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import tech.wajs.battleship.dto.Grid;
import tech.wajs.battleship.dto.ResponseDTO;
import tech.wajs.battleship.enums.ShotType;
import tech.wajs.battleship.exceptions.ErrorMessages;
import tech.wajs.battleship.repository.GridRepository;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class BattleshipControllerTest {
    private final ObjectMapper MAPPER = new JsonMapper();

    @Autowired
    private GridRepository repository;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MAPPER.findAndRegisterModules(); // because: `java.time.ZonedDateTime` not supported by default
    }

    @AfterEach
    void tearDown() throws NoSuchFieldException, IllegalAccessException {
        Field grid = repository.getClass().getDeclaredField("grid");
        grid.setAccessible(true);
        grid.set(repository, null);
    }

    @Test
    void shouldCreateNewGridWhenNoGameIsInRepository() throws Exception {
        //given
        //when
        MockHttpServletResponse response = useStartEndpoint();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(repository.getGrid()).isNotNull();
    }

    @Test
    void shouldCreateNewGridWhenGridIsAlreadyInRepository() throws Exception {
        //given
        //when
        useStartEndpoint();
        Grid grid = repository.getGrid();
        MockHttpServletResponse response = useStartEndpoint();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(repository.getGrid() == grid).isFalse();
    }

    @Test
    void shouldReturnCorrectlyParsedCoordinates() throws Exception {
        //given
        String string = "A10";
        Integer expectedX = 0;
        Integer expectedY = 9;

        //when
        useStartEndpoint();
        MockHttpServletResponse response = useShotEndpoint(string);
        ResponseDTO responseObject = MAPPER.readValue(response.getContentAsString(), ResponseDTO.class);

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(responseObject.getRow()).isEqualTo(expectedX);
        assertThat(responseObject.getColumn()).isEqualTo(expectedY);
    }

    @Test
    void shouldReturnCorrectShotType() throws Exception {
        //given
        String string = "A10";
        useStartEndpoint();
        repository.getGrid().getLocation(0, 9).setShip(null);

        //when
        MockHttpServletResponse response = useShotEndpoint(string);
        ResponseDTO responseObject = MAPPER.readValue(response.getContentAsString(), ResponseDTO.class);

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(responseObject.getShot()).isEqualTo(ShotType.MISS);
    }

    @Test
    void shouldReturn406WhenTryToShotButGameHasNotStarted() throws Exception {
        //given
        String string = "A10";

        //when
        MockHttpServletResponse response = useShotEndpoint(string);

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_ACCEPTABLE.value());
        assertThat(response.getContentAsString()).contains(ErrorMessages.NOT_STARTED.description);
    }

    private MockHttpServletResponse useStartEndpoint() throws Exception {

        return mockMvc.perform(MockMvcRequestBuilders
                              .post("/api/start"))
                      .andReturn().getResponse();
    }

    private MockHttpServletResponse useShotEndpoint(String string) throws Exception {

        return mockMvc.perform(MockMvcRequestBuilders
                              .put("/api/shot?coordinates=" + string))
                      .andReturn().getResponse();
    }

}
