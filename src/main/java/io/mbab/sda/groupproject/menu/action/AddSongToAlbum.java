package io.mbab.sda.groupproject.menu.action;

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

    System.out.println("Podaj autora piosenki");

    input = scanner.nextLine();

    builder.author(input).build();

    findAlbum(builder);

    Song song = builder.build();

//    System.out.println("Podaj nr albumu");
//
//    input = scanner.nextLine();
//
//    var optional = albumsRepository.findById(Integer.valueOf(input));
//
//    Albums album = optional.get();
//
//    var song = builder.album(album).build();

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

  private void findAlbum(SongBuilder builder) {
    System.out.println("Podaj nr albumu");
    var input = scanner.nextLine();

    if (pressedZero(input)) return;

    albumsRepository
        .findById(Integer.valueOf(input))
        .ifPresentOrElse(
            album -> builder.album(album),
            () -> {
              System.out.println("Nie znaleziono albumu o numerze " + input);
            });

  }


}
