package com.bookmakerApp.service.impl;

import com.bookmakerApp.model.AccountModel;
import com.bookmakerApp.model.BetTicketModel;
import com.bookmakerApp.model.EventModel;
import com.bookmakerApp.model.UserModel;
import com.bookmakerApp.repository.AccountRepository;
import com.bookmakerApp.repository.BetTicketRepository;
import com.bookmakerApp.repository.EventRepository;
import com.bookmakerApp.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class DefaultBetTicketServiceImplTest {

    @InjectMocks
    private DefaultBetTicketServiceImpl defaultBetTicketService;
    @Mock
    private BetTicketRepository betTicketRepository;
    @Mock
    private EventRepository eventRepository;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private UserRepository userRepository;

    private BetTicketModel betTicket;
    private EventModel event1;
    private EventModel event2;
    private List<EventModel> events;
    private AccountModel account;

    @BeforeEach
    public void setUp() {
        betTicket = new BetTicketModel();
        event1 = new EventModel();
        event2 = new EventModel();
        events = new LinkedList<>();
        final UserModel user = new UserModel();
        account = new AccountModel();
        account.setIdAccount(1000L);
        user.setIdUser(100L);
        betTicket.setUser(user);
        account.setUser(user);
        account.setBankBalance(BigDecimal.valueOf(10000));
        Mockito.when(userRepository.findUserModelByIdUser(user.getIdUser())).thenReturn(user);
        Mockito.when(accountRepository.getAccountModelByUser_IdUser(user.getIdUser())).thenReturn(account);
        Mockito.when(eventRepository.getEventModelsByIdEvent(event1.getIdEvent())).thenReturn(event1);
        Mockito.when(eventRepository.getEventModelsByIdEvent(event2.getIdEvent())).thenReturn(event2);
    }

    @Test
    public void testCalculateWhenBetTicketHaveTwoEvents() {
        //given
        final Double event1Odds = 2.0;
        final Double event2Odds = 2.0;
        final BigDecimal deposit = BigDecimal.valueOf(1000);
        final Double expectedOdds = event1Odds * event2Odds;
        final BigDecimal expectedToWin =
                deposit.multiply(BigDecimal.valueOf(expectedOdds));
        event1.setOdds(event1Odds);
        event2.setOdds(event2Odds);
        events.add(event1);
        events.add(event2);
        betTicket.setEvents(events);
        betTicket.setDeposit(deposit);
        //when
        defaultBetTicketService.addBetTicket(betTicket);
        //then
        Assert.assertEquals(expectedOdds, betTicket.getTotalOdds());
        Assert.assertEquals(expectedToWin, betTicket.getToWin());
    }

    @Test
    public void testCalculateWhenUserAccountBalanceIsLessThanTheDeposit() {
        //given
        final Double event1Odds = 2.0;
        final Double event2Odds = 2.0;
        final BigDecimal deposit = BigDecimal.valueOf(1000);
        account.setBankBalance(BigDecimal.ZERO);
        event1.setOdds(event1Odds);
        event2.setOdds(event2Odds);
        events.add(event1);
        events.add(event2);
        betTicket.setEvents(events);
        betTicket.setDeposit(deposit);
        //when
        Exception exception = Assert.assertThrows(IllegalArgumentException.class, () ->{
            defaultBetTicketService.addBetTicket(betTicket);});
        //then
        Assertions.assertNotNull(exception);
    }
}
