package com.westeros.webapi.contract;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieSummaryDto {

   private long id;
   private String title;
   private String homepage;
   private String language;
}
