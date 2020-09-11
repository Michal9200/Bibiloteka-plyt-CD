package io.mbab.sda.groupproject.menu.action;

import io.mbab.sda.groupproject.menu.CustomScanner;
import io.mbab.sda.groupproject.menu.MenuActionContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MainAction implements MenuAction {

  private final CustomScanner scanner;
  private final MenuActionContext ctx;

  @Override
  public void execute() {
    System.out.println("0) Zamknij aplikację");
    System.out.println("1) Dodaj album");
    System.out.println("2) Wyswietl albumy");
    System.out.println("3) Dodaj piosenkę do albumu");
    System.out.println("4) Wyświetl utwory danego albumu");
    System.out.println("5) Wyszukaj album");
    System.out.println("6) Wyświetl utwory danego albumu");

    var input = scanner.nextLine();

    if (input.equals("0")) {
      System.exit(0);
      return;
    }

    if (input.equals("1")) {
      ctx.use(CreateAlbumAction.class).execute();
      return;
    }

    if (input.equals("2")) {
      ctx.use(ViewAlbumAction.class).execute();
      return;
    }

    if (input.equals("3")) {
      ctx.use(AddSongToAlbum.class).execute();
      return;
    }

    if (input.equals("4")) {
      ctx.use(DisplaySongsForAlbum.class).execute();
      return;
    }

    if (input.equals("5")) {
      ctx.use(FindAlbumsByTitleAndArtist.class).execute();
      return;
    }

    if (input.equals("6")) {
      ctx.use(FindSongsByTitleAndArtist.class).execute();
      return;
    }

    System.out.println("Wprowadzono nieprawidłowa wartość!");
    execute();
  }
}
