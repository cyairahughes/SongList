package cmsc256;

import bridges.data_src_dependent.Song;

import java.util.Comparator;
/****************************************************************************
 * Cyaira Hughes
 ****************************************************************************
 * 256-901
 * Programming Project 3
 * The SongComparator class implements the Comparator interface for objects of
 * type Song and compares the values of album title and song title to be
 * used in the SongList class to sort them alphabetically
 * September 23, 2020
 *
 ****************************************************************************/
public class SongComparator implements Comparator<Song> {
    public int compare(Song song1, Song song2) {
        //Compares the artist's name (this should always equal 0)
        int value1 = song1.getArtist().compareTo(song2.getArtist());
        //If the value or comparing the artist's name to itself is 0, then it compares album titles
        if (value1 == 0) {
            int value2 = song1.getAlbumTitle().compareTo(song2.getAlbumTitle());
            //When the album titles are equivalent, it compares the value of the song titles
            if (value2 == 0) {
                return song1.getSongTitle().compareTo(song2.getSongTitle());
            //When the album titles are not equivalent it returns the value of the comparison
            } else {
                return value2;
            }
        }
        //Returns the value of the artists name comparison if non of the above options are true
        return value1;
    }
}
