package io.mbab.sda.groupproject.menu.action;

import io.mbab.sda.groupproject.entity.Album;
import io.mbab.sda.groupproject.entity.Song;
import io.mbab.sda.groupproject.entity.Song.SongBuilder;
import io.mbab.sda.groupproject.menu.CustomScanner;
import io.mbab.sda.groupproject.menu.MenuActionContext;
import io.mbab.sda.groupproject.repository.AlbumsRepository;
import io.mbab.sda.groupproject.repository.SongsRepository;
import lombok.RequiredArgsConstructor;

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
    var builder = Song.builder().title(input);

    System.out.println("Podaj nazwisko autora:");
    input = scanner.nextLine();
    if (pressedZero(input)) return;
    builder.author(input);

    addAlbum(builder);
    Song song = builder.build();
    songsRepository.create(song);

    ctx.use(MainAction.class).execute();
  }

  private void addAlbum(SongBuilder builder) {
    System.out.println("Podaj id albumu do którego dodajemy utwór: ");
    String string = (scanner.nextLine());
    int albumId = Integer.parseInt(string);
    if (pressedZero(string)) return;
    albumsRepository
        .findById(albumId)
        .ifPresentOrElse(
            album -> builder.album(album),
            () -> {
              System.out.println("Podany id albumu nie istnieje");
              addAlbum(builder);
            });
  }

  private boolean pressedZero(String input) {
    if (input.equals("0")) {
      ctx.use(MainAction.class).execute();
      return true;
    }
    return false;
  }
    }
