import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

public class Shuffler {

	static String first, second;

	public Shuffler() {



	}//end constructor

	public File getSong() {

		String ar;
		AudioFile f;

		File folder = new File("C:\\Users\\ama82\\Repositories\\Shuffle\\Shuffle\\Music"), randSong;
		File[] listOfMusic = folder.listFiles();

		try {

			while(true) {

				randSong = listOfMusic[new Random().nextInt(listOfMusic.length - 1)];
				f = AudioFileIO.read(randSong);
				Tag tag = f.getTag();
				ar = tag.getFirst(FieldKey.ARTIST);
				if(!ar.equals(first) && !ar.equals(second)) {

					second = first;
					first = ar;
					
					System.out.println("First - " + first);
					System.out.println("Second - " + second);
					System.out.println();
					
					return randSong;
					
				}

			}

		} catch (CannotReadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TagException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ReadOnlyFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAudioFrameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;

	}//end getSong
	
	public String getSongName(File s) {
		
		AudioFile f;
		try {
			
			f = AudioFileIO.read(s);
			Tag tag = f.getTag();
			return tag.getFirst(FieldKey.TITLE);
			
		} catch (CannotReadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TagException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ReadOnlyFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAudioFrameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
		
	}//end getSongName
	
public String getAlbumName(File s) {
		
		AudioFile f;
		try {
			
			f = AudioFileIO.read(s);
			Tag tag = f.getTag();
			return tag.getFirst(FieldKey.ALBUM);
			
		} catch (CannotReadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TagException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ReadOnlyFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAudioFrameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
		
	}//end getArtistName

}//end class shuffler
