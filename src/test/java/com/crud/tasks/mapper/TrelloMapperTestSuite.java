package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTestSuite {
    @InjectMocks
    private TrelloMapper trelloMapper;

    @Test
    public void mapToListTest() {
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "List1", false);
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(trelloListDto1);
        //When
        String result = trelloMapper.mapToList(trelloListsDto).get(0).getName();

        //Then
        assertEquals("List1", result);
    }
    @Test
    public void mapToListDtoTest() {
        //Given
        TrelloList trelloList1 = new TrelloList("1", "List1", false);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList1);
        //When
        String result = trelloMapper.mapToListDto(trelloLists).get(0).getName();

        //Then
        assertEquals("List1", result);
    }
    @Test
    public void mapToCardTest() {
        //Given
        TrelloCardDto cardDto1 = new TrelloCardDto("CardDto1", "Desc1", "up", "1");
        //When
        trelloMapper.mapToCard(cardDto1);
        String resultName = trelloMapper.mapToCard(cardDto1).getName();
        String resultDesc = trelloMapper.mapToCard(cardDto1).getDescription();
        String resultPos = trelloMapper.mapToCard(cardDto1).getPos();
        String resultListId = trelloMapper.mapToCard(cardDto1).getListId();
        //Then
        assertEquals("CardDto1", resultName);
        assertEquals("Desc1", resultDesc);
        assertEquals("up", resultPos);
        assertEquals("1", resultListId);
    }
    @Test
    public void mapToCardDtoTest() {
        //Given
        TrelloCard card1 = new TrelloCard("Card1", "Desc1", "up", "1");
        //When
        trelloMapper.mapToCardDto(card1);
        String resultName = trelloMapper.mapToCardDto(card1).getName();
        String resultDesc = trelloMapper.mapToCardDto(card1).getDescription();
        String resultPos = trelloMapper.mapToCardDto(card1).getPos();
        String resultListId = trelloMapper.mapToCardDto(card1).getListId();
        //Then
        assertEquals("Card1", resultName);
        assertEquals("Desc1", resultDesc);
        assertEquals("up", resultPos);
        assertEquals("1", resultListId);
    }
    @Test
    public void mapToBoardsTest() {
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("ListDto1", "1", false);
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(trelloListDto1);
        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("TrelloBoardDtoName1", "1", trelloListsDto);
        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();
        trelloBoardsDto.add(trelloBoardDto1);
        //When
        String result = trelloMapper.mapToBoards(trelloBoardsDto).get(0).getName();

        //Then
        assertEquals("TrelloBoardDtoName1", result);
    }
    @Test
    public void mapToBoardsDtoTest() {
        //Given
        TrelloList trelloList1 = new TrelloList("List1", "1", false);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList1);
        TrelloBoard trelloBoard1 = new TrelloBoard("TrelloBoardName1", "1", trelloLists);
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard1);
        //When
        String result = trelloMapper.mapToBoardsDto(trelloBoards).get(0).getName();

        //Then
        assertEquals("TrelloBoardName1", result);
    }
}