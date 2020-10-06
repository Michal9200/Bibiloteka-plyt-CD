package io.mbab.sda.groupproject.menu.action;

import io.mbab.sda.groupproject.entity.Album;
import io.mbab.sda.groupproject.entity.Song;
import io.mbab.sda.groupproject.menu.MenuActionContext;
import io.mbab.sda.groupproject.repository.AlbumsRepository;
import io.mbab.sda.groupproject.repository.SongsRepository;
import lombok.RequiredArgsConstructor;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class WriteLibraryCDToJason implements MenuAction  {

    private final MenuActionContext ctx;
    private final AlbumsRepository albumRepository;
    private final SongsRepository songsRepository;

    @Override
    public void execute() {

        System.out.println("0) Przejdź do poprzedniego menu");

        ObjectMapper mapper = new ObjectMapper();

        List<Album> listAlbums = albumRepository.getAll();
        List<Song> listSongs = songsRepository.getAll();

        try {
            mapper.writeValue(new File("zapisaneAlbumu.json"), listAlbums);
            mapper.writeValue(new File("zapisanePiosenki.json"), listSongs);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ctx.use(MainAction.class).execute();


}

    private boolean pressedZero(String input) {
        if (input.equals("0")) {
            ctx.use(MainAction.class).execute();
            return true;
        }
        return false;
    }
}

