package util_information.path_check;

import file_parsing.sorting_files.SortingFiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class PathCheck {

    public static void pathFileCheck  () {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the path to the directory");
        String pathFile = scanner.nextLine();

        try {
            Files.walkFileTree(Paths.get(pathFile), new SortingFiles());
        } catch (IOException e) {
            System.out.println("not file");
        }

    }

}
