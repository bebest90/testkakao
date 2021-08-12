package com.sparta.springcore.util;




import com.sparta.springcore.dto.ItemDto;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Component
public class NaverShopSearch {
    public String search(String query) {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id", "SwN79E3EreYX9y0Yuatg");
        headers.add("X-Naver-Client-Secret", "j7jQFeyKFZ");
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange("https://openapi.naver.com/v1/search/book.json?query=" + query, HttpMethod.GET, requestEntity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();
        String response = responseEntity.getBody();
        System.out.println("Response status: " + status);
        System.out.println(response);
        return (response);

    }

    public List<ItemDto> fromJSONtoItems(String result) {
        JSONObject rjson = new JSONObject(result);
        JSONArray items = rjson.getJSONArray("items");


        List<ItemDto> itemDtoList = new ArrayList<>();

        for (int i = 0; i < items.length(); i++) {
            JSONObject itemJson = items.getJSONObject(i);
            ItemDto itemDto = new ItemDto(itemJson);

            itemDtoList.add(itemDto);


        }
        return itemDtoList;
    }
}


//    public static List<ItemDto> main(String[] args) {
//        NaverBookSearch naverShopSearch = new NaverBookSearch();
//        String result = naverShopSearch.search("아마존");
//        JSONObject rjson = new JSONObject(result);
//        JSONArray items = rjson.getJSONArray("items");
//
//        List<ItemDto> itemDtoList = new ArrayList<>();
//
//        for (int i=0; i<items.length(); i++) {
//         JSONObject itemJson = items.getJSONObject(i);
//
//         ItemDto itemDto = new ItemDto(itemJson);
//         itemDtoList.add(itemDto);
//
//
//        }
//        return itemDtoList;
//    }
//}


//            System.out.println(itemJson);
//            String title = itemJson.getString("title");
//            String image = itemJson.getString("image");
//            String author = itemJson.getString("author");
//            String link = itemJson.getString("link");
//            int discount = itemJson.getInt("discount");
//            int price = itemJson.getInt("price");
//            String publisher = itemJson.getString("publisher");
//            String description = itemJson.getString("description");
//            int pubdate = itemJson.getInt("pubdate");
//            System.out.println(price);