package cmsc256;
import bridges.connect.Bridges;
import bridges.connect.DataSource;
import bridges.data_src_dependent.Song;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
/****************************************************************************
 * Cyaira Hughes
 ****************************************************************************
 * 256-901
 * Programming Project 3
 * The SongList class contains a main method that calls the method
 * getSongsByArtist with a command line input and returns each song
 * in alphabetical order
 * September 23, 2020
 *
 ****************************************************************************/
public class SongList {
    public static void main(String[] args) {
        Bridges bridges = new Bridges(5, "CyairaHughes", "910423764620");
        DataSource ds = bridges.getDataSource();
        List<Song> data = null;

        try {

            data = ds.getSongData();

        } catch (Exception e) {

            System.out.println("Unable to connect to Bridges.");

        }
        //allows input from command line
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Artist Name: ");
        //sets the variable artist to the command line input
        String artist = input.nextLine();

       //instantiates the SongList class
       SongList songList = new SongList();
       //calls the method getSongsByArtist and sets the value to the string str
       String str = songList.getSongsByArtist(artist);
       //prints out the value of str
       System.out.print(str);
    }
    //Instance variable
    private LList<Song> songData = new LList<>();

    //Returns songs by the artist mentioned
    public String getSongsByArtist(String artist) {
        //Connects to bridges
        Bridges bridges = new Bridges(5, "CyairaHughes", "910423764620");
        DataSource ds = bridges.getDataSource();
        //Creates list
        List<Song> data = null;

        //Tries to create list of song object and if fails, prints out "Unable to connect to Bridges"
        try {

            data = ds.getSongData();

        } catch (Exception e) {

            System.out.println("Unable to connect to Bridges.");

        }
        //Sorts the list according to specifications made in SongComparator class
        Collections.sort(data, new SongComparator());

        //Moves curr to start
        songData.moveToStart();
        //Inserts contents of list into LList
        for (int i = 0; i < data.size(); i++) {
            songData.insert(data.get(i));
            if (i != data.size() - 1)
                songData.next();
        }
        //Moves curr to start again
        songData.moveToStart();
        String str;
        String album = "";
        String song = "";
        //Prints out each song of the artist found in the playlist
        for (int i = 0; i < songData.length(); i++) {
            str = songData.getValue().getArtist();
            if (str.equalsIgnoreCase(artist)) {
                song = songData.getValue().getSongTitle();
                album = songData.getValue().getAlbumTitle();
                System.out.println("Title: " + song + " Artist: " + artist + " Album: " + album);
            }
            songData.next();
        }
        //If the album and song variables are not updated, returns "No songs by the given artist are on the playlist"
        if(album.equals("") && song.equals(""))
            return "No songs by the given artist are on the playlist";
        return "";
    }
}

