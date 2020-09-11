package io.mbab.sda.groupproject.menu.action;

import io.mbab.sda.groupproject.menu.CustomScanner;
import io.mbab.sda.groupproject.menu.MenuActionContext;
import io.mbab.sda.groupproject.repository.SongsRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindSongsByTitleAndArtist implements MenuAction {

    private final MenuActionContext ctx;
    private final CustomScanner scanner;
    private final SongsRepository songRepository;

    @Override
    public void execute() {

        System.out.println("0) Przejdź do poprzedniego menu");

        System.out.println("Podaj tytuł lub autora szukanej piosenki");

        var input = scanner.nextLine();

        if (pressedZero(input)) return;

        var albums = songRepository.findByAuthor(input);

        albums.addAll(songRepository.findByTitle(input));

        if (albums.isEmpty()) {
            System.out.println("Brak danych do wyświetlenia");
        } else {
            System.out.println("\n");
            albums.forEach(System.out::println);
            System.out.println("\n");
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
