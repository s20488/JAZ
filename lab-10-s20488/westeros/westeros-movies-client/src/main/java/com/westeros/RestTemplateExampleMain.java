package com.westeros;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Tu jest tylko prosty przykład
 * jak wykorzystać obiekt typu RestTemplate
 * do ściągania prostych danych z internetu
 *
 * sciąga wygenerowane dane usera z endpointu: https://randomuser.me/api/
 */
public class RestTemplateExampleMain {

    public static void main(String[] args){
        /**
         * Tworzymy obiekt RestTemplate
         */
        RestTemplate restTemplate = new RestTemplate();

        /**
         * definiujemy adres
         */
        String randomUserUrl
                = "https://randomuser.me/api/";

        /**
         * wysyłamy rządanie HTTP GET
         */
        ResponseEntity<RandomUserResultsDto> response
                = restTemplate.getForEntity(randomUserUrl , RandomUserResultsDto.class);

        /**
         * z BODY odpowiedzi pobieramy gotowy obiekt z danymi z serwisu
         */
        var users = response.getBody();
    }

   static class RandomUserResultsDto {
       /**
        * adnotacja @JsonProperty służy mapowania nazw pól klasy na nazwy pól JSON'a
        */
       @JsonProperty("results") // results - nazwa pola w jsonie z serwisu
        List<UserDto> users;
    }
   static class UserDto{
        @JsonProperty("gender")
        private String gender;

        private String email;
        private String phone;

       public String getGender() {
           return gender;
       }

       public void setGender(String gender) {
           this.gender = gender;
       }

       public String getEmail() {
           return email;
       }

       public void setEmail(String email) {
           this.email = email;
       }

       public String getPhone() {
           return phone;
       }

       public void setPhone(String phone) {
           this.phone = phone;
       }
   }
}
