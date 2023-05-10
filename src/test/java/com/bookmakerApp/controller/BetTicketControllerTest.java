package com.bookmakerApp.controller;

import com.bookmakerApp.facade.dtos.betticket.BetTicketModelDto;
import com.bookmakerApp.model.BetTicketModel;
import com.bookmakerApp.model.EventModel;
import com.bookmakerApp.model.UserModel;
import com.bookmakerApp.repository.BetTicketRepository;
import com.bookmakerApp.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class BetTicketControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private BetTicketRepository betTicketRepository;
    @Autowired
    private UserRepository userRepository;

    private BetTicketModel newBetTicket;
    private List<EventModel> events = new LinkedList<>();

    @Test
    @WithMockUser
    @Transactional
    void shouldReturnListOfBetTicketDtos() throws Exception {
        //given
        prepareTestData();
        //when
        MvcResult mvcResult =
                mockMvc.perform(get("/betTicketsUser/"
                        + newBetTicket.getUser().getIdUser()))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();
        //then
        String contentString = mvcResult.getResponse().getContentAsString();
        BetTicketModelDto[] betTicketDto =
                objectMapper.readValue(contentString
                        , BetTicketModelDto[].class);
        Assert.assertEquals(events, Arrays.stream(betTicketDto).findFirst().get().getEvents());
    }

    @Test
    @Transactional
    void shouldReturnUnauthorizedStatus() throws Exception {
        //given
        prepareTestData();
        //when
        MvcResult mvcResult = mockMvc.perform(get("/betTicketsUser/"
                        + newBetTicket.getUser().getIdUser()))
                .andDo(print())
                .andExpect(status().is(401))
                .andReturn();
        //then
        String contentString = mvcResult.getResponse().getContentAsString();
        Assert.assertTrue(ObjectUtils.isEmpty(contentString));
    }


    private void prepareTestData() {
        UserModel newUser = new UserModel();
        newUser.setAge(20);
        userRepository.save(newUser);
        newBetTicket = new BetTicketModel();
        newBetTicket.setUser(newUser);
        newBetTicket.setEvents(events);
        betTicketRepository.save(newBetTicket);
    }

}
