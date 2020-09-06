package io.mbab.sda.groupproject.menu.action;

import io.mbab.sda.groupproject.entity.Albums;
import io.mbab.sda.groupproject.entity.Songs;
import io.mbab.sda.groupproject.menu.CustomScanner;
import io.mbab.sda.groupproject.menu.MenuActionContext;
import io.mbab.sda.groupproject.repository.AlbumsRepository;
import io.mbab.sda.groupproject.repository.SongsRepository;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
public class AddSongToAlbum implements MenuAction {
  private final CustomScanner scanner;
  private final MenuActionContext ctx;
  private final SongsRepository songsRepository;
  private final AlbumsRepository albumsRepository;

  @Override
  public void execute() {

    System.out.println("0) Przejdź do poprzedniego menu");

    System.out.println("Podaj tytuł piosenki");

    var input = scanner.nextLine();

    if (pressedZero(input)) return;

    var builder = Songs.builder().title(input);

    System.out.println("Podaj autora piosenki");

    input = scanner.nextLine();

    builder.author(input).build();

    System.out.println("Podaj nr albumu");

    input = scanner.nextLine();

    var album = albumsRepository.findById(Integer.valueOf(input));

    var song = builder.album(album).build();

    if (pressedZero(input)) return;

    songsRepository.create(song);
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
