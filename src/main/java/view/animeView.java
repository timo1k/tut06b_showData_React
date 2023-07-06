package view;

// classes imported from java.sql.*
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.anime.*;
import dbUtils.*;


public class animeView {

    public static StringDataList getAllAnimes(DbConn dbc) {

        // sdl will be an empty array and DbError with "" 
        StringDataList sdl = new StringDataList(); 
        
        // sd will have all of it's fields initialized to ""
        StringData sd = new StringData();
        
        try {
            String sql = "SELECT anime_id, anime_name, anime_japanese_name, anime_img, watch_date, anime_rating, anime_desc, anime.web_user_id, web_user.user_email "
                    + "FROM anime, web_user "
                    + "Where anime.web_user_id = web_user.web_user_id "
                    + "ORDER BY anime_id ";  
            
            PreparedStatement stmt = dbc.getConn().prepareStatement(sql);
            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                
                sd = new StringData();
                
                // the formatUtils methods do not throw exceptions, but if they find illegal data, they write 
                // a message right in the field that they are trying to format.

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
                sdl.add(sd);
            }
            results.close();
            stmt.close();
        } catch (Exception e) {
            sd.errorMsg = "Exception thrown in animeView.getAllAnimes(): " + e.getMessage();
            sdl.add(sd);
        }
        return sdl;
    }
}