package model.anime;

public class StringData {

    public String animeId = "";
    public String animeName = "";
    public String animeJapaneseName = "";
    public String animeImg = "";
    public String watchDate = "";
    public String animeRating = "";
    public String animeDesc = "";
    public String webUserId = ""; 
    public String userEmail = "";
    
   
    public String errorMsg = "";

    // default constructor leaves all data members with empty string (Nothing null).
    public StringData() {
    }
    
    // public int getCharacterCount() {
    //     String s = this.animeId + this.animeName + this.animeJapaneseName + this.animeImg + this.watchDate
    //             + this.animeRating + this.animeDesc + this.webUserId + this.userEmail;
    //     return s.length();
    // }

}