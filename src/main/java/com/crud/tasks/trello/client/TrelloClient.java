package com.crud.tasks.trello.client;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.config.TrelloConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class TrelloClient {
// dr
//    @Value("${trello.api.endpoint.prod}")
//    private String trelloApiEndpoint;
//
//    @Value("${trello.app.key}")
//    private String trelloAppKey;
//
//    @Value("${trello.app.token}")
//    private String trelloToken;
//
//    @Value("${trello.app.userName}")
//    private String userName;

    @Autowired
    private TrelloConfig trelloConfig;

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloClient.class);

    private URI getUrl() {
        return UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/members/"+trelloConfig.getUserName()+"/boards")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .queryParam("fields", "name,id")
                .queryParam("lists", "all").build().encode().toUri();
//        dr czesc
        //        return UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/"+userName+"/boards")
//                .queryParam("key", trelloAppKey)
//                .queryParam("token", trelloToken)
//                .queryParam("fields", "name,id")
//                .queryParam("lists", "all").build().encode().toUri();
    }

    public List<TrelloBoardDto> getTrelloBoards() {

        try {
            System.out.println(getUrl());
            TrelloBoardDto[] boardsResponse = restTemplate.getForObject(getUrl(), TrelloBoardDto[].class);
            return Arrays.asList(Optional.ofNullable(boardsResponse).orElse(new TrelloBoardDto[0]));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
//        if (boardsResponse != null) {
//            return Arrays.asList(boardsResponse);
//        }
//        return new ArrayList<>();
    }

    public CreatedTrelloCard createNewCard(TrelloCardDto trelloCardDto) {
        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/cards")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .queryParam("name", trelloCardDto.getName())
                .queryParam("desc", trelloCardDto.getDescription())
                .queryParam("pos", trelloCardDto.getPos())
                .queryParam("idList", trelloCardDto.getListId()).build().encode().toUri();

//        dr czesc
//        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/cards")
//                .queryParam("key", trelloAppKey)
//                .queryParam("token", trelloToken)
//                .queryParam("name", trelloCardDto.getName())
//                .queryParam("desc", trelloCardDto.getDescription())
//                .queryParam("pos", trelloCardDto.getPos())
//                .queryParam("idList", trelloCardDto.getListId()).build().encode().toUri();

        return restTemplate.postForObject(url, null, CreatedTrelloCard.class);
    }
}
