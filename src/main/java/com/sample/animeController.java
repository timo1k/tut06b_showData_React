package com.sample;


import model.anime.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dbUtils.*;
import view.animeView;

@RestController
public class animeController {

  @RequestMapping(value = "/anime/getAll", produces = "application/json")
  public String allUsers() {
    StringDataList list = new StringDataList(); // dbError empty, list empty
    DbConn dbc = new DbConn();
    list = animeView.getAllAnimes(dbc);

    dbc.close(); // EVERY code path that opens a db connection must close it
                 // (or else you have a database connection leak).

    return Json.toJson(list); // convert sdl obj to JSON Format and return that.
  }

  @RequestMapping(value = "/anime/findById", params = {"animeId"}, produces = "application/json")
  public String getById(@RequestParam("animeId") String animeId) {
      StringData sd = new StringData();
      if (animeId == null) {
          sd.errorMsg = "Error: URL must be anime/findById?animeId=xx " +
                  "where xx is the animeId of the desired anime record.";
      } else {
          DbConn dbc = new DbConn();
          sd = DbMods.findById(dbc, animeId);
          dbc.close(); // EVERY code path that opens a db connection must close it
          // (or else you have a database connection leak).
      }
      return Json.toJson(sd);
  }

  
}
