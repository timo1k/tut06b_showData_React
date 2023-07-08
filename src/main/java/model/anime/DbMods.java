package model.anime;

import dbUtils.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DbMods {
  public static StringData findById(DbConn dbc, String id) {

    // The find API needs to represent three cases: found web_user, not found, db error. 
    model.anime.StringData sd = new model.anime.StringData();
    try {
        String sql = "SELECT anime_id, anime_name, anime_japanese_name, anime_img, watch_date, anime_rating, anime_desc, anime.web_user_id, web_user.user_email "
                + "FROM anime, web_user "
                + "Where anime.web_user_id = web_user.web_user_id "
                + "AND anime_id = ?";

        PreparedStatement stmt = dbc.getConn().prepareStatement(sql);

        // Encode the id (that the user typed in) into the select statement, into the first (and only) ? 
        stmt.setString(1, id);

        ResultSet results = stmt.executeQuery();
        if (results.next()) { // id is unique, one or zero records expected in result set

            // plainInteger returns integer converted to string with no commas.
            sd.animeName = Format.fmtString(results.getObject("anime_name"));
            sd.animeId = Format.fmtInteger(results.getObject("anime_id"));
            sd.animeJapaneseName = Format.fmtString(results.getObject("anime_japanese_name"));
            sd.animeImg = Format.fmtString(results.getObject("anime_img"));
            sd.watchDate = Format.fmtDate(results.getObject("watch_date"));
            sd.animeRating = Format.fmtInteger(results.getObject("anime_rating"));
            sd.animeDesc = Format.fmtString(results.getObject("anime_desc"));
            sd.webUserId = Format.fmtInteger(results.getObject("web_user_id"));
            sd.userEmail = Format.fmtString(results.getObject("user_email"));
        } else {
            sd.errorMsg = "Anime Id Not Found.";
        }
        results.close();
        stmt.close();
    } catch (Exception e) {
        sd.errorMsg = "Exception thrown in DbMods.findById(): " + e.getMessage();
    }
    return sd;
  } // findById
        

} // class
